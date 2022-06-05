package com.lmj.bms.entity;

public class Account_User {
    //数据库的字段
    private int user_number;
    private String name;
    private String password;
    private String department;
    private int available;
    private String profile_photo;

    //记录key值,实现单用户登陆
    private String onlineKey;
    //记录登陆的时间
    private Long onlineTime;

    public int getUser_number() {
        return user_number;
    }

    public void setUser_number(int user_number) {
        this.user_number = user_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getOnlineKey() {
        return onlineKey;
    }

    public void setOnlineKey(String onlineKey) {
        this.onlineKey = onlineKey;
    }

    public Long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"user_number\":")
                .append("\""+user_number+"\"");
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"department\":\"")
                .append(department).append('\"');
        sb.append(",\"available\":")
                .append(available);
        sb.append(",\"profile_photo\":\"")
                .append(profile_photo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
