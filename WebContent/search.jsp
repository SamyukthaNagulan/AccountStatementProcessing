<%@ page isELIgnored="false"%>
<html>
<head>
<title>SEARCH</title>
<style>
body {
	background-color: #009cde;
	background-image: radial-gradient(circle farthest-side at center bottom, #009cde, #003087
		125%);
	color: #fff;
	text-align: center;
}

input[type=text] {
	width: 20%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
	outline:0;
	align: center;
	font-size:20px;
}

input[type=submit] { 
	width: 6em;
	height: 2em;  
	outline:0;
	size:20px;
	font-size: 20px;
	background-color: white;
	border-radius: 40px/39px;
}


</style>
</head>

<body>
	<br />
	<br>
	<%
		String msg = (String) request.getAttribute("message");
	out.println(msg);
	%>
	<br><br><br><br>
	<form action="SearchServlet" method="post">
			Enter your ledger balance 
			<input type="text" name="balance">
			<br><br><br>
			Enter your account number 
			<input type="text" name="accno"> 
			<br><br><br>
			<input type="submit">
	</form>

</body>
</html>