package com.lmj.bms.account.user;

public class Book {
    private String book_id;
    private String title;
    private String author;
    private String type;
    private Integer book_count;

    public Book(String book_id, String title, String author, String type, Integer book_count) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.book_count = book_count;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBook_count() {
        return book_count;
    }

    public void setBook_count(Integer book_count) {
        this.book_count = book_count;
    }
}
