# File Upload Download
### Requirements
- Spring Boot
- Maven
- JAVA 18
- PostgreSql Container With DB Named "filesdb" (requirements for db in application.properties)


### Endpoints
```
POST /files/upload HTTP/1.1
Host: localhost:8080
Content-Length: 264
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

----WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="/C:/Users/TCABALAK/Desktop/LINUX.docx"
Content-Type: application/vnd.openxmlformats-officedocument.wordprocessingml.document

(data)
----WebKitFormBoundary7MA4YWxkTrZu0gW
```
