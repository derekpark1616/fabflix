package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import org.apache.tomcat.jdbc.pool.PoolProperties;
//import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Servlet implementation class DBCPInsertStarServlet
 */
@WebServlet(description = "DBCP for inserting star", urlPatterns = { "/DBCPInsertStarServlet" })
public class DBCPInsertStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DBCPInsertStarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		System.out.println("Now attempting to initialize the DB");

		try {

			// Create a datasource for pooled connections.
			DBCP_DB.datasource_emp = (DataSource) getServletContext().getAttribute("DBCPoolEmp");
			System.out.println("DB Initialization complete...");
			// Register the driver for non-pooled connections.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8"); // Response mime
															// type
		PrintWriter out = response.getWriter();

		DBCP_DB dbs = new DBCP_DB();
		Connection connection = null;

		String queryPar = request.getParameter("pool").trim();
		boolean plEnabled = queryPar.equalsIgnoreCase("yes") ? true : false;

		String fn, ln, dt, ph;
		fn = request.getParameter("fName");
		ln = request.getParameter("lName");
		dt = request.getParameter("dob");
		ph = request.getParameter("Photo");

		if (fn.isEmpty()) {
			fn = "";

		}
		if (ln.isEmpty()) {
			ln = fn;
			fn = "";
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
			if (DB.dbcon == null) {
				String encodedURL = response.encodeRedirectURL("_dashboard/empaccess.html");
				response.sendRedirect(encodedURL);

			}

			// connection = dbs.getConnection(plEnabled);
			connection = dbs.getConnection(plEnabled, 1);
			PreparedStatement starinsert = connection.prepareStatement(
					"INSERT INTO `stars` (`first_name`,`last_name`,`dob`,`photo_url`) VALUES (?,?,?,?)");

			starinsert.setString(1, fn);
			starinsert.setString(2, ln);
			starinsert.setDate(3, dob);
			starinsert.setString(4, ph);

			starinsert.executeUpdate();

			// for (String FullName:SAXParserStars.starFullNameSet){ }

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
			System.out.println(Tools.dbs);
			return;
		}

		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("Star Inserted Successfuly");
		out.println("<a href =" + "_dashboard/empDashboard.jsp" + ">" + "Continue" + "</a>");
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
