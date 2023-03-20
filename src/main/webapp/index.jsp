<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="./css/style.css"> -->
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<link rel="stylesheet" type="text/css" href="./css/css3.css" >
<title>Main page</title>
</head>
<body>

	<div id="main-form-wrap">
		<div id="container">
			<header>
				<!--     <a href="#" id="logo"><img src="images/logo.png"  alt=""></a> -->
				<nav>
					<ul>
<!-- 							header relative to user login status -->
						<%	//check if there is already user signed in 
							String userName = (String) session.getAttribute("user");
							if(userName != null){ //not signed in
								out.print("<li><a href=\"index.jsp\" >Home</a></li>");
								out.print("<li><a href=\"YourAccount.jsp\"> Your Account " + "" + "</a></li>");
								out.print("<li><a href=\"Logout.jsp\" class=\"highlight\"> Logout " + userName + "</a></li>");
							} else{
								out.print("<li><a href=\"index.jsp\" >Home</a></li>");
								out.print("<li><a href=\"login.html\"> Sign in </a></li>");
								out.print("<li><a href=\"registration.html\" > Sign up </a></li>");
							}
						%>
						
					</ul>
				</nav>
			</header>
		</div>
 		<div id="inner-container">  <!--  shortening URL form; after clicking submit button go to "Original....jsp file"-->
			<form name="UrlInput" method="post" action="OriginalUrltoDatabase.jsp" id="input-form">
<!-- 				Type your URL to be shortened: -->
				<br />
				<div>
				 	Enter a long URL to make it shorter
					 <input type="text" name="original_URL" placeholder="Type here your long URL" title="Please type here your URL"/> <br />
				</div>
				<br />
				<div >
					Expiration date <br/>
					 <input type="date" name="expiration_time" title="Please set expiration date of your shortened URL"/> <br />
					 <div style="font-weight: normal">
						 Specify your link expiration date. 
						 <br/>
						 Otherwise shortened link will be valid for 7 days.
					 </div>
				</div>	
				<br />
				<input type="submit" value="Make it shorter!" /> <br />
				</form>
		</div> 

	</div>

	<!-- 	<footer> -->
	<!-- 		<div class="container"><h4>Author: </h4> -->
	<!-- 					 Blazej Pastuszynski</div> -->
	<!-- 			<div class="holder_content"> -->
	<!-- 				<section class="group1"> -->
						
						 
	<!-- 				</section> -->
	<!-- 			</div> -->
	<!-- 	</footer> -->


</body>
</html>