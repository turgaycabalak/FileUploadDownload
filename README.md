# File Upload Download
### Requirements
- Spring Boot
- Maven
- JAVA 18
- PostgreSql Container With DB Named "filesdb" (requirements for db in application.properties)
- <hr>
### About Project
They can upload or download any file with extension we specify. The file will be located on any path we specify on localhost. When they send any request to the deleting file endpoint, the file will be transfered any other folder which means the deleted files folder. So we need to specify it as well. Also we have a rdb which hold the information about file such as name, extension, size, path and an id given by db. Your CRUD operations will be executed on db and folder together. If any error occurres, a message will be sent to the client about the error.
<hr>
### Configurations
You can
<hr>
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
<hr>
##### Download All Files with Pagination
```
GET /files/getall?page=0&size=5 HTTP/1.1
Host: localhost:8080
```
<hr>
##### Download File Informations By Id
```
GET /files/download/8 HTTP/1.1
Host: localhost:8080
```
<hr>
##### Delete File By Id (Transfering the file to the "DeletedFiles" folder from "UploadedFiles" folder)
```
DELETE /files/delete/9 HTTP/1.1
Host: localhost:8080
```
<hr>
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
<hr>
