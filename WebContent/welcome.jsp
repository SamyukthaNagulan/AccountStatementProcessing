<%@ page isELIgnored="false"%>
<html>
<head>
<title>Spring MVC user registration and login example using JdbcTemplate + MySQL</title>
</head>

<body>

	${msg}
	<br>
	File: <br />
<form action="FileUpload" method="post"
                        enctype="multipart/form-data">
<input type="file" name="guru_file" size="50" />
<br />
<input type="submit" value="Upload" />
	</form>

</body>
</html>