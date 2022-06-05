#include "adminwindow.h"
#include "ui_adminwindow.h"
#include"QSettings"
#include<QMessageBox>
#include<QFileDialog>
#include<QJsonArray>
#include<QJsonDocument>
#include<qDebug>
AdminWindow::AdminWindow(QString number,QString onlineKey,QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::AdminWindow)
{
    this->number=number;
    this->onlineKey=onlineKey;
    ui->setupUi(this);
    this->setCentralWidget(ui->tabWidget);
    getInfo();
    getAllBook();
    getAllBorrow();
    getAllUser();
}

AdminWindow::~AdminWindow()
{
    delete ui;
}


void AdminWindow::getInfo()
{
    if(getInfoManager==nullptr){
        QString data="type=admin&number="+number+"&onlineKey="+onlineKey;
        getInfoManager=HttpRequest::postRequest("/account/info",data,this,SLOT(getInfoSlot(QNetworkReply*)));
    }
}

void AdminWindow::getInfoSlot(QNetworkReply*reply)
{
    QByteArray bytes=reply->readAll();
    admin_number=HttpRequest::getJsonValue(bytes,"admin_number");
    name=HttpRequest::getJsonValue(bytes,"name");
    profile_photo=HttpRequest::getJsonValue(bytes,"profile_photo");
    reply->deleteLater();
    getInfoManager->deleteLater();
    getInfoManager=nullptr;
    if(number==admin_number)
    {
        ui->label_info->setText("姓名:"+name+"\r\n\r\n"+"账号:"+admin_number);
        get_profile_photo();
    }
    else
    {
        QMessageBox::information(this,"提示","登陆已过期,请重新登陆");
        on_pushButton_logout_clicked();
    }
}

void AdminWindow::on_pushButton_logout_clicked()
{
    QSettings settings(LoginWindow::configPath(),QSettings::IniFormat);
    settings.setValue("auto_login",0);
    this->close();
    LoginWindow *login=new LoginWindow();
    login->show();
}


void AdminWindow::get_profile_photo()
{
    //若上一个请求未结束,则不做处理
    if(get_profile_photoManager==nullptr)
        get_profile_photoManager=HttpRequest::getRequest("/account/profile_photo/get?profile_photo="+profile_photo,this,SLOT(get_profile_photoSlot(QNetworkReply*)));
}
void AdminWindow::get_profile_photoSlot(QNetworkReply*reply)
{
    //将数据读取到图片中
    profile_photo_picture.loadFromData(reply->readAll());
    profile_photo_picture.scaled(ui->label_profile_photo->size(), Qt::KeepAspectRatio);
    ui->label_profile_photo->setScaledContents(true);
    ui->label_profile_photo->setPixmap(profile_photo_picture);
    //释放内存,防止内存溢出
    reply->deleteLater();
    get_profile_photoManager->deleteLater();
    get_profile_photoManager=nullptr;

}


void AdminWindow::on_pushButton_name_clicked()
{
    updateInfo(ui->lineEdit_name->text(),"");
}

void AdminWindow::updateInfo(QString name, QString password)
{
    if(updateInfoManager==nullptr){
        QString data="type=admin&number="+number+"&onlineKey="+onlineKey;
        if(name.isEmpty()==false)data+="&name="+name;
        if(password.isEmpty()==false)data+="&password="+password;

        updateInfoManager=HttpRequest::postRequest("/account/update",data,this,SLOT(updateInfoSlot(QNetworkReply*)));
    }
}

void AdminWindow::updateInfoSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    reply->deleteLater();
    updateInfoManager->deleteLater();
    updateInfoManager=nullptr;
    if(HttpRequest::getJsonValue(bytes,"code")=="7")
    {
        QMessageBox::information(this,"修改成功",HttpRequest::getJsonValue(bytes,"msg"));
        getInfo();
    }else
    {
        QMessageBox::information(this,"修改失败",HttpRequest::getJsonValue(bytes,"msg"));
    }
}
void AdminWindow::update_profile_photo()
{
    if(update_profile_photoManager==nullptr){
        QFile file(ui->lineEdit_profile_photo->text());
        file.open(QIODevice::ReadOnly);
        QByteArray file_data = file.readAll();
        file.close();
        QString data = "type=admin&number="+number+"&onlineKey="+onlineKey;
        data+="&profile_photo=\""+file_data.toBase64()+"\"";

        update_profile_photoManager=HttpRequest::postRequest("/account/update",data,this,SLOT(update_profile_photoSlot(QNetworkReply*)));
    }
}

void AdminWindow::update_profile_photoSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    QString msg=HttpRequest::getJsonValue(bytes,"msg");
    QMessageBox::information(this,"提示",msg);
    reply->deleteLater();
    update_profile_photoManager->deleteLater();
    update_profile_photoManager=nullptr;
    getInfo();
}

