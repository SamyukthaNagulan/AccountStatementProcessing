<%@ page isELIgnored="false"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<br>
	<br>
	<center><h1>Login Form</h1>

	<br>
	<br>
	<form action="login" method="post">
		<pre>
		 <strong>Login Here | <a href="registration.jsp">Click here to Register</a></strong>
		<br>
		User Id: <input type="text" name="userId" />
		<br>
		Password: <input type="password" name="password" />
		<br>
		<input type="submit" value="Login" />
		</pre>
	</form>
	</center>
	${msg}

</body>
</html>