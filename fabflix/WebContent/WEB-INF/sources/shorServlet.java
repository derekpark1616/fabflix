package fabflix;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class shorServlet
 */
@WebServlet("/shorServlet")
public class shorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//PrintWriter out = response.getWriter();
		String qt = request.getParameter("qte");
		
		String mvId = request.getParameter("mvId");
		int q  = Integer.parseInt(qt);
		int m  = Integer.parseInt(mvId);
		String mvTitle = request.getParameter("mvTitle");
		String mvBan = request.getParameter("mvBan");
		
		
		MovieProcess g = new MovieProcess();
		g.insertQuantity(m, q);
		
		request.getSession().setAttribute("mvQt",q);
		request.getSession().setAttribute("mvTitle",mvTitle);
		request.getSession().setAttribute("mvId",m);
		request.getSession().setAttribute("mvBan",mvBan);
		
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
