package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DBCPStoredProcServlet
 */
@WebServlet(description = "To deal with inserting data into the movie table", urlPatterns = { "/DBCPStoredProcServlet" })
public class DBCPStoredProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCPStoredProcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException {
	   System.out.println("Now attempting to initialize the DB");
    	
    	try {
    			
	    //Create a datasource for pooled connections.
	    DBCP_DB.datasource_emp = (DataSource) getServletContext().getAttribute("DBCPoolEmp");
	    System.out.println("DB Initialization complete...");
	    //Register the driver for non-pooled connections.
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		    }
		    catch (Exception e) {
		      throw new ServletException(e.getMessage());
		    }

    } 
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");    // Response mime type
		String queryPar = request.getParameter("pool").trim();
		boolean plEnabled = queryPar.equalsIgnoreCase("yes")? true: false;
		
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
		System.out.println("Looking to insert in DB ...");
		
		try {
			if( DB.dbcon == null ) {
				String encodedURL = response.encodeRedirectURL("_dashboard/empaccess.html");   
                response.sendRedirect(encodedURL);
				
				//String encodedURL = response.encodeRedirectURL("empaccess.html");   
                //response.sendRedirect(encodedURL);
			}

		
		} catch ( Exception e ) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  //System.exit(0);
			  System.out.println(Tools.dbs);
			  return;
			}

			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		if (plEnabled){
			DBCPCallProcedure.createProcedure();
			DBCPCallProcedure.callProcedure(ttle,year,dirc, fN,lN,gen);
			System.out.println("Insertion completed ...");	
			
		}else {
			//CallProcedure.createProcedure();
			//CallProcedure.callProcedure(ttle,year,dirc, fN,lN,gen);
			System.out.println("Insertion not completed ...");
			System.exit(0);
			out.println("Connection Pooling recommended ...");
			
		}
		
		out.println("Movie Inserted Successfuly...");	
		out.println("<a href =" + "_dashboard/empDashboard.jsp" +">" +"Continue" +"</a>");

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
