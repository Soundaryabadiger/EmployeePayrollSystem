<%

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

<title>Add Employee</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="navbar">

<div class="nav-left">

<a href="dashboard.jsp">Dashboard</a>

<a href="addEmployee.jsp">Add Employee</a>

<a href="ViewEmployeesServlet">View Employees</a>

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

<h2>Add Employee</h2>

<form action="AddEmployeeServlet"

method="post"

enctype="multipart/form-data">

<label>Name</label>

<input type="text"
name="name"
required>

<label>Email</label>

<input type="email"
name="email"
required>

<label>Department</label>

<input type="text"
name="department"
required>

<label>Salary</label>

<input type="text"
name="salary"
required>

<label>Joining Date</label>

<input type="date"
name="joiningDate"
required>

<label>Photo</label>

<input type="file"
name="photo">

<br><br>

<input type="submit"
value="Add Employee">

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
        "☀️";
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
        "☀️";
    }
};

</script>

</body>

</html>