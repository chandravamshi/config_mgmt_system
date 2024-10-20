# Backend

## Overview
This project provides an API for managing  user and configuration records. It allows users and users and to create, read, update, and delete configuration entries through a RESTful interface. The backend is containerized with Docker, making it easy to deploy and manage.

### Prerequisites

Ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

### Installation

1. **Clone the repository** (if not already done):

   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Navigate to the backend directory**:

   ```bash
   cd backend
   ```

3. **Build and start the Docker containers**:

   ```bash
   docker-compose up --build
   ```


### API Endpoints for Users

Once the Docker containers are running, you can access the API at `http://localhost:8081`.

#### User Registration

To create a new user, send a POST request to the following endpoint:

```
POST http://localhost:8081/api/users/register
```

#### Request Body

You need to include a JSON object in the request body with the following structure:

```json
{
    "username": "qwertss",
    "password": "password133ss",
    "role": "test"
}
```

### Example Request

Here’s an example using `curl`:

```bash
curl -X POST http://localhost:8081/api/users/register \
-H "Content-Type: application/json" \
-d '{
    "username": "qwertss",
    "password": "password133ss",
    "role": "test"
}'
```
### API Endpoints for Config

* you can access the API at `http://localhost:8082`.

- **Get All Config Records**

  Retrieve all configuration records:

  ```
  GET http://localhost:8082/api/configs/
  ```

- **Create a New Config Record**

  To create a new configuration record, send a POST request:

  ```
  POST http://localhost:8082/api/configs/
  ```

  **Request Body**:

  ```json
  {
      "configKey": "testkey",
      "value": "testvalue",
      "description": "testdescription"
  }
  ```

- **Update an Existing Config Record**

  To update a specific configuration record (e.g., with ID `1`), send a PUT request:

  ```
  PUT http://localhost:8082/api/configs/1
  ```

  **Request Body**:

  ```json
  {
      "configKey": "updatedkey",
      "value": "updatedvalue",
      "description": "updateddescription"
  }
  ```

- **Delete a Config Record**

  To delete a specific configuration record (e.g., with ID `1`), send a DELETE request:

  ```
  DELETE http://localhost:8082/api/configs/1
  ```

### Example Requests

Here are example requests using `curl` for each operation:

- **Get All Config Records**:

  ```bash
  curl -X GET http://localhost:8082/api/configs/
  ```

- **Create New Config Record**:

  ```bash
  curl -X POST http://localhost:8082/api/configs/ \
  -H "Content-Type: application/json" \
  -d '{
      "configKey": "testkey",
      "value": "testvalue",
      "description": "testdescription"
  }'
  ```

- **Update Config Record**:

  ```bash
  curl -X PUT http://localhost:8082/api/configs/1 \
  -H "Content-Type: application/json" \
  -d '{
      "configKey": "updatedkey",
      "value": "updatedvalue",
      "description": "updateddescription"
  }'
  ```

- **Delete Config Record**:

  ```bash
  curl -X DELETE http://localhost:8082/api/configs/1
  ```

---
---

# Frontend


## Config Dashboard 

## Overview

The Config Dashboard is a web application built with Angular that allows users to manage configuration records easily. Users can create, read, update, and delete configuration entries through a user-friendly interface.


### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Node.js](https://nodejs.org/) (including npm)
- [Angular CLI](https://angular.io/cli)

### Installation

1. **Clone the repository** (if you haven't already):

   ```bash
   git clone <repository-url>
   cd config-dash
   ```

2. **Install dependencies**:

   ```bash
   npm install
   ```

3. **Run the application**:

   ```bash
   ng serve
   ```

4. **Open your browser** and navigate to `http://localhost:4200`.

### Logging In

Please Use the following credentials to log in or else it wont work:

- **Username**: `user`
- **Password**: `password`

Once logged in, you will be directed to the dashboard where you can manage configuration records.

### Dashboard Features

The dashboard provides the following functionalities:

- **Create Configuration Records**: Add new configuration entries.
- **Read Configuration Records**: View existing configuration entries.
- **Update Configuration Records**: Modify existing entries.
- **Delete Configuration Records**: Remove unwanted entries.

### Example Usage

After logging in, you will see an interface that allows you to perform CRUD operations on configuration records. Follow these steps:

1. **Create a New Record**:
   - Fill out the form with the required details and submit.

2. **Read Existing Records**:
   - View all current records displayed on the dashboard.

3. **Update a Record**:
   - Select an existing record, modify its details, and save changes.

4. **Delete a Record**:
   - Choose a record to delete and confirm the action.


