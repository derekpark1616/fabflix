package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertStarServlet
 */
@WebServlet(description = "Star Insertion", urlPatterns = { "/_dashboard/InsertStarServlet" })
public class InsertStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");    // Response mime type
		PrintWriter out = response.getWriter();


		String fn, ln, dt, ph;
		fn = request.getParameter("fName");
		ln = request.getParameter("lName");
		dt = request.getParameter("dob");
		ph = request.getParameter("Photo");
		
		
		if (fn.isEmpty()){	
			fn = "";
			
		}
		if (ln.isEmpty() ){
			ln = fn;	
			fn  = "";
		}

		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = null;
		try {
			myDate = formatter.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date dob = new java.sql.Date(myDate.getTime());

		try {
			if( DB.dbcon == null ) {
				String encodedURL = response.encodeRedirectURL("empaccess.html");   
                response.sendRedirect(encodedURL);
				
				//String encodedURL = response.encodeRedirectURL("empaccess.html");   
                //response.sendRedirect(encodedURL);
			}	
				//dbconn = DatabaseConnection.getConnection();
			PreparedStatement starinsert = DB.dbcon.prepareStatement( "INSERT INTO `stars` (`first_name`,`last_name`,`dob`,`photo_url`) VALUES (?,?,?,?)");	
			
				starinsert.setString(1,fn);
				starinsert.setString(2,ln);
				starinsert.setDate(3, dob);
				starinsert.setString(4,ph);
				
				starinsert.executeUpdate();
		
			//for (String FullName:SAXParserStars.starFullNameSet){	}

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

		out.println("Star Inserted Successfuly");
		
		out.println("<a href =" + "empDashboard.jsp" +">" +"Continue" +"</a>");
		//String encodedURL = response.encodeRedirectURL("../_dashboard//empDashboard.jsp");   
        //response.sendRedirect(encodedURL);

        /*
        request.getSession().setAttribute("fName",fn);
        request.getSession().setAttribute("lName", ln);
        request.getSession().setAttribute("dob", dob);
        request.getSession().setAttribute("photo", ph);
        
        //RequestDispatcher show = request.getRequestDispatcher("/displayMovieList.jsp");
        RequestDispatcher show = request.getRequestDispatcher("/insertStar.jsp");
        //RequestDispatcher show = request.getRequestDispatcher("movie.go");
        show.forward(request, response);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
