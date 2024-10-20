# Backend

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
