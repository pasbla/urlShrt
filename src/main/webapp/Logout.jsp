<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Refresh" content="1;url=index.jsp">
<link rel="stylesheet" href="./css/style.css">
<title>Logout</title>
</head>
<body>

	<% session.invalidate(); %>
	<div id="login-form-wrap">

		<p>You have been successfully logged out</p>
		<div id="create-account-wrap">
			<p>
			 
			<p>
		</div>


	</div>

</body>
</html>