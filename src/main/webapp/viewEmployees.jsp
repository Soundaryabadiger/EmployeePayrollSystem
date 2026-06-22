<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>

<%

String admin =
(String) session.getAttribute("admin");

if(admin == null) {

    response.sendRedirect("login.jsp");
}

List<Employee> list =
(List<Employee>) request.getAttribute("employees");

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>View Employees</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/style.css">

<style>

.search-box {

    margin-bottom:25px;
}

.search-box input[type=text] {

    width:300px;

    padding:10px;

    border-radius:5px;

    border:1px solid gray;
}

.search-box input[type=submit] {

    margin-left:10px;
}

.profile-link {

    text-decoration:none;

    font-weight:bold;

    color:#2563eb;
}

.dark-mode .profile-link {

    color:#60a5fa;
}

</style>

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

<h2>Employee List</h2>

<form action="SearchEmployeeServlet"
method="get"
class="search-box">

<input type="text"
name="keyword"
placeholder="Search by Name or Department">

<input type="submit"
value="Search">

</form>

<table>

<tr>

<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Department</th>
<th>Salary</th>
<th>Joining Date</th>
<th>Actions</th>

</tr>

<%

if(list != null && !list.isEmpty()) {

for(Employee emp : list) {

%>

<tr>

<td>

<%= emp.getEmpId() %>

</td>

<td>

<a href="EmployeeProfileServlet?id=<%= emp.getEmpId() %>"
class="profile-link">

<%= emp.getName() %>

</a>

</td>

<td>

<%= emp.getEmail() %>

</td>

<td>

<%= emp.getDepartment() %>

</td>

<td>

Rs.
<%= emp.getSalary() %>

</td>

<td>

<%= emp.getJoiningDate() %>

</td>

<td>

<a class="action-btn edit-btn"

style="margin-right:12px;"

href="EditEmployeeServlet?id=<%= emp.getEmpId() %>">

Edit

</a>

<a class="action-btn delete-btn"

onclick="return confirm('Are you sure you want to delete this employee?');"

href="DeleteEmployeeServlet?id=<%= emp.getEmpId() %>">

Delete

</a>

</td>

</tr>

<%

}

} else {

%>

<tr>

<td colspan="7"
style="text-align:center;
padding:20px;">

No Employees Found

</td>

</tr>

<%

}

%>

</table>

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