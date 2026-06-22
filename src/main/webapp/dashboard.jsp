<%@ page import="java.util.Map" %>

<%

String admin =
(String) session.getAttribute("admin");

if(admin == null) {

    response.sendRedirect("login.jsp");
}

Integer totalEmployees =
(Integer) request.getAttribute("totalEmployees");

Integer presentToday =
(Integer) request.getAttribute("presentToday");

Integer absentToday =
(Integer) request.getAttribute("absentToday");

Double totalPayroll =
(Double) request.getAttribute("totalPayroll");

Map<String,Integer> departmentCounts =

(Map<String,Integer>)
request.getAttribute("departmentCounts");

if(totalEmployees == null) {

    totalEmployees = 0;
}

if(presentToday == null) {

    presentToday = 0;
}

if(absentToday == null) {

    absentToday = 0;
}

if(totalPayroll == null) {

    totalPayroll = 0.0;
}

StringBuilder labels =
new StringBuilder();

StringBuilder values =
new StringBuilder();

if(departmentCounts != null) {

for(Map.Entry<String,Integer> entry
: departmentCounts.entrySet()) {

labels.append("'")
.append(entry.getKey())
.append("',");

values.append(
entry.getValue())
.append(",");
}
}

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Dashboard</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/style.css">

<script src=
"https://cdn.jsdelivr.net/npm/chart.js">
</script>

<style>

.dashboard-cards {

    display:flex;
    gap:25px;
    flex-wrap:wrap;
    margin-top:30px;
}

.card {

    width:230px;
    padding:30px;
    border-radius:12px;
    color:white;
    font-size:22px;
    font-weight:bold;
    text-align:center;
    transition:0.3s;
}

.card:hover {

    transform:translateY(-5px);
}

.blue {

    background:#2563eb;
}

.green {

    background:#16a34a;
}

.red {

    background:#dc2626;
}

.purple {

    background:#7c3aed;
}

.nav-buttons {

    margin-top:40px;
}

.nav-buttons a {

    text-decoration:none;
    color:white;
    background:#2563eb;
    padding:12px 22px;
    border-radius:6px;
    margin-right:15px;
    transition:0.3s;
}

.nav-buttons a:hover {

    background:#1d4ed8;
}

.chart-container {

    display:flex;
    gap:30px;
    flex-wrap:wrap;
    margin-top:50px;
}

.chart-box {

    flex:1;

    max-width:420px;

    height:350px;

    background:white;

    padding:20px;

    border-radius:10px;

    margin:auto;
}

.dark-mode .chart-box {

    background:#1e293b;
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

<span id="themeIcon">&#9790;</span>

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

<h2>HR Management Dashboard</h2>

<div class="dashboard-cards">

<div class="card blue">

Total Employees

<br><br>

<%= totalEmployees %>

</div>

<div class="card green">

Present Today

<br><br>

<%= presentToday %>

</div>

<div class="card red">

Absent Today

<br><br>

<%= absentToday %>

</div>

<div class="card purple">

Total Payroll

<br><br>

Rs.
<%= String.format("%.2f", totalPayroll) %>

</div>

</div>

<div class="nav-buttons">

<a href="addEmployee.jsp">

Add Employee

</a>

<a href="ViewEmployeesServlet">

Manage Employees

</a>

</div>

<div class="chart-container">

<div class="chart-box">

<h3>Attendance Statistics</h3>

<canvas id="attendanceChart"></canvas>

</div>

<div class="chart-box">

<h3>Employees By Department</h3>


<canvas id="departmentChart"
style="max-height:280px;"></canvas>

</div>

</div>

</div>

<script>

const ctx =
document.getElementById(
'attendanceChart');

new Chart(ctx, {

    type: 'bar',

    data: {

        labels: [

        'Present',
        'Absent'

        ],

        datasets: [{

            label:
            'Attendance Statistics',

            data: [

            <%= presentToday %>,
            <%= absentToday %>

            ],

            backgroundColor: [

            '#22c55e',
            '#ef4444'

            ],

            borderWidth: 1
        }]
    },

    options: {

        responsive: true
    }
});

const deptCtx =
document.getElementById(
'departmentChart');

new Chart(deptCtx, {

    type: 'pie',

    data: {

        labels: [

        <%= labels.toString() %>

        ],

        datasets: [{

            data: [

            <%= values.toString() %>

            ],

            backgroundColor: [

            '#2563eb',
            '#16a34a',
            '#dc2626',
            '#7c3aed',
            '#f59e0b',
            '#0ea5e9',
            '#14b8a6'

            ],

            borderWidth: 1
        }]
    },

    options: {

        responsive: true
    }
});

function toggleTheme() {

    document.body.classList.toggle(
    "dark-mode");

    let themeText =
    document.getElementById(
    "themeText");

    let themeIcon =
    document.getElementById(
    "themeIcon");

    if(document.body.classList.contains(
    "dark-mode")) {

        localStorage.setItem(
        "theme",
        "dark");

        themeText.innerHTML =
        "Dark Mode";

        themeIcon.innerHTML =
        "&#9790;";

    } else {

        localStorage.setItem(
        "theme",
        "light");

        themeText.innerHTML =
        "Light Mode";

        themeIcon.innerHTML =
        "&#9728;";
    }

    location.reload();
}

window.onload = function() {

    let theme =
    localStorage.getItem(
    "theme");

    let themeText =
    document.getElementById(
    "themeText");

    let themeIcon =
    document.getElementById(
    "themeIcon");

    if(theme === "dark") {

        document.body.classList.add(
        "dark-mode");

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