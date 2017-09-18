package fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBCPAjaxPopUpMovieInfoServlet
 */
@WebServlet(description = "Ajax DBCP", urlPatterns = { "/DBCPAjaxPopUpMovieInfoServlet" })
public class DBCPAjaxPopUpMovieInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCPAjaxPopUpMovieInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		//response.getWriter().append("Served at: ").append(request.getContextPath());	
				response.setContentType("text/html");    // Response mime type
				// Output stream to STDOUT
				PrintWriter out = response.getWriter();
				
				String mvId;
				mvId = request.getParameter("movieId");
				int movieId = Integer.parseInt(mvId);
				System.out.println("the movie ID is: " +movieId);
				
				DBCP_MovieProcess mv = new DBCP_MovieProcess(); 
				ArrayList<Movie> movies=new ArrayList<Movie>();
				movies = mv.getMovieByIdAjx(movieId);
				
				out.println("<TABLE BORDER=1 CELLPADDING=1 CELLSPACING=1 WIDTH=80%>");
				out.println("<tr>" +"</tr>");
				out.println("<tr>" +"<th>" +"Year"+"</th>"+"<th>" +"Star First Name" +"</th>"+"<th>" +"Star Last Name" +"</th>" +"<th>" +"Movie Banner" +"</th>"  +"</tr>");
				
				for (Movie m:movies){
					out.println("<tr>" +"<td>" +m.getMovieYear()+"</td>"+"<td>" +m.getMovieStarFn() +"</td>" +"<td>" +m.getMovieStarLn() +"</td>"+"<td>" + "<a href =" +m.getMovieBanner()  +">" +"Movie Banner" +"</a>" +"</td>"+"</tr>");
				}
				out.println("</TABLE>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
