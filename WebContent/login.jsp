<%@ page isELIgnored="false"%>
<html>
<head>
<title>LOGIN</title>
<style>
body {
	background-color: #009cde;
	background-image: radial-gradient(circle farthest-side at center bottom, #009cde, #003087
		125%);
	color: #fff;
	text-align: center;
	color: white;
}

strong {
	font-size: 14px;
	color: white;
}

a {
	color: white;
	font-size: 14px;
}

form {
	width: 90%;
}

pre {
	font-size: 20px;
}

input[type=text] {
	outline:0;
	text-align:center;
	width: 12em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}

input[type=password] {
	outline:0;
	text-align:center;
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
	background-color: lightviolet;
	border-radius: 40px/39px;
}

button {
	outline:0;
	width: 6em;
	height: 2em;
	font-size: 20px;
	background-color: lightviolet;
	border-radius: 40px/39px;
}
</style>
</head>
<body>
	<br>
	<br>
	<h1>Login Form</h1>

	<br>
	<br>
	<form action="login" method="post">
		<pre>
		 <strong>Login Here | <a href="registration.jsp">Click here to Register</a></strong>
		<br>
		Email Id: <input type="text" name="email" />
		<br>
		Password: <input type="password" name="password" />
		<br>
		<input type="submit" value="Login" />
		</pre>
	</form>

	${msg}

</body>
</html>