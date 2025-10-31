# 🧩 API Endpoints - Herbarium Backend

Tests for Herbarium Backend API endpoints.

## 🤔 Overview

Herbarium will be a full-stack web application that allows users to explore a comprehensive database of medicinal plants, save their favorites in a personal dashboard, and create and manage their own herbal recipes and preparation methods.

## 🚀 Quick Start

- **Base URL**: `http://localhost:8080/api`
- **Test with**: [Postman](https://www.postman.com/) collection included in this folder

##  🌟 Available Endpoints

### 1. User Registration

#### **Register a new user account**

- **URL**: `/register`
- **Method**: `POST`
- **Content-Type**: `application/json`

##### **Request Example:**

```json
{
  "email": "user@example.com",
  "password": "c2VjcmV0MTIz",        // Must be Base64 encoded!
  "confirmPassword": "c2VjcmV0MTIz"  // Must be Base64 encoded!
}
```

##### ✅ **Success Response (201)**

```json
{
  "message": "User registered successfully",
  "email": "user@example.com",
  "userId": 1
}
```

##### 🛑 **Error Response (400)**

```json
{
  "message": "Registration failed: Email already registered",
  "email": "user@example.com", 
  "userId": null
}
```

### ⛔ Common Errors

- `Email already registered` - Email exists in database.
- `Email should be valid` - Invalid email format.
- `Passwords do not match` - Password fields don't match.
- `Password must be at least 6 characters` - Password too short.
- `Invalid password encoding` - Password not properly Base64 encoded.

## 💡 Testing Tips

1. Use the [Postman](https://www.postman.com/) collection for easy testing.
2. Remember to encode passwords in Base64.
3. Start your Spring Boot server first (`./mvnw spring-boot:run`).
