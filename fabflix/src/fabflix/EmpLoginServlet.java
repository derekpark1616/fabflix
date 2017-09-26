package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmpLoginServlet
 */
@WebServlet(description = "Employee Dashboard Access", urlPatterns = { "/_dashboard/EmpLoginServlet" })
public class EmpLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpLoginServlet() {
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

		HttpSession session = request.getSession();
		String email = request.getParameter("empEmail");
		String passwd = request.getParameter("empPassword");

		String qry = "SELECT * FROM employees WHERE email = ? AND password =?";
		int rowCount = 0;
		List<Object> d = new ArrayList<>();

		out.println("<HTML><HEAD> <TITLE>Fabflix Movie Store</TITLE></HEAD>");
		out.println("<BODY><H1>Fabflix Movie Store</H1>");

		try {

			if (DB.dbcon == null) {
				DB.dbcon = DB.getConnection();
			}

			PreparedStatement userCredentialsQuery = DB.dbcon.prepareStatement(qry);
			userCredentialsQuery.setString(1, email);
			userCredentialsQuery.setString(2, passwd);
			ResultSet results = userCredentialsQuery.executeQuery();

			while (results.next()) {
				d.add(results);
				DB.empID = results.getString(1);
			}

			rowCount = d.size();
			out.println("size:  </BODY>" + rowCount);
			System.out.println("size:  " + rowCount);
			// Integer accessCount = (Integer)session.getAttribute("user");
			// String heading;

			if (rowCount != 1) {

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/_dashboard/empaccess.html");
				out.println("<font color=red>User name or password incorrect.</font>");
				System.out.println("User name or password incorrect.");
				rd.include(request, response);
			} else {

				out.println("Access Granted... Thank you</BODY>");

				session.setAttribute("user", email);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("user", email);
				response.addCookie(userName);

				// Get the encoded URL string
				// String encodedURL =
				// response.encodeRedirectURL("empDashboard.jsp");
				// response.sendRedirect(encodedURL);

				RequestDispatcher rd = request.getRequestDispatcher("/_dashboard/empDashboard.jsp");
				System.out.println("Access Granted... Thank you");
				rd.forward(request, response);

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
