package fabflix;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


public class DBInteraction {
	//public static Connection dbconn = null;	// Our Database COnnection
	public static List<String> lMv;
	public static List<Integer> listMId;
	public static List<String> gx;
	
	public static List<Integer> starId;
	public static List<String> STAR;
	
	
	
	public static void loadStars(){
		
		gx = null;
		gx = new ArrayList<String>();
		String fn, ln, fnln ;
		
		
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}	
			//dbconn = DatabaseConnection.getConnection();
	
			PreparedStatement getStarFulName = DB.dbcon.prepareStatement("SELECT id,first_name,last_name FROM stars " );
			
			
			//getGenre.setString(1, genre);
			ResultSet cc = getStarFulName.executeQuery();	
			//long starId = 0;
			while(cc.next()){
				fn=cc.getString("first_name");
				ln=cc.getString("last_name");
				fn = Tools.cleanString(fn);
				ln = Tools.cleanString(ln);
				fnln = fn + " " + ln;
				gx.add(fnln);
			}
		} catch ( Exception e ) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  //System.exit(0);
			  return;
			}

			try {
				//Main.MainMenu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public static void loadStars2(){
		STAR = null;
		starId = null;
		
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}	
			PreparedStatement getFLName = DB.dbcon.prepareStatement("SELECT id,first_name,last_name FROM stars " );
			
			STAR = new ArrayList<String>();
			starId = new ArrayList<Integer>();
			ResultSet cc = getFLName.executeQuery();
			
			String fn = "", ln = "";
			int stId;
			String fnln;
			
			while(cc.next()){
				fn=cc.getString("first_name");
				ln=cc.getString("last_name");
				fn = Tools.cleanString(fn);
				ln = Tools.cleanString(ln);
				fnln = fn +" "+ln;
				stId=cc.getInt("id");
				
				STAR.add(fnln);
				starId.add(stId);
			}
		} catch ( Exception e ) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  //System.exit(0);
			  return;
			}

			try {
				//Main.MainMenu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static void loadMovies(){
		listMId = null;
		lMv = null;
		
		int ID;
		String t = "";

		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}
				
			//dbconn = DatabaseConnection.getConnection();
			
			PreparedStatement getMovieTitle = DB.dbcon.prepareStatement("SELECT id,title FROM movies " );
			
			listMId = new ArrayList<Integer>();
			lMv = new ArrayList<String>();
			ResultSet cMv = getMovieTitle.executeQuery();
			
			while(cMv.next()){
				t=cMv.getString("title");
				t = Tools.cleanString(t);
				ID = cMv.getInt("id");
				lMv.add(t);
				listMId.add(ID);
			}
			
		cMv.close();		
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  //System.exit(0);
		  
		  System.out.println(Tools.dbs);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static void InsertMoreMv(int mvID, String mvTtle, String mvDir ){
		int yr = Tools.randBetween (1925,2011);
		int retId =0;
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}
			
			/*
			//dbconn = DatabaseConnection.getConnection();
			String bigQ ="INSERT INTO `movies`(`id`,`title`,`year`,`director`) SELECT * FROM (SELECT ?,?,?,?) AS tmp WHERE NOT EXISTS (SELECT name FROM movies WHERE title = ? ) LIMIT 1;";
			PreparedStatement movieinsert = DB.dbcon.prepareStatement(bigQ);
			
			movieinsert.setLong(1,mvID);
			movieinsert.setString(2,mvTtle);
			movieinsert.setLong(3,yr);
			movieinsert.setString(4,mvDir);
			movieinsert.setLong(5,mvID);
			retId = movieinsert.executeUpdate();
			*/
		
			PreparedStatement movieinsert = DB.dbcon.prepareStatement("INSERT IGNORE INTO `movies` (`id`,`title`,`year`,`director`) VALUES (?,?,?,?)");	
					movieinsert.setLong(1,mvID);
					movieinsert.setString(2,mvTtle);
					movieinsert.setLong(3,yr);
					movieinsert.setString(4,mvDir);
					retId = movieinsert.executeUpdate();
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 
		  //System.exit(0);
		  System.out.println(Tools.dbs);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retId +" row(s) affected ");
		
	}
	
	public static void InsertStar(String f, String l, String date) throws ParseException{
		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(date);
		java.sql.Date dob = new java.sql.Date(myDate.getTime());
		int retId =0;
		String FullName = f + " " + l;
		
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}	
				//dbconn = DatabaseConnection.getConnection();
			PreparedStatement starinsert = DB.dbcon.prepareStatement( "INSERT INTO `stars` (`first_name`,`last_name`,`dob`) VALUES (?,?,?)");	
			
			//Integer ii = null;
			if (!STAR.contains(FullName)||STAR.contains(Tools.ukw)){
				//(!gx.contains(FullName)||gx.contains(Tools.ukw))
				//starinsert.setInt(1, ii);
				starinsert.setString(1,f);
				starinsert.setString(2,l);
				starinsert.setDate(3, dob);
				retId = starinsert.executeUpdate();
			}
			
			
			//for (String FullName:SAXParserStars.starFullNameSet){	}

		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  //System.exit(0);
		  System.out.println(Tools.dbs);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retId +" row(s) affected ");	
		
	}
	
	
	
	public static void InsertMovie(int i,String title, int year, String director){
		int retId =0;
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}
				
			//dbconn = DatabaseConnection.getConnection();
	
			
			/*
			String bigQ = "INSERT INTO movies(`id`,`title`,`year`,`director`) SELECT * FROM (SELECT ?,?,?,?) AS tmp WHERE NOT EXISTS (SELECT title FROM movies WHERE title = ?) LIMIT 1;";
			PreparedStatement movieinsert = DB.dbcon.prepareStatement(bigQ);
			
			movieinsert.setLong(1,i);
			movieinsert.setString(2,title);
			movieinsert.setLong(3,year);
			movieinsert.setString(4,director);
			movieinsert.setString(5,title);
			retId = movieinsert.executeUpdate();
			*/
			
			PreparedStatement movieinsert = DB.dbcon.prepareStatement( "INSERT IGNORE INTO `movies` (`id`,`title`,`year`,`director`) VALUES (?,?,?,?)");
				if (!lMv.contains(title)||lMv.contains(Tools.ukw)){
					movieinsert.setLong(1,i);
					movieinsert.setString(2,title);
					movieinsert.setInt(3, year);
					movieinsert.setString(4,director);	
					retId = movieinsert.executeUpdate();
			}
	
			
		
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  //System.exit(0);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retId +" row(s) affected ");	
		
	}

	

	
	public static void InsertStarsInMovies() {
		int retId = 0;
	
		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}
				
			//dbconn = DatabaseConnection.getConnection();
			PreparedStatement stareInMovieinsert = DB.dbcon.prepareStatement( "INSERT INTO `stars_in_movies` (`star_id`,`movie_id`) VALUES (?,?)");
			PreparedStatement starinsert = DB.dbcon.prepareStatement( "INSERT INTO `stars` (`first_name`,`last_name`) VALUES (?,?)");
			PreparedStatement getStarId = DB.dbcon.prepareStatement("SELECT * FROM stars WHERE first_name = ? AND last_name =?" );

	
			for(Map.Entry<String,Integer> pair: SAXParserSGInMovies.MovieIdStageMap.entrySet()) {	// For each genre in our map
				String w = pair.getKey();	// Grab the Star stage name
				int f = pair.getValue();	// Grab the movie Id in this content
				if (!STAR.contains(w)){
					String sf = Tools.SetF(w);
					String sl = Tools.SetL(w);
					starinsert.setString(1, sf); // need to split the stage name here
					starinsert.setString(2, sl); //need to split stagename here
					retId = starinsert.executeUpdate();
					
					getStarId.setString(1, sf); //use the splited word
					getStarId.setString(2, sl);
					
					ResultSet res_starId = getStarId.executeQuery();
					
					if (res_starId.next()) {
					    do {
					      // Logic to retrieve the data from the resultset.
					      // eg: rs.getString("abc");
					    	long starId  = res_starId.getLong(1);
					    	stareInMovieinsert.setLong(1, starId);
							stareInMovieinsert.setLong(2, f);
							stareInMovieinsert.executeUpdate();
							
					    } while(res_starId.next());
					}
					
					
					
				/*	
					// Find the genre Id in our database
					if( res_starId.next() ) {									// If the term is in our databse,
							Long starId = res_starId.getLong(1);

					stareInMovieinsert.setLong(1, starId);
					stareInMovieinsert.setLong(2, f);
					stareInMovieinsert.executeUpdate();
				}
					
				*/	
					
					res_starId.close();
					
				}
			}

			System.out.println("stars and stars_in_movies Tables updated successfuly... ");
			
			
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  //System.exit(0);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retId +" row(s) affected ");	
		
	}

	

	public static void InsertGenre() {
		int retId = 0;

		try {
			if( DB.dbcon == null ) {
				DB.dbcon = DB.getConnection();
			}
				
			//dbconn = DatabaseConnection.getConnection();
			PreparedStatement genreInMovieinsert = DB.dbcon.prepareStatement( "INSERT INTO `genres_in_movies` (`genre_id`,`movie_id`) VALUES (?,?)");
			PreparedStatement genreinsert = DB.dbcon.prepareStatement( "INSERT INTO `genres` (`name`) VALUES (?)");
			
			
			PreparedStatement getGenreId = DB.dbcon.prepareStatement("SELECT * FROM genres WHERE name = ?" );
			
			
			//getGenre.setString(1, genre);
			PreparedStatement getGenre = DB.dbcon.prepareStatement("SELECT name FROM genres " );
			List<String> go = new ArrayList<String>();
			String fr;
			ResultSet cc = getGenre.executeQuery();	
			while(cc.next()){
				fr=cc.getString("name");
				go.add(fr);
			}
	
			for(Map.Entry<String,Integer> pair: SAXParserMovies.catMovieMap.entrySet()) {	// For each genre in our map
				String w = pair.getKey();	// Grab the genre
				int f = pair.getValue();	// Grab the movie Id in this content
				if (!go.contains(w)){
					genreinsert.setString(1, w);
					retId = genreinsert.executeUpdate();
					
					
					getGenreId.setString(1, w);
					ResultSet res_genreId = getGenreId.executeQuery();
					

					if (res_genreId.next()) {
					    do {
					      // Logic to retrieve the data from the resultset.
					      // eg: rs.getString("abc");
					    	Long genreId = res_genreId.getLong(1);
					    	genreInMovieinsert.setLong(1, genreId);
							genreInMovieinsert.setLong(2, f);
							genreInMovieinsert.executeUpdate();
							
					    } while(res_genreId.next());
					}
					
					res_genreId.close();
					
					/*
					// Find the genre Id in our database
					if( res_genreId.next() ) {									// If the term is in our databse,
						genreId = res_genreId.getLong(1);
					
					}
					res_genreId.close();	
					genreInMovieinsert.setLong(1, genreId);
					genreInMovieinsert.setLong(2, f);
					genreInMovieinsert.executeUpdate();
		*/
				}
			
			}
			cc.close();
			System.out.println("genres and genres_in_movies Tables updated successfuly... ");
			
			
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  //System.exit(0);
		  return;
		}

		try {
			//Main.MainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retId +" row(s) affected ");	
		
	}

	
	
	
	
}
