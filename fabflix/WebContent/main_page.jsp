<%@ page import="fabflix.*" %>

<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>FabFlix Movie Store</title>
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>

<body>
<h1> Searching our Movie Database Store via Several means</h1>
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
    		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
		}
	}else{
		sessionID = session.getId();
	}

%>



<%@ include file="WEB-INF/header.jsp"%>

<table >
  <tr>
    
    <td width="50%" height="158"><%@ include file="browse.html"%></td><td width="50%" height="138" align="Left">Ajax Auto Search Box<%@ include file="schMovieByTitleAjax.html"%></td> 
  </tr>

  <tr>
    <td width="20%" ><%@ include file="DBCP_searchMovieByTitle.jsp"%></td>
    
  </tr>
</table>

<%@ include file="WEB-INF/footer.jsp"%>
</body>
</html>