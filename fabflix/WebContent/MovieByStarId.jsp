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
	mvId = (String)session.getAttribute("mvId");
	yr = (String)session.getAttribute("yr");
	
%>

<%
  List<Movie> movies = (List<Movie>)session.getAttribute("search-results");
  
if (movies == null){
    movies = new ArrayList<Movie>();

}
%>


<h3>Single Movie Info: </h3>
<p><p>

<table border="1" cellpadding="5" cellspacing="5" class="sortable">
        <tr>
            <th>Movie ID</th>
            <th>Movie Title</th>
            <th>Star Id</th>
        </tr>

	<% 
		for (Movie movie:movies) {
		
	%>
	     <tr>
			<td style= "border-right:solid;"><a href="/fabflix/movie.do?movieId=<%=movie.id%>" ><%=movie.id%></a></td>
			<td style= "border-right:solid;"><a href =" <%=movie.bannerUrl%>" ><%=movie.title %></a></td>
			<td style= "border-right:solid;"><%=movie.getMovieStarId() %> </td> 
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
