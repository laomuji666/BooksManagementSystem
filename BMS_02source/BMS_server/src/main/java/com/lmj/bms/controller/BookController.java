package com.lmj.bms.controller;

import com.lmj.bms.entity.Book;
import com.lmj.bms.entity.Book_Borrow;
import com.lmj.bms.entity.Book_Borrow_My;
import com.lmj.bms.entity.ReturnJson;
import com.lmj.bms.mapper.BookMapper;
import com.lmj.bms.model.account.AccountModel;
import com.lmj.bms.model.book.BookModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private static final String url = "book";
    @Autowired
    BookModel bookModel;
    @Autowired
    AccountModel accountModel;


    //获取所有图书信息
    @GetMapping(url+"/all")
    public Book[] selectAllBook() {
        return bookModel.selectAllBook();
    }

    //获取所有可借图书信息
    @GetMapping(url+"/available")
    public Book[] selectAvailableBook(){
        return bookModel.selectAvailableBook();
    }

    //更新图书信息
    @RequestMapping(url+"/update")
    public String  updateBook(Integer number,String onlineKey,
                              Integer book_id,String title,
                              String author,String type ,
                              Integer source_num,Integer available,
                              String reason,String add_time,
                              String bar_code){
        if (number==null||onlineKey==null||book_id==null||
                title==null||author==null||type==null||source_num==null||
                available==null||reason==null||add_time==null||bar_code==null
        )return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if(bookModel.updateBook(book_id, title, author, type, source_num, available, reason, add_time,bar_code))
            return ReturnJson.UPDATE_BOOK_SUCCESS;
        return ReturnJson.UPDATE_BOOK_FAIL;
    }

    //录入书籍
    @RequestMapping(url+"/add")
    public String insertBook(Integer number,String onlineKey,String title,String author,String type,String bar_code){
        if (number==null||onlineKey==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (title==null|| author==null||type==null||bar_code==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if (bookModel.insertBook(title,author,number,type,bar_code)==true)return ReturnJson.BOOK_INSERT_SUCCESS;
        return ReturnJson.BOOK_INSERT_FAIL;
    }

    //获取所有借阅信息
    @RequestMapping(url+"/borrow/all")
    public Book_Borrow[] selectAllBorrow(Integer number, String onlineKey){
        if (number==null||onlineKey==null)return null;
        if(accountModel.isOnline("admin",number,onlineKey))
            return bookModel.selectAllBorrow();
        return null;
    }

    //获取我的借阅信息
    @RequestMapping(url+"/borrow/my")
    public Book_Borrow_My[] selectMyBorrow(Integer number, String onlineKey){
        if (number==null||onlineKey==null)return null;
        if(accountModel.isOnline("user",number,onlineKey))
            return bookModel.selectMyBorrow(number);
        return null;
    }

    //修改借阅信息
    @RequestMapping(url+"/borrow/update")
    public String updateBorrow(Integer number,String onlineKey,
                               Integer borrow_id,Integer borrow_book,
                               Integer borrow_admin,Integer borrow_user ,
                               String borrow_time, String return_time,
                               Integer available){
        if (number==null||onlineKey==null||borrow_id==null||
                borrow_book==null||borrow_admin==null||borrow_user==null||borrow_time==null||
                available==null||return_time==null
        )return ReturnJson.ERROR_MISSING_PARAMETER;
        if (return_time.isEmpty())return_time=null;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if(bookModel.updateBorrow(borrow_id, borrow_book, borrow_admin, borrow_user, borrow_time, return_time, available))
            return ReturnJson.UPDATE_BORROW_SUCCESS;
        return ReturnJson.UPDATE_BORROW_FAIL;
    }

    //借书
    @RequestMapping(url+"/borrow/add")
    public String borrowBook(Integer number,String onlineKey,Integer book_id,Integer borrow_user){
        if (number==null||onlineKey==null||book_id==null||borrow_user==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if (bookModel.borrowBook(book_id,number,borrow_user))return ReturnJson.BOOK_BORROW_SUCCESS;
        return ReturnJson.BOOK_BORROW_FAIL;
    }

    //还书
    @RequestMapping(url+"/borrow/return")
    public String returnBook(Integer number,String onlineKey,Integer book_id,Integer borrow_id){
        if (number==null||onlineKey==null||book_id==null||borrow_id==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if(bookModel.returnBook(book_id, borrow_id))return ReturnJson.RETURN_BOOK_SUCCESS;
        return ReturnJson.RETURN_BOOK_FAIL;
    }
}
