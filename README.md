# Employee Payroll System

A web-based HR & Payroll Management System built with Java JSP/Servlets and MySQL.

## Author
**Soundarya Badiger**
Electronics and Communication Engineering
KLE College of Engineering and Technology, Bengaluru
[LinkedIn](https://linkedin.com/in/soundarya-badiger-42875a268)

## Tech Stack
- Java (JSP, Servlets)
- MySQL (JDBC)
- HTML, CSS
- Apache Tomcat 9+

## Features
- Employee Login & Authentication
- Add / Edit / Delete Employees
- Mark Attendance
- Apply Leave
- Generate Payroll
- Employee Profile with Photo Upload
- Search Employee

## Project Structure
```
src/main/java/
├── controller/   → Servlets (Login, Dashboard, Employee CRUD, Payroll, etc.)
├── dao/          → Database Access Layer (EmployeeDAO)
├── model/        → POJOs (Employee, Payroll, Attendance, Leave)
└── util/         → DBConnection utility

src/main/webapp/
├── login.jsp
├── dashboard.jsp
├── addEmployee.jsp
├── editEmployee.jsp
├── viewEmployees.jsp
├── employeeProfile.jsp
└── WEB-INF/web.xml
```

## How to Run
1. Clone this repository
2. Import into Eclipse as **Dynamic Web Project**
3. Create MySQL database and run the SQL script
4. Update DB credentials in `src/main/java/util/DBConnection.java`
5. Deploy on **Apache Tomcat 9+**
6. Visit `http://localhost:8080/EmployeePayrollSystem/`
7. You will be redirected directly to the **Login Page**

## Architecture
This project follows **MVC Architecture** with **DAO Pattern**:
- **Model** → Java POJOs
- **View** → JSP Pages
- **Controller** → Servlets
- **DAO** → Database operations separated from business logic
