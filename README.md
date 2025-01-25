# PhD Management System

A web application for managing PhD student (doctorant) data with administrative features.

## Features

- Administrator authentication
- Bulk import of doctorant data via Excel
- Doctorant data management
- Secure API endpoints
- Pagination and search functionality

## Tech Stack

- Backend: Spring Boot 3.x
- Frontend: vite+react
- Database: PostgreSQL
- Security: Spring Security
- Excel Processing: Apache POI

## Prerequisites

- Java 17
- Maven
- PostgreSQL

## Setup

1. Clone the repository
```bash
git clone <repository-url>
```

2. Configure database in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/phd_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
java -jar target/phd-0.0.1-SNAPSHOT.jar
```

## Excel Import Format
Column order for doctorant import:
1. Name
2. Date of Birth
3. Place of Birth
4. CIN
5. CNE
6. Email
7. Phone Number
