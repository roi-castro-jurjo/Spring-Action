# Spring-Action

## Overview
This application is designed to manage and process purchase stock purchases, integrating a payment processing system. It follows a microservices architecture pattern, specifically utilizing the Saga pattern for distributed transactions, ensuring data consistency and reliability throughout the purchase lifecycle.

## Features
- **Purchase Order Creation**: Allows creating purchase orders with validation of necessary details.
- **Asynchronous Payment Processing**: Implements asynchronous payment processing with simulated random success or failure, reflecting real-world scenarios.
- **Saga Pattern Implementation**: Ensures transactional integrity across microservices.

## Technical Stack
- **Spring Boot**: For building the microservices.
- **MongoDB**: As the database for storing purchase and stock details.