void AdminWindow::getAllBook()
{
    if(getAllBookManager==nullptr){
        getAllBookManager=HttpRequest::getRequest("/book/all",this,SLOT(getAllBookSlot(QNetworkReply*)));
    }
}

void AdminWindow::getAllBookSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    QJsonArray array=QJsonDocument::fromJson(bytes).array();
    ui->tableWidget_book->clearContents();
    ui->tableWidget_book->setRowCount(array.size()+1);
    for (int i=0;i<array.size();i++ )
    {
        QJsonObject obj = array[i].toObject();
        QString book_id=QString::number(obj.value("book_id").toInt());
        QString title=obj.value("title").toString();
        QString author=obj.value("author").toString();
        QString type=obj.value("type").toString();
        QString source_num=QString::number(obj.value("source_num").toInt());
        QString available=QString::number(obj.value("available").toInt());
        QString reason=obj.value("reason").toString();
        QString add_time=obj.value("add_time").toString();
        QString bar_code=obj.value("bar_code").toString();
        ui->tableWidget_book->setItem(i,0,new QTableWidgetItem(book_id));
        ui->tableWidget_book->setItem(i,1,new QTableWidgetItem(title));
        ui->tableWidget_book->setItem(i,2,new QTableWidgetItem(author));
        ui->tableWidget_book->setItem(i,3,new QTableWidgetItem(type));
        ui->tableWidget_book->setItem(i,4,new QTableWidgetItem(source_num));
        ui->tableWidget_book->setItem(i,5,new QTableWidgetItem(available));
        ui->tableWidget_book->setItem(i,6,new QTableWidgetItem(reason));
        ui->tableWidget_book->setItem(i,7,new QTableWidgetItem(add_time));
        ui->tableWidget_book->setItem(i,8,new QTableWidgetItem(bar_code));
    }
    reply->deleteLater();
    getAllBookManager->deleteLater();
    getAllBookManager=nullptr;
}
void AdminWindow::getAllBorrow()
{
    if(getAllBorrowManager==nullptr){
        QString data="number="+number+"&onlineKey="+onlineKey;
        getAllBorrowManager=HttpRequest::postRequest("/book/borrow/all",data,this,SLOT(getAllBorrowSlot(QNetworkReply*)));
    }
}

void AdminWindow::getAllBorrowSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();

    QJsonArray array=QJsonDocument::fromJson(bytes).array();
    ui->tableWidget_borrow->clearContents();
    ui->tableWidget_borrow->setRowCount(array.size()+1);

    for (int i=0;i<array.size();i++ )
    {
        QJsonObject obj = array[i].toObject();

        QString borrow_id=QString::number(obj.value("borrow_id").toInt());
        QString borrow_book=QString::number(obj.value("borrow_book").toInt());
        QString borrow_admin=QString::number(obj.value("borrow_admin").toInt());
        QString borrow_user=QString::number(obj.value("borrow_user").toInt());
        QString borrow_time=obj.value("borrow_time").toString();
        QString return_time=obj.value("return_time").toString();
        QString available=QString::number(obj.value("available").toInt());
        ui->tableWidget_borrow->setItem(i,0,new QTableWidgetItem(borrow_id));
        ui->tableWidget_borrow->setItem(i,1,new QTableWidgetItem(borrow_book));
        ui->tableWidget_borrow->setItem(i,2,new QTableWidgetItem(borrow_admin));
        ui->tableWidget_borrow->setItem(i,3,new QTableWidgetItem(borrow_user));
        ui->tableWidget_borrow->setItem(i,4,new QTableWidgetItem(borrow_time));
        ui->tableWidget_borrow->setItem(i,5,new QTableWidgetItem(return_time));
        ui->tableWidget_borrow->setItem(i,6,new QTableWidgetItem(available));
    }

    reply->deleteLater();
    getAllBorrowManager->deleteLater();
    getAllBorrowManager=nullptr;
}

void AdminWindow::getAllUser()
{
    if(getAllUserManager==nullptr){
        QString data="number="+number+"&onlineKey="+onlineKey;
        getAllUserManager=HttpRequest::postRequest("/account/all/user",data,this,SLOT(getAllUserSlot(QNetworkReply*)));
    }
}

