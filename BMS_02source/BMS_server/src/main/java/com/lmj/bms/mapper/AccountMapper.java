package com.lmj.bms.mapper;


import com.lmj.bms.entity.Account_Admin;
import com.lmj.bms.entity.Account_User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface AccountMapper {

    //增加一个管理员
    @Insert("insert into account_admin(name,password) values(#{name},#{password});")
    int InsertAdmin(@Param("name")String name,@Param("password") String password);
    //获得最新的账号
    @Select("select max(admin_number) from account_admin")
    int SelectMaxAdminNumber();

    //增加一个普通用户
    @Insert("insert into account_user(name,password,department) values(#{name},#{password},#{department});")
    int InsertUser(@Param("name")String name,@Param("password") String password,@Param("department") String department);
    //获得最新的账号
    @Select("select max(user_number) from account_user")
    int SelectMaxUserNumber();

    //查找管理员账号密码
    @Select("select *from account_admin where " +
            "admin_number = #{admin_number} and " +
            "password = #{password} and available = 1;")
    Account_Admin SelectAdmin(@Param("admin_number") int admin_number, @Param("password") String password);

    //查找普通用户账号密码
    @Select("select *from account_user where " +
            "user_number = #{user_number} and " +
            "password = #{password} and available = 1;")
    Account_User SelectUser(@Param("user_number") int account_user, @Param("password") String password);

    //更新用户信息
    @Update("update account_user set " +
            "name = #{name}," +
            "password = #{password}," +
            "department = #{department}," +
            "available = #{available}," +
            "profile_photo = #{profile_photo} "+
            "where user_number = #{user_number};")
    int UpdateUserFromAdmin(@Param("user_number")Integer user_number,@Param("name")String name,
                      @Param("password")String password,@Param("department")String department,
                      @Param("available")Integer available,@Param("profile_photo")String profile_photo);



    //修改管理员信息
    @Update("update account_admin set " +
            "name = #{name},"+
            "password = #{password} ," +
            "profile_photo = #{profile_photo} " +
            "where admin_number = #{admin_number};")
    int UpdateAdmin(@Param("admin_number")int admin_number,@Param("name")String name,@Param("password")String password,@Param("profile_photo")String profile_photo);

    //修改普通用户信息
    @Update("update account_user set " +
            "name = #{name},"+
            "password = #{password} ," +
            "profile_photo = #{profile_photo} " +
            "where user_number =#{user_number};")
    int UpdateUser(@Param("user_number")int user_number,@Param("name")String name,@Param("password")String password,@Param("profile_photo")String profile_photo);

    //获取所有管理员账号信息,仅用于调试
    @Select("select *from account_admin;")
    Account_Admin[] SelectAllAdmin();

    //获取所有普通用户账号信息,仅用于调试
    @Select("select *from account_user;")
    Account_User[] SelectAllUser();

}
