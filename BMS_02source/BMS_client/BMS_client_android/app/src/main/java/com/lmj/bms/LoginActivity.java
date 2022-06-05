package com.lmj.bms;

import static android.text.InputType.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lmj.bms.account.admin.AdminActivity;
import com.lmj.bms.account.user.UserActivity;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.ActivityUtil;
import com.lmj.bms.util.http.AccountHttp;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    private static LoginActivity m_activity;
    private static final int TEXT_PASSWORD=TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD|TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD;
    private static final int TEXT_PASSWORD_VISIBLE=TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD|TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

    private String codeKey="";
    private byte[] codePicture;
    private String type="user";
    private String onlineKey="";
    private EditText edit_number;
    private EditText edit_password;
    private EditText edit_code;
    private ImageView imageViewCode;
    private Button btn_login;
    private TextView text_change;
    private TextView text_show;
    private TextView text_register;
    private CheckBox check_remember;
    private CheckBox check_auto_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_activity=this;
        //隐藏标题栏
        getSupportActionBar().hide();
        //顶部状态栏透明
        ActivityUtil.setTransparentStatusBar(this);
        //初始化组件
        initWidget();
        imageViewCode.callOnClick();
        //读取保存的账号密码
        readAccount();
    }
    //初始化绑定组件和事件
    private void initWidget(){
        //绑定输入框
        edit_number=findViewById(R.id.activity_login_edit_text_number);
        edit_password=findViewById(R.id.activity_login_edit_text_password);
        edit_code=findViewById(R.id.activity_login_edit_text_code);
        //绑定图片
        imageViewCode=findViewById(R.id.activity_login_image_view_code);
        //绑定按钮
        btn_login=findViewById(R.id.activity_login_btn_login);
        //绑定标签
        text_change=findViewById(R.id.activity_login_text_view_type_change);
        text_show=findViewById(R.id.activity_login_text_view_type_show);
        text_register=findViewById(R.id.activity_login_text_view_register);
        //选择框
        check_remember=findViewById(R.id.activity_login_check_box_remember);
        check_auto_login=findViewById(R.id.activity_login_check_box_auto_login);
        //点击图片切换验证码
        imageViewCode.setOnClickListener(view -> new Thread(() -> {
            codeKey=AccountHttp.getCodeKey();
            if (codeKey==null)return;
            codePicture=AccountHttp.getCodePicture(codeKey);
            runOnUiThread(()->{
                imageViewCode.setImageBitmap(BitmapFactory.decodeByteArray(codePicture,0,codePicture.length));
            });
        }).start());
        //点击登陆
        btn_login.setOnClickListener(view -> {
            new Thread(()->{
                String result= AccountHttp.login(type,edit_number.getText().toString()
                        ,edit_password.getText().toString(),codeKey,
                        edit_code.getText().toString());
                try {
                    JSONObject object =new JSONObject(result);
                    String msg=object.getString("msg");
                    if(object.getString("code").equals("0"))
                    {
                        //登陆成功
                        onlineKey=msg;
                        //保存账号密码
                        writeAccount();
                        //加载登陆成功的窗口
                        runOnUiThread(()->openActivity());
                    }else
                    {
                        //登陆失败
                        runOnUiThread(()->{
                            //提示错误原因
                            Toast.makeText(this,type+":"+msg, Toast.LENGTH_SHORT).show();
                            //更新验证码
                            imageViewCode.callOnClick();
                            //清空验证码
                            edit_code.setText("");
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).start();
        });
        //点击显示隐藏密码
        edit_password.setOnTouchListener((view, motionEvent) -> {
            Drawable drawable = edit_password.getCompoundDrawables()[0];
            //如果左边没有图片
            if (drawable==null)return false;
            Rect bounds=drawable.getBounds();
            //如果不是按下事件，不再处理
            if (motionEvent.getAction() != MotionEvent.ACTION_UP) return false;
            //计算左边的图片,显示/隐藏密码
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
        //切换登陆类型
        text_change.setOnClickListener((view)->{
            if (type.equals("user")){
                type="admin";
                text_show.setText("管理登陆");
            }else{
                type="user";
                text_show.setText("用户登陆");
            }
        });
        text_register.setOnClickListener((view)->{
            startActivity(new Intent(this,RegisterActivity.class));
        });
    }

    private void openActivity(){
        AccountData.type=type;
        AccountData.number=edit_number.getText().toString();
        AccountData.onlineKey=onlineKey;
        Intent it;
        if (type.equals("admin")){
            it =new Intent(LoginActivity.this, AdminActivity.class);
        }else{
             it=new Intent(LoginActivity.this, UserActivity.class);
        }
        startActivity(it);
        this.finish();
    }

    //读取账号密码
    private void readAccount(){
        SharedPreferences sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("remember",false)){
            String number=sharedPreferences.getString("number","");
            String password=sharedPreferences.getString("password","");
            type = sharedPreferences.getString("type","user");
            onlineKey=sharedPreferences.getString("onlineKey","");
            check_remember.setChecked(true);
            edit_number.setText(number);
            edit_password.setText(password);
            if(onlineKey != null && !onlineKey.isEmpty()){
                check_auto_login.setChecked(true);
                openActivity();
            }else
                check_auto_login.setChecked(false);

            if (type.equals("admin"))text_show.setText("管理员登陆");
        }

    }
    //保存账号密码
    private void writeAccount(){
        SharedPreferences.Editor edit = getSharedPreferences("account", Context.MODE_PRIVATE).edit();
        if (check_remember.isChecked()){
            edit.putBoolean("remember",true);
            edit.putString("number",edit_number.getText().toString());
            edit.putString("password",edit_password.getText().toString());
            edit.putString("type",type);
            if(check_auto_login.isChecked()) edit.putString("onlineKey",onlineKey);
            else edit.putString("onlineKey","");
        }else {
            edit.clear();
        }
        edit.commit();
    }
    //注销登陆 去除自动登陆
    public static void logout(){
        SharedPreferences.Editor edit = m_activity.getSharedPreferences("account", Context.MODE_PRIVATE).edit();
        edit.putString("onlineKey","");
        edit.commit();
    }
}