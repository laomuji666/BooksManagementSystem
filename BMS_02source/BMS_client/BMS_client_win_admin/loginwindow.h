#ifndef LOGINWINDOW_H
#define LOGINWINDOW_H

#include <QMainWindow>
#include"httprequest.h"
QT_BEGIN_NAMESPACE
namespace Ui { class LoginWindow; }
QT_END_NAMESPACE

class LoginWindow : public QMainWindow
{
    Q_OBJECT

public:
    //保存账号密码的配置文件
    static QString configPath(){
        return "account.ini";;
    }

    LoginWindow(QWidget *parent = nullptr);
    ~LoginWindow();
private slots:
    void on_checkBox_show_password_stateChanged(int arg1);
protected:
    bool eventFilter(QObject *watched, QEvent *event);
private:
    void writeConfig();
    void readConfig();
    //打开管理窗口
    void openAdminWindow();
private:
    Ui::LoginWindow *ui;


//以下为http请求
private:
    QString codeKey;
    void getCodeKey();
    QNetworkAccessManager*getCodeKeyManager=nullptr;
    QPixmap codePicture;
    void getCodePicture();
    QNetworkAccessManager*getCodePictureManager=nullptr;
    void registerAdmin();
    QNetworkAccessManager*registerAdminManager=nullptr;
    QString onlineKey="";
    void loginAdmin();
    QNetworkAccessManager*loginAdminManager=nullptr;
private slots:
    void getCodeKeySlot(QNetworkReply*reply);
    void getCodePictureSlot(QNetworkReply*reply);
    void registerAdminSlot(QNetworkReply*reply);
    void loginAdminSlot(QNetworkReply*reply);
};

#endif // LOGINWINDOW_H
