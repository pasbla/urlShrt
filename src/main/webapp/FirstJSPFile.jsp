<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
  <br/>
  <br/>
<%
	//import java.io.IOException;
	//import java.io.PrintWriter;
	//import java.net.URL;
	
	String url = request.getRequestURL().toString();
	String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
%>  
	Your mainURL is: <a href=<%=baseURL%>> <%=baseURL%></a>

 
 
	
</body>
</html>