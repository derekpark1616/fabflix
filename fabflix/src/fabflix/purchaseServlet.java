package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class purchaseServlet
 */
@WebServlet("/purchaseServlet")
public class purchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public purchaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String cCard;
		String fName;
		String lName;
		String expDate;
		response.setContentType("text/html"); // Response mime type
		// Output stream to STDOUT
		PrintWriter out = response.getWriter();

		cCard = request.getParameter("CreditCard");
		fName = request.getParameter("FirstName");
		lName = request.getParameter("LastName");
		expDate = request.getParameter("ExpirationDate");

		List<Integer> cartContaint = new ArrayList<>();

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date myDate = null;
		try {
			myDate = formatter.parse(expDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block

			out.println("<font color=red>Some issue with the date entered...</font>");
			// e1.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Date expiration = new java.sql.Date(myDate.getTime());
		MovieProcess g = new MovieProcess();

		try {
			if (DB.dbcon == null) {
				String encodedURL = response.encodeRedirectURL("login.html");
				response.sendRedirect(encodedURL);
			}

			String qry = "SELECT * FROM shopping_cart WHERE shopping_cart.customerId =?";
			PreparedStatement stm = DB.dbcon.prepareStatement(qry);
			stm.setLong(1, DB.customerID);
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				do {

					int m_T = rs.getInt("movieId");
					cartContaint.add(m_T);

				} while (rs.next());
			} else {
				// No data
				out.println("<font color=red>No Product in the cart so far... keep shopping.</font>");
				String encodedURL = response.encodeRedirectURL("main_page.jsp");
				response.sendRedirect(encodedURL);
			}

			String q = "SELECT * FROM creditcards WHERE (id = ? AND first_name =? AND last_name = ? AND expiration= ?) ";

			PreparedStatement salesInsert = DB.dbcon
					.prepareStatement("INSERT INTO `sales` (`customer_id`,`movie_id`,`sale_date`) VALUES (?,?,?)");
			PreparedStatement getCcard = DB.dbcon.prepareStatement(q);

			getCcard.setString(1, cCard);
			getCcard.setString(2, fName);
			getCcard.setString(3, lName);
			getCcard.setDate(4, expiration);

			ResultSet cc = getCcard.executeQuery();

			if (cc.next()) {
				do {

					for (int m : cartContaint) {
						// movieId = m.id;
						salesInsert.setLong(1, DB.customerID);
						salesInsert.setLong(2, m);
						salesInsert.setDate(3, sqlDate);
						salesInsert.executeUpdate(); // Store into our Database
														// the Credit Card info
					}

				} while (cc.next());
				g.emptyCart();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main_page.jsp");
				out.println("<font color=red>Operation Successful....Thank you</font>");
				rd.include(request, response);

			} else {
				// No data
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer_registration.jsp");
				out.println("<font color=red>No Match Found....</font>");
				rd.include(request, response);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL Exception:  " + ex.getMessage());
				ex = ex.getNextException();
			} // end while
		} // end catch SQLException

		catch (java.lang.Exception ex) {
			out.println("<HTML>" + "<HEAD><TITLE>" + "Movie Database: Error" + "</TITLE></HEAD>\n<BODY>"
					+ "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
			return;
		}
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
