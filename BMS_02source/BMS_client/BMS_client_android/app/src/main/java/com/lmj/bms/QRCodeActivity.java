package com.lmj.bms;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.ActivityUtil;
import com.lmj.bms.util.QRCodeUtil;

public class QRCodeActivity extends AppCompatActivity {
    private ImageView image_qrcode;
    private TextView text_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getSupportActionBar().hide();
        ActivityUtil.setTransparentStatusBar(this);
        image_qrcode=findViewById(R.id.activity_qrcode_image_qrcode);
        text_content=findViewById(R.id.activity_qrcode_text_content);
        String func=getIntent().getStringExtra("func");
        if (func.equals("borrow")){
            borrowBook();
        }else if (func.equals("return")){
            returnBook();
        }else {
            text_content.setText("功能错误");
        }
    }
    private void borrowBook(){
        String func=getIntent().getStringExtra("func");
        String user_number=getIntent().getStringExtra("user_number");
        String book_id=getIntent().getStringExtra("book_id");
        String type=getIntent().getStringExtra("type");
        String title=getIntent().getStringExtra("title");
        String author=getIntent().getStringExtra("author");
        String strQR="{\"book_id\":\""+book_id+"\"," +
                "\"title\":\""+title+"\"," +
                "\"func\":\""+func+ "\"," +
                "\"user_number\":\""+user_number+"\"}";
        Bitmap qrBitmap=QRCodeUtil.createQRCodeBitmap(strQR,300,300);
        Glide.with(this).load(qrBitmap).into(image_qrcode);
        String text= String.format("功能:借书\n用户号:%s\n书籍编号:%s\n书籍分类:%s\n书籍标题:%s\n书籍信息:%s",user_number,book_id,type,title,author);
        text_content.setText(text);
    }

    private void returnBook(){
        String func=getIntent().getStringExtra("func");
        String user_number = getIntent().getStringExtra("user_number");
        String borrow_id = getIntent().getStringExtra("borrow_id");
        String book_id = getIntent().getStringExtra("book_id");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String borrow_time = getIntent().getStringExtra("borrow_time");
        String strQR="{\"book_id\":\""+book_id+"\"," +
                "\"title\":\""+title+"\"," +
                "\"func\":\""+func+ "\"," +
                "\"user_number\":\""+user_number+"\"," +
                "\"borrow_id\":\""+borrow_id+"\"}";
        Bitmap qrBitmap=QRCodeUtil.createQRCodeBitmap(strQR,300,300);
        Glide.with(this).load(qrBitmap).into(image_qrcode);
        String text= String.format("功能:还书\n用户号:%s\n借阅编号:%s\n书籍编号:%s\n书籍标题:%s\n书籍信息:%s\n借阅时间:%s",
                user_number,borrow_id,book_id,title,author,borrow_time);
        text_content.setText(text);
    }
}