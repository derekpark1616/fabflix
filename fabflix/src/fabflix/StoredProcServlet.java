package fabflix;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoredProcServlet
 */
@WebServlet(name = "StoredProcServlet", description = "To call the stored procedure and insert data to db", urlPatterns = { "/_dashboard/StoredProcServlet" })
public class StoredProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoredProcServlet() {
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
		  // Output stream to STDOUT
		PrintWriter out = response.getWriter();
     
		
		
		String ttle, yr, dirc, fN, lN, gen;
        
		ttle = request.getParameter("ttle");
		yr = request.getParameter("yr");
		dirc =  request.getParameter("director");
		fN = request.getParameter("fName");
		lN = request.getParameter("lName");
		gen = request.getParameter("genre");

		if (lN.isEmpty() ){
			lN = fN;	
			fN  = "";
		}
		int year = Integer.parseInt(yr);
		
		
		CallProcedure.createProcedure();
		CallProcedure.callProcedure(ttle,year,dirc, fN,lN,gen);

		out.println("Movie Inserted Successfuly...");	
		out.println("<a href =" + "empDashboard.jsp" +">" +"Continue" +"</a>");

		//String encodedURL = response.encodeRedirectURL("../_dashboard//empDashboard.jsp");   
        //response.sendRedirect(encodedURL);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
