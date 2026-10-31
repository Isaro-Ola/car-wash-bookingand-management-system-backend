# Car Wash Booking and Management System (Backend)

**Student:** Isaro Muhirwa Ola
**Student ID:** 26878
**Course:** INSY 8322 Web Technology and Internet, Mid-Term Project
**Project:** Car Wash Booking and Management System

REST API built with Spring Boot, Spring Data JPA and PostgreSQL for booking and managing car washes.

## How to run

1. Install Java 17 or higher and PostgreSQL.
2. Create a PostgreSQL database named `carwash`.
3. Open `src/main/resources/application.properties` and replace `YOUR_POSTGRES_PASSWORD` with your PostgreSQL password.
4. From the project folder run `./mvnw spring-boot:run` (on Windows: `mvnw spring-boot:run`).
5. The API starts on `http://localhost:8080` and Hibernate creates the tables automatically.
6. Import `car-wash-bookingand-management-system-midterm.postman_collection.json` into Postman to test the endpoints.
