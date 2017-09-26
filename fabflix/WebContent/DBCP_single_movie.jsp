<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fabflix.*"%>

<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"%>


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
  List<Movie> movies = (List<Movie>)session.getAttribute("search-results");
  
if (movies == null){
    movies = new ArrayList<Movie>();

}
%>


<h3>Single Movie Info:</h3>
<p>
<p>
<table border="1" cellpadding="5" cellspacing="5" class="sortable">
	<tr>
		<th>Movie ID</th>
		<th>Movie Banner</th>
		<th>Year</th>
		<th>Director</th>
		<th>Banner</th>
		<th>List Genre</th>
		<th>Actors List</th>
	</tr>

	<% 
		for (Movie movie:movies) {
	%>
	<tr>
		<td style="border-right: solid;"><%=movie.id%></td>
		<td style="border-right: solid;"><a href=" <%=movie.bannerUrl%>"><%=movie.title %></a></td>
		<td style="border-right: solid;"><%=movie.year %></td>
		<td style="border-right: solid;"><%=movie.director %></td>
		<td style="border-right: solid;"><a href=" <%=movie.trailer%>">Trailer
				URL</a></td>
		<td style="border-right: solid;"><a
			href="/fabflix/genre.doDBCP?movieId=<%=movie.id%>">list Category</a></td>
		<td style="border-right: solid;"><a
			href="/fabflix/star.doDBCP?movieId=<%=movie.id%>">list Stars</a></td>
	</tr>
	<%
	  }
	
	%>
</table>
Your Customer ID is:
<%=DB.customerID %>


<!-- need to encode all the URLs where we want session information to be passed -->
<form name="cart1" action="shorServlet" method="post">
	Enter Quantity:
	<p>
		<input type="number" name="qte" min="1" value="1"
			placeholder="Enter Quantity">
	</p>
	<p>
		<input type="hidden" name="mvId" value="<%=mvId%>">
	</p>
	<p>
		<input type="hidden" name="mvTitle" value="<%=title%>">
	</p>
	<p>
		<input type="hidden" name="mvBan" value="<%=banner%>">
	</p>
	<p>
		<input type="submit" value="Add to Shopping Cart">
	</p>
</form>


<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
	<input type="submit" value=" or Click to Logout">
</form>

<!-- <a href =" single_star.jsp">Movie Star Info</a> -->


<p>
	<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>
</p>

<p>
	<a href="DBCP_searchMovieByTitle.jsp"> Go back to Search Movie
		Page</a>
</p>
</body>
</html>
