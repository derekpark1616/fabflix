<%@ page import="fabflix.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>

<script>
function validateForm(frm)
{
	
	if(frm.FirstName.value=="" && frm.LastName.value=="")
    {
      alert("First and Last Names cannot be both empty at the same time... At least one must be filled...");
      frm.FirstName.focus();
      return false;
    }
    else if(frm.CreditCard.value=="")
    {
      alert("Credit Card cannot be left blank");
      frm.CreditCard.focus();
      return false;
    }
    else if(frm.Address.value=="")
    {
      alert("Address cannot be Blank");
      frm.Address.focus();
      return false;
    }

    else if(frm.Email.value=="")
    {
      alert("Address cannot be Blank");
      frm.Email.focus();
      return false;
    }
    else if(frm.Password.value=="")
    {
      alert("Address cannot be Blank");
      frm.Password.focus();
      return false;
    }
	
    else if(frm.Password.value!=frm.ConfirmPassword.value)
    {
      alert("Password does not match");
      frm.Password.focus();
      return false;
    }
	
}

</script>

  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" charset="UTF-8">
  <title>Fabflix  Customer Registration </title>
  
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
<%@ include file="WEB-INF/headerCustomer.jsp"%>
  <section class="container">
    <div class="login">
      <h1>Fabflix  Customer Registration</h1>
      <form name="frm" method="post" action="CustomerResgistrationServlet"  onSubmit="return validateForm(frm)">
        <p><input type="text" name="FirstName" value="" placeholder="First Name"></p>
        <p><input type="text" name="LastName" value="" placeholder="Last Name"></p>
        <p><input type="text" name="CreditCard" value="" placeholder="Credit Card Number"></p>
        <p><input type="text" name="Address" value="" placeholder="Home Address"></p>
        <p><input type="text" name="Email" value="" placeholder="Email Address"></p>
        <p><input type="password" name="Password" value="" placeholder="Password"></p>
        <p><input type="password" name="ConfirmPassword" value="" placeholder="Confirm Password"></p>
        <p class="rm">
          <label>
            <input type="checkbox" name="rm" id="rm">
            Remember me...
          </label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Register"></p>
      </form>
    </div>
  </section>
 
</body>
</html>