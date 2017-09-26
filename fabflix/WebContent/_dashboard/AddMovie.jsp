<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
	charset="UTF-8">
<title>Fabflix Movie Store</title>
<link rel="stylesheet" href="../css/style.css">
<script src="../checkDate.js"></script>
<script>
function validateForm(frm)
{
	
	 if(frm.fName.value=="")
	    {
	      alert("First name blank");
	      frm.fName.focus();
	      return true;
	    }
	    else if(frm.fName.value=="" && frm.lName.value=="")
	    {
	      alert("Fist and Last Names cannot be both empty at the same time... At least one must be filled...");
	      frm.fName.focus();
	      return false;
	    }	
	
		else if(frm.ttle.value=="")
	    {
	      alert("Movie Title cannot be  blank");
	      frm.ttle.focus();
	      return false;
	    }
	    else if(frm.yr.value=="")
	    {
	      alert("Movie year cannot be blank.");
	      frm.yr.focus();
	      return false;
	    } 
	    else if(frm.director.value=="")
	    {
	        alert("Movie director cannot be blank.");
	        frm.director.focus();
	        return false;
	      } 
	    else if(!CheckDate(frm.dob)) return false;

    return true;
}

</script>

</head>

<body>
	<section class="container">
		<div class="login">
			<h1>Fabflix Movie Insertion Form</h1>
			<form name="frm" method="get" action="../StoredProcServlet"
				onSubmit="return validateForm(frm)">
				<p>Inserting a Movie in the database</p>
				<p>
					<input type="text" name="ttle" value="" placeholder="Movie Title">
				</p>
				<p>
					<input type="number" name="yr" value=""
						placeholder="Year of Release">
				</p>
				<p>
					<input type="text" name="director" value=""
						placeholder="Movie Director">
				</p>
				<p>
					<input type="text" name="fName" value=""
						placeholder="Star First Name">
				</p>

				<p>
					<input type="text" name="lName" value=""
						placeholder="Star Last Name">
				</p>

				<p>
					<input type="text" name="genre" value="" placeholder="Movie Genre">
				</p>
				<p class="submit">
					<input type="submit" name="commit" value="Insert Movie"> <input
						type="reset" name="clearForn" value="Clear Form">
				</p>
			</form>
		</div>
	</section>

</body>
</html>