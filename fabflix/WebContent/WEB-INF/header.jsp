<%@ page import="fabflix.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ page language="java" import="java.sql.*" %>

<html>
<head>
<title>Header page</title>
<style>
.st
{
  background-color:#CBE0E7;
  font-weight:bold;
  text-align:center
}
</style>
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>

<body>

<table  >
  <tr>
    <td class="st"><a href="main_page.jsp">Home</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="/fabflix/b.jsp?nRows=10">Browse Movies List</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="purchase.jsp">Products Checkout</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="cart_01.jsp">My Cart</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="customer_registration.jsp">Register</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="logout_r.jsp">Login</a></td>
    <td>&nbsp;</td>
    <td class="st"><a href="logout.jsp">Logout</a></td>
    <td>&nbsp;</td>
  </tr>
</table>

 </body>
</html>