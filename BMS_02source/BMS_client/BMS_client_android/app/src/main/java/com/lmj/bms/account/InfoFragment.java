package com.lmj.bms.account;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lmj.bms.LoginActivity;
import com.lmj.bms.R;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.PictureUtil;
import com.lmj.bms.util.http.AccountHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class InfoFragment extends Fragment {
    private static final int REQUEST_CODE=123;
    private static String []permissions=new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int CHOOSE_PHOTO=11;
    private ImageView profile_photo;
    private RecyclerView recyclerView;
    private String info_number;
    private String info_name;
    private String info_password;
    private String info_department;
    private String info_profile_photo;
    //用于存放RecyclerView的数据
    private HashMap<String,String>infoMap=new LinkedHashMap<>();
    //适配器
    private InfoAdapter infoAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_info, container, false);
        initWidget(v);
        initInfo();
        checkPermissions();
        return v;
    }
    private void initWidget(View v){
        profile_photo=v.findViewById(R.id.fragment_info_profile_photo);
        recyclerView=v.findViewById(R.id.fragment_info_recycler_view);
        profile_photo.setOnClickListener(view->{
            pickPhoto();
        });
        infoAdapter=new InfoAdapter(getActivity(),infoMap,this);
        recyclerView.setAdapter(infoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    public void initInfo(){
        new Thread(() -> {
            String info = AccountHttp.getInfo(AccountData.type, AccountData.number, AccountData.onlineKey);
            if (info==null)return;
            try {
                JSONObject obj =new JSONObject(info);
                if (AccountData.type.equals("user")) {
                    info_number=obj.getString("user_number");
                    info_department=obj.getString("department");
                }
                else {
                    info_number=obj.getString("admin_number");
                }
                info_name=obj.getString("name");
                info_password=obj.getString("password");
                info_profile_photo=obj.getString("profile_photo");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(() -> {
                //如果没有获取到信息
                if (info_number==null||info_number.equals(AccountData.number)==false){
                    //退出自动登陆
                    LoginActivity.logout();
                    Toast.makeText(getActivity(), "信息获取失败,请重新登陆", Toast.LENGTH_SHORT).show();
                    getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                    //退出程序
                    getActivity().finish();
                    return;
                }
                //将数据添加进去
                infoMap.clear();
                infoMap.put("类型",AccountData.type);
                infoMap.put("账号",info_number);
                infoMap.put("姓名",info_name);
                if(AccountData.type.equals("user")) infoMap.put("详细",info_department);
                infoMap.put("密码","修改");
                infoMap.put("登陆","退出登陆");
                infoAdapter.notifyDataSetChanged();
                get_profile_photo();
            });

        }).start();
    }

    private void get_profile_photo(){
        new Thread(() -> {
            byte[]bytes=AccountHttp.get_profile_photo(info_profile_photo);

            getActivity().runOnUiThread(() -> {
                Glide.with(getActivity()).load(bytes).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(profile_photo);
            });
        }).start();
    }

    private void pickPhoto(){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(it,CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!= Activity.RESULT_OK)return;
        switch (requestCode){
            case CHOOSE_PHOTO:
                Glide.with(getActivity()).load(data.getData()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(profile_photo);
                new Thread(()->{
                    try {
                        Thread.sleep(1000);
                        updatePicture();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
        }
    }
    private void updatePicture(){
            getActivity().runOnUiThread(()->{
                Bitmap bitmap = ((BitmapDrawable) profile_photo.getDrawable()).getBitmap();
                String photoStr= PictureUtil.bitmapToBase64(bitmap);
                new Thread(()->{
                    AccountHttp.updateInfo(AccountData.type,AccountData.number,AccountData.onlineKey,
                            null,null,photoStr);
                }).start();
            });
    }
    @AfterPermissionGranted(REQUEST_CODE)
    private void checkPermissions(){
        if (EasyPermissions.hasPermissions(getActivity(),permissions)==false){
            EasyPermissions.requestPermissions(this,"选择头像需要文件读写权限,请确认授权",REQUEST_CODE,permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(REQUEST_CODE, permissions, grantResults, this);
    }


}