void AdminWindow::getAllUserSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    QJsonArray array=QJsonDocument::fromJson(bytes).array();
    ui->tableWidget_user->clearContents();
    ui->tableWidget_user->setRowCount(array.size()+1);

    for (int i=0;i<array.size();i++ )
    {
        QJsonObject obj = array[i].toObject();

        QString user_number=QString::number(obj.value("user_number").toInt());
        QString name=obj.value("name").toString();
        QString password=obj.value("password").toString();
        QString department=obj.value("department").toString();
        QString available=QString::number(obj.value("available").toInt());
        QString profile_photo=obj.value("profile_photo").toString();
        ui->tableWidget_user->setItem(i,0,new QTableWidgetItem(user_number));
        ui->tableWidget_user->setItem(i,1,new QTableWidgetItem(name));
        ui->tableWidget_user->setItem(i,2,new QTableWidgetItem(password));
        ui->tableWidget_user->setItem(i,3,new QTableWidgetItem(department));
        ui->tableWidget_user->setItem(i,4,new QTableWidgetItem(available));
        ui->tableWidget_user->setItem(i,5,new QTableWidgetItem(profile_photo));
    }

    reply->deleteLater();
    getAllUserManager->deleteLater();
    getAllUserManager=nullptr;
}


void AdminWindow::updateBook(QString data)
{
    if(updateBookManager==nullptr){
        updateBookManager=HttpRequest::postRequest("/book/update",data,this,SLOT(updateBookSlot(QNetworkReply*)));
    }
}

void AdminWindow::updateBookSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    reply->deleteLater();
    updateBookManager->deleteLater();
    updateBookManager=nullptr;
    getAllBook();
}

void AdminWindow::updateBorrow(QString data)
{
    if(updateBorrowManager==nullptr){
        updateBorrowManager=HttpRequest::postRequest("/book/borrow/update",data,this,SLOT(updateBorrowSlot(QNetworkReply*)));
    }
}

void AdminWindow::updateBorrowSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    reply->deleteLater();
    updateBorrowManager->deleteLater();
    updateBorrowManager=nullptr;
    getAllBorrow();
}
void AdminWindow::updateUser(QString data)
{
    if(updateUserManager==nullptr){
        updateUserManager=HttpRequest::postRequest("/account/update/user_from_admin",data,this,SLOT(updateUserSlot(QNetworkReply*)));
    }
}

void AdminWindow::updateUserSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    reply->deleteLater();
    updateUserManager->deleteLater();
    updateUserManager=nullptr;
    getAllUser();
}
void AdminWindow::insertBook()
{
    if(insertBookManager==nullptr){
        QString data="number="+number+"&onlineKey="+onlineKey+"&title=newbook&author=newbook&bar_code=0";
        insertBookManager=HttpRequest::postRequest("/book/add",data,this,SLOT(insertBookSlot(QNetworkReply*)));
    }
}

void AdminWindow::insertBookSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    reply->deleteLater();
    insertBookManager->deleteLater();
    insertBookManager=nullptr;
    getAllBook();
}

void AdminWindow::insertBorrow(QString book_id,QString borrow_user)
{
    if(insertBorrowManager==nullptr){
        QString data="number="+number+"&onlineKey="+onlineKey+"&book_id="+book_id+"&borrow_user="+borrow_user;
        insertBorrowManager=HttpRequest::postRequest("/book/borrow/add",data,this,SLOT(insertBorrowSlot(QNetworkReply*)));
    }
}

void AdminWindow::insertBorrowSlot(QNetworkReply*reply)
{
    QByteArray bytes = reply->readAll();
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    reply->deleteLater();
    insertBorrowManager->deleteLater();
    insertBorrowManager=nullptr;
    getAllBorrow();
}

void AdminWindow::insertUser()
{
    //若上一个请求未结束,则不做处理
    if(insertUserManager==nullptr)
    {
        QString data="number="+number+"&onlineKey="+onlineKey+"&name=new_user&password=new_user&department=new_user";

        insertUserManager=HttpRequest::postRequest("/account/register/user_from_admin",data,this,SLOT(insertUserSlot(QNetworkReply*)));
    }
}
void AdminWindow::insertUserSlot(QNetworkReply*reply)
{
    QByteArray bytes=reply->readAll();
    QString rcode=HttpRequest::getJsonValue(bytes,"code");//8表示注册成功
    QString rmsg=HttpRequest::getJsonValue(bytes,"msg");
    ui->statusBar->showMessage(HttpRequest::getJsonValue(bytes,"msg"),100000);
    //释放内存,防止内存溢出
    reply->deleteLater();
    insertUserManager->deleteLater();
    insertUserManager=nullptr;
    getAllUser();
}

void AdminWindow::on_pushButton_password_clicked()
{
    updateInfo("",ui->lineEdit_password->text());
}


void AdminWindow::on_pushButton_open_file_clicked()
{
    QString filename = QFileDialog::getOpenFileName(this,tr("选择图片"),"",tr("图片文件(*jpg *png);"));
    ui->lineEdit_profile_photo->setText(filename);
}


void AdminWindow::on_pushButton_profile_photo_clicked()
{
    update_profile_photo();
}


