package com.lmj.bms.util.http;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookHttp {
    private static OkHttpClient availableBookHttpClient=new OkHttpClient();
    private static OkHttpClient myBorrowHttpClient=new OkHttpClient();
    private static OkHttpClient bookBorrowOrReturnHttpClient=new OkHttpClient();
    private static OkHttpClient addBookHttpClient = new OkHttpClient();
    public static String getAvailableBook(){
        try {
            Request request = new Request.Builder().url(Url.url_available_book).build();
            Response execute = availableBookHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            return null;
        }
    }

    public static String getMyBorrow(String number,String onlineKey){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("number",number);
            formBody.add("onlineKey",onlineKey);
            Request request = new Request.Builder().url(Url.url_my_borrow).post(formBody.build()).build();
            Response execute = myBorrowHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String bookBorrowOrReturn(String func,String number,String onlineKey,String book_id,String borrow_id,String borrow_user){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("number",number);
            formBody.add("onlineKey",onlineKey);
            formBody.add("book_id",book_id);
            formBody.add("borrow_user",borrow_user);
            String url;
            if (func.equals("borrow")){
                url=Url.url_borrow_book;
            }else if (func.equals("return")){
                formBody.add("borrow_id",borrow_id);
                url=Url.url_return_book;
            }else {
                return null;
            }
            Request request = new Request.Builder().url(url).post(formBody.build()).build();
            Response execute = bookBorrowOrReturnHttpClient.newCall(request).execute();
            if (execute==null)return null;
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String addBook(String number,String onlineKey,String title,String author,String type,String bar_code){
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("number",number);
            formBody.add("onlineKey",onlineKey);
            formBody.add("title",title);
            formBody.add("author",author);
            formBody.add("type",type);
            formBody.add("bar_code",bar_code);
            String url = Url.url_add_book;
            Request request = new Request.Builder().url(url).post(formBody.build()).build();
            Response execute = addBookHttpClient.newCall(request).execute();
            if (execute==null)return "";
            return execute.body().string();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
