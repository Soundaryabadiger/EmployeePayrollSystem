<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Admin Login</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container" style="width:40%; margin-top:100px;">

<h2>Admin Login</h2>

<form action="LoginServlet" method="post">

Username:

<input type="text"
name="username"
required>

Password:

<input type="password"
name="password"
required>

<input type="submit"
value="Login">

</form>

</div>

</body>
</html>