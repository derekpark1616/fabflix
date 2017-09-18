<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
	charset="UTF-8">
<title>Fabflix Movie Store</title>
<link rel="stylesheet" href="css/style.css">

</head>

<body>
	<section class="container">
		<div class="login">
			<h1>Fabflix Movie Search Page</h1>
			<form name="frm" method="get" action="DBCPSrchMovieServlet"
				onSubmit="return validateForm(frm)">
				<p>Movie database search using a combination of...:</p>
				<p>
					<input type="text" name="Title" value="" placeholder="Movie Title">
				</p>
				<p>
					<input type="text" name="Year" value="" placeholder="Year">
				</p>
				<p>
					<input type="text" name="Director" value="" placeholder="Director">
				</p>
				<p>
					<input type="text" name="stare" value="" placeholder="Star Name">
				</p>
				<p>
					<input type="hidden" name="pg" value="1">
				</p>
				
				<p>
					Make a Selection: <select name="nRows">
						<option value="10" selected="selected">50</option>
						<option value="25">25</option>
						<option value="50">50</option>
						<option value="100">100</option>
            			<option value="200">200</option>
					</select>
				</p>
				<p>
					Connection pooling (Yes or No): <select name="pool">
						<option value="yes" selected="selected">Yes</option>
						<option value="no">No</option>	
					</select>
				</p>
				
				<p class="submit">
					<input type="submit" name="commit" value="Search...">
				</p>
			</form>
		</div>
	</section>

</body>
</html>