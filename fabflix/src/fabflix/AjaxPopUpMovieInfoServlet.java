package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AjaxPopUpMovieInfoServlet
 */
@WebServlet(description = "Provide movie details when mouse over", urlPatterns = { "/AjaxPopUpMovieInfoServlet" })
public class AjaxPopUpMovieInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxPopUpMovieInfoServlet() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setContentType("text/html"); // Response mime type
		// Output stream to STDOUT
		PrintWriter out = response.getWriter();

		String mvId;
		mvId = request.getParameter("movieId");
		int movieId = Integer.parseInt(mvId);
		System.out.println("the movie ID is: " + movieId);

		MovieProcess mv = new MovieProcess();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies = mv.getMovieByIdAjx(movieId);

		out.println("<TABLE BORDER=1 CELLPADDING=1 CELLSPACING=1 WIDTH=80%>");
		out.println("<tr>" + "</tr>");
		out.println("<tr>" + "<th>" + "Year" + "</th>" + "<th>" + "Star First Name" + "</th>" + "<th>"
				+ "Star Last Name" + "</th>" + "<th>" + "Movie Banner" + "</th>" + "</tr>");

		for (Movie m : movies) {
			out.println("<tr>" + "<td>" + m.getMovieYear() + "</td>" + "<td>" + m.getMovieStarFn() + "</td>" + "<td>"
					+ m.getMovieStarLn() + "</td>" + "<td>" + "<a href =" + m.getMovieBanner() + ">" + "Movie Banner"
					+ "</a>" + "</td>" + "</tr>");
		}
		out.println("</TABLE>");

		/*
		 * Gson gson = new Gson(); JsonElement element = gson.toJsonTree(movies,
		 * new TypeToken<List<Movie>>() {}.getType());
		 * 
		 * JsonArray jsonArray = element.getAsJsonArray();
		 * response.setContentType("application/json");
		 * response.getWriter().print(jsonArray);
		 */

		/*
		 * 
		 * Movie movieInfo = mv.getMovieByIdAjx(movieId);
		 * 
		 * 
		 * year = movieInfo.getMovieYear(); String yr = Integer.toString(year);
		 * banner = movieInfo.getMovieBanner(); starFn =
		 * movieInfo.getMovieStarFn(); starLn = movieInfo.getMovieStarLn();
		 * 
		 * request.getSession().setAttribute("mvId",mvId);
		 * request.getSession().setAttribute("yr",yr);
		 * request.getSession().setAttribute("banner",banner);
		 * request.getSession().setAttribute("starFn",starFn);
		 * request.getSession().setAttribute("starLn",starLn);
		 * 
		 * request.getSession().setAttribute("search-results",movieInfo);
		 * 
		 */

		// RequestDispatcher dispatcher
		// =request.getRequestDispatcher("single_movie.jsp");
		// dispatcher.forward(request,response);

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
