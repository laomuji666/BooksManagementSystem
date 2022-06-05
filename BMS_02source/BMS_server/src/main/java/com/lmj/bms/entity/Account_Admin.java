package com.lmj.bms.entity;


public class Account_Admin {
    //数据库的字段
    private int admin_number;
    private String name;
    private String password;
    private int available;
    private String profile_photo;

    //记录key值,实现单用户登陆
    private String onlineKey;
    //记录登陆的时间
    private Long onlineTime;

    public int getAdmin_number() {
        return admin_number;
    }

    public void setAdmin_number(int admin_number) {
        this.admin_number = admin_number;
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
        sb.append("\"admin_number\":")
                .append("\""+admin_number+"\"");
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"available\":")
                .append(available);
        sb.append(",\"profile_photo\":\"")
                .append(profile_photo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
