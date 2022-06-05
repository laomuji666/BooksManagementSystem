package com.lmj.bms.entity;

public class Book_Borrow_My {
    private int borrow_id;
    private int borrow_book;
    private int borrow_admin;
    private int borrow_user;
    private String borrow_time;
    private String return_time;
    private int available;
    private int book_id;
    private String title;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getBorrow_book() {
        return borrow_book;
    }

    public void setBorrow_book(int borrow_book) {
        this.borrow_book = borrow_book;
    }

    public int getBorrow_admin() {
        return borrow_admin;
    }

    public void setBorrow_admin(int borrow_admin) {
        this.borrow_admin = borrow_admin;
    }

    public int getBorrow_user() {
        return borrow_user;
    }

    public void setBorrow_user(int borrow_user) {
        this.borrow_user = borrow_user;
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
