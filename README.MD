# Multishop

**Multishop** is an e-commerce application designed to manage customers, items, and orders for a multi-shop system. It utilizes the **Spring Framework** and **Hibernate** to provide a seamless experience for placing and managing orders with full transactional support.

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Contributing](#contributing)
- [License](#license)

## Overview

Multishop is a comprehensive e-commerce solution built using **Spring Framework** and **Hibernate**. It allows users to manage customers, items, and orders with full transactional support, ensuring data integrity during order placement. The system automatically rolls back in case of any failures during the transactional processes, ensuring the consistency of the database.

## Technologies Used

- **Java** (JDK 17)
- **Spring ** (for application framework)
- **Spring Data JPA** (for ORM with Hibernate)
- **Spring Transactions** (for managing transactional operations)
- **Hibernate** (for ORM)
- **MySQL** (or any relational database)
- **Maven** (for dependency management)
- **JUnit** (for testing)

## Features

- **Customer Management**: Create, update, and delete customers.
- **Item Management**: Manage inventory items (CRUD operations).
- **Order Management**: Place and manage orders with transactional support.
- **Transactional Rollbacks**: Ensures that any failure during an order placement rolls back all changes, maintaining data consistency.
- **Order Details**: View detailed information about each order and the associated items.

## Installation

### Prerequisites

- **Java 17**
- **Maven** (for managing dependencies)
- **MySQL** (or any other relational database)

### Steps

1. **Clone the repository**:

    ```bash
    git clone https://github.com/thisarunavod/Assignment-Spring-.git
    ```

2. **Navigate to the project directory**:

    ```bash
    cd multishop
    ```

3. **Configure the database**:

   Update the `application.properties` file in `src/main/resources/`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/multishopdb
    spring.datasource.username=******
    spring.datasource.password=******
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. **Install the dependencies**:

    ```bash
    mvn clean install
    ```

5. **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

## Running the Application

Once the application is running, you can access it at:

- **API URL**: `http://localhost:8080/MultiShop`

### Example Endpoints:

- **Customer API**:
    - `GET /api/customers`: Fetch all customers
    - `POST /api/customers`: Create a new customer

- **Order API**:
    - `POST /api/orders`: Place a new order
    - `GET /api/orders/{id}`: Fetch an order by ID

- **Item API**:
    - `GET /api/items`: Fetch all items
    - `PUT /api/items/{id}`: Update item details






This `README.md` reflects the usage of Spring Framework and Hibernate, and it includes detailed sections for installation, running the application, and handling APIs. You can further modify it based on any specific details of your Multishop project.
