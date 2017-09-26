package fabflix;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bg2Servlet
 */
@WebServlet("/Bg2Servlet")
public class Bg2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bg2Servlet() {
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
		PrintWriter out = response.getWriter();
		String rows = request.getParameter("nRows");

		if (rows.equalsIgnoreCase(null)) {
			rows = "10";
		}
		int nRowsToDisplay = Integer.parseInt(rows);

		String mT;
		mT = request.getParameter("lt");
		String mTitle = mT;

		int pg = 1;

		if (request.getParameter("pg") != null)
			pg = Integer.parseInt(request.getParameter("pg"));

		MovieProcess g = new MovieProcess();

		List<Movie> movieList = g.searchMvTitle(mTitle, (pg - 1) * nRowsToDisplay, nRowsToDisplay);

		for (Movie mv : movieList) {
			out.println("Here the value of pge:" + pg);
			System.out.println(mv.id + mv.director);
		}

		int noOfRecords = g.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / nRowsToDisplay);

		request.getSession().setAttribute("search-results", movieList);
		request.getSession().setAttribute("noOfPages", noOfPages);
		request.getSession().setAttribute("currentPage", pg);
		request.getSession().setAttribute("nRows", nRowsToDisplay);
		request.getSession().setAttribute("ttle", mTitle);

		// RequestDispatcher show =
		// request.getRequestDispatcher("/browse_t_g.jsp");
		RequestDispatcher show = request.getRequestDispatcher("/browsing_t.jsp");
		// RequestDispatcher show = request.getRequestDispatcher("movie.go");
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
