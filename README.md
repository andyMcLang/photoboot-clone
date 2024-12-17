# Photoz Clone Application

A simple **Spring Boot** RESTful API for uploading, downloading, and managing photos. The project uses **H2 Database**, supports file uploads, and serves as a foundation for a photo management system.

---

## Features
- **Upload photos** as binary data (BLOB) with metadata (name and content type).
- **Download photos** with HTTP headers for file name and content type.
- **Retrieve photo details** and list all photos.
- **Delete photos** by ID.
- **CORS configuration** for frontend access.

---

## Technologies Used
- **Spring Boot** (REST API, Web MVC)
- **H2 Database** (In-memory/file-based database for development)
- **Spring Data JPA** (CRUD operations)
- **Maven** (Build tool)
- **Jakarta Validation** (Field validation)
- **MultipartFile** (File uploads)
- **CORS Configuration**

---

## Prerequisites
- **Java 17+** installed
- **Maven** (or Maven Wrapper provided: `./mvnw` or `mvnw.cmd`)
- **Postman** or similar tool for testing API endpoints

---

## Getting Started

---

### 1. Clone the Repository

git clone https://github.com/andyMcLang/photoz-clone.git
cd photoz-clone

---

### 2. Run the Application
Use Maven Wrapper to build and run the application:

./mvnw clean package

java -jar target/photoz-clone-0.0.1-SNAPSHOT.jar

Alternatively, run the application using your IDE (e.g., IntelliJ or Eclipse).

---

### Database Configuration
The application uses an H2 Database stored as a file.

Location: \~/springboot (configured in application.properties).

Console Access: H2 Console

JDBC URL: jdbc:h2:file:\~/springboot;AUTO_SERVER=TRUE

Username: (empty)

Password: (empty)

Database Schema

The schema is created using bacschema.sql:

create table if not exists photoz (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    file_name varchar(255),
    content_type varchar(255),
    data binary large object
);

---

## API Endpoints
### 1. Root Endpoint
GET /
Response: "Hello World!"

---

### 2. Retrieve All Photos
GET /photoz
Response: List of all photos' metadata.

---

### 3. Retrieve Photo by ID
GET /photoz/{id}
Response: JSON object containing photo details.

---

### 4. Upload a Photo
POST /photoz
Request:
MultipartFile: data (file binary content)
Response: Metadata of the uploaded photo.
Example using cURL:

curl -X POST "http://localhost:8080/photoz" \
     -F "data=@example.jpg"

---

### 5. Download a Photo
GET /download/{id}
Response: File download with correct Content-Disposition headers.

---

### 6. Delete a Photo
DELETE /photoz/{id}
Response: HTTP Status 200 OK on success.

---

## CORS Configuration
The application allows requests from http://localhost:63342 for all HTTP methods:

Configured in WebConfig using CorsRegistry.

---

## Testing the Application
Run the backend server.
Use Postman, cURL, or similar tools to interact with the API.
Upload, retrieve, download, and delete photos using the provided endpoints.

---

## Future Improvements
Add security (e.g., JWT authentication).
Store photos in a cloud service (e.g., AWS S3).
Add unit tests and integration tests.
Build a frontend UI using React or Angular.

---

## License
This project is licensed under the MIT License.

---

## Author
Andreas Lang

GitHub: andyMcLang
