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
  List<Movie> movies = (List<Movie>)session.getAttribute("search-results");
  
if (movies == null){
    movies = new ArrayList<Movie>();

}
%>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="sorttable.js"></script>
<script src="ajxmouseover.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<link rel="stylesheet" href="css/styleAjx.css">


<script language=JavaScript RUNAT=SERVER>


// This function decodes the any string
 // that's been encoded using URL encoding technique
 function URLDecode(psEncodeString) 
 {
   return unescape(psEncodeString); 
 }
 </script>

<script>
  $(function() {
    $( "#ajaxDiv" ).dialog({
      modal: false,
      buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
        }
      }
    });
  });
  </script>

</head>
<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>

<h3>list of movies:</h3>
<p>
<p>
<table border="1" cellpadding="5" cellspacing="5" class="sortable">
	<tr>
		<th>Movie ID</th>
		<th>Movie Banner</th>
		<th>Title Name</th>
		<th>Year</th>
		<th>Director</th>
		<th>Banner</th>
		<th>List Genre</th>
		<th>Buy</th>
		<th>Actors List</th>
	</tr>

	<% 
		for (Movie movie:movies) {
	%>
	<tr>
		<td style="border-right: solid;"><a
			href="/fabflix/movie.do?movieId=<%=movie.id%>"><%=movie.id%></a></td>
		<td style="border-right: solid;"><a href=" <%=movie.bannerUrl%>"><%=movie.title %></a></td>
		<td style="border-right: solid;">
			<form id='myForm'>
				<input type='hidden' name='mvId' value='<%=movie.id%>' /> <br /> <input
					type='text' onmouseover="ajaxFunction('<%=movie.id%>');" size='50'
					value='<%=movie.title %>' />
			</form>
		</td>
		<td style="border-right: solid;"><%=movie.year %></td>
		<td style="border-right: solid;"><%=movie.director %></td>
		<td style="border-right: solid;"><a href=" <%=movie.trailer%>">Trailer
				URL</a></td>
		<td style="border-right: solid;"><a
			href="/fabflix/genre.do?movieId=<%=movie.id%>">list Category</a></td>
		<td style="border-right: solid;"><a
			href="/fabflix/movie.do?movieId=<%=movie.id%>">Buy Now</a></td>
		<td style="border-right: solid;"><a
			href="/fabflix/star.do?movieId=<%=movie.id%>">list Stars</a></td>
	</tr>
	<%
	  }
	
	%>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
	<td><a
		href="movie.go?pg=${currentPage - 1}&nRows=${nRows}&Title=${ttle}&Year=${Year}&Director=${Director}&stare=${stare}">Previous</a></td>
</c:if>

<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
<table>
	<tr>
		<c:forEach begin="1" end="${noOfPages}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<td>${i}</td>
				</c:when>
				<c:otherwise>
					<td><a
						href="movie.go?pg=${i}&nRows=${nRows}&Title=${ttle}&Year=${Year}&Director=${Director}&stare=${stare}">${i}</a></td>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
	<td><a
		href="movie.go?pg=${currentPage + 1}&nRows=${nRows}&Title=${ttle}&Year=${Year}&Director=${Director}&stare=${stare}">Next</a></td>
</c:if>


<div id='ajaxDiv' title="Movie Info Summary">
	<p>
		<span class="ui-icon ui-icon-circle-check"
			style="float: left; margin: 0 7px 140px 0;"></span>
	</p>
</div>
<p>
	<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
</script>
</p>

<p>
	<a href="searchMovieByTitle.jsp"> Go back to Search Movie Page</a>
</p>


</body>
</html>
