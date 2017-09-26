package fabflix;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DBCPSrchMovieServlet
 */
@WebServlet(description = "Let give it a try", urlPatterns = { "/DBCPSrchMovieServlet" })
public class DBCPSrchMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DBCPSrchMovieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		try {

			// Create a datasource for pooled connections.
			DBCP_DB.datasource = (DataSource) getServletContext().getAttribute("DBCPool");

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

		long startTime = System.nanoTime();

		DBCP_DB db = new DBCP_DB();
		response.setContentType("text/html;charset=UTF-8"); // Response mime
															// type
		// PrintWriter out = response.getWriter();
		String queryPar = request.getParameter("pool").trim();
		boolean plEnabled = queryPar.equalsIgnoreCase("yes") ? true : false;

		String rows = request.getParameter("nRows");
		int nRowsToDisplay = Integer.parseInt(rows);

		System.out.println("Pooled Connection Count:" + db.getpooledCount() + ", Non Pooled Connection Count: "
				+ db.getnonPooledCount());
		// Changes to accommodate more options for search by Drc
		String mT, yr, dr, sr;
		mT = request.getParameter("Title");
		yr = request.getParameter("Year");

		dr = request.getParameter("Director");
		sr = request.getParameter("stare");
		String mTitle = null, director = null, stare = null;
		String year = null;

		if (!mT.equals("")) {
			mTitle = mT;
		}
		if (!yr.equals("")) {
			year = yr;
		}
		if (!dr.equals("")) {
			director = dr;
		}
		if (!sr.equals("")) {
			stare = sr;
		}

		int pg = 1;
		if (request.getParameter("pg") != null)
			pg = Integer.parseInt(request.getParameter("pg"));

		List<Movie> movieList = null;
		int noOfRecords;

		/*
		 * DBCP_MovieProcess g = new DBCP_MovieProcess(); movieList =
		 * g.searchMovies(mTitle, year, director, stare,(pg - 1) *
		 * nRowsToDisplay, nRowsToDisplay); noOfRecords = g.getNoOfRecords();
		 */

		if (plEnabled) {
			DB.poolEnabled = true;
			DBCP_MovieProcess g = new DBCP_MovieProcess();
			movieList = g.searchMovies(mTitle, year, director, stare, (pg - 1) * nRowsToDisplay, nRowsToDisplay);
			noOfRecords = g.getNoOfRecords();

		} else {
			DB.poolEnabled = false;
			MovieProcess g = new MovieProcess();
			movieList = g.searchMovies(mTitle, year, director, stare, (pg - 1) * nRowsToDisplay, nRowsToDisplay);
			noOfRecords = g.getNoOfRecords();
		}

		// List<Movie> movieList =
		// g.searchMovies(mTitle,(pg-1)*nRowsToDisplay,nRowsToDisplay);
		System.out.println("Here the value of pages");
		for (Movie mv : movieList) {
			System.out.println("Here the value of pge:" + pg);
			// System.out.println("Here the value of sort:" +sort);
			System.out.println(mv.id + mv.director);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / nRowsToDisplay);
		request.getSession().setAttribute("search-results", movieList);
		request.getSession().setAttribute("noOfPages", noOfPages);
		request.getSession().setAttribute("currentPage", pg);
		request.getSession().setAttribute("nRows", nRowsToDisplay);
		request.getSession().setAttribute("Year", year);
		request.getSession().setAttribute("Director", director);
		request.getSession().setAttribute("stare", stare);
		request.getSession().setAttribute("ttle", mTitle);
		request.getSession().setAttribute("pool", "yes");

		RequestDispatcher show = request.getRequestDispatcher("/DBCPmovieListView.jsp");

		long stopTime = System.nanoTime();
		long total = stopTime - startTime;
		System.out.println("It tooks : " + total + " nano seconds to complete the search");

		// Performance calculation...
		// String TS = "C:\\TestJava\\TestResults\\DBCPts.txt"; //This is on a
		// window system
		String TS = "/home/shares/uci/cs122b/prj5/rs/DBCPts.txt";

		try {

			File file = new File(TS);
			FileWriter output = new FileWriter(file, true);
			PrintWriter writer = new PrintWriter(output);
			writer.println(total);
			writer.close();

		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
		show.forward(request, response);

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
