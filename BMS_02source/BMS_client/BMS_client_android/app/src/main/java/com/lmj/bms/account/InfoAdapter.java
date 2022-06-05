package com.lmj.bms.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lmj.bms.LoginActivity;
import com.lmj.bms.R;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.http.AccountHttp;

import java.util.HashMap;
import java.util.Set;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{
    private Activity activity;
    private InfoFragment fragment;
    private HashMap<String,String> infoMap;
    private Set<String> keySets;
    public InfoAdapter(Activity activity, HashMap<String,String> infoMap,InfoFragment fragment) {
        this.activity=activity;
        this.infoMap=infoMap;
        this.fragment=fragment;
        keySets=this.infoMap.keySet();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_user_info,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String type = (String) keySets.toArray()[position];
        String value = infoMap.get(type);
        holder.setData(type,value);
    }

    @Override
    public int getItemCount() {
        return infoMap.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_type;
        private TextView text_value;
        private ImageView image;
        private View view;
        //绑定控件
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_type=itemView.findViewById(R.id.item_user_info_text_type);
            text_value=itemView.findViewById(R.id.item_user_info_text_value);
            image=itemView.findViewById(R.id.item_user_info_image);
            view=itemView;
        }
        //绑定数据
        public void setData(String type,String value){
            text_type.setText(type);
            text_value.setText(value);
            if (type.equals("类型")){
                image.setVisibility(View.INVISIBLE);
                text_value.setOnClickListener(view -> {
                    Toast.makeText(activity, "类型不允许修改", Toast.LENGTH_SHORT).show();
                });
            }
            if (type.equals("账号")){
                image.setVisibility(View.INVISIBLE);
                text_value.setOnClickListener(view -> {
                    Toast.makeText(activity, "账号为系统生成,不允许修改", Toast.LENGTH_SHORT).show();
                });
            }
            if (type.equals("姓名")){
                text_value.setOnClickListener(view -> {
                    final EditText input = new EditText(activity);
                    new AlertDialog.Builder(activity).setTitle("输入需要修改的姓名").setView(input).setPositiveButton("确定", (dialog, which) -> {
                        String name = input.getText().toString();
                        new Thread(()->{
                            AccountHttp.updateInfo(AccountData.type,AccountData.number,AccountData.onlineKey,
                                    name,null,null);
                            fragment.initInfo();
                        }).start();
                    }).setNegativeButton("取消", null).create().show();
                });
            }
            if (type.equals("详细")){
                image.setVisibility(View.INVISIBLE);
                text_value.setOnClickListener(view -> {
                    Toast.makeText(activity, "该项目不允许自行修改,请联系管理员修改", Toast.LENGTH_SHORT).show();
                });
            }
            if (type.equals("密码")){
                text_value.setOnClickListener(view -> {
                    final EditText input = new EditText(activity);
                    new AlertDialog.Builder(activity).setTitle("输入需要修改的密码").setView(input).setPositiveButton("确定", (dialog, which) -> {
                        String password = input.getText().toString();
                        new Thread(()->{
                            AccountHttp.updateInfo(AccountData.type,AccountData.number,AccountData.onlineKey,
                                    null,password,null);
                        }).start();
                    }).setNegativeButton("取消", null).create().show();
                });
            }
            if (type.equals("登陆")){
                text_value.setOnClickListener(view -> {
                    LoginActivity.logout();
                    activity.startActivity(new Intent(activity,LoginActivity.class));
                    activity.finish();
                });
            }




        }
    }
}
