# Coding Convention

## 1. Purpose

This document defines the coding conventions and development rules used in the Personal Finance Manager project.

The purpose is to keep the code consistent, readable, maintainable, and easier to review.

## 2. Backend Coding Convention

### 2.1 Package Structure

The backend follows a layered package structure:
```text
com.personalfinance.manager
├── config
├── controller
├── service
├── repository
├── entity
├── dto
└── exception
```
- `config`: Application and security configuration.

- `controller`: Handles HTTP requests and responses.

- `service`: Contains business logic.

- `repository`: Handles database access.

- `entity`: Represents database entities.

- `dto`: Represents data transferred between the client and server.

- `exception`: Contains custom exceptions and exception handling.

### 2.2 Naming Convention



The project follows standard Java naming conventions.



- Classes use PascalCase.

- Methods use camelCase.

- Variables use camelCase.

- Constants use UPPER_SNAKE_CASE.

- Package names use lowercase.

### 2.3 Class Responsibilities



Each layer should have a clear responsibility.



- Controller handles HTTP requests and responses.

- Service contains business logic.

- Repository handles database operations.

- Entity represents database data.

- DTO represents data transferred between the client and server.

### 2.4 Method Convention



Methods should have clear and descriptive names.



Use verbs that describe the operation.



Examples:



- `createExpense()`

- `updateExpense()`

- `deleteExpense()`

- `getExpenseById()`

- `getAllExpenses()`

### 2.5 DTO Convention



DTOs are used to transfer data between the client and server.



DTO names should describe their purpose.



Examples:



- `CreateExpenseRequest`

- `UpdateExpenseRequest`

- `ExpenseResponse`

- `LoginRequest`

- `LoginResponse`

### 2.6 Exception Handling



Exceptions should be handled consistently across the application.



Custom exceptions should be used for expected business errors.



Global exception handling should be used to return consistent API error responses.

## 3. Frontend Coding Convention



### 3.1 Component Naming



React components use PascalCase.



Examples:



- `ExpenseList`

- `ExpenseForm`

- `Dashboard`

- `LoginForm`

### 3.2 Variable and Function Naming



Variables and functions use camelCase.



Examples:



- `expenseList`

- `totalExpense`

- `fetchExpenses()`

- `handleSubmit()`

- `calculateBalance()`

## 4. Database Naming Convention



### 4.1 Table Naming



Database table names use snake_case.



Examples:



- `users`

- `expenses`

- `expense_categories`

- `incomes`

### 4.2 Column Naming



Database column names use snake_case.



Examples:



- `user_id`

- `category_id`

- `expense_amount`

- `created_at`

- `updated_at`

## 5. Git Convention



### 5.1 Branch Naming



The project uses the following branch naming convention:



- `main`: Stable production-ready code.

- `develop`: Main development branch.

- `feature/*`: New features.

- `bugfix/*`: Bug fixes.

- `release/*`: Release preparation.

### 5.2 Commit Messages



Commit messages should follow the Conventional Commits format.



Examples:



- `feat: add expense creation`

- `fix: validate expense amount`

- `docs: update API documentation`

- `test: add expense service tests`

- `refactor: simplify expense service`

- `chore: configure GitHub Actions`

## 6. General Rules



- Code should be readable and easy to understand.

- Avoid unnecessary duplication.

- Keep methods focused on a single responsibility.

- Do not commit passwords, API keys, or other secrets to Git.

- Configuration that differs between environments should use environment variables.

- Code should be reviewed and tested before merging into `develop`.

