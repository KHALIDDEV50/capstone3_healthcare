# 🏥 Healthcare Management System

A **Spring Boot REST API** for managing healthcare data, medication schedules, and health assessments.

The project is built using **Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, Jakarta Validation, and Lombok**.

---

# 📌 Project Overview

The Healthcare Management System is a backend application designed to manage user healthcare information.

The project currently includes:

- 💊 Medication Schedule Management
- 🩺 Health Assessment Management
- 👤 User-based healthcare data
- 🗄️ MySQL database integration
- ✅ DTO validation
- ⚠️ Custom exception handling
- 🔄 CRUD operations
- 📅 Date management
- 🤖 AI-related health assessment data

---

# 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring Web | REST API |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| MySQL | Database |
| Jakarta Validation | Request Validation |
| Lombok | Reduce Boilerplate Code |
| Maven | Dependency Management |
| Postman | API Testing |
| Git & GitHub | Version Control |

---

# 📂 Project Structure

```text
src
└── main
    └── java
        └── com.example.capstone3
            │
            ├── API
            │   ├── ApiException
            │   └── ApiResponse
            │
            ├── Advice
            │   └── ControllerAdvice
            │
            ├── Controller
            │   ├── MedicationScheduleController
            │   └── HealthAssessmentController
            │
            ├── DTO
            │   ├── MedicationScheduleRequestDTO
            │   └── HealthAssessmentRequestDTO
            │
            ├── Model
            │   ├── MedicationSchedule
            │   └── HealthAssessment
            │
            ├── Repository
            │   ├── MedicationScheduleRepository
            │   └── HealthAssessmentRepository
            │
            ├── Service
            │   ├── MedicationScheduleService
            │   └── HealthAssessmentService
            │
            └── Capstone3Application
```

---

# 🗄️ Database

The project uses **MySQL**.

### Database

```text
healthcare
```

### Database Configuration

Configure the database in:

```text
src/main/resources/application.properties
```

```properties
spring.application.name=Capstone3

spring.datasource.url=jdbc:mysql://localhost:3306/healthcare
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.web.error.include-message=always
spring.web.error.include-stacktrace=always
```

---

# 💊 Medication Schedule

The `MedicationSchedule` module manages medication schedules for users.

### Fields

```text
id
userId
medicationName
dosage
mealRelation
times
startDate
endDate
isActive
createdAt
updatedAt
```

### Times Field

The `times` field is stored in MySQL as a JSON column while Java handles it as a `String`.

```java
@Column(columnDefinition = "json")
private String times;
```

Example:

```json
"times": "[\"08:00\", \"14:00\", \"20:00\"]"
```

---

## Medication Schedule API

### Get All Medication Schedules

```http
GET /api/v1/medication-schedule/get
```

### Get Medication Schedule By ID

```http
GET /api/v1/medication-schedule/get/{id}
```

### Get Medication Schedules By User ID

```http
GET /api/v1/medication-schedule/user/{userId}
```

### Get Active Medication Schedules

```http
GET /api/v1/medication-schedule/user/{userId}/active
```

### Add Medication Schedule

```http
POST /api/v1/medication-schedule/add
```

Example Request:

```json
{
    "userId": 1,
    "medicationName": "Panadol",
    "dosage": "500mg",
    "mealRelation": "After Meal",
    "times": "[\"08:00\", \"14:00\", \"20:00\"]",
    "startDate": "2026-10-05",
    "endDate": "2026-10-10",
    "isActive": true
}
```

### Update Medication Schedule

```http
PUT /api/v1/medication-schedule/update/{id}
```

### Delete Medication Schedule

```http
DELETE /api/v1/medication-schedule/delete/{id}
```

---

# 🩺 Health Assessment

The `HealthAssessment` module manages health assessments associated with users.

### Fields

```text
id
userId
previousAssessmentId
attachments
userNotes
profileSnapshot
extractedValues
aiConclusion
trend
isCurrent
assessmentDate
nextDueDate
createdAt
```

### JSON Fields

The following fields are stored as MySQL JSON columns:

```java
@Column(columnDefinition = "json")
private String attachments;

@Column(columnDefinition = "json")
private String profileSnapshot;

@Column(columnDefinition = "json")
private String extractedValues;
```

---

## Health Assessment API

### Get All Health Assessments

```http
GET /api/v1/health-assessment/get
```

### Get Health Assessment By ID

```http
GET /api/v1/health-assessment/get/{id}
```

### Get Health Assessments By User ID

```http
GET /api/v1/health-assessment/user/{userId}
```

### Get Current Health Assessment

```http
GET /api/v1/health-assessment/user/{userId}/current
```

### Add Health Assessment

```http
POST /api/v1/health-assessment/add
```

Example Request:

