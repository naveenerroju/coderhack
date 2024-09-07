# CoderHack

## Overview

`CoderHack` is a RESTful API service developed using Spring Boot to manage the leaderboard for a coding contest platform. The application allows users to register, update their scores, and receive virtual badges based on their scores. The service uses MongoDB to store and manage user data and badges.

---

## Problem Description

This project manages the leaderboard for a single contest on the coding platform. Users can register, update their scores, and earn badges based on their score ranges. It also offers CRUD operations to manage user registrations and score updates.

---

## Requirements

- **CRUD Operations**: Full support for Create, Read, Update, and Delete operations on users.
  
- **User Fields**:
  - **User ID** (Unique Identifier)
  - **Username**
  - **Score** (0 <= Score <= 100)
  - **Badges** (Possible Values: Code Ninja, Code Champ, Code Master)

- **User Registration**:
  - User registration requires a **User ID** and **Username**.
  - On registration, the score is set to **0** and badges are empty.

- **Score Updates**:
  - Only the **Score** field is updatable via PUT requests.
  - Badges are awarded automatically based on the score:
    - 1 <= Score < 30 → **Code Ninja**
    - 30 <= Score < 60 → **Code Champ**
    - 60 <= Score <= 100 → **Code Master**

- **User Retrieval**:
  - User data is retrievable and sorted by score in descending order.

- **Badge Validation**:
  - Each user can have at most 3 unique badges.
  - Valid: {Code Ninja, Code Champ, Code Master}
  - Invalid: {Code Ninja, Code Ninja, Code Master}

- **Performance**: Sorting must be done with time complexity of **O(nlogn)**.

---

## Endpoints

### 1. Retrieve All Users
- **GET** `/users`
- **Description**: Fetches all registered users, sorted by their score.

### 2. Retrieve a Specific User
- **GET** `/users/{userId}`
- **Description**: Fetches details of a specific user using their unique `User ID`.

### 3. Register a New User
- **POST** `/users`
- **Description**: Registers a new user in the contest. The request body must contain a unique `User ID` and `Username`. 

### 4. Update User Score
- **PUT** `/users/{userId}`
- **Description**: Updates the score of a specific user. Badges are updated based on the new score.

### 5. Delete a User
- **DELETE** `/users/{userId}`
- **Description**: Deregisters the user from the contest and removes them from the leaderboard.

---

## Badge Awarding Logic

| Score Range           | Awarded Badge  |
|-----------------------|----------------|
| 1 <= Score < 30       | Code Ninja     |
| 30 <= Score < 60      | Code Champ     |
| 60 <= Score <= 100    | Code Master    |

A user can receive multiple badges based on score progression.

---

## Error Handling

- **Validation**: Score must be between 0 and 100. Other constraints include the unique constraint on the user’s `User ID`.
- **Error Codes**:
  - **400 Bad Request**: When invalid input is provided, such as an invalid score.
  - **404 Not Found**: When trying to retrieve or update a non-existent user.
  - **409 Conflict**: When a user with the same ID already exists.

---

## Technologies

- **Java 17**
- **Spring Boot 3.3.3**
- **MongoDB**
- **Maven**
- **JUnit 5**: For testing

---

## Setup and Running the Application

### Prerequisites

- Java 17+
- Maven 3+
- MongoDB
- Postman (Optional, for testing)

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/naveenerroju/coderhack.git
   cd coderhack
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   The API will be available at `http://localhost:8080`.

---

## Testing

### JUnit Test Cases
Basic test cases have been written using JUnit for the following functionalities:
- **User Registration**
    - Verifies that a user is registered with a score of `0` and respective badge.
- **Score Update**
    - Verifies that the score updates correctly and that the appropriate badges are awarded.
- **User Deletion**
    - Verifies that a user is deleted successfully.

### Run Tests
To run the tests, use:
```bash
mvn test
```

---

## Postman Collection

For easier testing, import the following [Postman Collection](https://www.postman.com/coderhack-leaderboard-api-testing).

---

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── coderhack
│   │   │   │   │   ├── controller
│   │   │   │   │   ├── service
│   │   │   │   │   ├── repository
│   │   │   │   │   ├── model
│   ├── test
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── coderhack
├── pom.xml
└── README.md
```

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Author

- **Naveen Kumar**  
  [naveenkumarerroju@gmail.com](mailto:naveenkumarerroju@gmail.com)

---

## Additional Resources

1. **Spring Boot Documentation**: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
2. **MongoDB Documentation**: [https://docs.mongodb.com/](https://docs.mongodb.com/)
3. **Postman Documentation**: [https://learning.postman.com/docs/getting-started/introduction/](https://learning.postman.com/docs/getting-started/introduction/)