void AdminWindow::on_tabWidget_currentChanged(int index)
{
    if(index==0)getInfo();
    if(index==1)getAllBook();
    if(index==2)getAllBorrow();
    if(index==3)getAllUser();
}


void AdminWindow::on_tableWidget_book_itemChanged(QTableWidgetItem *item)
{
    if(getAllBookManager!=nullptr)return;
    int row = item->row();

    if(row==ui->tableWidget_book->rowCount()-1)
    {
        if(item->column()!=0)
        {
            QMessageBox::information(this,"提示","插入书籍仅允许填写book_id");
            getAllBook();
            return;
        }
        insertBook();
    }
    else
    {
        if(item->column()==0)
        {
            QMessageBox::information(this,"提示","book_id不允许修改");
            getAllBook();
            return;
        }
        QString book_id=ui->tableWidget_book->item(row,0)->text();
        QString title=ui->tableWidget_book->item(row,1)->text();
        QString author=ui->tableWidget_book->item(row,2)->text();
        QString type=ui->tableWidget_book->item(row,3)->text();
        QString source_num=ui->tableWidget_book->item(row,4)->text();
        QString available=ui->tableWidget_book->item(row,5)->text();
        QString reason=ui->tableWidget_book->item(row,6)->text();
        QString add_time=ui->tableWidget_book->item(row,7)->text();
        QString bar_code=ui->tableWidget_book->item(row,8)->text();
        QString data="number="+admin_number+"&onlineKey="+onlineKey+
                "&book_id="+book_id+"&title="+title+
                "&author="+author+"&type="+type+
                "&source_num="+source_num+"&available="+available+
                "&bar_code="+bar_code+
                "&reason="+reason+"&add_time="+add_time;
        updateBook(data);
    }
}


void AdminWindow::on_tableWidget_borrow_itemChanged(QTableWidgetItem *item)
{
    if(getAllBorrowManager!=nullptr)return;
    int row = item->row();
    if(row==ui->tableWidget_borrow->rowCount()-1)
    {
        if(item->column()==0)
        {
            if(ui->tableWidget_borrow->item(row,1)!=nullptr &&
               ui->tableWidget_borrow->item(row,3)!=nullptr)
                insertBorrow(ui->tableWidget_borrow->item(row,1)->text(),ui->tableWidget_borrow->item(row,3)->text());
            else{
                QMessageBox::information(this,"提示","请填写书籍编号和借阅用户后再进行插入借阅记录");
            }
            return;
        }
    }
    else
    {
        if(item->column()==0)
        {
            QMessageBox::information(this,"提示","borrow_id不允许修改");
            return;
        }
        QString borrow_id=ui->tableWidget_borrow->item(row,0)->text();
        QString borrow_book=ui->tableWidget_borrow->item(row,1)->text();
        QString borrow_admin=ui->tableWidget_borrow->item(row,2)->text();
        QString borrow_user=ui->tableWidget_borrow->item(row,3)->text();
        QString borrow_time=ui->tableWidget_borrow->item(row,4)->text();
        QString return_time=ui->tableWidget_borrow->item(row,5)->text();
        QString available=ui->tableWidget_borrow->item(row,6)->text();
        QString data="number="+admin_number+"&onlineKey="+onlineKey+
                "&borrow_id="+borrow_id+"&borrow_book="+borrow_book+
                "&borrow_admin="+borrow_admin+"&borrow_user="+borrow_user+
                "&borrow_time="+borrow_time+"&return_time="+return_time+
                "&available="+available;
        updateBorrow(data);
    }
}


void AdminWindow::on_tableWidget_user_itemChanged(QTableWidgetItem *item)
{
    if(getAllUserManager!=nullptr)return;
    int row = item->row();
    if(row==ui->tableWidget_user->rowCount()-1)
    {
        if(item->column()!=0)
        {
            QMessageBox::information(this,"提示","请填写user_id进行插入用户");
            return;
        }
        insertUser();
    }
    else
    {
        if(item->column()==0)
        {
            QMessageBox::information(this,"提示","user_id不允许修改");
            return;
        }
        QString user_number=ui->tableWidget_user->item(row,0)->text();
        QString name=ui->tableWidget_user->item(row,1)->text();
        QString password=ui->tableWidget_user->item(row,2)->text();
        QString department=ui->tableWidget_user->item(row,3)->text();
        QString available=ui->tableWidget_user->item(row,4)->text();
        QString profile_photo=ui->tableWidget_user->item(row,5)->text();


        QString data="number="+admin_number+"&onlineKey="+onlineKey+
                "&user_number="+user_number+"&name="+name+
                "&password="+password+"&department="+department+
                "&available="+available+"&profile_photo="+profile_photo;
        updateUser(data);
    }
}

