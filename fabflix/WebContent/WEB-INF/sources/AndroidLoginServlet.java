package fabflix;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.Enumeration;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class AndroidLoginServlet
 */
@WebServlet(description = "Android Login Servlet", urlPatterns = { "/AndroidLoginServlet" })
public class AndroidLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//response.setContentType("text/html;charset=UTF-8");
        
		JSONObject json = new JSONObject();
         
 
        //ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        Enumeration<String> paramNames = request.getParameterNames();
        
        String params[] = new String[2];
        int i = 0;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
 
            //System.out.println(paramName);
            String[] paramValues = request.getParameterValues(paramName);
            params[i] = paramValues[0];
            //System.out.println(params[i]);
            i++;
        }
 
        //String sql = "SELECT uname, password FROM users where uname=? and password=?";
        String loginQry = "SELECT * FROM customers WHERE email = ? AND password =?";
        if( DB.dbcon == null ){ 
			try {
				DB.dbcon = DB.getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
         
        try {
            PreparedStatement ps = DB.dbcon.prepareStatement(loginQry);
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                json.put("info", "success");
            } else {
                json.put("info", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
 
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
