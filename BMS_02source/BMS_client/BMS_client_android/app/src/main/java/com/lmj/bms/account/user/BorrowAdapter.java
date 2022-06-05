package com.lmj.bms.account.user;

import android.app.Activity;
import android.content.Intent;
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

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.ViewHolder>{
    private Activity activity;
    private List<Book_Borrow> borrowList;
    public BorrowAdapter(Activity activity, List<Book_Borrow> borrowList) {
        this.activity=activity;
        this.borrowList=borrowList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_borrow,parent,false);
        return new BorrowAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book_Borrow borrow=borrowList.get(position);
        holder.setData(borrow);
    }

    @Override
    public int getItemCount() {
        return borrowList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_borrow_id;
        private TextView text_book_id;
        private TextView text_title;
        private TextView text_text_author;
        private TextView text_borrow_time;
        private TextView text_return_time;
        private ImageView image_return;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_borrow_id=itemView.findViewById(R.id.item_borrow_text_borrow_id);
            text_book_id=itemView.findViewById(R.id.item_borrow_text_book_id);
            text_title=itemView.findViewById(R.id.item_borrow_text_title);
            text_text_author=itemView.findViewById(R.id.item_borrow_text_author);
            text_borrow_time=itemView.findViewById(R.id.item_borrow_text_borrow_time);
            text_return_time=itemView.findViewById(R.id.item_borrow_text_return_time);
            image_return=itemView.findViewById(R.id.item_book_image_return);
        }
        public void setData(Book_Borrow borrow){
            text_borrow_id.setText(borrow.getBorrow_id());
            text_book_id.setText(borrow.getBook_id());
            text_title.setText(borrow.getTitle());
            text_text_author.setText(borrow.getAuthor());
            text_borrow_time.setText(borrow.getBorrow_time());
            String returnTime=borrow.getReturn_time();
            text_return_time.setText(returnTime);
            if (returnTime==null||returnTime.equals("null")){
                image_return.setVisibility(View.VISIBLE);
                image_return.setOnClickListener((view)->{
                    Intent it = new Intent(activity, QRCodeActivity.class);
                    it.putExtra("func","return");
                    it.putExtra("user_number",AccountData.number);
                    it.putExtra("borrow_id",text_borrow_id.getText().toString());
                    it.putExtra("book_id",text_book_id.getText().toString());
                    it.putExtra("title",text_title.getText().toString());
                    it.putExtra("author",text_text_author.getText().toString());
                    it.putExtra("borrow_time",text_borrow_time.getText().toString());
                    activity.startActivity(it);
                });
            }else{
                image_return.setVisibility(View.INVISIBLE);
            }
        }
    }
}
