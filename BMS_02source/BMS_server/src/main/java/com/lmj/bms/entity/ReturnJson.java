package com.lmj.bms.entity;

public class ReturnJson {
    //账号有关的返回的json信息
    public static final String ERROR_MISSING_PARAMETER="{\"code\":\"-1\",\"msg\":\"提交参数不全\"}";
    public static final String LOGIN_SUCCESS = "{\"code\":\"0\",\"msg\":\"{OnlineKey}\"}";
    public static final String LOGIN_FAIL_NOT_FOUNT = "{\"code\":\"1\",\"msg\":\"登陆失败,账号或密码不正确\"}";
    public static final String LOGIN_FAIL_NOT_AVAILABLE = "{\"code\":\"2\",\"msg\":\"该账号已注销\"}";
    public static final String CODE_FAIL_TIME_OUT = "{\"code\":\"3\",\"msg\":\"codeKey超过有效期或已经使用过了\"}";
    public static final String CODE_FAIL_INCORRECT = "{\"code\":\"4\",\"msg\":\"验证码错误\"}";
    public static final String TYPE_FAIL = "{\"code\":\"5\",\"msg\":\"type参数不正确\"}";
    public static final String ONLINE_KEY_FAIL = "{\"code\":\"6\",\"msg\":\"onlineKey不正确\"}";
    public static final String UPDATE_SUCCESS = "{\"code\":\"7\",\"msg\":\"个人信息更新成功\"}";
    public static final String REGISTER_SUCCESS_ADMIN = "{\"code\":\"8\",\"msg\":\"{number}\"}";
    public static final String REGISTER_SUCCESS_USER = "{\"code\":\"9\",\"msg\":\"{number}\"}";
    public static final String ONLINE_NOT_ONLINE = "{\"code\":\"10\",\"msg\":\"登陆已过期,请重新登陆\"}";
    public static final String BOOK_INSERT_SUCCESS = "{\"code\":\"11\",\"msg\":\"书籍录入成功\"}";
    public static final String BOOK_INSERT_FAIL = "{\"code\":\"12\",\"msg\":\"书籍录入失败\"}";
    public static final String BOOK_BORROW_SUCCESS = "{\"code\":\"13\",\"msg\":\"书籍借阅成功\"}";
    public static final String BOOK_BORROW_FAIL = "{\"code\":\"14\",\"msg\":\"书籍借阅失败,该书籍已被借走或损坏\"}";
    public static final String UPLOAD_SUCCESS = "{\"code\":\"16\",\"msg\":\"图片上传成功\"}";
    public static final String UPDATE_BOOK_SUCCESS = "{\"code\":\"17\",\"msg\":\"书籍信息更新成功\"}";
    public static final String UPDATE_BOOK_FAIL = "{\"code\":\"18\",\"msg\":\"书籍信息更新失败\"}";
    public static final String UPDATE_BORROW_SUCCESS = "{\"code\":\"19\",\"msg\":\"借阅信息更新成功\"}";
    public static final String UPDATE_BORROW_FAIL = "{\"code\":\"20\",\"msg\":\"借阅信息更新失败\"}";
    public static final String UPDATE_USER_ALL_SUCCESS = "{\"code\":\"21\",\"msg\":\"普通用户信息更新成功\"}";
    public static final String UPDATE_USER_ALL_FAIL = "{\"code\":\"22\",\"msg\":\"普通用户信息更新失败\"}";
    public static final String RETURN_BOOK_SUCCESS = "{\"code\":\"23\",\"msg\":\"书籍归还成功\"}";
    public static final String RETURN_BOOK_FAIL = "{\"code\":\"24\",\"msg\":\"书籍归还失败\"}";
}
