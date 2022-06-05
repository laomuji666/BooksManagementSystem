package com.lmj.bms.model.account;

import com.lmj.bms.entity.Account_Admin;
import com.lmj.bms.entity.Account_User;
import com.lmj.bms.entity.ReturnJson;
import com.lmj.bms.mapper.AccountMapper;
import com.lmj.bms.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import java.util.*;

@Component
public class AccountModel {

    //登陆有效持续时间毫秒
    //若登陆后超过该时间,需要重新登陆
    //1000*60*60*24*7表示七天
    private static final long DURATION_TIME=1000*60*60;

    //定时循环检查时间,毫秒
    private static final int SCHEDULED_TIME=1000*5;

    @Autowired
    private AccountMapper accountMapper;

    //用于随机生成OnlineKey
    private Random random=new Random();

    //管理员账号在线管理器,admin_number映射
    private Map<Integer,Account_Admin>adminMap=new HashMap<>();

    //普通用户在线管理器,user_number映射
    private Map<Integer,Account_User>userMap=new HashMap<>();

    //登陆账号
    public String login(String type,int number,String password) {
        if (type.equals("admin")){
            Account_Admin account_admin = accountMapper.SelectAdmin(number, password);
            if (account_admin==null)return ReturnJson.LOGIN_FAIL_NOT_FOUNT;
            if (account_admin.getAvailable()!=1)return ReturnJson.LOGIN_FAIL_NOT_AVAILABLE;
            //生成一个随机的OnlineKey,用于验证登陆状态
            account_admin.setOnlineKey(DigestUtils.md5DigestAsHex(String.valueOf(random.nextInt()).getBytes()));
            //设置登陆的时间
            account_admin.setOnlineTime(System.currentTimeMillis());
            //因为是hashmap,所以可以直接put,不需要判断,直接替换了
            adminMap.put(account_admin.getAdmin_number(),account_admin);
            LogUtil.logTimeAndString("管理员登陆:账号:"+account_admin.getAdmin_number()+" 名称:"+account_admin.getName());
            return ReturnJson.LOGIN_SUCCESS.replace("{OnlineKey}",account_admin.getOnlineKey());
        }
        if (type.equals("user")) {
            Account_User account_user = accountMapper.SelectUser(number, password);
            if (account_user==null)return ReturnJson.LOGIN_FAIL_NOT_FOUNT;
            if (account_user.getAvailable()!=1)return ReturnJson.LOGIN_FAIL_NOT_AVAILABLE;
            //生成一个随机的OnlineKey,用于验证登陆状态
            account_user.setOnlineKey(DigestUtils.md5DigestAsHex(String.valueOf(random.nextInt()).getBytes()));
            //设置登陆的时间
            account_user.setOnlineTime(System.currentTimeMillis());
            //因为是hashmap,所以可以直接put,不需要判断,直接替换了
            userMap.put(account_user.getUser_number(),account_user);
            LogUtil.logTimeAndString("普通用户登陆:账号:"+account_user.getUser_number()+" 名称:"+account_user.getName());
            return ReturnJson.LOGIN_SUCCESS.replace("{OnlineKey}",account_user.getOnlineKey());
        }
        return ReturnJson.TYPE_FAIL;
    }

    //注册账号
    public String register(String type,String name,String password,String department) {
        if (type.equals("admin")){
            if (accountMapper.InsertAdmin(name,password)==1){
                String admin_number=String.valueOf(accountMapper.SelectMaxAdminNumber());
                return ReturnJson.REGISTER_SUCCESS_ADMIN.replace("{number}",admin_number);
            }
        }
        if (type.equals("user")){
            if (accountMapper.InsertUser(name,password,department)==1){
                String user_number=String.valueOf(accountMapper.SelectMaxUserNumber());
                return ReturnJson.REGISTER_SUCCESS_USER.replace("{number}",user_number);
            }
        }
        return ReturnJson.TYPE_FAIL;
    }

