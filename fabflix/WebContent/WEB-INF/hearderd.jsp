<%@ page import="fabflix.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ page language="java" import="java.sql.*"%>

<html>
<head>
<title>Header page</title>
<style>
.st {
	background-color: #FFCCFF;
	font-weight: bold;
	text-align: center
}
</style>
<link rel="stylesheet" href="../css/styleC.css" type="text/css">

</head>

<body>

	<table>
		<tr>
			<td class="st"><a href="empDashboard.jsp">Employee Home</a></td>
			<td>&nbsp;</td>
			<td class="st"><a href="customer_registration.jsp">Register
					As a Customer</a></td>
			<td>&nbsp;</td>
			<td class="st"><a href="empaccess.html">Employee Login</a></td>
			<td>&nbsp;</td>
			<td class="st"><a href="logout.jsp">Store Front or Logout</a></td>
			<td>&nbsp;</td>
		</tr>
	</table>

</body>
</html>