package com.lmj.bms.entity;

public class Book {
    //数据库的字段
    private int book_id;
    private String title;
    private String author;
    private String type;
    private int source_num;
    private int available;
    private String reason;
    private String add_time;
    private String bar_code;
    private Integer book_count;

    public Integer getBook_count() {
        return book_count;
    }

    public void setBook_count(Integer book_count) {
        this.book_count = book_count;
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

    public int getSource_num() {
        return source_num;
    }

    public void setSource_num(int source_num) {
        this.source_num = source_num;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }
}
