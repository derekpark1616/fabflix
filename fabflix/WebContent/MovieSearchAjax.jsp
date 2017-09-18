<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fabflix.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>

<%
	String string = request.getParameter("val");
	String squery = null, dquery = null;

	if (string == null || string.trim().equals("")) {
		out.print("Enter Some letter of the title of the movie");
	} else {
		out.print(string);
		try {

			if (DB.dbcon == null) {
				DB.dbcon = DB.getConnection();
			}
			
			dquery = "SELECT m.id,m.title AS Title FROM movies as m WHERE MATCH(m.title) AGAINST";
			

			if (string != null) {
				String[] keys = string.split(" ");
				if(keys.length > 1){
					for(int i=0; i < keys.length; i++){
						if(i == 0){
							squery = dquery + "('+" + keys[i] + " ";
						}else if(i < keys.length-1){
							squery = squery.concat("+"+keys[i]+ " ");
						}else if(i == keys.length-1){
							squery = squery.concat(keys[i]+"*' IN BOOLEAN MODE);");
						}
					}
				}else {
					squery = dquery + "('"+string+"*' IN BOOLEAN MODE);";
				}
				System.out.println(squery);
				
			}

			PreparedStatement ps = DB.dbcon.prepareStatement(squery);
			//ps.setString(1,"%" + string + "%");

			//ps.setString(1,s);  
			ResultSet rs = ps.executeQuery();
%>
<TABLE BORDER="3" CELLPADDING="0" CELLSPACING="0" WIDTH="25%"
	align="left">

	<%
		while (rs.next()) {
					Integer mvId = null;
					mvId = rs.getInt(1);
					String ok = rs.getString(2);
	%>

	<tr>
		<td align="left"><a href="/fabflix/movie.DBCPDo?movieId=<%=mvId%>"><%=ok%></a></td>
		<td align="left">
	</tr>

	<%
		}

				rs.close();
				//DB.dbcon.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>

</TABLE>
