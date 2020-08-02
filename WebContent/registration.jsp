<%@ page isELIgnored="false"%>
<html>
<head>
<title>E-Statement</title>
</head>
<body>
<img src="pic1.jpg">
	<br>
	<h1><center>E-STATEMENT PROCESSING</center></h1>
	<br>
	<h2><center>Registration Form</center></h2>
	<center>
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
	</center>
	${msg}


</body>
</html>