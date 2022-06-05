package com.lmj.bms.mapper;

import com.lmj.bms.entity.Book;
import com.lmj.bms.entity.Book_Borrow;
import com.lmj.bms.entity.Book_Borrow_My;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookMapper {
    //获得所有书籍信息
    @Select("select *from book ;")
    Book[] SelectAllBook();
    //更新书籍信息
    @Update("update book set " +
            "title = #{title}," +
            "author = #{author}," +
            "type = #{type}," +
            "source_num = #{source_num}," +
            "available = #{available}," +
            "reason = #{reason}," +
            "add_time = #{add_time}," +
            "bar_code = #{bar_code} " +
            "where book_id = #{book_id}")
    int UpdateBook(@Param("book_id")Integer book_id,@Param("title")String title,
                   @Param("author")String author,@Param("type")String type ,
                   @Param("source_num")Integer source_num,@Param("available")Integer available,
                   @Param("reason")String reason,@Param("add_time")String add_time,
                   @Param("bar_code")String bar_code);
    //获取所有可被借阅的书籍
    //@Select("select *from book where available=1;")
    @Select("select *,count(bar_code)as 'book_count' from book  where book.available=1 group by bar_code;")
    Book[] SelectAllAvailableBook();

    //录入书籍
    @Insert("insert into book(title,author,type,source_num,bar_code) values(#{title},#{author},#{type},#{source_num},#{bar_code});")
    int InsertBook(@Param("title")String title,@Param("author")String author,
                   @Param("type")String type, @Param("source_num") int source_num,
                   @Param("bar_code")String bar_code);

    //获取所有借阅信息
    @Select("select *from book_borrow;")
    Book_Borrow[] SelectAllBorrow();

    //获取我的所有借阅信息
    @Select("select book.book_id,book.title,book.author,br.* from book,(select * from book_borrow where" +
            " borrow_user = #{borrow_user}) as br where " +
            "book.book_id=br.borrow_book " +
            "order by borrow_id desc;")
    Book_Borrow_My[] SelectMyBorrow(@Param("borrow_user")Integer borrow_user);

    //更新借阅信息
    @Update("update book_borrow set " +
            "borrow_book = #{borrow_book}," +
            "borrow_admin = #{borrow_admin}," +
            "borrow_user = #{borrow_user}," +
            "borrow_time = #{borrow_time}," +
            "return_time = #{return_time}," +
            "available = #{available} " +
            "where borrow_id = #{borrow_id};")
    int UpdateBorrow(@Param("borrow_id")Integer borrow_id,@Param("borrow_book")Integer borrow_book,
                   @Param("borrow_admin")Integer borrow_admin,@Param("borrow_user")Integer borrow_user ,
                   @Param("borrow_time")String borrow_time,@Param("return_time")String return_time,
                   @Param("available")Integer available);

    //借书步骤1借书
    @Update("update book set available = 2 where book_id = #{book_id} and available=1;")
    int BorrowBookUpdate(@Param("book_id") int book_id);
    //借书步骤2将借阅信息保存
    //insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(3,10000,20210001);
    @Insert("insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(#{borrow_book},#{borrow_admin},#{borrow_user});")
    int BorrowBookInsert(@Param("borrow_book") int borrow_book,@Param("borrow_admin") int borrow_admin,@Param("borrow_user") int borrow_user);

    //还书步骤1还书
    @Update("update book set available = 1 where book_id = #{book_id};")
    int ReturnBookUpdateBook(@Param("book_id") int book_id);
    //还书步骤2更新借阅信息
    @Update("update book_borrow set available = 0,return_time=now() where borrow_id = #{borrow_id};")
    int ReturnBookUpdateBorrow(@Param("borrow_id") int borrow_id);

}
