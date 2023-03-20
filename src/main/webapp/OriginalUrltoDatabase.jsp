<%@page import="javax.lang.model.util.Elements.Origin"%>
<%@page import="com.mysql.cj.*"%>
<%@page import="database.DatabaseAccessServlet"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="database.DatabaseAccess"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="./css/styles.css">
	<link rel="stylesheet" type="text/css" href="./css/css3.css">
	<meta charset="ISO-8859-1">
	<title>Generated short URL</title>
</head>
<body>
	<%@
	page 
		import="java.time.*" 
		import="java.util.Random"
		import="java.sql.*"
	%>
		
	<%!ResultSet resultSet = null;
	public String text = "text";%>

	<div id="main-form-wrap">
		<div id="big-container">
			<header>
				<!--     <a href="#" id="logo"><img src="images/logo.png" width="221" height="100" alt=""></a> -->
				<nav>
					<ul>

						<%
						
						String userName = (String) session.getAttribute("user");
						String userMail = (String) session.getAttribute("userMail");
						if (userName != null) { //checking if there is user logged in
							out.print("<li><a href=\"index.jsp\" >Home</a></li>");
							out.print("<li><a href=\"YourAccount.jsp\"> Your Account " + "" + "</a></li>");
							out.print("<li><a href=\"Logout.jsp\" > Logout " + userName + "</a></li>");
						} else {
							out.print("<li><a href=\"index.jsp\" >Home</a></li>");
							out.print("<li><a href=\"login.html\" class=\"highlight\"> Login </a></li>");
						}
						%>
					</ul>
				</nav>

			</header>

			<%
				int leftLimit = 48; // letter 'a'
				int rightLimit = 122; // letter 'z'
				int targetStringLength = 10; //length of the generatedUrl
				String OriginalUrl = request.getParameter("original_URL");
				String expTimeString = request.getParameter("expiration_time");
				String generatedURL = null;
				if (OriginalUrl == null || OriginalUrl == "") {
					response.sendRedirect("index.jsp");
				} else {
					if (expTimeString == null || expTimeString == "") { //if there is no exp time specified by user
						expTimeString = LocalDate.now().plusDays(7).toString(); //set it 7 days from today	
					}
					//	LocalDate expirationTime = LocalDate.parse(expTimeString);
				
					Random random = new Random();
					String HostUrl = request.getRequestURL().toString();
					String baseURL = HostUrl.substring(0, HostUrl.length() - request.getRequestURI().length())
					+ request.getContextPath() + "/";
					String generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	
					generatedURL = baseURL + "$/" + generatedString;
					DatabaseAccessServlet DbServ = new DatabaseAccessServlet();
					//	String expirationDate = "2023-03-23";
					try {
				
						DbServ.saveUrlToDataBase(userMail, generatedURL, OriginalUrl, expTimeString);
					} catch (Exception e) {
						out.println("Error message: " + e.toString());
					}
				}
			%>

			<div id="inner-container">
				<form name="UrlOutput" method="post" action="#" id="input-form">
					<br />
					<div>
						Your long URL <br/>
						 <input type="url" name="original_URL" placeholder="Your long URL"
							value=<%=OriginalUrl%> /> <br />
					</div>
					<br />
					<div>
						Your shortened URL <br/>
						 <input type="url" name="shortened_URL" placeholder="Your shortened URL"
							value=<%=generatedURL%> /> <br />
					</div>
					<br/>
					<div>
						Your shortened URL is valid until <br /> 
						<input type="text" name="expiration_time"
							title="Expiration date" value=<%=expTimeString%> /> <br />
							
					</div>
				</form>
			</div>


		</div>
		</div>
	</div>


	<br />
</body>
</html>