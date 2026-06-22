<%@ page import="model.Employee" %>
<%@ page import="model.Attendance" %>
<%@ page import="model.Payroll" %>
<%@ page import="model.Leave" %>
<%@ page import="java.util.List" %>

<%

String admin =
(String) session.getAttribute("admin");

if(admin == null) {

    response.sendRedirect("login.jsp");
}

Employee emp =
(Employee) request.getAttribute("employee");

List<Attendance> attendanceList =
(List<Attendance>)
request.getAttribute("attendanceList");

List<Payroll> payrollList =
(List<Payroll>)
request.getAttribute("payrollList");

List<Leave> leaveList =
(List<Leave>)
request.getAttribute("leaveList");

String photoName =
emp.getPhoto();

if(photoName == null || photoName.equals("")) {

    photoName = "default.png";
}

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Employee Profile</title>

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

<h2>Employee Profile</h2>

<img
src="images/<%= photoName %>"
width="150"
height="150"

style="
border-radius:50%;
margin-bottom:20px;
border:3px solid #2563eb;">

<br><br>

<form action="UpdatePhotoServlet"
method="post"
enctype="multipart/form-data">

<input type="hidden"
name="empId"
value="<%= emp.getEmpId() %>">

<input type="file"
name="photo"
required>

<br><br>

<input type="submit"
value="Upload Photo">

</form>

<br>

<h3>

Name:
<%= emp.getName() %>

</h3>

<p>

<b>Email:</b>
<%= emp.getEmail() %>

</p>

<p>

<b>Department:</b>
<%= emp.getDepartment() %>

</p>

<p>

<b>Salary:</b>
Rs. <%= emp.getSalary() %>

</p>

<p>

<b>Joining Date:</b>
<%= emp.getJoiningDate() %>

</p>

<hr>

<div style="margin-bottom:25px;">

<a href="MarkAttendanceServlet?empId=<%= emp.getEmpId() %>&status=Present"

style="
background-color:green;
color:white;
padding:10px 20px;
text-decoration:none;
border-radius:5px;
margin-right:15px;
display:inline-block;
margin-bottom:15px;">

Present

</a>

<a href="MarkAttendanceServlet?empId=<%= emp.getEmpId() %>&status=Absent"

style="
background-color:red;
color:white;
padding:10px 20px;
text-decoration:none;
border-radius:5px;
display:inline-block;
margin-bottom:15px;">

Absent

</a>

</div>

<div style="margin-bottom:30px;">

<a href="GeneratePayrollServlet?empId=<%= emp.getEmpId() %>"

style="
background-color:#2563eb;
color:white;
padding:10px 20px;
text-decoration:none;
border-radius:5px;
display:inline-block;">

Generate Payroll

</a>

<button onclick="printPayroll()"

style="
background-color:#16a34a;
color:white;
padding:10px 20px;
border:none;
border-radius:5px;
cursor:pointer;
margin-left:15px;">

Export PDF

</button>

</div>

<hr>

<h3>Apply Leave</h3>

<form action="ApplyLeaveServlet"
method="post">

<input type="hidden"
name="empId"
value="<%= emp.getEmpId() %>">

<label>Leave Date</label>

<input type="date"
name="leaveDate"
required>

<label>Reason</label>

<input type="text"
name="reason"
required>

<br><br>

<input type="submit"
value="Apply Leave">

</form>

<hr>

<h3>Attendance History</h3>

<table>

<tr>

<th>Date</th>
<th>Status</th>

</tr>

<%

if(attendanceList != null &&
!attendanceList.isEmpty()) {

for(Attendance att : attendanceList) {

%>

<tr>

<td>

<%= att.getAttendanceDate() %>

</td>

<td>

<%= att.getStatus() %>

</td>

</tr>

<%

}

} else {

%>

<tr>

<td colspan="2"
style="text-align:center;">

No Attendance Records Found

</td>

</tr>

<%

}

%>

</table>

<hr>

<h3>Payroll History</h3>

<table>

<tr>

<th>Month</th>
<th>Present Days</th>
<th>Absent Days</th>
<th>Total Salary</th>

</tr>

<%

if(payrollList != null &&
!payrollList.isEmpty()) {

for(Payroll payroll : payrollList) {

%>

<tr>

<td>

<%= payroll.getMonth() %>

</td>

<td>

<%= payroll.getPresentDays() %>

</td>

<td>

<%= payroll.getAbsentDays() %>

</td>

<td>

Rs. <%= payroll.getTotalSalary() %>

</td>

</tr>

<%

}

} else {

%>

<tr>

<td colspan="4"
style="text-align:center;">

No Payroll Records Found

</td>

</tr>

<%

}

%>

</table>

<hr>

<h3>Leave History</h3>

<table>

<tr>

<th>Leave Date</th>
<th>Reason</th>
<th>Status</th>

</tr>

<%

if(leaveList != null &&
!leaveList.isEmpty()) {

for(Leave leave : leaveList) {

%>

<tr>

<td>

<%= leave.getLeaveDate() %>

</td>

<td>

<%= leave.getReason() %>

</td>

<td>

<%= leave.getStatus() %>

</td>

</tr>

<%

}

} else {

%>

<tr>

<td colspan="3"
style="text-align:center;">

No Leave Records Found

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


function printPayroll() {

    window.print();
}
</script>

</body>

</html>