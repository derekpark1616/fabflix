package fabflix;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updateQteServlet
 */
@WebServlet("/updateQteServlet")
public class updateQteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateQteServlet() {
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
		// HttpSession session = request.getSession();

		String movieId = request.getParameter("movieId");
		String qte = request.getParameter("qte");
		int m = Integer.parseInt(movieId);
		int q = Integer.parseInt(qte);

		response.setContentType("text/html"); // Response mime type

		MovieProcess g = new MovieProcess();

		try {
			g.updateQteCart(m, q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("mvQt", q);
		request.getSession().setAttribute("mvId", m);

		RequestDispatcher show = request.getRequestDispatcher("/cart_01.jsp");
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
