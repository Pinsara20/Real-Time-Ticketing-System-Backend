# Real-Time Ticketing System Backend

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction
The Real-Time Ticketing System Backend is designed to support the operations of a ticketing system. It offers real-time capabilities to handle ticket sales, cancellations, and updates efficiently.

## Features
- Real-time updates on ticket availability
- User authentication and authorization
- Ticket booking and cancellation
- Payment processing
- Administrative dashboard

##CLI
### Configuration
The configuration is stored in a `configuration.json` file. The configuration includes:
- Total number of tickets
- Ticket release rate (tickets per interval)
- Customer retrieval rate (customers per interval)
- Maximum ticket capacity

### Usage
1. Run the `Main` class.
2. If a previous configuration exists, choose to use it or enter a new configuration.
3. The system will start with the configured number of vendors and customers.
4. Enter number 01 to exit the application.

## Technologies Used
- **Programming Language:** Node.js
- **Framework:** Express.js
- **Database:** MongoDB
- **Real-Time Communication:** Socket.io
- **Authentication:** JWT (JSON Web Tokens)
- **Payment Gateway:** Stripe

## Installation
## Frontend

### Installation
1. Navigate to the frontend directory:
   ```sh
   cd frontend
   1. Install the dependencies:
npm install
2. Running the Application```sh
  1.Start the development server:
    npm start
  2.Open your browser and navigate to http://localhost:

## Backend
Installation
1. Navigate to the backend directory:
  cd backend
2. Build the project using Maven:
  mvn clean install

Running the Application
1. Start the Spring Boot application:
  mvn spring-boot:run
2. The backend server will start on http://localhost:8080.

## API Endpoints
1. Configuration(`configuration.json`)
  POST /ticketing/configConfigure the ticketing system.Request Body: 
    {
       "totalTickets": 1000,
      "ticketReleaseRate": 10,
      "customerRetrievalRate": 5,
      "maxTicketCapacity": 500
    }

2. Start/Stop Simulation
   
  i. POST /ticketing/start  
  
    Start the ticketing simulation.
  ii. POST /ticketing/stop  
  
    Stop the ticketing simulation.

3. Customer Count
  POST /ticketing/customer-count
    Set the number of customers.Request Body:
    ```json
    {
      "customerCount": 100
    }

5. Vendor Count```sh
  POST /ticketing/vendor-count
    Set the number of vendors.
    Request Body:
    ```json
    {
      "vendorCount": 10
    }

## Usage
After setting up the project, you can use the following commands:
- **Start development server:** `npm run dev`

## API Documentation
The API documentation provides detailed information about the available endpoints, request parameters, and responses. [[Link to API Documentation](https://learning.postman.com/docs/designing-and-developing-your-api/developing-an-api/defining-an-api/)](#)


