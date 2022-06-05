#include "httprequest.h"


QNetworkAccessManager* HttpRequest::getRequest(QString url,const QObject *receiver, const char *member){
    url = getUrl() + url;
    QNetworkRequest request;
    request.setUrl(url);
    QNetworkAccessManager*networkManager=new QNetworkAccessManager();
    networkManager->get(request);
    if(member!=nullptr)QObject::connect(networkManager,SIGNAL(finished(QNetworkReply*)),receiver,member);
    return networkManager;
}

QNetworkAccessManager* HttpRequest::postRequest(QString url,QString data,const QObject *receiver, const char *member){
    url = getUrl() + url;
    QNetworkRequest request;
    request.setUrl(url);
    request.setHeader(QNetworkRequest::KnownHeaders::ContentTypeHeader,"application/x-www-form-urlencoded");
    QNetworkAccessManager*networkManager=new QNetworkAccessManager();
    networkManager->post(request,data.toUtf8());
    if(member!=nullptr)QObject::connect(networkManager,SIGNAL(finished(QNetworkReply*)),receiver,member);
    return networkManager;
}

QNetworkAccessManager* HttpRequest::postFileRequest(QString url,QString data,const QObject *receiver, const char *member){
    url = getUrl() + url;
    QNetworkRequest request;
    request.setUrl(url);
    request.setHeader(QNetworkRequest::KnownHeaders::ContentTypeHeader,QVariant("text/jpeg"));
    request.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"file\"; filename=\"abc.jpg\""));
    QNetworkAccessManager*networkManager=new QNetworkAccessManager();
    networkManager->post(request,data.toUtf8());
    if(member!=nullptr)QObject::connect(networkManager,SIGNAL(finished(QNetworkReply*)),receiver,member);
    return networkManager;
}

QString HttpRequest::getJsonValue(const QByteArray &jsonByte,QString key){
    QJsonDocument jsdocument=QJsonDocument::fromJson(jsonByte);
    QJsonObject obj=jsdocument.object();
    return obj.value(key).toString();
}
