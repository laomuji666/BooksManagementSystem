#ifndef ADMINWINDOW_H
#define ADMINWINDOW_H

#include <QMainWindow>
#include"httprequest.h"
#include"loginwindow.h"
#include<QPixmap>
#include<QTableWidgetItem>
namespace Ui {
class AdminWindow;
}

class AdminWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit AdminWindow(QString number,QString onlineKey,QWidget *parent = nullptr);
    ~AdminWindow();

private:
    Ui::AdminWindow *ui;
    QString number;
    QString onlineKey;
    QString admin_number;
    QString name;
    QString profile_photo;

//以下为http请求
private:
    void getInfo();
    QNetworkAccessManager*getInfoManager=nullptr;

    QPixmap profile_photo_picture;
    void get_profile_photo();
    QNetworkAccessManager*get_profile_photoManager=nullptr;

    void updateInfo(QString name,QString password);
    QNetworkAccessManager*updateInfoManager=nullptr;

    void update_profile_photo();
    QNetworkAccessManager*update_profile_photoManager=nullptr;

    void getAllBook();
    QNetworkAccessManager*getAllBookManager=nullptr;

    void updateBook(QString data);
    QNetworkAccessManager*updateBookManager=nullptr;

    void insertBook();
    QNetworkAccessManager*insertBookManager=nullptr;

    void getAllBorrow();
    QNetworkAccessManager*getAllBorrowManager=nullptr;

    void updateBorrow(QString data);
    QNetworkAccessManager*updateBorrowManager=nullptr;

    void insertBorrow(QString book_id,QString borrow_user);
    QNetworkAccessManager*insertBorrowManager=nullptr;

    void getAllUser();
    QNetworkAccessManager*getAllUserManager=nullptr;

    void updateUser(QString data);
    QNetworkAccessManager*updateUserManager=nullptr;

    void insertUser();
    QNetworkAccessManager*insertUserManager=nullptr;


private slots:
    void getInfoSlot(QNetworkReply*reply);
    void get_profile_photoSlot(QNetworkReply*reply);
    void updateInfoSlot(QNetworkReply*reply);
    void update_profile_photoSlot(QNetworkReply*reply);
    void getAllBookSlot(QNetworkReply*reply);
    void updateBookSlot(QNetworkReply*reply);
    void insertBookSlot(QNetworkReply*reply);
    void getAllBorrowSlot(QNetworkReply*reply);
    void updateBorrowSlot(QNetworkReply*reply);
    void insertBorrowSlot(QNetworkReply*reply);
    void getAllUserSlot(QNetworkReply*reply);
    void updateUserSlot(QNetworkReply*reply);
    void insertUserSlot(QNetworkReply*reply);



    void on_pushButton_logout_clicked();
    void on_pushButton_name_clicked();
    void on_pushButton_password_clicked();
    void on_pushButton_open_file_clicked();
    void on_pushButton_profile_photo_clicked();
    void on_tabWidget_currentChanged(int index);
    void on_tableWidget_book_itemChanged(QTableWidgetItem *item);
    void on_tableWidget_borrow_itemChanged(QTableWidgetItem *item);
    void on_tableWidget_user_itemChanged(QTableWidgetItem *item);
};

#endif // ADMINWINDOW_H
