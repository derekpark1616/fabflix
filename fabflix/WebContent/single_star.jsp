<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fabflix.*" %>

<%@ page language="java" import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = Tools.ukw, director= Tools.ukw, banner = Tools.ukw, trailerU = Tools.ukw;
	String yr ="1900", mvId ="999999";

	title = (String)session.getAttribute("title");
	director = (String)session.getAttribute("director");
	banner = (String)session.getAttribute("banner");
	trailerU = (String)session.getAttribute("trailerUrl");
	mvId = (String)session.getAttribute("mvId");
	yr = (String)session.getAttribute("yr");
	
%>

<%
  List<Star> stars= (List<Star>)session.getAttribute("star-search-results");
  
if (stars == null){
	stars = new ArrayList<Star>();

}
%>


<h3>Single Star Info: </h3>
<p><p>

<table border="1" cellpadding="5" cellspacing="5" class="sortable">
        <tr>
            <th>Star ID</th>
            <th>Star First Name</th>
            <th>Star last Name</th>
            <th>Star DOB</th>
            <th>Star Photo</th>
            <th>Movie List</th>
        </tr>

	<% 
		for (Star star:stars) {
	%>
	     <tr>
			<td style= "border-right:solid;"><a href="/fabflix/star.goo?starId=<%=star.mid%>" ><%=star.mid%></a></td>
			<td style= "border-right:solid;"><%=star.fname %> </td>
			<td style= "border-right:solid;"><%=star.lname %> </td>
			<td style= "border-right:solid;"><%=star.dob %> </td>
			<td style= "border-right:solid;"><a href =" <%=star.photo %>" >Picture </a></td>
			<td style= "border-right:solid;"><a href="/fabflix/star.goo?starId=<%=star.mid%>" >list Movies</a></td>   
	     </tr>
	<%
	  }
	
	%>  
</table>
Your Customer ID is: <%=DB.customerID %>

<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<input type="submit" value=" or Click to Logout" >
</form>

<!-- <a href =" single_star.jsp">Movie Star Info</a> -->



<p>
<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>
</p>

<p>
<a href ="searchMovieByTitle.jsp" > Go back to Search Movie Page</a></p>
</body>
</html>
