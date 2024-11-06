# payment-service

This is a Spring Boot application for managing payments, built with an H2 in-memory database and implementing the OpenAPI 3.0 specification. It follows the CQRS pattern and includes comprehensive validation for creating and retrieving payments.

## Prerequisites

To run this application, you’ll need:

- **Java 17** or higher
- **Maven 3.6** or higher

## Getting Started

Clone the repository to your local machine:

```bash
git clone https://github.com/CreativeAshu/payment-service.git
cd payment-service
```

## Running the Application
You can run the application directly using Maven:

```bash
mvn spring-boot:run
```
The application will start on http://localhost:8080


# API Endpoints
The following endpoints are available:

Create Payment - POST /payments
Create a new payment. Required fields include amount, currency, and counterparty.

Get Payments - GET /payments
Retrieve payments with optional filtering by minAmount and currencies

Example Requests
## Create Payment:

```bash
curl -X POST "http://localhost:8080/payments" -H "Content-Type: application/json" -d '{
  "amount": 100.00,
  "currency": "GBP",
  "counterparty": {
    "type": "SORT_CODE_ACCOUNT_NUMBER",
    "accountNumber": "12345678",
    "sortCode": "123456"
  }
}'
```

## Retrieve Payments:

```bash
curl -X GET "http://localhost:8080/payments?minAmount=10&currencies=GBP,USD" -H "accept: application/json"
```

# Contact:
For questions, please reach out to ashutosh.gupta.mca@gmail.com
