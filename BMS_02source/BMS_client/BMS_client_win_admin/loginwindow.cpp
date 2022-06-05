#include "loginwindow.h"
#include "ui_loginwindow.h"
#include<QSettings>
#include<QLineEdit>
#include<QMessageBox>
#include<QTimer>
#include<qDebug>
#include"adminwindow.h"

LoginWindow::LoginWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::LoginWindow)
{
    ui->setupUi(this);
    ui->label_code_image->installEventFilter(this);
    ui->pushButton_login->installEventFilter(this);
    ui->pushButton_register->installEventFilter(this);
    getCodeKey();
    QTimer *timer=new QTimer(this);
    connect(timer,&QTimer::timeout,this,[&](){
        readConfig();
    });
    timer->start(100);
    timer->setSingleShot(true);
}

LoginWindow::~LoginWindow()
{
    delete ui;
}

void LoginWindow::writeConfig()
{
    QSettings settings(configPath(),QSettings::IniFormat);
    if(ui->checkBox_remember->isChecked()){
        settings.setValue("number",ui->lineEdit_number->text());
        settings.setValue("password",ui->lineEdit_password->text());
        if(ui->checkBox_auto_login->isChecked())
            settings.setValue("onlineKey",onlineKey);
    }else{
        settings.clear();
    }
    settings.setValue("auto_login",ui->checkBox_auto_login->isChecked());
    settings.setValue("remember",ui->checkBox_remember->isChecked());
}

void LoginWindow::readConfig()
{
    QSettings settings(configPath(),QSettings::IniFormat);
    if(settings.value("remember").toBool()==true){
        ui->lineEdit_number->setText(settings.value("number").toString());
        ui->lineEdit_password->setText(settings.value("password").toString());
        ui->checkBox_auto_login->setChecked(settings.value("auto_login").toBool());
        ui->checkBox_remember->setChecked(true);
        if(ui->checkBox_auto_login->isChecked()){
            onlineKey=settings.value("onlineKey").toString();
            if(onlineKey.isEmpty()==0 )openAdminWindow();
        }
    }
}
bool LoginWindow::eventFilter(QObject *watched, QEvent *event)
{
    if(event->type()==QEvent::MouseButtonPress)
    {
        if(watched==ui->pushButton_login)
        {
            loginAdmin();
        }
        if(watched==ui->pushButton_register)
        {
            registerAdmin();
        }
        if(watched==ui->label_code_image)
        {
            getCodeKey();
        }
    }
    return QWidget::eventFilter(watched,event);
}


void LoginWindow::on_checkBox_show_password_stateChanged(int arg1)
{
    if(arg1==2)ui->lineEdit_password->setEchoMode(QLineEdit::EchoMode::Normal);
    else ui->lineEdit_password->setEchoMode(QLineEdit::EchoMode::Password);
}

void LoginWindow::getCodeKey()
{
    //若上一个请求未结束,则不做处理
    if(getCodeKeyManager==nullptr)
        getCodeKeyManager=HttpRequest::getRequest("/account/code/key",this,SLOT(getCodeKeySlot(QNetworkReply*)));
}
void LoginWindow::getCodeKeySlot(QNetworkReply*reply)
{
    //获取codeKey
    codeKey=reply->readAll();
    //释放内存,防止内存溢出
    reply->deleteLater();
    getCodeKeyManager->deleteLater();
    getCodeKeyManager=nullptr;
    //通过codeKey获取图片验证码
    getCodePicture();
}

void LoginWindow::getCodePicture()
{
    //若上一个请求未结束,则不做处理
    if(getCodePictureManager==nullptr)
        getCodePictureManager=HttpRequest::getRequest("/account/code/picture?codeKey="+codeKey,this,SLOT(getCodePictureSlot(QNetworkReply*)));
}
void LoginWindow::getCodePictureSlot(QNetworkReply*reply)
{
    //将数据读取到图片中
    codePicture.loadFromData(reply->readAll());
    codePicture.scaled(ui->label_code_image->size(), Qt::KeepAspectRatio);
    ui->label_code_image->setScaledContents(true);
    ui->label_code_image->setPixmap(codePicture);
    //释放内存,防止内存溢出
    reply->deleteLater();
    getCodePictureManager->deleteLater();
    getCodePictureManager=nullptr;
    //将之前输入的验证码清除
    ui->lineEdit_code->setText("");
}

void LoginWindow::registerAdmin()
{
    //若上一个请求未结束,则不做处理
    if(registerAdminManager==nullptr)
    {
        QString data = "type=admin&name="+ui->lineEdit_name->text()+
                "&password="+ui->lineEdit_password->text()+
                "&codeKey="+codeKey+
                "&codeStr="+ui->lineEdit_code->text();
        registerAdminManager=HttpRequest::postRequest("/account/register",data,this,SLOT(registerAdminSlot(QNetworkReply*)));
    }
}
void LoginWindow::registerAdminSlot(QNetworkReply*reply)
{
    QByteArray bytes=reply->readAll();
    QString rcode=HttpRequest::getJsonValue(bytes,"code");//8表示注册成功
    QString rmsg=HttpRequest::getJsonValue(bytes,"msg");

    //释放内存,防止内存溢出
    reply->deleteLater();
    registerAdminManager->deleteLater();
    registerAdminManager=nullptr;

    if(rcode=="8")
    {
        ui->lineEdit_number->setText(rmsg);
        QMessageBox::information(this,"注册成功","注册的账号已经放入账号栏,请牢记您的账号:"+rmsg);
    }else
    {
        QMessageBox::information(this,"注册失败",rmsg);
    }
    //无论是否注册成功 都更新验证码
    getCodeKey();
}

void LoginWindow::loginAdmin()
{
    //若上一个请求未结束,则不做处理
    if(registerAdminManager==nullptr)
    {
        QString data = "type=admin&number="+ui->lineEdit_number->text()+
                "&password="+ui->lineEdit_password->text()+
                "&codeKey="+codeKey+
                "&codeStr="+ui->lineEdit_code->text();
        loginAdminManager=HttpRequest::postRequest("/account/login",data,this,SLOT(loginAdminSlot(QNetworkReply*)));
    }
}
void LoginWindow::loginAdminSlot(QNetworkReply*reply)
{
    QByteArray bytes=reply->readAll();
    QString rcode=HttpRequest::getJsonValue(bytes,"code");//0表示登陆成功
    QString rmsg=HttpRequest::getJsonValue(bytes,"msg");
    //释放内存,防止内存溢出
    reply->deleteLater();
    loginAdminManager->deleteLater();
    loginAdminManager=nullptr;
    if(rcode=="0")
    {
        QMessageBox::information(this,"登陆成功",rmsg);
        //登陆成功的msg就是onlineKey
        onlineKey=rmsg;
        //登陆成功后更新配置
        writeConfig();
        //打开登陆成功后的窗口
        openAdminWindow();
        qDebug()<<rmsg;
    }else
    {
        QMessageBox::information(this,"登陆失败",rmsg);
        //更新验证码
        getCodeKey();
    }
}


void LoginWindow::openAdminWindow()
{
    //打开窗口
    AdminWindow * window=new AdminWindow(ui->lineEdit_number->text(),onlineKey);
    window->show();
    this->close();
}
