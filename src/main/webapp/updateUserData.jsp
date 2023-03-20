<%@page import="jakarta.websocket.SendResult"%>
<%@page import="com.mysql.cj.*"%>
<%@page import="database.DatabaseAccessServlet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="jakarta.servlet.http.HttpServletResponse"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<link rel="stylesheet" type="text/css" href="./css/css3.css">
<title>Your Account</title>
</head>
<body>
	<div id="main-form-wrap">
		<div id="big-container">
			<header>
				<nav>
					<ul>
						<%
						String userName = (String) session.getAttribute("user");
						String userMail = (String) session.getAttribute("userMail");
						if (userName != null) {
							out.print("<li><a href=\"index.jsp\" >Home</a></li>");
							out.print("<li><a href=\" # \"> Your Account " + "" + "</a></li>");
							out.print("<li><a href=\"Logout.jsp\" class=\"highlight\" > Logout " + userName + "</a></li>");
						} else {
							response.sendRedirect("login.html");
							out.print("<li><a href=\"index.jsp\" >Home</a></li>");
							out.print("<li><a href=\"login.html\" class=\"highlight\"> Login </a></li>");
						}
						%>
					</ul>
				</nav>

			</header>


			<br />
			<div id="history-container" style="height: 50em">
  
				Unfortunately this functionality is not developed yet...

			</div>
		</div>
	</div>


</body>
</html>