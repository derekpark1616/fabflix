package fabflix;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AndroidMovieSearchServlet
 */
@WebServlet(description = "Jason Search Result return", urlPatterns = { "/AndroidMovieSearchServlet" })
public class AndroidMovieSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidMovieSearchServlet() {
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
		// response.setContentType("text/html;charset=UTF-8");

		// JsonObject jsonResponse = new JsonObject();
		JSONObject jsonResponse = new JSONObject();
		// JsonArray data = new JsonArray();

		List<AndroidMovie> list = new ArrayList<AndroidMovie>();

		// Map<String, String> map=new HashMap<String, String>();

		// ObjectOutputStream out = new
		// ObjectOutputStream(response.getOutputStream());
		Enumeration<String> paramNames = request.getParameterNames();

		String MvT = request.getParameter("mvId");
		System.out.println("The search movie with term Option 1 :" + MvT);

		String params[] = new String[1];
		int i = 0;
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			// System.out.println(paramName);
			String[] paramValues = request.getParameterValues(paramName);
			params[i] = paramValues[0];
			// System.out.println(params[i]);
			i++;
		}

		System.out.println("The search movie term Option 2 :" + params[0]);

		String string = params[0];
		/*
		 * String string = params[0] .replace("!", "!!") .replace("%", "!%")
		 * .replace("_", "!_") .replace("[", "![");
		 */

		String squery = null, dquery = null;
		dquery = "SELECT m.id AS movieId,m.title AS Title FROM movies as m WHERE MATCH(m.title) AGAINST";

		if (string != null) {
			String[] keys = string.split(" ");
			if (keys.length > 1) {
				for (int j = 0; j < keys.length; j++) {
					if (j == 0) {
						squery = dquery + "('+" + keys[j] + " ";
					} else if (j < keys.length - 1) {
						squery = squery.concat("+" + keys[j] + " ");
					} else if (j == keys.length - 1) {
						squery = squery.concat(keys[j] + "*' IN BOOLEAN MODE) ORDER BY m.id LIMIT 0,20;");
					}
				}
			} else {
				squery = dquery + "('" + string + "*' IN BOOLEAN MODE) ORDER BY m.id LIMIT 0,20;";
			}
			System.out.println(squery);

		}

		// squery = dquery + "('"+string+"*' IN BOOLEAN MODE) ORDER BY m.id
		// LIMIT 0,20;";

		// String searchQry = "SELECT m.id AS MovieId, m.title AS Title FROM
		// movies as m WHERE m.title LIKE ? ESCAPE '!' ORDER BY m.id LIMIT
		// 0,20";

		if (DB.dbcon == null) {
			try {
				DB.dbcon = DB.getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			// PreparedStatement ps = DB.dbcon.prepareStatement(searchQry);
			PreparedStatement ps = DB.dbcon.prepareStatement(squery);
			// ps.setString(1,"%" + string + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int mvId = rs.getInt(1);
				String ttl = rs.getString(2);

				AndroidMovie mv = null;
				mv = new AndroidMovie(mvId, ttl);

				// mv.setMovieId(rs.getInt(1));
				// mv.setMovieTitle(rs.getString(2));
				list.add(mv);

			}
			jsonResponse.put("JsonMovies", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());
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