```json
{
    "userId": 1,
    "previousAssessmentId": null,
    "attachments": "[\"blood-test.pdf\"]",
    "userNotes": "Feeling better than last week",
    "profileSnapshot": "{\"weight\":75,\"height\":175}",
    "extractedValues": "{\"bloodPressure\":\"120/80\"}",
    "aiConclusion": "The assessment indicates stable health.",
    "trend": "STABLE",
    "isCurrent": true,
    "assessmentDate": "2026-10-05",
    "nextDueDate": "2026-11-05"
}
```

### Update Health Assessment

```http
PUT /api/v1/health-assessment/update/{id}
```

### Delete Health Assessment

```http
DELETE /api/v1/health-assessment/delete/{id}
```

---

# ⚠️ Exception Handling

The project uses a custom:

```text
ApiException
```

with centralized exception handling through:

```text
ControllerAdvice
```

The project handles several types of exceptions, including:

- `ApiException`
- `MethodArgumentNotValidException`
- `ConstraintViolationException`
- `SQLIntegrityConstraintViolationException`
- `InvalidDataAccessResourceUsageException`
- `DataIntegrityViolationException`
- `HttpRequestMethodNotSupportedException`
- `HttpMessageNotReadableException`
- `MethodArgumentTypeMismatchException`
- `RuntimeException`

---

# ✅ Validation

Validation is implemented in the **DTO layer** using Jakarta Validation.

Example:

```java
@NotNull(message = "User ID is required")
private Long userId;
```

```java
@NotBlank(message = "Medication name is required")
@Size(max = 255, message = "Medication name must not exceed 255 characters")
private String medicationName;
```

Validation is activated in the Controller using:

```java
@RequestBody @Valid
```

The Entity classes are kept focused on database structure, while validation is handled through DTOs.

---

# 🏗️ Project Architecture

The project follows a simple layered architecture:

```text
              Client / Postman
                     │
                     ▼
                Controller
                     │
                     ▼
                    DTO
                     │
                     ▼
                  Service
                     │
                     ▼
                Repository
                     │
                     ▼
                   JPA
                     │
                     ▼
                  MySQL
```

### Controller

Responsible for:

- Receiving HTTP requests
- Calling the Service
- Returning HTTP responses

### DTO

Responsible for:

- Receiving request data
- Validating request data

### Service

Responsible for:

- Business logic
- Creating objects
- Updating objects
- Deleting objects
- Handling `ApiException`

### Repository

Responsible for:

- Database operations
- Querying data

### Model

Represents the database tables.

### Advice

Responsible for centralized exception handling.

---

# 🧪 API Testing

The APIs are tested using **Postman**.

## Medication Schedule Testing

The following operations have been tested:

```text
1. Add Medication Schedule
2. Get All Medication Schedules
3. Get Medication Schedule By ID
4. Get Medication Schedules By User ID
5. Get Active Medication Schedules
6. Update Medication Schedule
7. Delete Medication Schedule
```

## Health Assessment Testing

Testing sequence:

```text
1. Add Health Assessment
2. Get All Health Assessments
3. Get Health Assessment By ID
4. Get Health Assessments By User ID
5. Get Current Health Assessment
6. Update Health Assessment
7. Delete Health Assessment
```

---

# 🚀 How to Run

### 1. Clone the Repository

```bash
git clone YOUR_REPOSITORY_URL
```

### 2. Open the Project

Open the project using:

```text
IntelliJ IDEA
```

### 3. Create the Database

Open MySQL and run:

```sql
CREATE DATABASE healthcare;
```

### 4. Configure Database Credentials

Update:

```text
src/main/resources/application.properties
```

with your MySQL username and password.

### 5. Run the Application

Run:

```text
Capstone3Application
```

The application runs by default on:

```text
http://localhost:8080
```

---

# 📊 Current Project Status

## Completed

- [x] Spring Boot Project Setup
- [x] MySQL Database Configuration
- [x] Medication Schedule Model
- [x] Medication Schedule DTO
- [x] Medication Schedule Repository
- [x] Medication Schedule Service
- [x] Medication Schedule Controller
- [x] Medication Schedule CRUD Testing
- [x] Health Assessment Model
- [x] Health Assessment DTO
- [x] Health Assessment Repository
- [x] Health Assessment Service
- [x] Health Assessment Controller

## In Progress

- [ ] Health Assessment API Testing
- [ ] Additional Business Logic
- [ ] Additional Validation
- [ ] AI Integration
- [ ] Authentication & Authorization
- [ ] API Documentation
- [ ] Full System Integration Testing

---

# 👥 Team Project

This project is developed as a team-based **Java Spring Boot Capstone Project**.

The project follows shared development standards for:

- Naming conventions
- Project structure
- DTO validation
- Exception handling
- REST API design
- Database structure
- Git & GitHub workflow

---

# 📄 License

This project was developed for educational and training purposes as part of a Java / Spring Boot Capstone Project.
