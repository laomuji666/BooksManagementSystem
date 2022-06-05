drop database if exists bms_lmj;
create database bms_lmj;
use bms_lmj;

create table account_admin(
	admin_number int primary key auto_increment,
	name varchar(100) not null,
	password varchar(100) not null,
	available int default'1',
	profile_photo varchar(1000) default'admin.jpg'
)AUTO_INCREMENT = 10000;

create table account_user(
	user_number int primary key auto_increment,
	name varchar(100) not null,
	password varchar(100) not null,
    department varchar(100) not null,
	available int default 1,
	profile_photo varchar(1000) default 'user.jpg'
)AUTO_INCREMENT = 20210000;


create table book(
	book_id int primary key auto_increment,
    title varchar(200) not null,
	author varchar(200) not null,
	type varchar(200) default '',
    source_num int not null,
	foreign key (source_num) references account_admin(admin_number),
    available int default 1,
    reason varchar(1000),
	add_time datetime default now()
);

create table book_borrow(
	borrow_id int primary key auto_increment,
    borrow_book int not null,
    foreign key (borrow_book) references book(book_id),
	borrow_admin int not null,
    foreign key (borrow_admin) references account_admin(admin_number),
    borrow_user int not null,
    foreign key (borrow_user) references account_user (user_number),
    borrow_time datetime default now(),
    return_time datetime,
    available int default 1
)AUTO_INCREMENT = 1000000;

-- 管理员注册
insert into account_admin(name,password) values('laomuji666','laomuji'); 
insert into account_admin(name,password) values('dasfas234','123445a'); 
insert into account_admin(name,password) values('adfsgrds12','231453'); 
insert into account_admin(name,password) values('laomuji123','lmjlmj');
insert into account_admin(name,password) values('adsfdfs','41235fads'); 
insert into account_admin(name,password) values('grswbdfgs','1234 123dsa'); 
insert into account_admin(name,password) values('1235etwdsa','dgsag123'); 
insert into account_admin(name,password) values('234q23rtfads','123523'); 
insert into account_admin(name,password) values('dfgsgdf','gd2124sg'); 
insert into account_admin(name,password) values('12354fDZ','afsfadsvfa'); 
insert into account_admin(name,password) values('asdfsafgv','sdafg'); 

-- 管理员注销,直接将available设置为0,不删除数据,登陆时对available判断
update account_admin set available=0 where admin_number=10001;

-- 管理员登陆,有数据就是登陆成功,没有数据就是登陆失败
-- 根据available的值 返回不同的信息,如账号不存在,账号已注销,密码错误等
select * from account_admin where admin_number = 10000 and password = 'lmjlmj';

-- 普通用户注册
insert into account_user(name,password,department) values('qweaqaqa','sdfgg1','艺术-2101'); 
insert into account_user(name,password,department) values('eraw2qaqarqa','gfds','美术-2102');
insert into account_user(name,password,department) values('asdcc2','sdfgg1','汉语-2103'); 
insert into account_user(name,password,department) values('teqwt','qwt','计算机-2102');
insert into account_user(name,password,department) values('爱学习的老母鸡','lmjnb','网络-2102');
insert into account_user(name,password,department) values('qwetrew','eqwtfcx','艺术-2103'); 
insert into account_user(name,password,department) values('qtwerwqt','eqwt','计算机-2102');
insert into account_user(name,password,department) values('asdcqewtewc2','qetewt','艺术-2103'); 
insert into account_user(name,password,department) values('qewtewtwetew','eqwtewtewqtew','计算机-2102');
insert into account_user(name,password,department) values('eqwtw4tweew','sdfweqteqwteqwtgg1','艺术-2103'); 
insert into account_user(name,password,department) values('eqwteqwtew','eqwteqwtqew','计算机-2102');
insert into account_user(name,password,department) values('eqwtewt','sdfqweteqwgg1','艺术-2103'); 



-- 管理员修改个人信息
update account_admin set password = 'lmj123',profile_photo='adm.png' where admin_number = 10000;

-- 普通用户修改个人信息
update account_user set password = 'abcd123',profile_photo='usr.png' where user_number = 10001;

