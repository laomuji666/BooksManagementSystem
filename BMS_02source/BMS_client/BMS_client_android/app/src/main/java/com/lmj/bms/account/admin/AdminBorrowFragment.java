package com.lmj.bms.account.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lmj.bms.R;
import com.lmj.bms.account.user.Book;
import com.lmj.bms.account.user.BookAdapter;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.http.AccountHttp;
import com.lmj.bms.util.http.BookHttp;
import com.lmj.bms.zxing.android.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminBorrowFragment extends Fragment {
    private String func;
    private String book_id;
    private String title;
    private String user_number;
    private String borrow_id;
    private static final int REQUEST_CODE_CAPTURE=321;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_admin_borrow, container, false);
        initWidget(v);
        return v;
    }
    private void initWidget(View v){
        v.findViewById(R.id.fragment_admin_borrow_image_borrow).setOnClickListener((view)->{
            //动态权限申请
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                startCaptureActivity();
            }
        });
    }
    private void startCaptureActivity(){
        startActivityForResult(new Intent(getActivity(), CaptureActivity.class),REQUEST_CODE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == Activity.RESULT_OK) {
            if (data != null) selectFunc(data.getStringExtra("codedContent"));
        }
    }

    //通过扫描到的内容选择对应的功能
    private void selectFunc(String content){
        if(content==null || content.length() == 0) {
            Toast.makeText(getActivity(), "扫描的数据必须为二维码或条形码", Toast.LENGTH_SHORT).show();
            return;
        }

        //检查是否为数字,如果为数字 则表示 结果为条形码
        boolean isDigit = true;
        char[] array = content.toCharArray();
        for(char ch : array){
            if(Character.isDigit(ch) == false){
                isDigit =false;
                break;
            }
        }
        //增加书籍
        if(isDigit){
            Toast.makeText(getActivity(), "增加书籍", Toast.LENGTH_LONG).show();
            Intent it = new Intent(getActivity(),AddBookActivity.class);
            it.putExtra("bar_code",content);
            startActivity(it);
            return;
        }

        //不是数字 , 检查是否为需要的功能
        boolean isNeedFunc = false;
        try {
            JSONObject obj =new JSONObject(content);
            String f = obj.getString("func");
            if(f!=null && (f.equals("return")||f.equals("borrow"))){
                isNeedFunc = true;
            }
        } catch (JSONException e) {
            isNeedFunc = false;
        }

        //借书或者还书操作
        if(isNeedFunc){
            setBorrowOrReturnBook(content);
            return;
        }

        //否则 表示扫描到了 意外的功能,提示扫描的结果
        Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
    }
    private void setBorrowOrReturnBook(String content){
        try {
            JSONObject obj =new JSONObject(content);
            func = obj.getString("func");
            book_id = obj.getString("book_id");
            title = obj.getString("title");
            user_number=obj.getString("user_number");
            String dialogMessage= String.format(
                    "    用户号:%s\n    书籍编号:%s\n    书籍标题:%s\n",
                    user_number,book_id,title);
            if (func.equals("borrow")){
                createDialog("借书",dialogMessage);
            }else if(func.equals("return")){
                borrow_id=obj.getString("borrow_id");
                dialogMessage+="    借阅编号:"+borrow_id;
                createDialog("还书",dialogMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createDialog(String dialogTitle,String text){
        final TextView textView=new TextView(getActivity());
        textView.setTextSize(15);
        textView.setText(text);
        new AlertDialog.Builder(getActivity()).setTitle(dialogTitle).setView(textView).setPositiveButton("确定", (dialog, which) -> {
            new Thread(()->{
                String res=BookHttp.bookBorrowOrReturn(func,AccountData.number,AccountData.onlineKey,book_id,borrow_id,user_number);
                getActivity().runOnUiThread(()->{
                    Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
                });
            }).start();
        }).setNegativeButton("取消", null).create().show();
    }
}