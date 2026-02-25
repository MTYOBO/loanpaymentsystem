# Loan Payment System

A Spring Boot application for managing loans and payments, built with Maven.

## Description

This is a loan payment system that allows users to create loans and make payments against them. 
The system tracks loan balances and statuses, providing a RESTful API for loan and payment management.

## Features

- Create new loans
- Retrieve loan details
- Make payments against loans
- Automatic loan status management
- In-memory H2 database for development
- H2 console for database inspection

## Technologies Used

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **Spring Web MVC**
- **H2 Database**
- **Lombok**
- **Maven**

## Prerequisites

- Java 21 
- Maven 3.6+

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd loanpaymentsystem
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

## Running the Application

1. Start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

2. The application will start on `http://localhost:8080`

## API Endpoints

### Loans

- **POST** `/loans/` - Create a new loan
  - Request Body: Loan object
  - Response: Created loan details

- **GET** `/loans/{loanId}` - Get loan details
  - Path Parameter: `loanId` (Long)
  - Response: Loan details

### Payments

- **POST** `/payments/` - Make a payment
  - Request Body: Payment object with `paymentAmount` and `loanId`
  - Response: Payment details

## Database

The application uses an in-memory H2 database. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:dcbapp`
- Username: `sa`
- Password: `password`

## Testing

Run the tests using Maven:
```bash
./mvnw test
```

## Configuration

Application configuration can be found in `src/main/resources/application.properties`. Key configurations include:

- Database settings
- H2 console access
- JPA settings