    //定时清理账户登陆状态
    @Scheduled(fixedRate = SCHEDULED_TIME)
    private void cleanAccountLoginState() {
        //当前时间减去登陆有效持续时间,为最低的有效时间
        long validTime=System.currentTimeMillis()-DURATION_TIME;

        Set<Integer> keySet = adminMap.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()){
            Integer key =iterator.next();
            if (adminMap.get(key).getOnlineTime()<validTime) {
                LogUtil.logTimeAndString("管理员离线:账号:"+adminMap.get(key).getAdmin_number()+" 名称:"+adminMap.get(key).getName());
                iterator.remove();
            }
        }
        keySet = userMap.keySet();
        iterator = keySet.iterator();
        while (iterator.hasNext()){
            Integer key =iterator.next();
            if (userMap.get(key).getOnlineTime()<validTime) {
                LogUtil.logTimeAndString("普通用户离线:账号:"+userMap.get(key).getUser_number()+" 名称:"+userMap.get(key).getName());
                iterator.remove();
            }
        }
    }

    //获取个人信息
    public String getInfo(String type,Integer number,String onlineKey){
        if (type.equals("admin") && adminMap.get(number)!=null){
            if(adminMap.get(number).getOnlineKey().equals(onlineKey)) {
                return adminMap.get(number).toString();
            }
            return ReturnJson.ONLINE_KEY_FAIL;
        }
        if (type.equals("user") && userMap.get(number)!=null){
            if(userMap.get(number).getOnlineKey().equals(onlineKey)) {
                return userMap.get(number).toString();
            }
            return ReturnJson.ONLINE_KEY_FAIL;
        }
        return ReturnJson.TYPE_FAIL;
    }

    //修改个人信息
    public String updateInfo(String type,Integer number,String onlineKey,String name,String password,String profile_photo){
        if (type.equals("admin") && adminMap.get(number)!=null){
            if(adminMap.get(number).getOnlineKey().equals(onlineKey)) {
                if (name==null)name=adminMap.get(number).getName();
                else adminMap.get(number).setName(name);

                if (password==null)password = adminMap.get(number).getPassword();
                else adminMap.get(number).setPassword(password);

                if (profile_photo==null)profile_photo = adminMap.get(number).getProfile_photo();
                else adminMap.get(number).setProfile_photo(profile_photo);

                accountMapper.UpdateAdmin(number,name,password,profile_photo);
                return ReturnJson.UPDATE_SUCCESS;
            }
            return ReturnJson.ONLINE_KEY_FAIL;
        }
        if (type.equals("user") && userMap.get(number)!=null){
            if(userMap.get(number).getOnlineKey().equals(onlineKey)) {
                if (name==null)name = userMap.get(number).getName();
                else userMap.get(number).setName(name);

                if (password==null)password = userMap.get(number).getPassword();
                else userMap.get(number).setPassword(password);

                if (profile_photo==null)profile_photo = userMap.get(number).getProfile_photo();
                else userMap.get(number).setProfile_photo(profile_photo);

                accountMapper.UpdateUser(number,name,password,profile_photo);
                return ReturnJson.UPDATE_SUCCESS;
            }
            return ReturnJson.ONLINE_KEY_FAIL;
        }
        return ReturnJson.TYPE_FAIL;
    }

    //检查是否在线
    public boolean isOnline(String type,Integer number,String onlineKey){
        if (type.equals("admin")){
            if (adminMap.get(number)!=null && adminMap.get(number).getOnlineKey().equals(onlineKey))
                return true;
        }
        if (type.equals("user")){
            if (userMap.get(number)!=null && userMap.get(number).getOnlineKey().equals(onlineKey))
                return true;
        }
        return false;
    }

    //获取所有用户信息
    public Account_User[] getAllUser(){
        return accountMapper.SelectAllUser();
    }

    //修改用户信息
    public boolean updateUserFromAdmin(Integer user_number,String name,
                                 String password,String department,
                                 Integer available,String profile_photo){
        try {
            int i=accountMapper.UpdateUserFromAdmin(user_number, name, password, department, available, profile_photo);
            if (i==1)return true;
            else return false;
        }catch (Exception e){
            return false;
        }
    }

}
