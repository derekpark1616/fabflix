package fabflix;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DBCPSingleMovieServlet
 */
@WebServlet(description = "Single Movie Search with Pooling", urlPatterns = { "/DBCPSingleMovieServlet" })
public class DBCPSingleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCPSingleMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
	    try {
	 
	    	//Create a datasource for pooled connections.
	    DBCP_DB.datasource = (DataSource) getServletContext().getAttribute("DBCPool");

	   //Register the driver for non-pooled connections.
	   Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }
	    catch (Exception e) {
	      throw new ServletException(e.getMessage());
	    }

	  } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		DBCP_DB db = new DBCP_DB();
		
		String title = Tools.ukw, director= Tools.ukw, banner = Tools.ukw,trailerUrl = Tools.ukw;
		Integer year =1900;
		String gr = Tools.ukw, stFn= Tools.ukw, stLn= Tools.ukw, stPh= Tools.ukw;

		Integer stId =999999, gId = 999999;
		
		String mvId;
		mvId = request.getParameter("movieId");
			int movieId = Integer.parseInt(mvId);
			List<Movie> SingelListMovie = null;
			
			if (DB.poolEnabled){
				 DBCP_MovieProcess mv = new DBCP_MovieProcess();
			 	 SingelListMovie = mv.getMovieById(movieId);
			}else {
				 MovieProcess mv = new MovieProcess();
			 	 SingelListMovie = mv.getMovieById(movieId);
			}

			 String yr = Integer.toString(year);
			 System.out.println("Pooled Connection Count:"+db.getpooledCount()+", Non Pooled Connection Count: "+db.getnonPooledCount());

			 String stID =Integer.toString(stId);
			 String gID =Integer.toString(gId);
			 
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

			 RequestDispatcher dispatcher =request.getRequestDispatcher("DBCP_single_movie.jsp");
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