-- 录入书籍
insert into book(title,author,source_num,type) values('网络规划与设计','尤国华',10000,'网络');
insert into book(title,author,source_num,type) values('网络系统集成与综合布线','刘天华',10001,'网络');
insert into book(title,author,source_num,type) values('Linux操作系统(微课版)','杨云',10000,'操作系统');
insert into book(title,author,source_num,type) values('路由与交换技术','刘丹宁',10000,'网络');
insert into book(title,author,source_num,type) values('网络技术实训','张曼',10000,'网络');
insert into book(title,author,source_num,type) values('现代密码学','杨波',10000,'其它');
insert into book(title,author,source_num,type) values('数据库系统原理及应用','胡致杰',10000,'数据库');
insert into book(title,author,source_num,type) values('路由与交换技术','赵新胜',10000,'网络');
insert into book(title,author,source_num,type) values('安卓入门到入土','老母鸡',10001,'安卓开发');
insert into book(title,author,source_num,type) values('C++从入门到放弃','老母鸡',10001,'编程');
insert into book(title,author,source_num,type) values('pyton入门到入门','老母鸡',10001,'编程');
insert into book(title,author,source_num,type) values('go语言网络开发','老母鸡',10001,'网络');
insert into book(title,author,source_num,type) values('kotlin巨多的语法','老母鸡',10001,'编程');
insert into book(title,author,source_num,type) values('Visual C++ 2010开发权威指南','尹成',10001,'编程');
insert into book(title,author,source_num,type) values('Visual C++ 2012开发权威指南','尹成',10001,'编程');
insert into book(title,author,source_num,type) values('安卓调库技巧','老母鸡',10001,'安卓开发');

-- 查看所有可借书籍
select *from book where available=1;

-- 借书
update book set available = 2 where book_id = 9;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(9,10000,20210004);
update book set available = 2 where book_id = 10;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(10,10003,20210004);
update book set available = 2 where book_id = 1;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(1,10000,20210001);
update book set available = 2 where book_id = 3;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(3,10001,20210001);
update book set available = 2 where book_id = 5;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(5,10000,20210002);
update book set available = 2 where book_id = 8;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(8,10002,20210004);
update book set available = 2 where book_id = 12;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(12,10000,20210004);
update book set available = 2 where book_id = 6;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(6,10001,20210003);
update book set available = 2 where book_id = 7;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(7,10000,20210004);
update book set available = 2 where book_id = 4;
insert into book_borrow(borrow_book,borrow_admin,borrow_user) values(4,10001,20210002);

-- 还书
update book set available = 1 where book_id = 9;
update book_borrow set available = 0,return_time=now() where borrow_id =1000000;


-- 管理员自行处理破损遗失书籍
update book set available = 0 where book_id = 10;
update book set reason = '管理员检查时发现,借阅该书的同学长时间未归还书籍,做遗失处理.' where book_id = 10;
update book_borrow set available = 3 where borrow_id =1000001;

-- 用户联系管理员处理遗失书籍
update book set available = 0 where book_id = 3;
update book set reason = '某同学遗失书籍,进行了赔偿.' where book_id = 3;
update book_borrow set available = 2,return_time=now() where borrow_id =1000003;

-- 查看某位同学的所有借阅记录
select book.book_id,book.title,book.author,br.* from book,(select * from book_borrow where borrow_user = 20210004) as br where book.book_id=br.borrow_book order by borrow_id desc;

-- 查看某位同学正在借阅的书籍信息,in(1,3)因为1是正在借阅3是损坏未处理,都表示未还书,属于借阅状态
select book.*,br.* from book,(select * from book_borrow where (available in (1,3) )and borrow_user = 20210001) as br where book.book_id=br.borrow_book;


-- 为图书增加扫描条形码功能 
ALTER TABLE book add column bar_code varchar(50) default '';
-- 测试条码
-- 原始数据需要删掉,因为没有条码 无法判断书籍数量 
delete from book_borrow where borrow_id >0;
delete from book where book_id >0;
-- 录入书籍测试
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','刘丹宁',10000,'9787115456502','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','刘丹宁',10000,'9787115456502','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','刘丹宁',10000,'9787115456502','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('路由与交换技术','陈美娟(华为)',10000,'9787115474742','网络');
insert into book(title,author,source_num,bar_code,type) values('数据库系统原理及应用','胡致杰',10000,'9787302506539','数据库');
insert into book(title,author,source_num,bar_code,type) values('数据库系统原理及应用','胡致杰',10000,'9787302506539','数据库');
insert into book(title,author,source_num,bar_code,type) values('数据库系统原理及应用','胡致杰',10000,'9787302506539','数据库');
insert into book(title,author,source_num,bar_code,type) values('数据库系统原理及应用','胡致杰',10000,'9787302506539','数据库');
insert into book(title,author,source_num,bar_code,type) values('数据库系统原理及应用','胡致杰',10000,'9787302506539','数据库');



-- 查询所有书籍信息,返回的是第一本书籍的book_id
select *,count(bar_code)as'book_count' from book group by bar_code ;

-- 查询所有可用书籍 ,返回的是第一本书籍的book_id
select *,count(bar_code)as'book_count' from book  where book.available=1 group by bar_code;