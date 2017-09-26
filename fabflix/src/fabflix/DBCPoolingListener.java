package fabflix;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class DBCPoolingListener
 *
 */
@WebListener
public class DBCPoolingListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public DBCPoolingListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

		try {

			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
			DataSource ds_emp = (DataSource) envCtx.lookup("jdbc/moviedb_emp");

			// Obtain our environment naming context
			// Context envCtx = (Context) new
			// InitialContext().lookup("java:comp/env");
			// Look up our data source
			// DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");

			arg0.getServletContext().setAttribute("DBCPool", ds);
			arg0.getServletContext().setAttribute("DBCPoolEmp", ds_emp);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
