package fabflix;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class DBCPCallProcedure {

	public DBCPCallProcedure() {

	}

	public static void createProcedure() {

		DBCP_DB dbs = new DBCP_DB();
		Connection connection = null;
		Statement stmt = null;

		try {

			connection = dbs.getConnection(true, 1);
			stmt = connection.createStatement();

			stmt.execute("DROP procedure IF EXISTS `add_movie`; "
					+ "CREATE DEFINER=`testuser`@`%` PROCEDURE `add_movie`(" + "IN  t VARCHAR(100)," + "IN y INT(11),"
					+ "IN d VARCHAR(100)," + "IN fn VARCHAR(45)," + "IN ln VARCHAR(50)," + "IN ge VARCHAR(32))"
					+ "BEGIN" + "DECLARE movieId INT;" + "DECLARE starId INT;" + "DECLARE genreId INT;" +

					"INSERT INTO `movies`(`title`,`year`,`director`) SELECT * FROM (SELECT t,y,d) AS tmp WHERE NOT EXISTS (SELECT title FROM movies WHERE title = t) LIMIT 1;"
					+ "INSERT INTO `genres`(`name`) SELECT * FROM (SELECT ge) AS gen WHERE NOT EXISTS (SELECT name FROM genres WHERE name = ge) LIMIT 1;"
					+ "INSERT INTO `stars`(`first_name`,`last_name`) SELECT * FROM (SELECT fn,ln) AS str WHERE NOT EXISTS "
					+ "(SELECT first_name, last_name FROM stars WHERE first_name = fn AND last_name = ln) LIMIT 1; "
					+ "SELECT id FROM movies WHERE title = t LIMIT 1 INTO movieId;"
					+ "SELECT id FROM stars WHERE first_name = fn AND last_name = ln LIMIT 1 INTO starId;"
					+ "SELECT id FROM genres WHERE name = ge LIMIT 1 INTO genreId;"
					+ "INSERT INTO `genres_in_movies`(`genre_id`,`movie_id`) SELECT * FROM (SELECT genreId,movieId) AS ginm "
					+ "WHERE NOT EXISTS (SELECT genre_id, movie_id FROM genres_in_movies WHERE genre_id = genreId AND movie_id = movieId) LIMIT 1;"
					+ "INSERT INTO `stars_in_movies`(`star_id`,`movie_id`) SELECT * FROM (SELECT starId,movieId) AS sinm "
					+ "WHERE NOT EXISTS (SELECT star_id, movie_id FROM stars_in_movies WHERE star_id = starId AND movie_id = movieId) LIMIT 1;"
					+ "END");

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}

			}
		}

	}

	public static void dropProcedure() {
		DBCP_DB dbs = new DBCP_DB();
		Connection connection = null;
		Statement stmt = null;

		try {

			connection = dbs.getConnection(true, 1);
			stmt = connection.createStatement();
			stmt.execute("DROP PROCEDURE IF EXISTS `add_movie`");
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}
		}

	}

	public static void callProcedure(String title, int year, String director, String star_fn, String star_ln,
			String mv_genre) {

		DBCP_DB dbs = new DBCP_DB();
		Connection connection = null;
		CallableStatement cs = null;

		try {

			connection = dbs.getConnection(true, 1);

			cs = connection.prepareCall("{call add_movie(?,?,?,?,?,?)}");
			cs.setString(1, title);
			cs.setInt(2, year);
			cs.setString(3, director);
			cs.setString(4, star_fn);
			cs.setString(5, star_ln);
			cs.setString(6, mv_genre);
			cs.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("SQLException: " + e.getMessage());
				}
			}

		}
	}

}
