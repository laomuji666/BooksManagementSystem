/********************************************************************************
** Form generated from reading UI file 'loginwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.3.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LOGINWINDOW_H
#define UI_LOGINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_LoginWindow
{
public:
    QWidget *centralwidget;
    QLabel *label;
    QLineEdit *lineEdit_number;
    QLabel *label_2;
    QLineEdit *lineEdit_password;
    QLabel *label_3;
    QLineEdit *lineEdit_code;
    QLabel *label_code_image;
    QPushButton *pushButton_register;
    QPushButton *pushButton_login;
    QCheckBox *checkBox_remember;
    QCheckBox *checkBox_show_password;
    QLineEdit *lineEdit_name;
    QLabel *label_4;
    QCheckBox *checkBox_auto_login;

    void setupUi(QMainWindow *LoginWindow)
    {
        if (LoginWindow->objectName().isEmpty())
            LoginWindow->setObjectName(QString::fromUtf8("LoginWindow"));
        LoginWindow->resize(400, 300);
        LoginWindow->setMinimumSize(QSize(400, 300));
        LoginWindow->setMaximumSize(QSize(400, 300));
        centralwidget = new QWidget(LoginWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        label = new QLabel(centralwidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(20, 60, 70, 40));
        QFont font;
        font.setFamilies({QString::fromUtf8("\345\271\274\345\234\206")});
        font.setPointSize(15);
        label->setFont(font);
        lineEdit_number = new QLineEdit(centralwidget);
        lineEdit_number->setObjectName(QString::fromUtf8("lineEdit_number"));
        lineEdit_number->setGeometry(QRect(100, 60, 200, 40));
        QFont font1;
        font1.setPointSize(15);
        lineEdit_number->setFont(font1);
        label_2 = new QLabel(centralwidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setGeometry(QRect(20, 110, 70, 40));
        label_2->setFont(font);
        lineEdit_password = new QLineEdit(centralwidget);
        lineEdit_password->setObjectName(QString::fromUtf8("lineEdit_password"));
        lineEdit_password->setGeometry(QRect(100, 110, 200, 40));
        lineEdit_password->setFont(font1);
        lineEdit_password->setEchoMode(QLineEdit::Password);
        label_3 = new QLabel(centralwidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setGeometry(QRect(20, 160, 70, 40));
        label_3->setFont(font);
        lineEdit_code = new QLineEdit(centralwidget);
        lineEdit_code->setObjectName(QString::fromUtf8("lineEdit_code"));
        lineEdit_code->setGeometry(QRect(100, 160, 120, 40));
        lineEdit_code->setFont(font1);
        lineEdit_code->setMaxLength(4);
        lineEdit_code->setEchoMode(QLineEdit::Normal);
        label_code_image = new QLabel(centralwidget);
        label_code_image->setObjectName(QString::fromUtf8("label_code_image"));
        label_code_image->setGeometry(QRect(230, 160, 130, 40));
        label_code_image->setStyleSheet(QString::fromUtf8("background-color: rgb(0, 0, 0);\n"
"color: rgb(255, 255, 255);"));
        label_code_image->setAlignment(Qt::AlignCenter);
        pushButton_register = new QPushButton(centralwidget);
        pushButton_register->setObjectName(QString::fromUtf8("pushButton_register"));
        pushButton_register->setGeometry(QRect(30, 220, 160, 50));
        pushButton_login = new QPushButton(centralwidget);
        pushButton_login->setObjectName(QString::fromUtf8("pushButton_login"));
        pushButton_login->setGeometry(QRect(210, 220, 160, 50));
        checkBox_remember = new QCheckBox(centralwidget);
        checkBox_remember->setObjectName(QString::fromUtf8("checkBox_remember"));
        checkBox_remember->setGeometry(QRect(310, 60, 80, 40));
        checkBox_show_password = new QCheckBox(centralwidget);
        checkBox_show_password->setObjectName(QString::fromUtf8("checkBox_show_password"));
        checkBox_show_password->setGeometry(QRect(310, 110, 80, 40));
        lineEdit_name = new QLineEdit(centralwidget);
        lineEdit_name->setObjectName(QString::fromUtf8("lineEdit_name"));
        lineEdit_name->setGeometry(QRect(100, 10, 200, 40));
        lineEdit_name->setFont(font1);
        label_4 = new QLabel(centralwidget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setGeometry(QRect(20, 10, 70, 40));
        label_4->setFont(font);
        checkBox_auto_login = new QCheckBox(centralwidget);
        checkBox_auto_login->setObjectName(QString::fromUtf8("checkBox_auto_login"));
        checkBox_auto_login->setGeometry(QRect(310, 10, 80, 40));
        LoginWindow->setCentralWidget(centralwidget);

        retranslateUi(LoginWindow);

        QMetaObject::connectSlotsByName(LoginWindow);
    } // setupUi

    void retranslateUi(QMainWindow *LoginWindow)
    {
        LoginWindow->setWindowTitle(QCoreApplication::translate("LoginWindow", "bms-admin-login", nullptr));
        label->setText(QCoreApplication::translate("LoginWindow", "\350\264\246  \345\217\267:", nullptr));
        label_2->setText(QCoreApplication::translate("LoginWindow", "\345\257\206  \347\240\201:", nullptr));
        label_3->setText(QCoreApplication::translate("LoginWindow", "\351\252\214\350\257\201\347\240\201:", nullptr));
        lineEdit_code->setInputMask(QString());
        label_code_image->setText(QCoreApplication::translate("LoginWindow", "\347\202\271\345\207\273\346\233\264\346\226\260\351\252\214\350\257\201\347\240\201", nullptr));
        pushButton_register->setText(QCoreApplication::translate("LoginWindow", "\346\263\250\345\206\214", nullptr));
        pushButton_login->setText(QCoreApplication::translate("LoginWindow", "\347\231\273\351\231\206", nullptr));
        checkBox_remember->setText(QCoreApplication::translate("LoginWindow", "\350\256\260\344\275\217\345\257\206\347\240\201", nullptr));
        checkBox_show_password->setText(QCoreApplication::translate("LoginWindow", "\346\230\276\347\244\272\345\257\206\347\240\201", nullptr));
        lineEdit_name->setText(QString());
        lineEdit_name->setPlaceholderText(QCoreApplication::translate("LoginWindow", "\344\273\205\347\224\250\344\272\216\346\263\250\345\206\214", nullptr));
        label_4->setText(QCoreApplication::translate("LoginWindow", "\345\247\223  \345\220\215:", nullptr));
        checkBox_auto_login->setText(QCoreApplication::translate("LoginWindow", "\350\207\252\345\212\250\347\231\273\351\231\206", nullptr));
    } // retranslateUi

};

namespace Ui {
    class LoginWindow: public Ui_LoginWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LOGINWINDOW_H
