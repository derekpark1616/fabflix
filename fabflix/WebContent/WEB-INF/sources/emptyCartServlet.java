package fabflix;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class emptyCartServlet
 */
@WebServlet("/emptyCartServlet")
public class emptyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public emptyCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String movieId = request.getParameter("movieId");
		int m  = Integer.parseInt(movieId);
		
		
		
		response.setContentType("text/html");    // Response mime type

        MovieProcess g = new MovieProcess();
        
        try {
			g.emptyCart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		request.getSession().setAttribute("mvId",m);
		
		
		RequestDispatcher show = request.getRequestDispatcher("/cart_01.jsp");
	     //RequestDispatcher show = request.getRequestDispatcher("movie.go");
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
