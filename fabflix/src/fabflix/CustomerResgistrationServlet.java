package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerResgistrationServlet
 */
@WebServlet(description = "Customer Registration form", urlPatterns = { "/CustomerResgistrationServlet" })
public class CustomerResgistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerResgistrationServlet() {
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
		  // Output stream to STDOUT
      PrintWriter out = response.getWriter();
 
      
      
		String fName;
		String lName;
		String creditCard;
		String address;
		String email;
		String password;

		fName = request.getParameter("FristName");
		lName = request.getParameter("LastName");
		creditCard = request.getParameter("CreditCard");
		address = request.getParameter("Address");
		email = request.getParameter("Email");
		password = request.getParameter("Password");
		
		if (lName.isEmpty() ){
			lName = fName;	
			fName  = "";
		}

		try {
			
			if( DB.dbcon == null ){ 
				DB.dbcon = DB.getConnection();
			}

			PreparedStatement customerinsert = DB.dbcon.prepareStatement( "INSERT INTO `customers` (`first_name`,`last_name`,"
					+ "`cc_id`,`address`,`email`,`password`) VALUES (?,?,?,?,?,?)");
			
			PreparedStatement getCcard = DB.dbcon.prepareStatement("Select cc.id from creditcards as cc where cc.id = ? ");
			getCcard.setString(1, creditCard);
			ResultSet cc = getCcard.executeQuery();
			
			if (cc.next()) {
			    do {
			      // Logic to retrieve the data from the resultset.
					customerinsert.setString(1,fName);
					customerinsert.setString(2,lName);
					customerinsert.setString(3,creditCard);
					customerinsert.setString(4,address);
					customerinsert.setString(5,email);
					customerinsert.setString(6,password);	
					customerinsert.executeUpdate();	// Store into our Database the Star info
					
			    } while(cc.next());
			    
			    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");      
                out.println("<font color=red>Thank you...</font>");
                rd.include(request, response);
			    
			} else {
			    // No data
				out.println("<font color=red>Credit card missing... Launching the page to add if wanted...</font>");
				
				String encodedURL = response.encodeRedirectURL("register_cc.jsp");   
				response.sendRedirect(encodedURL);
				
			}
		
			String encodedURL = response.encodeRedirectURL("login.html");   
			response.sendRedirect(encodedURL);
			
		cc.close();
		//DB.dbcon.close();
		
		} catch (SQLException ex) {
            while (ex != null) {
                System.out.println ("SQL Exception:  " + ex.getMessage ());
                ex = ex.getNextException ();
            }  // end while
        }  // end catch SQLException

		catch(java.lang.Exception ex)
        {
            out.println("<HTML>" +
                        "<HEAD><TITLE>" +
                        "Movie Database: Error" +
                        "</TITLE></HEAD>\n<BODY>" +
                        "<P>SQL error in doGet: " +
                        ex.getMessage() + "</P></BODY></HTML>");
            return;
        }
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
