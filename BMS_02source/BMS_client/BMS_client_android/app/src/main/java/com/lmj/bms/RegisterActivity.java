package com.lmj.bms;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.ActivityUtil;
import com.lmj.bms.util.http.AccountHttp;
import com.lmj.bms.util.http.BookHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private static final int TEXT_PASSWORD=TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD|TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD;
    private static final int TEXT_PASSWORD_VISIBLE=TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD|TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

    private String codeKey="";
    private byte[] codePicture;
    private EditText edit_name;
    private EditText edit_password;
    private EditText edit_department;
    private EditText edit_code;
    private Button btn_register;
    private ImageView imageViewCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        ActivityUtil.setTransparentStatusBar(this);
        init();
    }
    private void init(){
        edit_name=findViewById(R.id.activity_register_edit_text_name);
        edit_password=findViewById(R.id.activity_register_edit_text_password);
        edit_department=findViewById(R.id.activity_register_edit_text_department);
        edit_code=findViewById(R.id.activity_register_edit_text_code);
        btn_register=findViewById(R.id.activity_register_btn_register);
        imageViewCode=findViewById(R.id.activity_register_image_view_code);
        //???????????????????????????
        imageViewCode.setOnClickListener(view -> new Thread(() -> {
            codeKey= AccountHttp.getCodeKey();
            if (codeKey==null)return;
            codePicture=AccountHttp.getCodePicture(codeKey);
            runOnUiThread(()->{imageViewCode.setImageBitmap(BitmapFactory.decodeByteArray(codePicture,0,codePicture.length));});
        }).start());
        //????????????????????????
        edit_password.setOnTouchListener((view, motionEvent) -> {
            Drawable drawable = edit_password.getCompoundDrawables()[0];
            //????????????????????????
            if (drawable==null)return false;
            Rect bounds=drawable.getBounds();
            //???????????????????????????????????????
            if (motionEvent.getAction() != MotionEvent.ACTION_UP) return false;
            //?????????????????????,??????/????????????
            if (motionEvent.getX()<100) {
                Drawable drawableLeft=null;
                if (edit_password.getInputType()==TEXT_PASSWORD) {
                    edit_password.setInputType(TEXT_PASSWORD_VISIBLE);
                    drawableLeft=getResources().getDrawable(R.drawable.login_icon_password_show);
                }else {
                    edit_password.setInputType(TEXT_PASSWORD);
                    drawableLeft=getResources().getDrawable(R.drawable.login_icon_password_hide);
                }
                drawableLeft.setBounds(bounds);
                edit_password.setCompoundDrawables(drawableLeft,null,null,null);
            }
            return false;
        });
        //????????????
        btn_register.setOnClickListener(view -> {
            new Thread(()->{
                String result= AccountHttp.register("user",
                        edit_name.getText().toString()
                        ,edit_password.getText().toString(),
                        codeKey,
                        edit_code.getText().toString(),
                        edit_department.getText().toString());
                try {
                    JSONObject object =new JSONObject(result);
                    String msg=object.getString("msg");
                    if(object.getString("code").equals("9"))
                    {
                        runOnUiThread(()->{
                            //????????????
                            final TextView textView=new TextView(this);
                            textView.setTextSize(20);
                            textView.setText("\n    ???????????????:"+msg+"\n    ??????????????????????????????\n");
                            new AlertDialog.Builder(this).setTitle("????????????").setView(textView).setPositiveButton("??????", (dialog, which) -> {
                                finish();
                            }).create().show();
                        });
                    }else
                    {
                        //??????
                        runOnUiThread(()->{
                            //??????????????????
                            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
                            //???????????????
                            imageViewCode.callOnClick();
                            //???????????????
                            edit_code.setText("");
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).start();
        });
        imageViewCode.callOnClick();
    }
}