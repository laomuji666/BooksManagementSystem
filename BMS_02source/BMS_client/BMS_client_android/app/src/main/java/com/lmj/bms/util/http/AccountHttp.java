package com.lmj.bms.util.http;

import okhttp3.FormBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccountHttp {
    private static OkHttpClient codeKeyHttpClient=new OkHttpClient();
    private static OkHttpClient codePictureHttpClient=new OkHttpClient();
    private static OkHttpClient loginHttpClient=new OkHttpClient();
    private static OkHttpClient registerHttpClient=new OkHttpClient();
    private static OkHttpClient infoHttpClient=new OkHttpClient();
    private static OkHttpClient profile_photoClient=new OkHttpClient();
    private static OkHttpClient updateHttpClient=new OkHttpClient();
    public static String getCodeKey(){
        try {
            Request request = new Request.Builder().url(Url.url_code_key).build();
            Response execute = codeKeyHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            return null;
        }
    }

    public static byte[] getCodePicture(String codeKey){
        try {
            Request request = new Request.Builder().url(Url.url_code_picture+"?codeKey="+codeKey).build();
            Response execute = codePictureHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().bytes();
        }catch (Exception e)
        {
            return null;
        }
    }

    public static String login(String type,String number,String password,String codeKey,String codeStr){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("type",type);
            formBody.add("number",number);
            formBody.add("password",password);
            formBody.add("codeKey",codeKey);
            formBody.add("codeStr",codeStr);
            Request request = new Request.Builder().url(Url.url_login).post(formBody.build()).build();
            Response execute = loginHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static String register(String type,String name,String password,String codeKey,String codeStr,String department){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("type",type);
            formBody.add("name",name);
            formBody.add("password",password);
            formBody.add("codeKey",codeKey);
            formBody.add("codeStr",codeStr);
            formBody.add("department",department);
            Request request = new Request.Builder().url(Url.url_register).post(formBody.build()).build();
            Response execute = registerHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static String getInfo(String type,String number,String onlineKey){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("type",type);
            formBody.add("number",number);
            formBody.add("onlineKey",onlineKey);
            Request request = new Request.Builder().url(Url.url_info).post(formBody.build()).build();
            Response execute = infoHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] get_profile_photo(String profile_photo){
        try {
            Request request = new Request.Builder().url(Url.url_profile_photo+"?profile_photo="+profile_photo).build();
            Response execute = profile_photoClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().bytes();
        }catch (Exception e)
        {
            return null;
        }
    }

    public static String updateInfo(String type,String number,String onlineKey,
                                String name,String password,String profile_photo){

        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("type",type);
            formBody.add("number",number);
            formBody.add("onlineKey",onlineKey);
            if (name!=null&&!name.isEmpty()) formBody.add("name",name);
            if (password!=null&&!password.isEmpty()) formBody.add("password",password);
            if (profile_photo!=null&&!profile_photo.isEmpty()) formBody.add("profile_photo",profile_photo);
            Request request = new Request.Builder().url(Url.url_update).post(formBody.build()).build();
            Response execute = updateHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
