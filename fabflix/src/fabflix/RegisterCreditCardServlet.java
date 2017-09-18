package fabflix;


import java.io.IOException;
import java.io.PrintWriter;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterCreditCardServlet
 */
@WebServlet(description = "Inserting Credit Card Data", urlPatterns = { "/RegisterCreditCardServlet" })
public class RegisterCreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCreditCardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String cCard;
		String fName;
		String lName;
		String expDate;
		response.setContentType("text/html");    // Response mime type
		  // Output stream to STDOUT
        PrintWriter out = response.getWriter();
       
        
		cCard = request.getParameter("CreditCard");
		fName = request.getParameter("FirstName");
		lName = request.getParameter("Lastname");
		expDate = request.getParameter("ExpirationDate");

		if (lName.isEmpty() ){
			lName = fName;	
			fName  = "";
		}

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date myDate = null;
			try {
				myDate = formatter.parse(expDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				
				out.println("<font color=red>Some issue with the date entered...</font>");
				//e1.printStackTrace();
			}
			java.sql.Date expirationDate = new java.sql.Date(myDate.getTime());

			try {
				if( DB.dbcon == null ){ 
					DB.dbcon = DB.getConnection();
				}

				PreparedStatement ccinsert = DB.dbcon.prepareStatement( "REPLACE INTO `creditcards` (`id`,`first_name`,`last_name`,`expiration`) VALUES (?,?,?,?)");
				ccinsert.setString(1,cCard);
				ccinsert.setString(2,fName);
				ccinsert.setString(3,lName);
				ccinsert.setDate(4, expirationDate);
				ccinsert.executeUpdate();		// Store into our Database the Credit Card info
				
				String encodedURL = response.encodeRedirectURL("login.html");   
                response.sendRedirect(encodedURL);

	            //DB.dbcon.close();	
			}  catch (SQLException ex) {
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
