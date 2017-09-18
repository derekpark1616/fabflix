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
  List<Movie> movies = (List<Movie>)session.getAttribute("search-results");
  
if (movies == null){
    movies = new ArrayList<Movie>();
}
%>

<head>
<script src="sorttable.js"></script>

</head>

<h3>list of movies: </h3>
<p><p>

<table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Movie ID</th>
            <th>Movie Title Name</th>
            <th>Movie Year</th>
            <th>Movie Director</th>
            <th>Movie Banner</th>
            <th>Movie Genre</th>
        </tr>

	<% 
		for (Movie movie:movies) {
	%>
	     <tr>
	      
	<td style= "border-right:solid;"><a href="/fabflix/movie.do?movieId=<%=movie.id%>" >Click here to buy</a></td>
	<td style= "border-right:solid;"><%=movie.title %> </td>
	<td style= "border-right:solid;"><%=movie.year %> </td>
	<td style= "border-right:solid;"><%=movie.director %> </td>
	<td> <a href =" <%=movie.bannerUrl%>">Banner URL</a></td>
	<td style= "border-right:solid;"><%=movie.genre %> </td>
	   
	     </tr>
	<%
	  }
	session.setAttribute("search-results",null);
	%>  
</table>

<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="movie.o?pg=${currentPage - 1}&nRows=${nRows}&ltr=${ttle}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table >
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="movie.o?pg=${i}&nRows=${nRows}&ltr=${ttle}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="movie.o?pg=${currentPage + 1}&nRows=${nRows}&ltr=${ttle}">Next</a></td>
    </c:if>

</body>
</html>
