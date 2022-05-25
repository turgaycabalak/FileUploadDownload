# File Upload Download
### Requirements
- Spring Boot
- Maven
- JAVA 18
- PostgreSql Container With DB Named "filesdb" (requirements for db in application.properties)
<hr>

### About Project
They can upload or download any file with extension we specify. The file will be located on any path we specify on localhost. When they send any request to the deleting file endpoint, the file will be transfered any other folder which means the deleted files folder. So we need to specify it as well. Also we have a rdb which hold the information about file such as name, extension, size, path and an id given by db. Your CRUD operations will be executed on db and folder together. If any error occurres, a message will be sent to the client about the error.
<hr>

### Spring Security with JWT
All endpoints are protected/private by Spring Security with JWT. User should register from the endpoit "/auth/signup" and then login through "/login". After successful login, a token will be taken from "header" with bearer. All request should send with this token inside header with name "Authorization" key. Otherwise endpoints will not be accessible. 

### Configurations
# application.properties
- DB
- File Upload Download Configurations (such as max file size)
- Uploade Files Folder Path
- Deleted Files Folder Path
- Valid Extensions
<hr>

### Endpoints
##### Sign Up
```
POST /auth/signup HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 52

{
    "email": "example",
    "password": "123456"
}
```
<hr>

##### Login
```
POST /login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 55

{
    "username": "example",
    "password": "123456"
}
```
<hr>

##### Upload File
```
POST /files/upload HTTP/1.1
Host: localhost:8080
Authorization: Barer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheWF6IiwiQXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDc2ODA0LCJleHAiOjE2NTM1OTg4MDB9.ONWqPioVW1n6uKmPKJgSkQQtFa71HUFCnBQc_H1lTH0bwfjq1-_Qxqn5QLS9dFSf_dumQk_8Z8Gcw7f2ysqaAA
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
Authorization: Barer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheWF6IiwiQXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDc2ODA0LCJleHAiOjE2NTM1OTg4MDB9.ONWqPioVW1n6uKmPKJgSkQQtFa71HUFCnBQc_H1lTH0bwfjq1-_Qxqn5QLS9dFSf_dumQk_8Z8Gcw7f2ysqaAA
```
<hr>

##### Download File Informations By Id
```
GET /files/download/13 HTTP/1.1
Host: localhost:8080
Authorization: Barer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheWF6IiwiQXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDc2ODA0LCJleHAiOjE2NTM1OTg4MDB9.ONWqPioVW1n6uKmPKJgSkQQtFa71HUFCnBQc_H1lTH0bwfjq1-_Qxqn5QLS9dFSf_dumQk_8Z8Gcw7f2ysqaAA
```
<hr>

##### Delete File By Id (Transfering the file to the "DeletedFiles" folder from "UploadedFiles" folder)
```
DELETE /files/delete/9 HTTP/1.1
Host: localhost:8080
Authorization: Barer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheWF6IiwiQXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDc2ODA0LCJleHAiOjE2NTM1OTg4MDB9.ONWqPioVW1n6uKmPKJgSkQQtFa71HUFCnBQc_H1lTH0bwfjq1-_Qxqn5QLS9dFSf_dumQk_8Z8Gcw7f2ysqaAA
```
<hr>

##### Update File By Id
```
PUT /files/update/8 HTTP/1.1
Host: localhost:8080
Authorization: Barer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheWF6IiwiQXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDc2ODA0LCJleHAiOjE2NTM1OTg4MDB9.ONWqPioVW1n6uKmPKJgSkQQtFa71HUFCnBQc_H1lTH0bwfjq1-_Qxqn5QLS9dFSf_dumQk_8Z8Gcw7f2ysqaAA
Content-Length: 205
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

----WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="/C:/Users/TCABALAK/Desktop/5mb.pdf"
Content-Type: application/pdf

(data)
----WebKitFormBoundary7MA4YWxkTrZu0gW
```
<hr>

