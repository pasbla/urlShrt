<%@page import="javax.lang.model.util.Elements.Origin"%>
<%@page import="com.mysql.cj.*"%>
<%@page import="database.DatabaseAccessServlet"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="database.DatabaseAccess"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Refresh" content="2;url=index.jsp"> <!-- redirext to index.jsp after x sec. -->
	<meta charset="ISO-8859-1">
	<title>Login  page</title>
	<link rel="stylesheet" href="./css/style.css">
</head>
<body>

	<%
	String email = request.getParameter("email");
	String passwd = request.getParameter("password");
	%>
	<div id="login-form-wrap">
		<form id="login-form" action="Login.jsp">
			<p>
				<%
				DatabaseAccessServlet DbServ = new DatabaseAccessServlet();
				try {
					String userName = DbServ.isUserValid(email, passwd); //check if there is user with pass valid
					if (userName != null) {
						session.setAttribute("user", userName);
						session.setAttribute("userMail", email);
						out.println("Hello " + userName + "!<br/>You have been logged in successfully");
					} else {
						out.println("Login failed<br/>");
					}
				} catch (Exception e) {
					out.println("Error message: " + e.toString());
				}
				%>
			</p>
			<p></p>
			<p></p>
		</form>
		<div id="create-account-wrap">
			<p>
			<p>
		</div>

	</div>
</body>
</html>