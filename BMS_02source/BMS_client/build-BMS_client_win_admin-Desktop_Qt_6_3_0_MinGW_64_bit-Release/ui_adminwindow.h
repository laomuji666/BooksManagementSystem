/********************************************************************************
** Form generated from reading UI file 'adminwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.3.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADMINWINDOW_H
#define UI_ADMINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_AdminWindow
{
public:
    QWidget *centralwidget;
    QTabWidget *tabWidget;
    QWidget *tab_info;
    QGroupBox *groupBox;
    QLabel *label_profile_photo;
    QLabel *label_info;
    QGroupBox *groupBox_2;
    QLabel *label_name;
    QLineEdit *lineEdit_name;
    QPushButton *pushButton_name;
    QLineEdit *lineEdit_password;
    QLabel *label_password;
    QPushButton *pushButton_password;
    QLineEdit *lineEdit_profile_photo;
    QLabel *label_photo;
    QPushButton *pushButton_profile_photo;
    QPushButton *pushButton_logout;
    QPushButton *pushButton_open_file;
    QWidget *tab_book;
    QTableWidget *tableWidget_book;
    QWidget *tab_borrow;
    QTableWidget *tableWidget_borrow;
    QWidget *tab_user;
    QTableWidget *tableWidget_user;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *AdminWindow)
    {
        if (AdminWindow->objectName().isEmpty())
            AdminWindow->setObjectName(QString::fromUtf8("AdminWindow"));
        AdminWindow->resize(810, 600);
        AdminWindow->setMinimumSize(QSize(810, 600));
        AdminWindow->setMaximumSize(QSize(810, 600));
        centralwidget = new QWidget(AdminWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        tabWidget = new QTabWidget(centralwidget);
        tabWidget->setObjectName(QString::fromUtf8("tabWidget"));
        tabWidget->setGeometry(QRect(0, 0, 810, 603));
        tab_info = new QWidget();
        tab_info->setObjectName(QString::fromUtf8("tab_info"));
        groupBox = new QGroupBox(tab_info);
        groupBox->setObjectName(QString::fromUtf8("groupBox"));
        groupBox->setGeometry(QRect(10, 10, 780, 250));
        label_profile_photo = new QLabel(groupBox);
        label_profile_photo->setObjectName(QString::fromUtf8("label_profile_photo"));
        label_profile_photo->setGeometry(QRect(10, 20, 200, 210));
        label_profile_photo->setStyleSheet(QString::fromUtf8("background-color: rgb(0, 0, 0);\n"
"color: rgb(255, 255, 255);"));
        label_profile_photo->setAlignment(Qt::AlignCenter);
        label_info = new QLabel(groupBox);
        label_info->setObjectName(QString::fromUtf8("label_info"));
        label_info->setGeometry(QRect(240, 20, 510, 210));
        QFont font;
        font.setFamilies({QString::fromUtf8("\345\271\274\345\234\206")});
        font.setPointSize(26);
        label_info->setFont(font);
        label_info->setAlignment(Qt::AlignCenter);
        groupBox_2 = new QGroupBox(tab_info);
        groupBox_2->setObjectName(QString::fromUtf8("groupBox_2"));
        groupBox_2->setGeometry(QRect(10, 270, 780, 280));
        label_name = new QLabel(groupBox_2);
        label_name->setObjectName(QString::fromUtf8("label_name"));
        label_name->setGeometry(QRect(30, 30, 40, 40));
        lineEdit_name = new QLineEdit(groupBox_2);
        lineEdit_name->setObjectName(QString::fromUtf8("lineEdit_name"));
        lineEdit_name->setGeometry(QRect(80, 30, 500, 40));
        pushButton_name = new QPushButton(groupBox_2);
        pushButton_name->setObjectName(QString::fromUtf8("pushButton_name"));
        pushButton_name->setGeometry(QRect(600, 30, 160, 40));
        lineEdit_password = new QLineEdit(groupBox_2);
        lineEdit_password->setObjectName(QString::fromUtf8("lineEdit_password"));
        lineEdit_password->setGeometry(QRect(80, 80, 500, 40));
        label_password = new QLabel(groupBox_2);
        label_password->setObjectName(QString::fromUtf8("label_password"));
        label_password->setGeometry(QRect(30, 80, 40, 40));
        pushButton_password = new QPushButton(groupBox_2);
        pushButton_password->setObjectName(QString::fromUtf8("pushButton_password"));
        pushButton_password->setGeometry(QRect(600, 80, 160, 40));
        lineEdit_profile_photo = new QLineEdit(groupBox_2);
        lineEdit_profile_photo->setObjectName(QString::fromUtf8("lineEdit_profile_photo"));
        lineEdit_profile_photo->setGeometry(QRect(80, 130, 500, 40));
        label_photo = new QLabel(groupBox_2);
        label_photo->setObjectName(QString::fromUtf8("label_photo"));
        label_photo->setGeometry(QRect(30, 130, 40, 40));
        pushButton_profile_photo = new QPushButton(groupBox_2);
        pushButton_profile_photo->setObjectName(QString::fromUtf8("pushButton_profile_photo"));
        pushButton_profile_photo->setGeometry(QRect(685, 130, 75, 40));
        pushButton_logout = new QPushButton(groupBox_2);
        pushButton_logout->setObjectName(QString::fromUtf8("pushButton_logout"));
        pushButton_logout->setGeometry(QRect(30, 200, 720, 70));
        pushButton_open_file = new QPushButton(groupBox_2);
        pushButton_open_file->setObjectName(QString::fromUtf8("pushButton_open_file"));
        pushButton_open_file->setGeometry(QRect(600, 130, 75, 40));
        tabWidget->addTab(tab_info, QString());
        tab_book = new QWidget();
        tab_book->setObjectName(QString::fromUtf8("tab_book"));
        tableWidget_book = new QTableWidget(tab_book);
        if (tableWidget_book->columnCount() < 9)
            tableWidget_book->setColumnCount(9);
        QTableWidgetItem *__qtablewidgetitem = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(0, __qtablewidgetitem);
        QTableWidgetItem *__qtablewidgetitem1 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(1, __qtablewidgetitem1);
        QTableWidgetItem *__qtablewidgetitem2 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(2, __qtablewidgetitem2);
        QTableWidgetItem *__qtablewidgetitem3 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(3, __qtablewidgetitem3);
        QTableWidgetItem *__qtablewidgetitem4 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(4, __qtablewidgetitem4);
        QTableWidgetItem *__qtablewidgetitem5 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(5, __qtablewidgetitem5);
        QTableWidgetItem *__qtablewidgetitem6 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(6, __qtablewidgetitem6);
        QTableWidgetItem *__qtablewidgetitem7 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(7, __qtablewidgetitem7);
        QTableWidgetItem *__qtablewidgetitem8 = new QTableWidgetItem();
        tableWidget_book->setHorizontalHeaderItem(8, __qtablewidgetitem8);
        tableWidget_book->setObjectName(QString::fromUtf8("tableWidget_book"));
        tableWidget_book->setGeometry(QRect(0, 0, 820, 550));
        tableWidget_book->setLayoutDirection(Qt::LeftToRight);
        tableWidget_book->horizontalHeader()->setDefaultSectionSize(90);
        tableWidget_book->verticalHeader()->setVisible(false);
        tabWidget->addTab(tab_book, QString());
        tab_borrow = new QWidget();
        tab_borrow->setObjectName(QString::fromUtf8("tab_borrow"));
        tableWidget_borrow = new QTableWidget(tab_borrow);
        if (tableWidget_borrow->columnCount() < 7)
            tableWidget_borrow->setColumnCount(7);
        QTableWidgetItem *__qtablewidgetitem9 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(0, __qtablewidgetitem9);
        QTableWidgetItem *__qtablewidgetitem10 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(1, __qtablewidgetitem10);
        QTableWidgetItem *__qtablewidgetitem11 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(2, __qtablewidgetitem11);
        QTableWidgetItem *__qtablewidgetitem12 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(3, __qtablewidgetitem12);
        QTableWidgetItem *__qtablewidgetitem13 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(4, __qtablewidgetitem13);
        QTableWidgetItem *__qtablewidgetitem14 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(5, __qtablewidgetitem14);
        QTableWidgetItem *__qtablewidgetitem15 = new QTableWidgetItem();
        tableWidget_borrow->setHorizontalHeaderItem(6, __qtablewidgetitem15);
        tableWidget_borrow->setObjectName(QString::fromUtf8("tableWidget_borrow"));
        tableWidget_borrow->setGeometry(QRect(0, 0, 810, 560));
        tableWidget_borrow->setLayoutDirection(Qt::LeftToRight);
        tableWidget_borrow->setSizeAdjustPolicy(QAbstractScrollArea::AdjustIgnored);
        tableWidget_borrow->horizontalHeader()->setCascadingSectionResizes(false);
        tableWidget_borrow->horizontalHeader()->setDefaultSectionSize(110);
        tableWidget_borrow->verticalHeader()->setVisible(false);
        tableWidget_borrow->verticalHeader()->setCascadingSectionResizes(false);
        tabWidget->addTab(tab_borrow, QString());
        tab_user = new QWidget();
        tab_user->setObjectName(QString::fromUtf8("tab_user"));
        tableWidget_user = new QTableWidget(tab_user);
        if (tableWidget_user->columnCount() < 6)
            tableWidget_user->setColumnCount(6);
        QTableWidgetItem *__qtablewidgetitem16 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(0, __qtablewidgetitem16);
        QTableWidgetItem *__qtablewidgetitem17 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(1, __qtablewidgetitem17);
        QTableWidgetItem *__qtablewidgetitem18 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(2, __qtablewidgetitem18);
        QTableWidgetItem *__qtablewidgetitem19 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(3, __qtablewidgetitem19);
        QTableWidgetItem *__qtablewidgetitem20 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(4, __qtablewidgetitem20);
        QTableWidgetItem *__qtablewidgetitem21 = new QTableWidgetItem();
        tableWidget_user->setHorizontalHeaderItem(5, __qtablewidgetitem21);
        tableWidget_user->setObjectName(QString::fromUtf8("tableWidget_user"));
        tableWidget_user->setGeometry(QRect(0, 0, 810, 560));
        tableWidget_user->setLayoutDirection(Qt::LeftToRight);
        tableWidget_user->setSizeAdjustPolicy(QAbstractScrollArea::AdjustIgnored);
        tableWidget_user->horizontalHeader()->setCascadingSectionResizes(false);
        tableWidget_user->horizontalHeader()->setDefaultSectionSize(130);
        tableWidget_user->verticalHeader()->setVisible(false);
        tableWidget_user->verticalHeader()->setCascadingSectionResizes(false);
        tabWidget->addTab(tab_user, QString());
        AdminWindow->setCentralWidget(centralwidget);
        statusBar = new QStatusBar(AdminWindow);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        AdminWindow->setStatusBar(statusBar);

        retranslateUi(AdminWindow);

        tabWidget->setCurrentIndex(1);


        QMetaObject::connectSlotsByName(AdminWindow);
    } // setupUi

    void retranslateUi(QMainWindow *AdminWindow)
    {
        AdminWindow->setWindowTitle(QCoreApplication::translate("AdminWindow", "admin-client-win  \350\257\245\345\256\242\346\210\267\347\253\257\350\257\267\344\270\215\350\246\201\346\273\245\347\224\250,\350\257\267\345\260\275\351\207\217\345\217\252\347\224\250\344\272\216\346\233\264\346\255\243\346\225\260\346\215\256", nullptr));
        groupBox->setTitle(QCoreApplication::translate("AdminWindow", "\344\270\252\344\272\272\350\265\204\346\226\231\345\261\225\347\244\272", nullptr));
        label_profile_photo->setText(QCoreApplication::translate("AdminWindow", "\345\244\264\345\203\217", nullptr));
        label_info->setText(QCoreApplication::translate("AdminWindow", "\345\247\223\345\220\215\357\274\232\350\200\201\346\257\215\351\270\241\n"
"\n"
"\350\264\246\345\217\267\357\274\23212345", nullptr));
        groupBox_2->setTitle(QCoreApplication::translate("AdminWindow", "\344\270\252\344\272\272\350\265\204\346\226\231\344\277\256\346\224\271", nullptr));
        label_name->setText(QCoreApplication::translate("AdminWindow", "\345\247\223\345\220\215\357\274\232", nullptr));
        lineEdit_name->setPlaceholderText(QCoreApplication::translate("AdminWindow", "\350\257\267\350\276\223\345\205\245\351\234\200\350\246\201\344\277\256\346\224\271\347\232\204\345\247\223\345\220\215", nullptr));
        pushButton_name->setText(QCoreApplication::translate("AdminWindow", "\344\277\256\346\224\271", nullptr));
        lineEdit_password->setPlaceholderText(QCoreApplication::translate("AdminWindow", "\350\276\223\345\205\245\351\234\200\350\246\201\344\277\256\346\224\271\347\232\204\345\257\206\347\240\201", nullptr));
        label_password->setText(QCoreApplication::translate("AdminWindow", "\345\257\206\347\240\201\357\274\232", nullptr));
        pushButton_password->setText(QCoreApplication::translate("AdminWindow", "\344\277\256\346\224\271", nullptr));
        lineEdit_profile_photo->setPlaceholderText(QCoreApplication::translate("AdminWindow", "\347\202\271\345\207\273\344\277\256\346\224\271\344\274\232\350\207\252\345\212\250\346\212\212\345\233\276\347\211\207\344\270\212\344\274\240\345\210\260\346\234\215\345\212\241\345\231\250\345\271\266\344\277\256\346\224\271\345\244\264\345\203\217", nullptr));
        label_photo->setText(QCoreApplication::translate("AdminWindow", "\345\244\264\345\203\217\357\274\232", nullptr));
        pushButton_profile_photo->setText(QCoreApplication::translate("AdminWindow", "\344\277\256\346\224\271", nullptr));
        pushButton_logout->setText(QCoreApplication::translate("AdminWindow", "\351\200\200\345\207\272(\345\217\226\346\266\210\350\207\252\345\212\250\347\231\273\345\275\225)", nullptr));
        pushButton_open_file->setText(QCoreApplication::translate("AdminWindow", "\351\200\211\345\233\276", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_info), QCoreApplication::translate("AdminWindow", "\344\270\252\344\272\272\344\277\241\346\201\257", nullptr));
        QTableWidgetItem *___qtablewidgetitem = tableWidget_book->horizontalHeaderItem(0);
        ___qtablewidgetitem->setText(QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\347\274\226\345\217\267", nullptr));
        QTableWidgetItem *___qtablewidgetitem1 = tableWidget_book->horizontalHeaderItem(1);
        ___qtablewidgetitem1->setText(QCoreApplication::translate("AdminWindow", "\346\240\207\351\242\230", nullptr));
        QTableWidgetItem *___qtablewidgetitem2 = tableWidget_book->horizontalHeaderItem(2);
        ___qtablewidgetitem2->setText(QCoreApplication::translate("AdminWindow", "\344\275\234\350\200\205/\345\207\272\347\211\210\347\244\276", nullptr));
        QTableWidgetItem *___qtablewidgetitem3 = tableWidget_book->horizontalHeaderItem(3);
        ___qtablewidgetitem3->setText(QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\347\261\273\345\236\213", nullptr));
        QTableWidgetItem *___qtablewidgetitem4 = tableWidget_book->horizontalHeaderItem(4);
        ___qtablewidgetitem4->setText(QCoreApplication::translate("AdminWindow", "\345\275\225\345\205\245\347\232\204\347\256\241\347\220\206\345\221\230", nullptr));
        QTableWidgetItem *___qtablewidgetitem5 = tableWidget_book->horizontalHeaderItem(5);
        ___qtablewidgetitem5->setText(QCoreApplication::translate("AdminWindow", "\346\230\257\345\220\246\345\217\257\347\224\250", nullptr));
        QTableWidgetItem *___qtablewidgetitem6 = tableWidget_book->horizontalHeaderItem(6);
        ___qtablewidgetitem6->setText(QCoreApplication::translate("AdminWindow", "\344\270\215\345\217\257\347\224\250\345\216\237\345\233\240", nullptr));
        QTableWidgetItem *___qtablewidgetitem7 = tableWidget_book->horizontalHeaderItem(7);
        ___qtablewidgetitem7->setText(QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\345\275\225\345\205\245\346\227\266\351\227\264", nullptr));
        QTableWidgetItem *___qtablewidgetitem8 = tableWidget_book->horizontalHeaderItem(8);
        ___qtablewidgetitem8->setText(QCoreApplication::translate("AdminWindow", "\346\235\241\345\275\242\347\240\201", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_book), QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\347\256\241\347\220\206", nullptr));
        QTableWidgetItem *___qtablewidgetitem9 = tableWidget_borrow->horizontalHeaderItem(0);
        ___qtablewidgetitem9->setText(QCoreApplication::translate("AdminWindow", "\345\200\237\351\230\205\347\274\226\345\217\267", nullptr));
        QTableWidgetItem *___qtablewidgetitem10 = tableWidget_borrow->horizontalHeaderItem(1);
        ___qtablewidgetitem10->setText(QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\347\274\226\345\217\267", nullptr));
        QTableWidgetItem *___qtablewidgetitem11 = tableWidget_borrow->horizontalHeaderItem(2);
        ___qtablewidgetitem11->setText(QCoreApplication::translate("AdminWindow", "\345\220\214\346\204\217\347\232\204\347\256\241\347\220\206\345\221\230", nullptr));
        QTableWidgetItem *___qtablewidgetitem12 = tableWidget_borrow->horizontalHeaderItem(3);
        ___qtablewidgetitem12->setText(QCoreApplication::translate("AdminWindow", "\345\200\237\344\271\246\347\232\204\347\224\250\346\210\267", nullptr));
        QTableWidgetItem *___qtablewidgetitem13 = tableWidget_borrow->horizontalHeaderItem(4);
        ___qtablewidgetitem13->setText(QCoreApplication::translate("AdminWindow", "\345\274\200\345\247\213\345\200\237\351\230\205\347\232\204\346\227\266\351\227\264", nullptr));
        QTableWidgetItem *___qtablewidgetitem14 = tableWidget_borrow->horizontalHeaderItem(5);
        ___qtablewidgetitem14->setText(QCoreApplication::translate("AdminWindow", "\345\275\222\350\277\230\347\232\204\346\227\266\351\227\264", nullptr));
        QTableWidgetItem *___qtablewidgetitem15 = tableWidget_borrow->horizontalHeaderItem(6);
        ___qtablewidgetitem15->setText(QCoreApplication::translate("AdminWindow", "\344\271\246\347\261\215\347\212\266\346\200\201", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_borrow), QCoreApplication::translate("AdminWindow", "\345\200\237\351\230\205\347\256\241\347\220\206", nullptr));
        QTableWidgetItem *___qtablewidgetitem16 = tableWidget_user->horizontalHeaderItem(0);
        ___qtablewidgetitem16->setText(QCoreApplication::translate("AdminWindow", "\347\224\250\346\210\267\350\264\246\345\217\267", nullptr));
        QTableWidgetItem *___qtablewidgetitem17 = tableWidget_user->horizontalHeaderItem(1);
        ___qtablewidgetitem17->setText(QCoreApplication::translate("AdminWindow", "\345\247\223\345\220\215", nullptr));
        QTableWidgetItem *___qtablewidgetitem18 = tableWidget_user->horizontalHeaderItem(2);
        ___qtablewidgetitem18->setText(QCoreApplication::translate("AdminWindow", "\345\257\206\347\240\201", nullptr));
        QTableWidgetItem *___qtablewidgetitem19 = tableWidget_user->horizontalHeaderItem(3);
        ___qtablewidgetitem19->setText(QCoreApplication::translate("AdminWindow", "\351\203\250\351\227\250", nullptr));
        QTableWidgetItem *___qtablewidgetitem20 = tableWidget_user->horizontalHeaderItem(4);
        ___qtablewidgetitem20->setText(QCoreApplication::translate("AdminWindow", "\345\270\220\345\217\267\347\212\266\346\200\201", nullptr));
        QTableWidgetItem *___qtablewidgetitem21 = tableWidget_user->horizontalHeaderItem(5);
        ___qtablewidgetitem21->setText(QCoreApplication::translate("AdminWindow", "\345\244\264\345\203\217\346\226\207\344\273\266\345\220\215", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_user), QCoreApplication::translate("AdminWindow", "\347\224\250\346\210\267\347\256\241\347\220\206", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AdminWindow: public Ui_AdminWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADMINWINDOW_H
