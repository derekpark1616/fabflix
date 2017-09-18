package fabflix;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBCPGenreListServlet
 */
@WebServlet(description = "Genre DBCP Search", urlPatterns = { "/DBCPGenreListServlet" })
public class DBCPGenreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCPGenreListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		String mvId;
		mvId = request.getParameter("movieId");
		int movieId = Integer.parseInt(mvId);
	
		 
		 	 DBCP_MovieProcess g = new DBCP_MovieProcess();
			 //Star starInfo = mv.getStarInfo(movieId);
			 
			 List<Genre> genreList = g.searchGenrebyMovieId(movieId);
			 
			 request.getSession().setAttribute("genre-results",genreList);
			 RequestDispatcher dispatcher =request.getRequestDispatcher("GenresListPerMovie.jsp");
			 dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}