<%@ page import="fabflix.*" %>

<!--Adapted from Original Java Servlet Session Management -->
<!--//Tutorial code by Pankaj Internet: www.journaldev.com -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
    
<!DOCTYPE html >
<html>
<head>
<%@ include file="WEB-INF/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Movie Login Success</title>
</head>
<body>
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
<h3>Hi <%=userName %>, do the checkout.</h3>
<br>
<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>