<%@page import="com.mysql.cj.x.protobuf.Mysqlx.ErrorOrBuilder"%>
<%@page import="database.DatabaseAccessServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Open from database</title>
</head>
<body>
<br/>
<%@
	page 	import="java.time.*" 
			import="java.util.Random" 
			import="jakarta.servlet.RequestDispatcher"
			import="jakarta.servlet.http.HttpServlet"
			import="jakarta.servlet.http.HttpServletRequest"
			import="jakarta.servlet.http.HttpServletResponse"
			import="java.io.IOException"
			import="java.io.PrintWriter"
			import="java.net.URL"
			import="java.sql.Connection"
			import="java.sql.DriverManager"
			import="java.sql.PreparedStatement"
			import="java.sql.ResultSet"
			import="java.sql.SQLException"
			import="java.sql.Statement"
			import="java.util.Date"
			import="java.net.*"
%>

<%
	String url = request.getRequestURL().toString();
	String requestUri = request.getRequestURI();
	String contextPath = request.getContextPath();
	String generatedString = requestUri.substring(contextPath.length()+3,requestUri.length()); // generatedString == substring from contextpath
%>
<%-- 	<br /> 
	Your URL is: <%=url %><br/>
	Your requestURI is: <%=requestUri%><br /> 
	Your contextPath is: <%=contextPath%><br /> 
	GeneratedString is:<%=generatedString %>		
	<br/><br/> --%>
<%
	DatabaseAccessServlet DbServ = new DatabaseAccessServlet();	
	String originalUrl = null;
	try{
		originalUrl = DbServ.readUrlFromDatabase(url);
		if (originalUrl != null){
			String protocol = originalUrl.substring(0, 4);
			if(protocol.equalsIgnoreCase("http")){
				response.sendRedirect(originalUrl);   		//automatic redirection to originalURL with http or https
			} else {
				response.sendRedirect("//"+originalUrl);   //automatic redirection to URL without http protocol
			}
		
		}else{
			out.println("No address in database");
		}
	} catch (Exception e) {
		out.println("No address in database (" + e.toString() + ") ");
	}
%>


</body>
</html>