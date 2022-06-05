package com.lmj.bms.account.user;

public class Book_Borrow {
    private String borrow_id;
    private String book_id;
    private String title;
    private String author;
    private String borrow_time;
    private String return_time;

    public Book_Borrow(String borrow_id, String book_id, String title, String author, String borrow_time, String return_time) {
        this.borrow_id = borrow_id;
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.borrow_time = borrow_time;
        this.return_time = return_time;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "Book_Borrow{" +
                "borrow_id='" + borrow_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrow_time='" + borrow_time + '\'' +
                ", return_time='" + return_time + '\'' +
                '}';
    }
}
