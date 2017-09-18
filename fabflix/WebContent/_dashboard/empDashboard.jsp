<%@ page import="fabflix.*" %>

<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>FabFlix Movie Store</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">

</head>

<body>

<%
String userName = null;
//allow access only if session exists
if(session.getAttribute("user") == null){
	    response.sendRedirect("empaccess.html");
	}else userName = (String) session.getAttribute("user");
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
	for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
	}
}
%>

<table >

  <tr>
    <td colspan="3" align="center"><%@ include file="../WEB-INF/dboard/header.jsp"%></td>
  </tr>
   <tr>
    <td colspan="3" align="center"><%@ include file="metadataRequest.html"%></td>
  </tr>
  
  <tr>
    <td width="50%" height="138"><%@ include file="DBCPinsertStar.jsp"%>&nbsp;</td><td width="50%" height="138"><%@ include file="DBCPAddMovie.jsp"%>&nbsp;</td>  
  </tr>
  
  
  <tr>
    <td colspan="3" align="center"><%@ include file="../WEB-INF/dboard/footer.jsp"%></td>
  </tr>
  
  
  
</table>

</body>
</html>