package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MetadataServlet
 */
@WebServlet(description = "Metadata Display", urlPatterns = { "/_dashboard/MetadataServlet" })
public class MetadataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetadataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");    // Response mime type
		PrintWriter out = response.getWriter();
		String dbTable = "", temp;
		temp = request.getParameter("dbTable");
		//dbTable=temp;
		try {
			if( DB.dbcon == null ){ 
				String encodedURL = response.encodeRedirectURL("_dashboard/empaccess.html");   
                response.sendRedirect(encodedURL);
				
				//String encodedURL = response.encodeRedirectURL("empaccess.html");   
                //response.sendRedirect(encodedURL);
			}
			 // Create an execute an SQL statement to select all of table"Stars" records
			
			String [] tables = new String[] {"movies","creditcards","customers","stars","genres","stars_in_movies","genres_in_movies","sales","employees"};		
			int arSize;
			
	
			if (!temp.equalsIgnoreCase("All")){
				arSize =1;
				
			}else arSize = tables.length;
			out.println("<TABLE BORDER=1 CELLPADDING=1 CELLSPACING=1 WIDTH=40%>");
				for (int i =0;i<arSize; i++){
					if (arSize==1){
						dbTable=temp;
					} else dbTable = tables[i];

					String metaQuery="select * from " + dbTable + " ";
					PreparedStatement tableData = DB.dbcon.prepareStatement(metaQuery);
					ResultSet result = tableData.executeQuery();
					
					//out.println("\nTable Name is : " +dbTable);
					
					
					ResultSetMetaData metadata = result.getMetaData();
					out.println("<tr>" +"</tr>");
					out.println("<tr>" +"<th>" +"Attribute"+"</th>"+"<th>" +"Type" +"</th>"+"</tr>");
					out.println("<tr>" +"<td>" +"Table "+dbTable+"</td>"+"<td>" +"has "+ metadata.getColumnCount()+ " columns" +"</td>"+"</tr>");
					
					//out.println("<tr>" +"<td>" +"Table Name"+"</td>"+"<td>" +dbTable +"</td>"+"</tr>");
					
					
					  // Print type of each attribute
					for (int j = 1; j <= metadata.getColumnCount(); j++){
						
						//out.println("<tr>" +"<td>" +"Type of attribute '" + metadata.getColumnName(j) + "' is " + metadata.getColumnTypeName(j));
						out.println("<tr>" +"<td>" +metadata.getColumnName(j)+"</td>"+"<td>" +metadata.getColumnTypeName(j) +"</td>"+"</tr>");
						
					}	
					result.close();
				}
				
				
				out.println("</TABLE>");
	
				out.println("<a href =" + "_dashboard/empDashboard.jsp" +">" +"Continue" +"</a>");
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
