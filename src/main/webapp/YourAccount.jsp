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
			<div id="history-container">
				<form name="updateForm" method="post" action="updateUserData.jsp">
					<div class="account-details">
						Your name: <input type="text" name="newUserMail"
							value=<%=userName%>
							placeholder="Change this field if you want to update your account details" />
						<br />
					</div>
					<div class="account-details">
						Your mail: <input type="text" name="newUserMail"
							value=<%=userMail%>
							placeholder="Change this field if you want to update your account details" />
						<br />
					</div>
					<div class="account-details">
						Your current password: <input type="password"
							name="newUserPassword" value="xxxx" /> <br />
						<br /> Your new password: <input type="password"
							name="newUserPassword" placeholder="Type new password" /> <br />
						<br /> Repeat new password: <input type="password"
							name="newUserPassword" placeholder="Retype new password" /> <br />
					</div>
					<input type="submit" value="Update" style="margin-left: 65px" /> <br />
					<br />
				</form>

				<table class="history-table">
					<thead>
						<tr>
							<th>Shortened URL</th>
							<th>Original URL</th>
							<th>Expiration date</th>
						</tr>
					</thead>
					<tbody>
						<%
						DatabaseAccessServlet DbServ = new DatabaseAccessServlet();
						try {
							ResultSet resultSet = DbServ.readUserHistory(userMail);
							out.println("<h2>Your history: </h2>");
							while (resultSet.next()) {
								String shortenedUrl = resultSet.getString("ShortenedUrl");
								String oUrl = resultSet.getString("OriginalUrl");
								Date date = resultSet.getDate("ExpirationDate");
								String protocol = oUrl.substring(0, 4); //checking if there is "http" in the URL
								if (protocol.equalsIgnoreCase("http")) {
							out.print("<tr><td><a href=\"" + shortenedUrl + "\">" + shortenedUrl + "</td><td> <a href=\"" + oUrl + "\">"
									+ oUrl + "</a></td><td>" + date + "</td>"); //no adding "//" to oURL
								} else {
							//adding '//' to oryginalURL
							out.print("<tr><td><a href=\"" + shortenedUrl + "\">" + shortenedUrl + "</a> </td><td> <a href=\"//" + oUrl
									+ "\">" + oUrl + "</a></td><td>" + date + "</td></tr>");
								}
								
							}

						} catch (Exception e) {
							out.println("Error message: " + e.toString());
						}
						%>
					
						
					</tbody>
				</table>

			</div>
		</div>
	</div>


</body>
</html>