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
 * Servlet implementation class SingleMovieServlet
 */
@WebServlet(description = "Single Movie Search", urlPatterns = { "/SingleMovieServlet" })
public class SingleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
			String title = Tools.ukw, director= Tools.ukw, banner = Tools.ukw,trailerUrl = Tools.ukw;
			Integer year =1900;
			String gr = Tools.ukw, stFn= Tools.ukw, stLn= Tools.ukw, stPh= Tools.ukw;
	
			Integer stId =999999, gId = 999999;
			
			String mvId;
			mvId = request.getParameter("movieId");
				int movieId = Integer.parseInt(mvId);
		
			 	 MovieProcess mv = new MovieProcess();
			 	 List<Movie> SingelListMovie = mv.getMovieById(movieId);
				 	
				 //year = movieInfo.getMovieYear();
				 String yr = Integer.toString(year);
				 
				 //stId = movieInfo.getMovieStarId();
				 //gId = movieInfo.getMovieGenreId();
				 String stID =Integer.toString(stId);
				 String gID =Integer.toString(gId);
				 
				 /*
				 title = movieInfo.getMovieTitle();
				 director = movieInfo.getMovieDirector();
				 banner = movieInfo.getMovieBanner();
				 trailerUrl = movieInfo.getMovieTrailer();
				 
				 gr = movieInfo.getMovieGenreName();
				 stFn = movieInfo.getMovieStarFn();
				 stLn = movieInfo.getMovieStarLn();
				 stPh = movieInfo.getMovieStarPhoto(); 
				
				*/
				 				 
				 
				 //Star starInfo = mv.getStarInfo(movieId);
				 request.getSession().setAttribute("mvId",mvId);
				 request.getSession().setAttribute("title",title);
				 request.getSession().setAttribute("yr",yr);
				 request.getSession().setAttribute("director",director);
				 request.getSession().setAttribute("banner",banner);
				 request.getSession().setAttribute("trailerUrl",trailerUrl);
				 request.getSession().setAttribute("gID",gID);
				 request.getSession().setAttribute("gr",gr);
				 request.getSession().setAttribute("stID",stID);
				 request.getSession().setAttribute("stFn",stFn);
				 request.getSession().setAttribute("stLn",stLn);
				 request.getSession().setAttribute("stPh",stPh);
		 
				 request.getSession().setAttribute("search-results",SingelListMovie);
				 
				 
				 //request.getSession().setAttribute("star-results",starInfo);
				 RequestDispatcher dispatcher =request.getRequestDispatcher("single_movie.jsp");
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
