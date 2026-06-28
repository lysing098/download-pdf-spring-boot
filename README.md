# 📄 Spring Boot PDF Upload & Download API

A Spring Boot REST API that allows users to upload PDF files, store them in the database, and download them by ID.

## 🚀 Features

* Upload PDF files
* Store PDF files in the database
* Download PDF files

## 🛠️ Technologies

* Java 23
* Spring Boot 4
* Spring Data JPA
* H2 Database
* Maven

## 📡 API Endpoints

### Upload PDF

```http
POST /api/v1/document
```

**Request**

* Content-Type: `multipart/form-data`

Parameter:

```
title
file
```

### Download PDF

```http
GET /api/v1/document/download/{id}
```

Returns the PDF file for download.


## ▶️ Running the Project

Clone the repository:

```bash
git clone https://github.com/your-username/pdf-upload-download.git
```

Run the application:

```bash
mvn spring-boot:run
```

