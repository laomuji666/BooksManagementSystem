package com.lmj.bms.account.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmj.bms.R;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.ActivityUtil;
import com.lmj.bms.util.http.BookHttp;

public class AddBookActivity extends AppCompatActivity {
    private EditText edit_title,edit_author,edit_size,edit_bar_code,edit_type;
    private Button btn_add_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().hide();
        ActivityUtil.setTransparentStatusBar(this);
        initWeight();
    }
    private void initWeight(){
        edit_title = findViewById(R.id.activity_add_book_edit_title);
        edit_author = findViewById(R.id.activity_add_book_edit_author);
        edit_size = findViewById(R.id.activity_add_book_edit_size);
        edit_bar_code = findViewById(R.id.activity_add_book_edit_bar_code);
        edit_bar_code.setText(getIntent().getStringExtra("bar_code"));
        edit_type = findViewById(R.id.activity_add_book_edit_type);
        btn_add_book = findViewById(R.id.activity_add_book_button_add_book);
        btn_add_book.setOnClickListener(view -> {
            String title = edit_title.getText().toString();
            String author = edit_author.getText().toString();
            String bar_code = edit_bar_code.getText().toString();
            String type = edit_type.getText().toString();
            Toast.makeText(this, "添加中,请稍后,添加完会自动关闭", Toast.LENGTH_LONG).show();
            new Thread(() -> {
                int size = Integer.parseInt(edit_size.getText().toString());
                for(int i =0;i<size;i++){
                    BookHttp.addBook(AccountData.number,AccountData.onlineKey,
                            title,author,type,bar_code);
                }
                runOnUiThread(() -> {
                    finish();
                });
            }).start();

        });
    }

}