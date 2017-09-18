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

 List<Star> stars = (List<Star>)session.getAttribute("star-results");
 if (stars == null){
	 stars = new ArrayList<Star>();
	}
 
 session.setAttribute("star-results", stars);
 
%>


<h3>list of stars: </h3>
<p><p>

<table border="1" cellpadding="5" cellspacing="5">

        <tr>
            <th>Star ID</th>
            <th>Star First Name</th>
            <th>Star Last Name</th>
            <th>Star DOB</th>
            <th>Star Photo</th>
   
        </tr>

	<% 
		for (Star star:stars) {
	%>
	     <tr>
			<td style= "border-right:solid;"><a href="/fabflix/star.goo?starId=<%=star.mid%>" ><%=star.mid%></a></td>
			<td style= "border-right:solid;"><%=star.fname%></td>
			<td style= "border-right:solid;"><%=star.lname%></td>
			<td style= "border-right:solid;"><%=star.dob %></td>
			<td style= "border-right:solid;"><a href="<%=star.photo%>">Picture</a> </td>
	     </tr>
	<%
	  }
	
	%>  
</table>

Your Customer ID is: <%=DB.customerID %>



<!-- need to encode all the URLs where we want session information to be passed -->

<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<input type="submit" value=" or Click to Logout" >
</form>

<p>
<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>
</p>

<p>
<a href ="searchMovieByTitle.jsp" > Go back to Search Movie Page</a></p>

</body>
</html>
