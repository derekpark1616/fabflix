<%@ page import="fabflix.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page language="java"
	import="java.sql.*, 
	java.util.*,
	java.io.IOException,
	javax.servlet.http.*,
	javax.servlet.*"%>

<!DOCTYPE html>
<html>



<%

Integer mvId = (Integer)session.getAttribute("mvId");
String mvTitle = (String)session.getAttribute("mvTitle");
Integer mvQt = (Integer)session.getAttribute("mvQt");
String mvBan = (String)session.getAttribute("mvBan");




session.setAttribute("mvId", mvId);
session.setAttribute("mvTitle", mvTitle);
session.setAttribute("mvQt", mvQt);
session.setAttribute("mvBan", mvBan);

%>

<%

try {
			
	if( DB.dbcon == null ){ 
		DB.dbcon = DB.getConnection();
	}
	
	
	String qry = "SELECT * FROM shopping_cart WHERE shopping_cart.customerId =?";
	Statement stmt = DB.dbcon.createStatement();
	PreparedStatement stm = DB.dbcon.prepareStatement(qry);
	stm.setLong(1, DB.customerID);
	
	ResultSet rs = stm.executeQuery();
	
	out.println("<TABLE BORDER=1 CELLPADDING=0 CELLSPACING=5 WIDTH=100 percent>" +"<tr><th>Movie ID</th><th>Customer ID</th><th>Total</th><th>Add</th><th>Sub</th></tr>");

	if (rs.next()) {
	    do {
	    	 int mid = rs.getInt("movieId");
	    	 int cusId = rs.getInt("customerId");
	         int mQ = rs.getInt("quantity");
	         
	         out.println("<tr>" +"<td>" + mid + "</td>"  + "<td>" + cusId + "</td>" +"<td>" + mQ+ "</td>" +"</tr>");
	
	    } while(rs.next());
	} else {
	    // No data
		out.println("<font color=red>No Product in the cart so far... keep shopping.</font>");
	}
		
	out.println("</TABLE>");
	
}catch (SQLException ex) {
    while (ex != null) {
        System.out.println ("SQL Exception:  " + ex.getMessage ());
        ex = ex.getNextException ();;
    }  // end while
}  // end catch SQLException

catch(java.lang.Exception ex)
{
    out.println("<HTML>" +
                "<HEAD><TITLE>" +
                "Movie Database: Error" +
                "</TITLE></HEAD>\n<BODY>" +
                "<P>SQL error in doGet: " +
                ex.getMessage() + "</P></BODY></HTML>");
}   

%>



<body>


	<div id="Main">

		<h1 align="center">Welcome to our Online Shopping Store</h1>
		<hr>


		<table id="Items" align="center" border="1" bgcolor="lightgreen">

			<tr>
				<td>Movie ID</td>
				<td>Movie Banner</td>
				<td>Movie Title</td>
				<td>Quantity</td>
			</tr>

			<tr>
				<td><%=mvId%></td>
				<td><a href=" <%=mvBan%>">Banner URL</a></td>
				<td><%=mvTitle%></td>
				<td><%=mvQt%></td>
			</tr>

			<tr>
				<td><a href=main_page.jsp> Return to shopping </a></td>
				<td><a href="purchase.jsp">Products Checkout</a></td>

			</tr>

		</table>

	</div>

	<div id="Cart" style="display: none">

		<h1 align="center">Online Shopping Cart</h1>

		<table id="tablecart" align="center" border="1" bgcolor="lightgreen">

			<tr>
				<td>Total</td>
				<td id="Total"></td>
			</tr>
		</table>

		<p align="center">
			<a href="#" onclick="return show('Main','Cart');">Return</a>
		</p>

	</div>


	<p align="center">Updating quantity in the cart</p>


	<section class="container">
		<div class="login">
			<h1>Enter here some data about the movie you want to update
				quantity</h1>
			<form name="frm" method="post" action="updateQteServlet"
				onSubmit="return validateForm(frm)">
				<p>
					<input type="number" name="movieId" value=""
						placeholder="Type exact Movie Id to update">
				</p>
				<p>
					<input type="number" name="qte" MIN="0" value="0"
						placeholder="Type the quantity you want or 0 to delete">
				</p>
				<p class="rm"></p>
				<p class="submit">
					<input type="submit" name="commit" value="Update">
				</p>
			</form>
		</div>
	</section>

	<section class="container">
		<div class="login">
			<h1>Use this from to empty you entire shopping cart</h1>
			<form name="frm" method="post" action="emptyCartServlet"
				onSubmit="return validateForm(frm)">
				<p>
					<input type="number" name="movieId" value=""
						placeholder="Type exact Movie Id to update">
				</p>

				<p class="rm"></p>
				<p class="submit">
					<input type="submit" name="commit" value="Empty the Cart Now">
				</p>
			</form>
		</div>
	</section>

	<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
		<input type="submit" value=" or Click to Logout">
	</form>




</body>

</html>