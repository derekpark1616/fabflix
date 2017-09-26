<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
	charset="UTF-8">
<title>Fabflix Movie Store</title>
<link rel="stylesheet" href="css/style.css">
<script src="../checkDate.js"></script>
<script>
function validateForm(frm)
{
    if(frm.fName.value=="")
    {
      alert("First  blank");
      frm.fName.focus();
      return true;
    }
    else if(frm.fName.value=="" && frm.lName.value=="")
    {
      alert("Fist and Last Names cannot be both empty at the same time... At least one must be filled...");
      frm.fName.focus();
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
			<h1>Fabflix Movie Star Insertion Form</h1>
			<form name="frm" method="get" action="../DBCPInsertStarServlet"
				onSubmit="return validateForm(frm)">
				<p>Inserting a Star in the database</p>
				<p>
					<input type="text" name="fName" value=""
						placeholder="Star First Name">
				</p>
				<p>
					<input type="text" name="lName" value=""
						placeholder="Star Last name">
				</p>
				<p>
					<input type="text" name="dob" value=""
						placeholder="Star DOB (yyyy-MM-dd)">
				</p>
				<p>
					<input type="text" name="Photo" value=""
						placeholder="Star URL Photo">
				</p>
				<p>
					Connection pooling (Yes or No): <select name="pool">
						<option value="yes" selected="selected">Yes</option>
						<option value="no">No</option>
					</select>
				</p>
				<p class="submit">
					<input type="submit" name="commit" value="Insert Star"> <input
						type="reset" name="clearForn" value="Clear Form">
				</p>
			</form>
		</div>
	</section>

</body>
</html>