package fabflix;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/LoginServlet")
//Adapted from Original Java Servlet Session Management 
//Tutorial code by Pankaj Internet: www.journaldev.com
/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Method for Searching the Database", urlPatterns = { "/LoginServlet" })

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public String getServletInfo() {
		return "Servlet connects to MySQL database and displays result of a SELECT";
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		response.setContentType("text/html"); // Response mime type
		/*
		 * DBCP_DB dbs = new DBCP_DB(); Connection connection = null; String
		 * queryPar = request.getParameter("pool").trim(); boolean plEnabled =
		 * queryPar.equalsIgnoreCase("yes")? true: false;
		 */

		// Output stream to STDOUT
		PrintWriter out = response.getWriter();
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		// Verify CAPTCHA.
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
			// errorString = "Captcha invalid!";
			out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
					+ "<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
			return;
		}

		try {
			// connection = dbs.getConnection(plEnabled);

			if (DB.dbcon == null) {
				DB.dbcon = DB.getConnection();
			}

			out.println("<HTML><HEAD> <TITLE>Fabflix Movie Store After db.getconnect</TITLE></HEAD>");
			out.println("<BODY><H1>Fabflix Movie Store Checking</H1>");
			String email = request.getParameter("Email");
			String passwd = request.getParameter("password");

			// String query = "SELECT * from customers WHERE email = '" + email
			// + "'";
			String qry = "SELECT * FROM customers WHERE email = ? AND password =?";
			int rowCount = 0;
			List<Object> d = new ArrayList<>();

			// PreparedStatement userCredentialsQuery =
			// connection.prepareStatement(qry);
			PreparedStatement userCredentialsQuery = DB.dbcon.prepareStatement(qry);
			userCredentialsQuery.setString(1, email);
			userCredentialsQuery.setString(2, passwd);
			ResultSet results = userCredentialsQuery.executeQuery();

			while (results.next()) {
				d.add(results);
				DB.customerID = results.getInt(1);
			}

			rowCount = d.size();
			out.println("size:  </BODY>" + rowCount);
			System.out.println("size:  " + rowCount);

			HttpSession session = request.getSession();
			// Integer accessCount = (Integer)session.getAttribute("user");
			// String heading;

			if (rowCount != 1) {

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				out.println("<font color=red>User name or password incorrect.</font>");
				System.out.println("User name or password incorrect.");
				rd.include(request, response);
			} else {

				out.println("Access Granted... Thank you</BODY>");
				System.out.println("Access Granted... Thank you");

				session.setAttribute("user", email);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("user", email);
				response.addCookie(userName);

				// Get the encoded URL string
				// String encodedURL =
				// response.encodeRedirectURL("/fabflix/main_page.jsp");
				// response.sendRedirect(encodedURL);
				// response.sendRedirect(response.encodeRedirectURL(encodedURL));

				RequestDispatcher show = request.getRequestDispatcher("/main_page.jsp");
				show.forward(request, response);

			}

			userCredentialsQuery.close();
			// Movie.dbcon.close();
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
