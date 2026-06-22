# 🏦 Bank Management System

A desktop-based Bank Management System developed using Java, Swing, AWT, JDBC, and MySQL. This project simulates essential banking operations such as account creation, secure login, deposits, withdrawals, balance enquiries, transaction history, and PIN management.

## 📌 Overview

The Bank Management System provides a simple and secure banking environment where users can manage their accounts through a graphical user interface.

The project demonstrates:
- Java GUI development using Swing and AWT
- Database connectivity using JDBC
- MySQL database management
- Account authentication and security
- Transaction processing and logging

---

## ✨ Features

### 👤 User Management
- New Account Registration
- Multi-step Signup Process
- Secure Login Authentication
- Customer Information Storage

### 💰 Banking Operations
- Deposit Money
- Withdraw Money
- Balance Enquiry
- PIN Change
- Fast Cash Transactions
- Mini Statement Generation

### 🗄 Database Management
- Customer Information Storage
- Account Details Management
- Transaction Logging
- Real-Time Balance Updates

### 🔒 Security Features
- PIN-Based Authentication
- Unique Card Number Generation
- Account Validation
- Secure Database Connectivity

---

## 🛠 Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core Application Development |
| Swing | GUI Development |
| AWT | GUI Components |
| JDBC | Database Connectivity |
| MySQL | Database Management |
| XAMPP | Local Database Server |
| Eclipse IDE | Development Environment |

---

## 📂 Project Structure

```
Bank-Management-System/
│
├── MainApp.java
├── Database.java
├── Login.java
├── Signup.java
├── Signup2.java
├── Signup3.java
├── Deposit.java
├── Withdraw.java
├── FastCash.java
├── BalanceEnquiry.java
├── MiniStatement.java
├── PinChange.java
│
├── icons/
│   ├── bank.png
│   ├── card.png
│   └── backbg.png
│
└── database/
    └── bank.sql
```

---

## 🗃 Database Schema

### Tables Used

#### signup
Stores customer personal information.

Fields:
- form_no
- name
- fname
- dob
- gender
- email
- marital_status
- address
- city
- pincode
- state

#### signup2
Stores additional customer information.

Fields:
- religion
- category
- income
- education
- occupation
- pan
- aadhar
- senior_citizen
- existing_account

#### signup3
Stores account details.

Fields:
- account_type
- card_number
- pin
- facilities

#### login
Stores authentication credentials.

Fields:
- card_number
- pin
- account_type
- facilities

#### account
Stores account balance.

Fields:
- pin
- balance

#### transaction_log
Stores transaction history.

Fields:
- id
- pin
- type
- amount
- date

---

## 🔄 System Workflow

1. User Registration
2. Personal Details Collection
3. Additional Details Collection
4. Account Creation
5. Card Number & PIN Generation
6. Login Authentication
7. Banking Operations
8. Transaction Recording
9. Balance Updates

---

## ⚙️ Installation & Setup

### Prerequisites

- JDK 8 or above
- Eclipse IDE
- XAMPP Server
- MySQL Connector/J

### Step 1: Clone Repository

## Clone Repository

1. Git Clone
```bash
git clone https://github.com/Kamalesh-Suresh-Kumar/III-Object-Oriented-Programming-Using-Java.git
```
2. Open `mini project/`

### Step 2: Start MySQL

1. Open XAMPP Control Panel
2. Start MySQL Service

### Step 3: Create Database

```sql
CREATE DATABASE bank;
```

Import the SQL file into MySQL.

### Step 4: Add JDBC Driver

1. Download MySQL Connector/J
2. Add JAR to project build path
3. Configure library in Eclipse

### Step 5: Configure Database Connection

```java
private static final String URL =
"jdbc:mysql://localhost:3306/bank";

private static final String USER = "root";
private static final String PASSWORD = "";
```

Update credentials if required.

### Step 6: Run Project

Run:

```java
MainApp.java
```

---

## 📸 Project Screenshots

### Login Page
![Login Page](mini%20project/SCREEN%20SHOOT/LOGIN.png)

### Signup Page - Step 1
![Signup](mini%20project/SCREEN%20SHOOT/SIGNUP.png)

### Signup Page - Step 2
![Signup2](mini%20project/SCREEN%20SHOOT/SIGNUP2.png)

### Signup Page - Step 3
![Signup3](mini%20project/SCREEN%20SHOOT/SIGNUP3.png)

### Main Menu
![Main Menu](mini%20project/SCREEN%20SHOOT/MAIN%20MENU.png)

### Deposit Money
![Deposit](mini%20project/SCREEN%20SHOOT/DEPOITE.png)

### Withdraw Money
![Withdraw](mini%20project/SCREEN%20SHOOT/WITHDRAW.png)

### Balance Enquiry
![Balance](mini%20project/SCREEN%20SHOOT/BALANCE.png)

### Fast Cash / Credit
![Credit](mini%20project/SCREEN%20SHOOT/CREDIT.png)

### Change PIN
![PIN Change](mini%20project/SCREEN%20SHOOT/PIN.png)

### Mini Statement
![Mini Statement](mini%20project/SCREEN%20SHOOT/MINI.png)

---

## 🎯 Learning Outcomes

Through this project, I gained experience in:

- Java Swing GUI Design
- JDBC Connectivity
- MySQL Database Design
- SQL Queries
- Event Handling
- Object-Oriented Programming
- Banking System Workflow
- Database Normalization

---

## 🚀 Future Enhancements

- Online Fund Transfer
- Account Statements Export
- Email Notifications
- OTP Verification
- Mobile Banking Support
- Admin Dashboard
- Interest Calculation
- Loan Management Module
- Password Encryption
- Cloud Database Integration

---

## 👨‍💻 Author

**Kamalesh S P**

B.E. Computer Science and Engineering

Rajalakshmi Engineering College

Academic Year: 2023 – 2027

---

## 📚 References

- JDBC Connectivity Documentation
- MySQL Documentation
- Java Swing Documentation
- Java AWT Documentation
- Eclipse IDE Documentation

---

## 📄 License

This project is developed for educational and learning purposes only.