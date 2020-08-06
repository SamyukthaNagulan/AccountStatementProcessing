<%@ page isELIgnored="false"%>
<html>
<head>
<title>Spring MVC user registration and login example using
	JdbcTemplate + MySQL</title>
<style>
body {
	background-color: #009cde;
	background-image: radial-gradient(circle farthest-side at center bottom, #009cde, #003087
		125%);
	color: #fff;
	text-align: center;
	color: white;
}

input[type=file] {
	font-size: 20px;
	align: center;
	padding: 20px;
	padding-left: 150px;
}

input[type=submit] {
	border:0;
	outline: 0;
	width: 6em;
	height: 2em;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}
</style>
</head>

<body>
	<br />
	<h1>${msg}</h1>
	<br>
	<h2>Choose the file to upload</h2>
	<form action="FileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="guru_file" size="50" accept=".csv" /> <br />
		<br /> <input type="submit" value="Upload" />
	</form>

</body>
</html>