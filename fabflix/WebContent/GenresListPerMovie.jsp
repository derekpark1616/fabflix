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

 List<Genre> genres = (List<Genre>)session.getAttribute("genre-results");
 if (genres == null){
	 genres= new ArrayList<Genre>();
	}
 
 session.setAttribute("genre-results", genres);
 
%>


<h3>list of Category: </h3>
<p><p>

<table border="1" cellpadding="5" cellspacing="5">

        <tr>
            <th>Genre ID</th>
            <th>Category</th>
        </tr>

	<% 
		for (Genre genre:genres) {
	%>
	     <tr>
			<td style= "border-right:solid;"><%=genre.gid%></td>
			<td style= "border-right:solid;"><%=genre.gname%></td>
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

<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>

</body>
</html>
