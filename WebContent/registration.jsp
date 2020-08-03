<%@ page isELIgnored="false"%>
<html>
<head>
<title>E-Statement</title>
</head>
<body>
<img src="pic1.jpg">
	<br>
	<h1>E-STATEMENT PROCESSING</h1>
	<br>
	<h2>Registration Form</h2>
	<form action="register" method="post">
		<pre>
		<br>
	    <strong>Register Here | <a href="login.jsp">Click here to Login</a></strong>
		<br>
		User Id: <input type="text" name="userId" />
		<br>
		Password: <input type="password" name="password" />
		<br>
		<input type="submit" value="Register" />
	</pre>
	</form>
	
	${msg}


</body>
</html>