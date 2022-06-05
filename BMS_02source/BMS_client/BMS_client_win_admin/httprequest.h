#ifndef HTTPREQUEST_H
#define HTTPREQUEST_H

#include <QObject>
#include <QNetworkRequest>
#include <QNetworkAccessManager>
#include <QJsonDocument>
#include <QJsonObject>
#include <QNetworkReply>
//QNetworkAccessManager*
//存在内存泄漏的问题
//需要手动释放内存
class HttpRequest
{
public:
    static QString getUrl()
    {
        //return "http://82.157.178.153:8088";
        return "http://localhost:8088";
    }
    static QNetworkAccessManager * getRequest(QString url,const QObject *receiver, const char *member);
    static QNetworkAccessManager * postRequest(QString url,QString data,const QObject *receiver, const char *member);
    static QNetworkAccessManager * postFileRequest(QString url,QString data,const QObject *receiver, const char *member);
    static QString getJsonValue(const QByteArray &jsonByte,QString key);
};
#endif // HTTPREQUEST_H

