<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<%@ include file="WEB-INF/header.jsp"%>
<%@ include file="browse.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Browsing Movies Database</title>
<%
String nRows = request.getParameter("nRows");
%>
</head>
<body style="background: #F2F2F2">




	<div align="left">

		<h1>Browse by Genre</h1>
		<p>
		<p>
		<p>
		<div align="left">
			<p>
				||<a href="/fabflix/movie.o?ltr=action&nRows=<%=nRows%>">Action</a>
				|| <a href="/fabflix/movie.o?ltr=ntur&nRows=<%=nRows%>">Adventure</a>
				|| <a href="/fabflix/movie.o?ltr=anima&nRows=<%=nRows%>">Animation</a>||
				<a href="/fabflix/movie.o?ltr=art&nRows=<%=nRows%>">Arts</a> ||<a
					href="/fabflix/movie.o?ltr=classi&nRows=<%=nRows%>">Classic</a> ||<a
					href="/fabflix/movie.o?ltr=medy&nRows=<%=nRows%>">Comedy</a>||
			</p>
		</div>
		<div align="left">
			<p>
				|| <a href="/fabflix/movie.o?ltr=Age&nRows=<%=nRows%>">Coming-of-Age-Drama</a>||
				<a href="/fabflix/movie.o?ltr=Crime&nRows=<%=nRows%>">Crime</a>|| <a
					href="/fabflix/movie.o?ltr=Gang">Gangster</a>|| <a
					href="/fabflix/movie.o?ltr=tectiv&nRows=<%=nRows%>">Detective</a>||
				<a href="/fabflix/movie.o?ltr=umentar&nRows=<%=nRows%>">Documentary</a>||
				<a href="/fabflix/movie.o?ltr=Dram&nRows=<%=nRows%>">Drama</a>||
			</p>
		</div>


		<div align="left">
			<p>
				||<a href="/fabflix/movie.o?ltr=epics&nRows=<%=nRows%>">Epics</a>
				|| <a href="/fabflix/movie.o?ltr=Histori&nRows=<%=nRows%>">Epics/Historical</a>
				|| <a href="/fabflix/movie.o?ltr=amily&nRows=<%=nRows%>">Family</a>||
				<a href="/fabflix/movie.o?ltr=Fantasy&nRows=<%=nRows%>">Fantasy</a>
				||<a href="/fabflix/movie.o?ltr=Noir&nRows=<%=nRows%>">Film-Noir</a>
				||<a href="/fabflix/movie.o?ltr=Foreign&nRows=<%=nRows%>">Foreign</a>||
			</p>
		</div>
		<div align="left">
			<p>
				|| <a href="/fabflix/movie.o?ltr=Horror&nRows=<%=nRows%>">Horror</a>||
				<a href="/fabflix/movie.o?ltr=Indie&nRows=<%=nRows%>">Indy</a>|| <a
					href="/fabflix/movie.o?ltr=James&nRows=<%=nRows%>">James Bond</a>||
				<a href="/fabflix/movie.o?ltr=kid&nRows=<%=nRows%>">Kid</a>|| <a
					href="/fabflix/movie.o?ltr=Love&nRows=<%=nRows%>">Love</a>|| <a
					href="/fabflix/movie.o?ltr=Musical&nRows=<%=nRows%>">Musical</a>||
			</p>
		</div>

		<div align="left">
			<p>
				||<a href="/fabflix/movie.o?ltr=Performing&nRows=<%=nRows%>">Performing
					Arts</a> || <a href="/fabflix/movie.o?ltr=Mystery&nRows=<%=nRows%>">Mystery</a>
				|| <a href="/fabflix/movie.o?ltr=Roman&nRows=<%=nRows%>">Roman</a>||
				<a href="/fabflix/movie.o?ltr=omance&nRows=<%=nRows%>">Romance</a>
				||<a href="/fabflix/movie.o?ltr=Sci&nRows=<%=nRows%>">Sci-Fi</a> ||<a
					href="/fabflix/movie.o?ltr=fiction&nRows=<%=nRows%>">Science
					Fiction</a>||
			</p>
		</div>
		<div align="left">
			<p>
				|| <a href="/fabflix/movie.o?ltr=Short&nRows=<%=nRows%>">Short</a>||
				<a href="/fabflix/movie.o?ltr=spy&nRows=<%=nRows%>">Spy</a>|| <a
					href="/fabflix/movie.o?ltr=Suspense&nRows=<%=nRows%>">Suspense</a>||
				<a href="/fabflix/movie.o?ltr=Thriller&nRows=<%=nRows%>">Thriller</a>||
				<a href="/fabflix/movie.o?ltr=Tragedy&nRows=<%=nRows%>">Tragedy</a>||
				<a href="/fabflix/movie.o?ltr=War&nRows=<%=nRows%>">War</a>||<a
					href="/fabflix/movie.o?ltr=Western&nRows=<%=nRows%>">Western</a>
				||
			</p>
		</div>

	</div>


	<div align="left">

		<p>
		<p>
		<p>
		<h1>Browse by Title</h1>


		<div align="left">
			<p>
				||<a href="/fabflix/movie.ok?lt=0&nRows=<%=nRows%>">0</a> || <a
					href="/fabflix/movie.ok?lt=1&nRows=<%=nRows%>">1</a> || <a
					href="/fabflix/movie.ok?lt=2&nRows=<%=nRows%>">2</a>|| <a
					href="/fabflix/movie.ok?lt=3&nRows=<%=nRows%>">3</a> || <a
					href="/fabflix/movie.ok?lt=4&nRows=<%=nRows%>">4</a> || <a
					href="/fabflix/movie.ok?lt=5&nRows=<%=nRows%>">5</a>|| <a
					href="/fabflix/movie.ok?lt=6&nRows=<%=nRows%>">6</a>|| <a
					href="/fabflix/movie.ok?lt=7&nRows=<%=nRows%>">7</a>|| <a
					href="/fabflix/movie.ok?lt=8&nRows=<%=nRows%>">8</a>|| <a
					href="/fabflix/movie.ok?lt=9&nRows=<%=nRows%>">9</a>||
			</p>
		</div>


		<div align="left">
			<p>
				||<a href="/fabflix/movie.ok?lt=a&nRows=<%=nRows%>">A</a> || <a
					href="/fabflix/movie.ok?lt=b&nRows=<%=nRows%>">B</a> || <a
					href="/fabflix/movie.ok?lt=c&nRows=<%=nRows%>">C</a>|| <a
					href="/fabflix/movie.ok?lt=d&nRows=<%=nRows%>">D</a> || <a
					href="/fabflix/movie.ok?lt=e&nRows=<%=nRows%>">E</a> || <a
					href="/fabflix/movie.ok?lt=f&nRows=<%=nRows%>">F</a>|| <a
					href="/fabflix/movie.ok?lt=g&nRows=<%=nRows%>">G</a>|| <a
					href="/fabflix/movie.ok?lt=h&nRows=<%=nRows%>">H</a>|| <a
					href="/fabflix/movie.ok?lt=i&nRows=<%=nRows%>">I</a>|| <a
					href="/fabflix/movie.ok?lt=j&nRows=<%=nRows%>">J</a>|| <a
					href="/fabflix/movie.ok?lt=k&nRows=<%=nRows%>">K</a>|| <a
					href="/fabflix/movie.ok?lt=l&nRows=<%=nRows%>">L</a>|| <a
					href="/fabflix/movie.ok?lt=m&nRows=<%=nRows%>">M</a> ||
			</p>
		</div>

		<div align="left">
			<p>
				||<a href="/fabflix/movie.ok?lt=n&nRows=<%=nRows%>">N</a>||<a
					href="/fabflix/movie.ok?lt=o&nRows=<%=nRows%>">O</a> || <a
					href="/fabflix/movie.ok?lt=p&nRows=<%=nRows%>">P</a>|| <a
					href="/fabflix/movie.ok?lt=q&nRows=<%=nRows%>">Q</a> || <a
					href="/fabflix/movie.ok?lt=r&nRows=<%=nRows%>">R</a> || <a
					href="/fabflix/movie.ok?lt=s&nRows=<%=nRows%>">S</a>|| <a
					href="/fabflix/movie.ok?lt=t&nRows=<%=nRows%>">T</a>||<a
					href="/fabflix/movie.ok?lt=u&nRows=<%=nRows%>">U</a>|| <a
					href="/fabflix/movie.ok?lt=v&nRows=<%=nRows%>">V</a>|| <a
					href="/fabflix/movie.ok?lt=w&nRows=<%=nRows%>">W</a>|| <a
					href="/fabflix/movie.ok?lt=x&nRows=<%=nRows%>">X</a>|| <a
					href="/fabflix/movie.ok?lt=y&nRows=<%=nRows%>">Y</a>|| <a
					href="/fabflix/movie.ok?lt=z&nRows=<%=nRows%>">Z</a>||
			</p>
		</div>
	</div>
</body>
</html>