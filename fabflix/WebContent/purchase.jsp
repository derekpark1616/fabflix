<%@ page import="fabflix.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"%>


<!DOCTYPE html>

<html>
<head>
<script src="checkDate.js"></script>
<script>
function validateForm(frm)
{
    if(frm.CreditCard.value=="")
    {
      alert("Credit Card cannot be left blank");
      frm.CreditCard.focus();
      return false;
    }
    else if(frm.FirstName.value=="" && frm.LastName.value=="")
    {
      alert("Fist and Last Names cannot be both empty at the same time... At least one must be filled...");
      frm.FirstName.focus();
      return false;
    }

    else if(!CheckDate(frm.ExpirationDate)) return false;
    return true;
}

</script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
	charset="UTF-8">
<title>Fabflix Movie Purchase</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<%@ include file="WEB-INF/header.jsp"%>
	<%
String userName = null;
//allow access only if session exists
if(session.getAttribute("user") == null){
    response.sendRedirect("login.html");
}else userName = (String) session.getAttribute("user");
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>

	<section class="container">
		<div class="login">
			<h1>Fabflix Credit Card Registration</h1>
			<form name="frm" method="post" action="purchaseServlet"
				onSubmit="return validateForm(frm)">
				<p>
					<input type="text" name="CreditCard" value=""
						placeholder="Credit Card Number">
				</p>
				<p>
					<input type="text" name="FirstName" value=""
						placeholder="First Name">
				</p>
				<p>
					<input type="text" name="LastName" value="" placeholder="Last Name">
				</p>
				<p>
					<input type="text" required pattern="\d{4}-\d{1,2}-\d{1,2}"
						name="ExpirationDate" value=""
						placeholder="Expiration Date (yyyy-mm-dd)">
				</p>
				<p class="rm">
					<label> <input type="checkbox" name="rm" id="rm">
						Remember me...
					</label>
				</p>
				<p class="submit">
					<input type="submit" name="commit" value="Continue">
				</p>
			</form>
		</div>
	</section>

</body>
</html>