
# Travel Nest Account Microservice

## Overview

Travel Nest Account is a **microservice** that provides functionalities for managing attractions and user ticketing related to the main **Travel Nest** application. This service is responsible for handling the backend operations, such as retrieving information about attractions, managing tickets, and providing security features like JWT-based user authentication.

It operates as a helper service and is integrated into the larger Travel Nest ecosystem, allowing it to serve the necessary data and facilitate user interactions for the Travel Nest platform.

## Features

- **City List Retrieval**: Provides a list of cities available in the system.
- **Attraction Management**: Allows for retrieving and adding attractions to the system.
- **Ticket Management**: Provides ticket purchasing functionality for attractions and allows users to manage their tickets.
- **User Authentication**: Integrates JWT-based authentication and authorization to secure endpoints and provide role-based access.
- **Admin Operations**: Provides admin-level access to delete attractions and manage ticket records.

## Technologies Used

- **Spring Boot**: The microservice is built using Spring Boot to provide fast development, ease of integration, and scalability.
- **JWT (JSON Web Token)**: Used for secure authentication and authorization in the application.
- **Spring Security**: Handles the security configurations, ensuring secure access to the service endpoints.
- **JPA / Hibernate**: Used for interaction with the MySQL database and persistence layer.
- **MySQL**: Used as the database for storing attraction and ticket data.
- **ModelMapper**: For mapping between DTOs and entities.

## Requirements

- **Java 17** or higher
- **MySQL** 8 or higher
- **Gradle** (for dependency management and building the project)

## Setup Instructions

### Step 1: Clone the Repository
Clone the Travel Nest Account repository to your local machine:

```bash
git clone https://github.com/your-username/travel-nest-account.git
```

### Step 2: Setup Environment Variables

Ensure you have the following environment variables configured:

- `db_username`: Database username
- `db_password`: Database password
- `MYSQL_HOST`: The hostname of your MySQL server (default: `localhost`)
- `MYSQL_PORT`: The port for your MySQL server (default: `3306`)
- `JWT_SECRET`: Secret key used for JWT authentication.

### Step 3: Database Configuration

Make sure that MySQL is running and you have created a database called `travel_nest_attractions`. You can use the `spring.datasource.url` in the `application.properties` to create the database automatically if it does not exist.

### Step 4: Build the Application

Build the application using Gradle:

```bash
./gradlew build
```

### Step 5: Run the Application

To run the application locally:

```bash
./gradlew bootRun
```

The application will start on port **8081** by default.

### Step 6: Test the API

You can test the various endpoints using Postman or any other API testing tool.

#### Example Endpoints:

- **Get Cities**: `GET /attraction/cities`
- **Get Attraction List**: `GET /{attraction-type}/list`
- **Get Attraction Details**: `GET /attraction/details/{uuid}`
- **Buy Tickets**: `POST /attraction/details/{uuid}`
- **Add Attraction**: `POST /{attraction-type}/add`
- **Delete Attraction**: `DELETE /attraction/delete/{uuid}`

## Security

This microservice uses **JWT (JSON Web Token)** for authentication. Make sure to generate a valid JWT token before accessing protected routes. The token can be obtained through the main Travel Nest application.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
