<%@ page isELIgnored="false"%>
<html>
<head>
<title>E-Statement</title>
<style>
body {
	background-color: #009cde;
	background-image: radial-gradient(circle farthest-side at center bottom, #009cde, #003087
		125%);
	color: #fff;
	text-align: center;
	color: white;
}

form {
	width: 90%;
}

pre {
	font-color: white;
	font-size: 20px;
}

input[type=text] {
	outline:0;
	width: 12em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}

input[type=email] {
	outline:0;
	width: 12em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}

strong {
	font-size: 14px;
	color: white;
}

a {
	color: white;
	font-size: 14px;
}

input[type=password] {
	outline:0;
	width: 12em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}

input[type=submit] {
	outline:0;
	width: 6em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}
</style>
</head>
<br>
<h1>E-STATEMENT PROCESSING</h1>
<body>
	<br>
	<br>
	<h2>Registration Form</h2>
	<form action="register" method="post">
		<pre>
		<br>
		User Name: <input type="text" name="userId" />
		<br>
		Email id: <input type="email" name="email" />
		<br />
		Password: <input type="password" name="password" />
		<br>
		<input type="submit" value="Register" />
		<br />
	    <strong>Already an user? | <a href="login.jsp">Click here to Login</a></strong>
	</pre>
	</form>

	${msg}


</body>
</html>