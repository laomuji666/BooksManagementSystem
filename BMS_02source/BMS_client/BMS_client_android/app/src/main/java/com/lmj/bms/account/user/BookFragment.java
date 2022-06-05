package com.lmj.bms.account.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lmj.bms.R;
import com.lmj.bms.account.InfoAdapter;
import com.lmj.bms.util.http.BookHttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {
    private List<Book> bookAllList=new ArrayList<>();
    private List<Book> bookList=new ArrayList<>();
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_book, container, false);
        initWidget(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBooks();
    }

    private void initWidget(View v){
        recyclerView=v.findViewById(R.id.fragment_book_recycler_view);
        bookAdapter=new BookAdapter(getActivity(),bookList);
        recyclerView.setAdapter(bookAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchView=v.findViewById(R.id.fragment_book_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showBook(newText);
                return false;
            }
        });
    }

    private void getBooks(){
        new Thread(()->{
            String s =BookHttp.getAvailableBook();
            if (s==null)return;
            try {
                JSONArray jsonArray = new JSONArray(s);
                bookAllList.clear();
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    Book book=new Book(
                            String.valueOf(object.getInt("book_id")),
                            object.getString("title"),
                            object.getString("author"),
                            object.getString("type"),
                            object.getInt("book_count"));
                    bookAllList.add(book);
                }
                showBook(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void showBook(String query){
        getActivity().runOnUiThread(()->{
            bookList.clear();
            for (Book book:bookAllList){
                if (query==null||query.isEmpty()){
                    bookList.add(book);
                }else{
                    if (book.getTitle().indexOf(query)!=-1||
                        book.getType().indexOf(query)!=-1||
                        book.getAuthor().indexOf(query)!=-1){
                        bookList.add(book);
                    }
                }
            }
            bookAdapter.notifyDataSetChanged();
        });
    }
}