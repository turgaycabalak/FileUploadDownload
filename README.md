# File Upload Download
### Requirements
- Spring Boot
- Maven
- JAVA 18
- PostgreSql Container With DB Named "filesdb" (requirements for db in application.properties)


### Endpoints
##### Upload File
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
##### Download All Files with Pagination
```
GET /files/getall?page=0&size=5 HTTP/1.1
Host: localhost:8080
```
##### Download File By Id
```
GET /files/download/8 HTTP/1.1
Host: localhost:8080
```
##### Delete File By Id (Transfering the file to the "DeletedFiles" folder from "UploadedFiles" folder)
```
DELETE /files/delete/9 HTTP/1.1
Host: localhost:8080
```
##### Update File By Id
```
PUT /files/update/8 HTTP/1.1
Host: localhost:8080
Content-Length: 205
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

----WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="/C:/Users/TCABALAK/Desktop/5mb.pdf"
Content-Type: application/pdf

(data)
----WebKitFormBoundary7MA4YWxkTrZu0gW
```
