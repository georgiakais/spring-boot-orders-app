# MyData Application - Setup Guide

## Introduction

This project is a Spring Boot application that interacts with a PostgreSQL database.

## Prerequisites

Before you begin, make sure you have the following installed on your system:

- [Java 11](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [DBeaver (Optional, for managing your database)](https://dbeaver.io/)

## Step 1: Install java
1. Install Java by downloading it from [AdoptOpenJDK](https://adoptopenjdk.net/) or [Oracle JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Follow the installer instructions to complete the installation.
3. Set the environment variable `JAVA_HOME`:
   - Path: `C:\Program Files\Java\jdk-11`.
4. Add `JAVA_HOME\bin` to your system's `Path`.
5. Open a Command Prompt and verify the installation by running:
   ```bash
   java -version
   
## Step 2: Install Maven
1. Download Maven from the official [Apache Maven website](https://maven.apache.org/download.cgi).
   - Select the **Binary zip archive** for your operating system.
2. Extract the downloaded ZIP file to a directory of your choice, e.g., `C:\Program Files\Maven`.
3. Set up the environment variable `MAVEN_HOME`:
   - Path: `C:\Program Files\Maven`.
4. Add the Maven `bin` directory to your system's `Path`:
   - Path: `C:\Program Files\Maven\bin`.
5. Open a Command Prompt and verify the Maven installation by running:
   ```bash
   mvn -v
   
## Step 3: Install PostgreSQL

1. **Download and install PostgreSQL**:
    - Visit the [PostgreSQL official website](https://www.postgresql.org/download/) and follow the instructions to install it on your machine.

2. **Create a database**:
    - After installation, open **pgAdmin** or use the **psql** command-line interface to create a database.
    - Create a new database with the name `my_Data`.

   Example SQL command to create the database:
   ```sql
   CREATE DATABASE my_Data;
      ```

3. **Configure Database Connection**:
    - Navigate to `src/main/resources/application.properties` in the extracted project folder.
    - Update the following details with your PostgreSQL connection information:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/my_Data
   spring.datasource.username=username
   spring.datasource.password=password
   spring.datasource.driverClassName=org.postgresql.Driver
   ```
   Important: Replace your_username and your_password with the PostgreSQL username and password you use to connect to the database.

## Step 4: Build the Project Using Maven

1. Open your terminal/command prompt.
2. Navigate to the project directory.
3. Run the following Maven command:
   ```bash
   mvn clean install
   ```

## Step 5: Run the Project

1. In the terminal, run:
   ```bash
   mvn spring-boot:run
   ```
   Alternatively, you can run the project directly from your IDE as follows:

1. **In IntelliJ IDEA**:
   - Right-click on the `MyDataApplication.java` file (or the main class).
   - Select **Run 'MyDataApplication'**.

2. **In Eclipse**:
   - Right-click on the project in the **Project Explorer**.
   - Select **Run As > Spring Boot App**.
   
3. Access the application at `http://localhost:8080`.
   Liquibase Database Migrations
   Note: The project uses Liquibase for managing database schema changes and initial data population. You do not need to manually run any SQL scripts.

### Liquibase Database Migrations

#### NOTE: When the application starts, Liquibase will automatically apply the necessary changes to the database, including:

1. Creating the users and orders tables.
2. Inserting the initial data, such as the admin user and regular users, as well as their orders.

## Testing the Application with Postman

After running the project, you can interact with your Spring Boot application using Postman, a popular tool for testing APIs.

1. **Start the application**:
   - Make sure your application is running at `http://localhost:8080`.

2. **Use Postman to test endpoints**:
   - You can use a **POST request** to register users, or interact with the orders endpoints, depending on your API setup.

### Example JSON for Testing:

To test , use the following JSON body in Postman:

- METHOD: post
- URL: http://localhost:8080/api/orders
- Request Body:
```json
{
  "description": "New Order",
  "productName":"New Order",
  "amount": 100.0,
  "user": {
    "id": 1
  }
}
```
- Expected Response:
```json
  "id:" 3,
  "createdAt": "2025-01-26T18:01:15.6770763",
  "productName": "New Order",
  "amount": 100.0,
  "quantity": 0,
  "description": "New Order",
  "user": {
  "id": 1,
  "createdAt": "2025-01-26T15:16:37.696373",
  "username": "admin",
  "password": "$2a$10$iawRIG8kOksY6bK2GCcEXeaSwgYZxMot9TtvsX4v2xd2DOEudnBl6",
  "role": "ADMIN"
  }
  }
```
