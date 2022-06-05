package com.lmj.bms.controller;

import com.lmj.bms.entity.Account_Admin;
import com.lmj.bms.entity.Account_User;
import com.lmj.bms.mapper.AccountMapper;
import com.lmj.bms.entity.ReturnJson;
import com.lmj.bms.model.account.AccountModel;
import com.lmj.bms.model.account.code.CodeModel;
import com.lmj.bms.util.LogUtil;
import com.lmj.bms.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Base64;


@RestController
public class AccountController {
    private static final String url = "account";
    @Autowired
    private AccountModel accountModel;
    @Autowired
    private CodeModel codeModel;


    //查看所有用户信息,仅用于调试
    @Autowired
    private AccountMapper accountMapper;
    class Account_ALL{
        private Account_Admin[] admins;
        private Account_User[] users;
        public Account_ALL(Account_Admin[] admins, Account_User[] users) { this.admins = admins;this.users = users; }
        public Account_Admin[] getAdmins() { return admins; }
        public Account_User[] getUsers() { return users; }
    }
    @GetMapping(url+"/all")
    public Account_ALL getAllAccount() {
        return new Account_ALL(accountMapper.SelectAllAdmin(),accountMapper.SelectAllUser());
    }

    //登陆
    @RequestMapping(url+"/login")
    public String login(String type,Integer number,String password,Long codeKey,String codeStr){
        //检查验证码
        if (codeKey==null||codeStr==null) return ReturnJson.CODE_FAIL_INCORRECT;
        if (codeModel.getCodePicture(codeKey)==null)return ReturnJson.CODE_FAIL_TIME_OUT;
        if (codeModel.verifyCode(codeKey,codeStr)==false)return ReturnJson.CODE_FAIL_INCORRECT;

        //检查参数
        if (type==null||number==null||password==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        //登陆
        return accountModel.login(type,number,password);
    }

    //注册
    @RequestMapping(url+"/register")
    public String register(String type,String name,String password,Long codeKey,String codeStr,String department){
        //检查验证码
        if (codeKey==null||codeStr==null) return ReturnJson.CODE_FAIL_INCORRECT;
        if (codeModel.getCodePicture(codeKey)==null)return ReturnJson.CODE_FAIL_TIME_OUT;
        if (codeModel.verifyCode(codeKey,codeStr)==false)return ReturnJson.CODE_FAIL_INCORRECT;

        //检查参数
        if (type==null||name==null||password==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        //user必须要有department参数,admin不需要该参数
        if (type.equals("user") && department==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        return accountModel.register(type, name, password, department);
    }

    //获取codeKey
    @GetMapping(url+"/code/key")
    public Long getCodeKey(){
        return codeModel.getCodeKey();
    }

    //通过codeKey获得验证码图片
    @GetMapping(value = url+"/code/picture", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getCodePicture(Long codeKey){
        return codeModel.getCodePicture(codeKey);
    }

    //查看个人信息
    @RequestMapping(url+"/info")
    public String getInfo(String type,Integer number,String onlineKey){
        if (type==null||number==null||onlineKey==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        return accountModel.getInfo(type, number, onlineKey);
    }

    //修改个人信息
    @RequestMapping(url+"/update")
    public String update(String type,Integer number,String onlineKey,String name,String password,String profile_photo){
        if (type==null||number==null||onlineKey==null) return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline(type, number, onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        //文件名
        String filename=null;
        if(profile_photo!=null){
            profile_photo=profile_photo.replace("\"","");
            profile_photo=profile_photo.replace(" ","+");
            filename=type+number+".jpg";
            //保存图片的目录
            String path = "profile_photo/";
            if(!new File(path).exists()) new File(path).mkdirs();

            try {
                PictureUtil.base64StrToImage(profile_photo,path+filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountModel.updateInfo(type, number, onlineKey,name, password, filename);
    }

    //获取头像
    @RequestMapping(value=url+"/profile_photo/get",produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[]  profile_photo(String profile_photo){
        if (profile_photo==null)return null;

        File file = new File("profile_photo/"+profile_photo);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (Exception e) {

        }

        return null;
    }

    //获取所有用户信息
    @RequestMapping(url+"/all/user")
    public Account_User[] getAllUser(Integer number,String onlineKey){
        if (number==null||onlineKey==null)return null;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return null;
        return accountModel.getAllUser();
    }

    //更新用户的所有信息
    @RequestMapping(url+"/update/user_from_admin")
    public String updateUserFromAdmin(Integer number,String onlineKey,
                             Integer user_number,String name,
                             String password,String department,
                             Integer available,String profile_photo){
        if (number==null||onlineKey==null||user_number==null||name==null||password==null
        ||department==null||available==null||profile_photo==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        if (accountModel.updateUserFromAdmin(user_number, name, password, department, available, profile_photo))
            return ReturnJson.UPDATE_USER_ALL_SUCCESS;
        return ReturnJson.UPDATE_USER_ALL_FAIL;
    }
    //注册用户账号,通过管理员注册,免去验证码
    @RequestMapping(url+"/register/user_from_admin")
    public String registerUserFromAdmin(Integer number,String onlineKey,
                                        String name,String password,String department){
        if (number==null||onlineKey==null)return ReturnJson.ERROR_MISSING_PARAMETER;
        if (accountModel.isOnline("admin",number,onlineKey)==false)return ReturnJson.ONLINE_NOT_ONLINE;
        return accountModel.register("user",name, password, department);
    }

}
