package com.lmj.bms.account.user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lmj.bms.R;
import com.lmj.bms.util.AccountData;
import com.lmj.bms.util.http.BookHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BorrowFragment extends Fragment {
    private RecyclerView recyclerView;
    BorrowAdapter borrowAdapter;
    private List<Book_Borrow> borrowList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_borrow, container, false);
        initWidget(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyBorrow();
    }

    private void initWidget(View v){
        recyclerView=v.findViewById(R.id.fragment_borrow_recycler_view);
        borrowAdapter =new BorrowAdapter(getActivity(),borrowList);
        recyclerView.setAdapter(borrowAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getMyBorrow(){
        new Thread(()->{
            borrowList.clear();
            String borrows= BookHttp.getMyBorrow(AccountData.number,AccountData.onlineKey);
            if (borrows==null)return;
            try {
                JSONArray jsonArray=new JSONArray(borrows);
                for (int i =0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    Book_Borrow book_borrow=new Book_Borrow(
                            String.valueOf(object.getInt("borrow_id")),
                            String.valueOf(object.getInt("book_id")),
                            object.getString("title"),
                            object.getString("author"),
                            object.getString("borrow_time"),
                            object.getString("return_time")
                    );

                    borrowList.add(book_borrow);
                }
                getActivity().runOnUiThread(()->borrowAdapter.notifyDataSetChanged());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }).start();
    }
}