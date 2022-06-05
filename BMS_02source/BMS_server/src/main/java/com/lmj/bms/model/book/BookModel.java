package com.lmj.bms.model.book;

import com.lmj.bms.entity.Book;
import com.lmj.bms.entity.Book_Borrow;
import com.lmj.bms.entity.Book_Borrow_My;
import com.lmj.bms.entity.ReturnJson;
import com.lmj.bms.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookModel {
    @Autowired
    private BookMapper bookMapper;

    //获取所有图书信息
    public Book[] selectAllBook() {
        return bookMapper.SelectAllBook();
    }
    //获取所有可借图书
    public Book[] selectAvailableBook() {
        return bookMapper.SelectAllAvailableBook();
    }
    //更新书籍信息
    public boolean updateBook(Integer book_id,String title,
                              String author,String type ,
                              Integer source_num,Integer available,
                              String reason,String add_time,
                              String bar_code){
        try {
            int i=bookMapper.UpdateBook(book_id, title, author, type, source_num, available, reason, add_time,bar_code);
            if (i==1)return true;
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    //录入书籍
    public boolean insertBook(String title,String author,Integer source_num,String type,String bar_code){
        if (bookMapper.InsertBook(title,author,type,source_num,bar_code)==1)return true;
        return false;
    }

    //获取所有借阅信息
    public Book_Borrow[] selectAllBorrow(){
        return bookMapper.SelectAllBorrow();
    }

    //获取我的借阅信息
    public Book_Borrow_My[] selectMyBorrow(Integer borrow_user){
        return bookMapper.SelectMyBorrow(borrow_user);
    }



    //更新借阅信息
    public boolean updateBorrow(Integer borrow_id,Integer borrow_book,
                                Integer borrow_admin,Integer borrow_user ,
                                String borrow_time, String return_time,
                                Integer available){
        try {
            int i=bookMapper.UpdateBorrow(borrow_id, borrow_book, borrow_admin, borrow_user, borrow_time, return_time, available);
            if (i==1)return true;
            else return false;
        }catch (Exception e){
            return false;
        }
    }

    //借书
    public boolean borrowBook(Integer book_id,Integer borrow_admin,Integer borrow_user){
        try {
            if (bookMapper.BorrowBookUpdate(book_id)!=1)return false;
            if(bookMapper.BorrowBookInsert(book_id,borrow_admin,borrow_user)!=1)return false;
        }catch (Exception e){
            return false;
        }
        return true;
    }
    //还书
    public boolean returnBook(Integer book_id,Integer borrow_id){
        try {
            if (bookMapper.ReturnBookUpdateBook(book_id)!=1)return false;
            if(bookMapper.ReturnBookUpdateBorrow(borrow_id)!=1)return false;
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
