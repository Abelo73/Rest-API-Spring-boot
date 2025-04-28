# Ticketing System API

This is a simple Ticketing System API built with **Spring Boot**, **PostgreSQL**, and **Swagger UI**. The application allows you to manage tickets, which can be created, updated, fetched, and deleted. It includes functionality such as pagination and status management for the tickets.

## Features

- Create, update, and delete tickets.
- Fetch all tickets with pagination.
- Track ticket creation and update times automatically.
- Use of Swagger UI for API documentation.
- Response structure wrapped in `BaseResponseDTO` for consistency.

## Technologies Used

- **Spring Boot**: Backend framework.
- **Spring Data JPA**: For ORM and database interaction.
- **PostgreSQL**: Database for storing ticket data.
- **Swagger UI**: For API documentation and testing.
- **JUnit + MockMvc**: For unit testing.

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/Abelo73/ticketing-system.git
