<%@ page import="model.Employee" %>

<%

Employee emp =
(Employee) request.getAttribute("employee");

String admin =
(String) session.getAttribute("admin");

if(admin == null) {

    response.sendRedirect("login.jsp");
}

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Edit Employee</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="navbar">

<div class="nav-left">

<a href="DashboardServlet">

Dashboard

</a>

<a href="addEmployee.jsp">

Add Employee

</a>

<a href="ViewEmployeesServlet">

View Employees

</a>

</div>

<div class="nav-right">

<button onclick="toggleTheme()"
class="theme-btn">

<span id="themeIcon">🌙</span>

<span id="themeText">

Dark Mode

</span>

</button>

<a href="LogoutServlet"
class="logout-btn">

Logout

</a>

</div>

</div>

<div class="container">

<h2>Edit Employee</h2>

<form action="UpdateEmployeeServlet"
method="post">

<input type="hidden"
name="empId"
value="<%= emp.getEmpId() %>">

<label>Name</label>

<input type="text"
name="name"
value="<%= emp.getName() %>"
required>

<label>Email</label>

<input type="email"
name="email"
value="<%= emp.getEmail() %>"
required>

<label>Department</label>

<input type="text"
name="department"
value="<%= emp.getDepartment() %>"
required>

<label>Salary</label>

<input type="text"
name="salary"
value="<%= emp.getSalary() %>"
required>

<label>Joining Date</label>

<input type="date"
name="joiningDate"
value="<%= emp.getJoiningDate() %>"
required>

<br><br>

<input type="submit"
value="Update Employee">

</form>

</div>

<script>

function toggleTheme() {

    document.body.classList.toggle("dark-mode");

    let themeText =
    document.getElementById("themeText");

    let themeIcon =
    document.getElementById("themeIcon");

    if(document.body.classList.contains("dark-mode")) {

        localStorage.setItem("theme", "dark");

        themeText.innerHTML =
        "Dark Mode";

        themeIcon.innerHTML =
        "&#9790;";

    } else {

        localStorage.setItem("theme", "light");

        themeText.innerHTML =
        "Light Mode";

        themeIcon.innerHTML =
        "&#9728;";
    }
}

window.onload = function() {

    let theme =
    localStorage.getItem("theme");

    let themeText =
    document.getElementById("themeText");

    let themeIcon =
    document.getElementById("themeIcon");

    if(theme === "dark") {

        document.body.classList.add("dark-mode");

        themeText.innerHTML =
        "Dark Mode";

        themeIcon.innerHTML =
        "&#9790;";

    } else {

        themeText.innerHTML =
        "Light Mode";

        themeIcon.innerHTML =
        "&#9728;";
    }
};

</script>

</body>

</html>