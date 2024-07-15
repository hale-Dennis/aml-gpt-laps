# Product Management System

## Overview

The Product Management System is a web application built using Spring Boot, Spring Data JPA, Thymeleaf, and Bootstrap. It provides functionalities for managing products, including adding, viewing, editing, and deleting products. The application also supports searching, pagination, and sorting of products.

## Features

- **Add New Products**: Users can add new products by providing details such as name, description, price, quantity, category, brand, weight, dimensions, and image URL.
- **View Products**: A list of all products is displayed on the main page, with options to view detailed information about each product.
- **Edit Products**: Users can edit the details of existing products.
- **Delete Products**: Users can delete products from the list.
- **Search Products**: Users can search for products by name or description.
- **Pagination**: The product list is paginated to improve usability and performance.
- **Sorting**: Products can be sorted by different attributes such as name, price, etc.

## Technologies Used

- **Spring Boot**: A framework to simplify the development of web applications.
- **Spring Data JPA**: Provides easy integration with relational databases and simplifies data access.
- **Thymeleaf**: A server-side Java template engine for web applications.
- **Bootstrap**: A front-end framework for building responsive and mobile-first websites.
- **MySQL**: A relational database management system for storing product data.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- MySQL

## Endpoints

- **GET /products** - View list of products
- **GET /products/new** - Show form to add a new product
- **POST /products** - Add a new product
- **GET /products/{id}** - View detailed information about a product
- **GET /products/edit/{id}** - Show form to edit a product
- **POST /products/edit/{id}** - Update a product
- **POST /products/delete/{id}** - Delete a product
- **GET /products/search** - Search for products

## Usage

### Adding a New Product

1. Click on the "Add New Product" button on the main page.
2. Fill in the product details and click "Add".

### Viewing a Product

1. Click on the "View" button next to a product in the list.
2. The detailed information about the product will be displayed.

### Editing a Product

1. Click on the "Edit" button next to a product in the list.
2. Update the product details and click "Update".

### Deleting a Product

1. Click on the "Delete" button next to a product in the list.
2. Confirm the deletion.

### Searching for Products

1. Enter the search term in the search box on the main page.
2. Click the "Search" button to view the search results.

