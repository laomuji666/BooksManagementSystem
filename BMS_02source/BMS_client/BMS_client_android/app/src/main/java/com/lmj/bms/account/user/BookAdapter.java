package com.lmj.bms.account.user;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lmj.bms.QRCodeActivity;
import com.lmj.bms.R;
import com.lmj.bms.util.AccountData;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private Activity activity;
    private List<Book> bookList;
    public BookAdapter(Activity activity, List<Book> bookList) {
        this.activity=activity;
        this.bookList=bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_book,parent,false);
        return new BookAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book=bookList.get(position);
        holder.setData(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_book_id;
        private TextView text_type;
        private TextView text_title;
        private TextView text_author;
        private TextView text_book_size;
        private ImageView image_borrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_book_id=itemView.findViewById(R.id.item_book_text_book_id);
            text_type=itemView.findViewById(R.id.item_book_text_book_type);
            text_title=itemView.findViewById(R.id.item_book_text_book_title);
            text_author=itemView.findViewById(R.id.item_book_text_book_author);
            text_book_size=itemView.findViewById(R.id.item_book_text_book_size);
            image_borrow=itemView.findViewById(R.id.item_book_image_borrow);
        }
        public void setData(Book book){
            if (!book.getBook_id().isEmpty()) text_book_id.setText(book.getBook_id());
            if (!book.getType().isEmpty()) text_type.setText(book.getType());
            if (!book.getTitle().isEmpty()) text_title.setText(book.getTitle());
            if (!book.getAuthor().isEmpty()) text_author.setText(book.getAuthor());
            if(book.getBook_count()!=null)text_book_size.setText(book.getBook_count()+"");
            if (image_borrow!=null){
                image_borrow.setOnClickListener((view)->{
                    Intent it = new Intent(activity, QRCodeActivity.class);
                    it.putExtra("book_id",text_book_id.getText().toString());
                    it.putExtra("type",text_type.getText().toString());
                    it.putExtra("title",text_title.getText().toString());
                    it.putExtra("author",text_author.getText().toString());
                    it.putExtra("user_number", AccountData.number);
                    it.putExtra("func","borrow");
                    activity.startActivity(it);
                });
            }
        }
    }
}
