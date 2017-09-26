package fabflix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static Connection dbcon;
	// static reference to itself
	private static DB instance = new DB();
	// JDBC driver name and database URL
	String driver = "com.mysql.jdbc.Driver";
	String urlDbase = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
	// String urlDbase =
	// "jdbc:mysql://localhost:3306/moviedb_project3_grading?useSSL=false";
	String loginUser = "testuser";
	String loginPasswd = "testpass";
	public static int customerID;
	public static String empID;
	public static boolean poolEnabled = true;

	// String urlDbase = "jdbc:mysql://sylvester-mccoy-v3:3306/info124grp06";
	// String loginUser = "inf124grp06";
	// String loginPasswd = "#e4ubreF";

	// sylvester-mccoy-v3

	public static String qrys = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year,m.director AS Director, "
			+ "m.banner_url AS Banner,m.trailer_url AS Trailer,g.name AS Genre,s.last_name AS LastName,s.photo_url AS Photo,s.first_name AS FirstName "
			+ "FROM movies as m,stars AS s, genres AS g,stars_in_movies AS sm, genres_in_movies AS gm "
			+ "WHERE ( m.id = sm.movie_id) AND (s.id=sm.star_id AND g.id=gm.genre_id)";

	public static String qryt = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE m.id = sm.movie_id AND s.id = sm.star_id";

	public static String qryAjxs = "SELECT m.title AS Title FROM movies as m WHERE m.title LIKE ? ESCAPE '!'";
	public static String qryAjx = "SELECT m.id,m.title AS Title FROM movies as m WHERE m.title LIKE";

	public static String starQry = "SELECT st.id, st.first_name,st.last_name, st.dob, st.photo_url,m.id AS MovieId, m.title AS Title "
			+ "FROM stars AS st, stars_in_movies AS sm, movies AS m "
			+ "WHERE st.id = ? AND sm.star_id = st.id AND sm.movie_id = m.id GROUP BY m.id;";

	public static String starQryByMovieId = "SELECT st.id AS starId, st.first_name AS FirstName,st.last_name AS LastName,st.dob AS DOB,"
			+ "st.photo_url AS Photo FROM stars AS st, stars_in_movies AS sm, movies AS m WHERE st.id = sm.star_id AND sm.movie_id = ?  GROUP BY st.id;";

	public static String AjxQryByMovieId = "SELECT m.year AS Year,m.banner_url AS Banner,st.first_name AS FirstName,st.last_name AS LastName FROM stars AS st,stars_in_movies AS sm,movies AS m WHERE m.id = ? AND (m.id = sm.movie_id AND st.id = sm.star_id) GROUP BY st.id;";

	public static String genreQryByMovieId = "SELECT gr.id AS genreId, gr.name AS Category FROM genres AS gr, genres_in_movies AS gm, movies AS m "
			+ "WHERE gr.id = gm.genre_id AND gm.movie_id = ?  GROUP BY gr.id;";

	public static String qry = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo, s.id AS PhotoId,s.first_name AS FirstName, s.dob AS DOB FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE m.title LIKE ? AND m.id = sm.movie_id AND s.id = sm.star_id LIMIT ?,?;";

	public static String qryg = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo , s.id AS PhotoId,s.first_name AS FirstName, s.dob AS DOB FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE g.name LIKE ? AND m.id = sm.movie_id AND s.id = sm.star_id LIMIT ?,?;";

	public static String qrygt = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo , s.id AS PhotoId,s.first_name AS FirstName, s.dob AS DOB FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE m.title ";

	public static String qrygg = "SELECT SQL_CALC_FOUND_ROWS m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo , s.id AS PhotoId,s.first_name AS FirstName, s.dob AS DOB FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE g.name ";

	public static String qryByMovieID = "SELECT m.id AS ID,m.title AS Title,m.year AS Year, m.director AS Director, m.banner_url AS Banner, "
			+ "m.trailer_url AS Trailer, g.name AS Genre, s.last_name AS LastName, s.photo_url AS Photo, s.id AS PhotoId, "
			+ "s.first_name AS FirstName, s.dob AS DOB FROM movies as m, "
			+ "stars AS s, genres AS g, stars_in_movies AS sm WHERE m.id = ? AND m.id = sm.movie_id AND s.id = sm.star_id LIMIT 1;";

	public static String qryByMovieId = "SELECT m.title AS Title,m.year AS Year,m.director AS Director, m.banner_url AS Banner,"
			+ "m.trailer_url AS Trailer,g.id AS GenreId, g.name AS Genre, s.id AS starId,s.last_name AS LastName,s.photo_url AS Photo,"
			+ "s.first_name AS FirstName, s.dob AS DOB, m.id AS MovieID FROM movies as m,stars AS s, genres AS g,stars_in_movies AS sm, genres_in_movies AS gm "
			+ "WHERE m.id = ? AND m.id = sm.movie_id AND s.id = sm.star_id GROUP BY s.id;";

	public static String MovieQryByStarId = "SELECT m.id AS ID,m.title AS Title,s.id AS StarId FROM movies as m, "
			+ "stars AS s,stars_in_movies AS sm WHERE s.id = ? AND m.id = sm.movie_id AND s.id = sm.star_id GROUP BY m.id;";

	// private constructor
	private DB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("\nMySQL Driver problem detected. Check it...");
			e.printStackTrace();
		}
	}

	public static DB getInstance() {
		return instance;
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = getInstance().dbConnection();
		System.out.println("\nDatabse is now open for interaction...");
		return con;
	}

	public Connection dbConnection() throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(urlDbase, loginUser, loginPasswd);

		System.out.println("\nDatabse access credentials validation successful...cool");
		return connection;
	}

	public static int countRows(Connection conn, String tableName) throws SQLException {

		// select the number of rows in the table
		Statement stmt = null;
		ResultSet rs = null;
		int rowCount = -1;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);
		} finally {
			rs.close();
			stmt.close();
		}

		return rowCount;

	}

}
