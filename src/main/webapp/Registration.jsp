<%@page import="javax.lang.model.util.Elements.Origin"%>
<%@page import="com.mysql.cj.*"%>
<%@page import="database.DatabaseAccessServlet"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="database.DatabaseAccess"%>
<%@page import="java.time.*"%>
<%@page import="java.util.Random"%>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Refresh" content="5;url=index.jsp">
	<title>Registration page</title>
	<link rel="stylesheet" href="./css/style.css">
</head>
<body>

 	<div id="login-form-wrap">
		<form id="login-form" action="Registration.jsp">
			<p>
				<%
				String userName = request.getParameter("username");
				String email = request.getParameter("email");
				String passwd = request.getParameter("password");
				try {
					DatabaseAccessServlet DbServ = new DatabaseAccessServlet();
					if(DbServ.saveNewUserToDatabase(userName, email, passwd)){
						out.println("Registered successfully");
					} else {;
						out.println("Could not register. <br/> Your email is registered or there was problem with connection");
					}
				} catch (Exception e) {
					//out.println("Error message: " + e.toString());
				}
				%>

			</p>
			<p></p>
			<p></p>
		</form>
		<div id="create-account-wrap">
			<p>This page will be automatically redirected<p>
		</div>
	</div>

</body>
</html>