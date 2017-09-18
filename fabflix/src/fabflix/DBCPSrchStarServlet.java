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
 * Servlet implementation class DBCPSrchStarServlet
 */
@WebServlet(description = "DBCP for Star Search", urlPatterns = { "/DBCPSrchStarServlet" })
public class DBCPSrchStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCPSrchStarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		String stId = request.getParameter("starId");
		int starId = Integer.parseInt(stId);
 
        
        DBCP_MovieProcess g = new DBCP_MovieProcess(); 
        List<Movie> MovieList = g.searchMoviesbyStarId(starId);
        
        for (Movie mv:MovieList){
        	out.println(mv.title);
        }
         
        request.getSession().setAttribute("search-results",MovieList);    
        RequestDispatcher show = request.getRequestDispatcher("/MovieByStarId.jsp");
        show.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
