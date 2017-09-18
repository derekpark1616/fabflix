package fabflix;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletResponse;


public class MovieProcess {
	
	private int noOfRecords;	
	static PreparedStatement getMovies;
	static PreparedStatement getStars;
	static PreparedStatement getGenres;
	
	PrintWriter out = null;
	
	
	
	public MovieProcess() {	}
		
	
	public  void insertQuantity(int movieId, int qt) {
		
		try {
			if( DB.dbcon == null ) {
			
			}
	
			
			PreparedStatement quantiyInsert = DB.dbcon.prepareStatement( "REPLACE INTO `shopping_cart` (`customerId`,`movieId`,`quantity`) VALUES (?,?,?)");
			
			quantiyInsert.setInt(1,DB.customerID);
			quantiyInsert.setInt(2,movieId);
			quantiyInsert.setInt(3,qt);
			
			quantiyInsert.executeUpdate();		// Store into our Database the Credit Card info

		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  System.exit(0);
		}
		
		System.out.print( "\nOperation complete... " );
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public List<Movie> getMovieById(int movieId) {
		   Movie movie =null;
		   List<Movie> searchResults = new ArrayList<Movie>();
		   PreparedStatement getMoviesByID;
		   
		   try
		   {
			   
			   if( DB.dbcon == null ){ 
					out.println("<font color=red>No Product in the cart so far ... keep shopping.</font>");
				}

			   getMoviesByID = DB.dbcon.prepareStatement(DB.qryByMovieId);
			   getMoviesByID.setLong(1, movieId);

			   ResultSet rs = getMoviesByID.executeQuery();
			
			
		while (rs.next()){
             
             movie = new Movie(); 
             movie.setMovieTitle(rs.getString(1));
             movie.setMovieYear(rs.getInt(2));
             movie.setMovieDirector(rs.getString(3));
             movie.setMovieBanner(rs.getString(4));
             movie.setMovieTrailer(rs.getString(5));
			 movie.setMovieGenreId(rs.getInt(6));
			 movie.setMovieGenreName(rs.getString(7));
			 movie.setMovieActorId(rs.getInt(8));
			 movie.setMovieActorLn(rs.getString(9));
			 movie.setMovieActorFn(rs.getString(10));
			 movie.setMovieActorPhoto(rs.getString(11));
			 movie.setStarYearBirth(rs.getInt(12)); 
			 movie.setMovieId(rs.getInt(13));
			 
			 searchResults.add(movie);
             //movie = new Movie(m_Title,m_Year,m_Director,m_Banner,m_Trailer);
     	}
     	
     	rs.close();
     	getMoviesByID.close();
     	//Movie.dbcon.close();
     	return searchResults;
     	
        }catch (SQLException ex) {
	              while (ex != null) {
	                    System.out.println ("SQL Exception:  " + ex.getMessage ());
	                    ex = ex.getNextException ();
	                }  // end while
	              return searchResults;
	            }  // end catch SQLException
		   catch (java.lang.Exception ex){
			   return null;
			   }
	}
	
	public ArrayList<Movie> getMovieByIdAjx(int movieId) {
		   Movie movie =null;
		   PreparedStatement getMoviesByID;
		   ArrayList<Movie> AjxMovies = new ArrayList<Movie>();
		   try
		   {
			   
			   if( DB.dbcon == null ){ 
					out.println("<font color=red>No Product in the cart so far ... keep shopping.</font>");
				}

			   getMoviesByID = DB.dbcon.prepareStatement(DB.AjxQryByMovieId);
			   getMoviesByID.setLong(1, movieId);
			   ResultSet rs = getMoviesByID.executeQuery();
	
		while (rs.next()){      
          movie = new Movie();
          movie.setMovieYear(rs.getInt(1));
          movie.setMovieBanner(rs.getString(2));
          movie.setMovieActorFn(rs.getString(3));
          movie.setMovieActorLn(rs.getString(4));
          AjxMovies.add(movie);
          //movie = new Movie(m_Title,m_Year,m_Director,m_Banner,m_Trailer);
  	}
  	
  	rs.close();
  	getMoviesByID.close();
  	//Movie.dbcon.close();
  	return AjxMovies;
  	
     }catch (SQLException ex) {
	              while (ex != null) {
	                    System.out.println ("SQL Exception:  " + ex.getMessage ());
	                    ex = ex.getNextException ();
	                }  // end while
	              return AjxMovies;
	            }  // end catch SQLException
		   catch (java.lang.Exception ex){
			   return null;
			   }
	}
	
	
	
	//
	public Star getStarInfo(int movieId) {
		   Star star = null;
		   try
		   {

			   if( DB.dbcon == null ){ 
					DB.dbcon = DB.getConnection();
				}
			   
  		getMovies = DB.dbcon.prepareStatement(DB.qryByMovieID);
  		getMovies.setLong(1, movieId);
			//getMovies.setLong(2, 1);
  	
		ResultSet rs = getMovies.executeQuery();
			
			
		while (rs.next()){
  		Map<String,String> starAndPhoto = new HashMap<String,String>();
  		List<Map<String, String>> listStar = new ArrayList<Map<String, String>> ();
  		/*
  		  Integer id = rs.getInt(1);
          String m_Title = rs.getString(2);
          Integer m_Year = rs.getInt(3);
          String m_Director = rs.getString(4);
          String m_Banner = rs.getString(5);
          //String m_Trailer = rs.getString(6);
          String m_Genre = rs.getString(7);
          */
          String m_StarLName = rs.getString(8);
          String m_StarPhoto = rs.getString(9);
          Integer starId = rs.getInt(10);
          String m_StarFName = rs.getString(11);
          java.sql.Date dob = rs.getDate(12);
          
          starAndPhoto.put(m_StarLName,m_StarPhoto);
          listStar.add(starAndPhoto);
  		//movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre,listStar);
  		 
  		 star = new Star(starId,m_StarFName,m_StarLName,dob,m_StarPhoto);
  	}
  	
  	rs.close();
  	getMovies.close();

  	return star;
  	
     }catch (SQLException ex) {
	              while (ex != null) {
	                    System.out.println ("SQL Exception:  " + ex.getMessage ());
	                    ex = ex.getNextException ();
	                }  // end while
	              return star;
	            }  // end catch SQLException
		   catch (java.lang.Exception ex){
			   return null;
			   }
	}

	
	public List<Movie> searchMovies(String title, String yr, String director, String star, int offset, int numberRecords) {
		
		long startTime =0;
		PreparedStatement getMoviesList = null;
		ResultSet rs = null;
		
		String tquery = null, fquery = null, yquery = null
				,dquery = null, squery = null ;
		List<Movie> searchResults = new ArrayList<Movie>();
		Movie movie = null;
		int year;
		
		try {
			if (DB.dbcon == null) {
				DB.dbcon = DB.getConnection();
			}
			
			if (title != null) {
				tquery = DB.qrys + " AND m.title LIKE '%" + title + "%'";
			}else{
				tquery = DB.qrys;
			}
			
			if (yr != null) {
				year = Integer.parseInt(yr);
				yquery = tquery + " AND m.year LIKE '%" + year + "%'";
			}else{
				yquery = tquery;
			}
			
			if(director != null) {
				dquery = yquery + " AND m.director LIKE '%" + director + "%'";
			}else{
				dquery = yquery;
			}
			
			if(star != null) {
				String[] names = star.split(" ");
				if(names.length > 1){
					squery = dquery + " AND s.first_name LIKE '%" + names[0] + "%'" 
							+ " AND s.last_name LIKE '%" + names[1] + "%'"
							+ " OR s.first_name LIKE '%" + names[1] + "%'" 
							+ " AND s.last_name LIKE '%" + names[0] + "%'";
				}
				else {
					squery = dquery + " AND s.first_name LIKE '%" + star + "%'"
							+ " OR s.last_name LIKE '%" + star + "%'";
				}
			}else{
				squery = dquery;
			}
			
			fquery = squery +" GROUP BY m.id LIMIT ?,?;";
			
			//introduced to calculate jdbc time
			startTime = System.nanoTime();	
			getMoviesList = DB.dbcon.prepareStatement(fquery);
		
			
			//getMovies.setString(1, s);
			getMoviesList.setLong(1,offset); 
			getMoviesList.setLong(2,numberRecords);
			
			
			rs = getMoviesList.executeQuery();
		
			
			while (rs.next()) {
				Map<String, String> starAndPhoto = new HashMap<String, String>();
				List<Map<String, String>> listStar = new ArrayList<Map<String, String>>();
				
				String m_StarLName = rs.getString(8);
				String m_StarPhoto = rs.getString(9);
				
		
				starAndPhoto.put(m_StarLName, m_StarPhoto);
				listStar.add(starAndPhoto);

				movie = new Movie();
				movie.setMovieId(rs.getInt(1));
				movie.setMovieTitle(rs.getString(2));
				movie.setMovieYear(rs.getInt(3));
				movie.setMovieDirector(rs.getString(4));
				movie.setMovieBanner(rs.getString(5));
				movie.setMovieTrailer(rs.getString(6));
				movie.setMovieGenreName(rs.getString(7));
				movie.setMovieActorFn(rs.getString(10));
				movie.setMovieActorLn(rs.getString(8));
				movie.setMovieActorFn(rs.getString(9));
				searchResults.add(movie);
			}

			rs.close();
			rs = getMoviesList.executeQuery("SELECT FOUND_ROWS()");

			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (getMoviesList != null)
					getMoviesList.close();
				// if(dbcon != null)
				// dbcon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		    long stopTime = System.nanoTime();
	        long total = stopTime-startTime; 
	        System.out.println("JDBC time Run: " +total +" " +"nano seconds");
	        
	        //String TJ = "C:\\TestJava\\TestResults\\DBCPDBtj.txt"; //This is on a window system
	        String TJ = "/home/shares/uci/cs122b/prj5/rs/DBCPDBtj.txt";
	         try{ 

	         	 File file = new File(TJ);
	             FileWriter output = new FileWriter(file, true);
	             PrintWriter writer = new PrintWriter(output);	
	             writer.println(total);
	             writer.close();
	         	
	         	}
	         	catch(Exception exc){
	         	exc.printStackTrace(); // If there was an error, print the info.
	         	}
	        
		return searchResults;
	}

	// sear
	public List<Movie> searchMvTitle(String keywords, int offset, int numberRecords) {
		   
		   List<Movie> searchResults = new ArrayList<Movie>();
		   Movie movie =null;
		   
		   try
		   {

			   if( DB.dbcon == null ){ 
					DB.dbcon = DB.getConnection();
				}

		String tquery = DB.qrygt + " LIKE " + keywords + "%' AND m.id = sm.movie_id AND s.id = sm.star_id order by m.title asc LIMIT ?,?";   
		getMovies = DB.dbcon.prepareStatement(tquery);
	
		getMovies.setLong(1, offset);
		getMovies.setLong(2, numberRecords);
		ResultSet rs = getMovies.executeQuery();
	
		while (rs.next()){
		  Map<String,String> starAndPhoto = new HashMap<String,String>();
		  List<Map<String, String>> listStar = new ArrayList<Map<String, String>> ();
		/*
		  Integer id = rs.getInt(1);
       String m_Title = rs.getString(2);
       Integer m_Year = rs.getInt(3);
       String m_Director = rs.getString(4);
       String m_Banner = rs.getString(5);
       //String m_Trailer = rs.getString(6);
       String m_Genre = rs.getString(7);
       */
       
       String m_StarLName = rs.getString(8);
       String m_StarPhoto = rs.getString(9);
       starAndPhoto.put(m_StarLName,m_StarPhoto);
       listStar.add(starAndPhoto);
       
       movie = new Movie();
       movie.setMovieId(rs.getInt(1));
       movie.setMovieTitle(rs.getString(2));
       movie.setMovieYear(rs.getInt(3));
       movie.setMovieDirector(rs.getString(4));
       movie.setMovieBanner(rs.getString(5));
       movie.setMovieGenreName(rs.getString(7));
       
		 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre,listStar);
		 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre);
		 searchResults.add(movie);
	}
	
	rs.close();
	rs = getMovies.executeQuery("SELECT FOUND_ROWS()");
	
	if(rs.next()){
     this.noOfRecords = rs.getInt(1);
	}
	
	//getMovies.close();
	//dbcon.close();
	//return searchResults;
	
		   } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }finally
	        {
	            try {
	                if(getMovies != null)
	                	getMovies.close();
	                //if(dbcon != null)
	                	//dbcon.close();
	                } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return searchResults;
	}

	//new function
	

	//sear
		public List<Star> searchStar(int starId) {
			   
			   List<Star> searchResults = new ArrayList<Star>();
			   Star star =null;
			   Movie movie = null;
			   try
			   {
				   if( DB.dbcon == null ){ 
						DB.dbcon = DB.getConnection();
					}
			   
			getStars = DB.dbcon.prepareStatement(DB.starQry);
			getStars.setLong(1, starId);
			ResultSet rs = getStars.executeQuery();
		
			while (rs.next()){
		       star = new Star();
		       movie = new Movie();
		       star.setStarId(rs.getInt(1));
		       star.setStarFName(rs.getString(2));
		       star.setStarLName(rs.getString(3));
		       star.setStarDob(rs.getDate(4));
		       star.setStarPhoto(rs.getString(5));
		       movie.setMovieId(rs.getInt(6));
		       movie.setMovieTitle(rs.getString(7));

			   searchResults.add(star);
		}
		
		rs.close();

			   } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally
		        {
		            try {
		                if(getStars != null)
		                	getStars.close();
		                //if(dbcon != null)
		                	//dbcon.close();
		                } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return searchResults;
		}
	
		
		
		public List<Star> searchStarbyMovieId(int mvId) {
			   
			   List<Star> searchResults = new ArrayList<Star>();
			   Star star =null;
			   try
			   {
				   if( DB.dbcon == null ){ 
						DB.dbcon = DB.getConnection();
					}
			   
			getStars = DB.dbcon.prepareStatement(DB.starQryByMovieId);
			getStars.setLong(1, mvId);
			ResultSet rs = getStars.executeQuery();
		
			while (rs.next()){
		       star = new Star();
		       
		       star.setStarId(rs.getInt(1));
		       star.setStarFName(rs.getString(2));
		       star.setStarLName(rs.getString(3));
		       
		       star.setStarDob(rs.getDate(4));
		       star.setStarPhoto(rs.getString(5));
		       
		       searchResults.add(star);
		}
		
		rs.close();

			   } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally
		        {
		            try {
		                if(getStars != null)
		                	getStars.close();
		                //if(dbcon != null)
		                	//dbcon.close();
		                } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return searchResults;
		}
	
		public List<Movie> searchMoviesbyStarId(int starId) {
			PreparedStatement getMoviesList; 
			   List<Movie> searchResults = new ArrayList<Movie>();
			  Movie movie =null;
			   try
			   {
				   if( DB.dbcon == null ){ 
						DB.dbcon = DB.getConnection();
					}
			   
				   getMoviesList = DB.dbcon.prepareStatement(DB.MovieQryByStarId);
				   getMoviesList.setLong(1, starId);
				   ResultSet rs = getMoviesList.executeQuery();
		
			while (rs.next()){
			   
			   movie = new Movie();
			   
		       movie.setMovieId(rs.getInt(1));
		       movie.setMovieTitle(rs.getString(2));
		       movie.setMovieActorId(rs.getInt(3));
		       
		       searchResults.add(movie);
		}
		
		rs.close();

			   } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally
		        {
		            try {
		                if(getStars != null)
		                	getStars.close();
		                //if(dbcon != null)
		                	//dbcon.close();
		                } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return searchResults;
		}
	
		
		
		
		
		public List<Genre> searchGenrebyMovieId(int mvId) {
			   
			   List<Genre> searchResults = new ArrayList<Genre>();
			   Genre genre =null;
			   try
			   {
				   if( DB.dbcon == null ){ 
						DB.dbcon = DB.getConnection();
					}
			   
			getGenres = DB.dbcon.prepareStatement(DB.genreQryByMovieId);
			getGenres.setLong(1, mvId);
			ResultSet rs = getGenres.executeQuery();
		
			while (rs.next()){
		       genre = new Genre();
		       
		       genre.setGenreId(rs.getInt(1));
		       genre.setGenreName(rs.getString(2));
		      
		       searchResults.add(genre);
		}
		
		rs.close();

			   } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally
		        {
		            try {
		                if(getStars != null)
		                	getStars.close();
		                //if(dbcon != null)
		                	//dbcon.close();
		                } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return searchResults;
		}
		
		
	//sear
		public List<Movie> searchMvGenre(String keywords, int offset, int numberRecords) {
			   
			   List<Movie> searchResults = new ArrayList<Movie>();
			   Movie movie =null;
			   String tquery = DB.qrygg + " LIKE '%" + keywords + "%' AND m.id = sm.movie_id AND s.id = sm.star_id order by m.title asc LIMIT ?,?";
			   
			   try
			   {

				   if( DB.dbcon == null ){ 
						DB.dbcon = DB.getConnection();
					}

			getMovies = DB.dbcon.prepareStatement(tquery);
	
			
			getMovies.setLong(1, offset);
			getMovies.setLong(2, numberRecords);
			ResultSet rs = getMovies.executeQuery();
		
			while (rs.next()){
			  Map<String,String> starAndPhoto = new HashMap<String,String>();
			  List<Map<String, String>> listStar = new ArrayList<Map<String, String>> ();
			/*
			  Integer id = rs.getInt(1);
	       String m_Title = rs.getString(2);
	       Integer m_Year = rs.getInt(3);
	       String m_Director = rs.getString(4);
	       String m_Banner = rs.getString(5);
	       //String m_Trailer = rs.getString(6);
	       String m_Genre = rs.getString(7);
	       */
	       String m_StarLName = rs.getString(8);
	       String m_StarPhoto = rs.getString(9);
	       starAndPhoto.put(m_StarLName,m_StarPhoto);
	       listStar.add(starAndPhoto);
	       
	       movie = new Movie();
	       movie.setMovieId(rs.getInt(1));
	       movie.setMovieTitle(rs.getString(2));
	       movie.setMovieYear(rs.getInt(3));
	       movie.setMovieDirector(rs.getString(4));
	       movie.setMovieBanner(rs.getString(5));
	       movie.setMovieGenreName(rs.getString(7));
	       
			 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre,listStar);
			 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre);
			 searchResults.add(movie);
		}
		
		rs.close();
		rs = getMovies.executeQuery("SELECT FOUND_ROWS()");
		
		if(rs.next()){
	     this.noOfRecords = rs.getInt(1);
		}
		
		//getMovies.close();
		//dbcon.close();
		//return searchResults;
		
			   } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally
		        {
		            try {
		                if(getMovies != null)
		                	getMovies.close();
		                //if(dbcon != null)
		                	//dbcon.close();
		                } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return searchResults;
		}

	
	
		
		
		
		public void emptyCart() throws Exception{
			//Scanner input = new Scanner( System.in );	
			if( DB.dbcon == null ){ 
				
			}
				
			//dbconn = DatabaseConnection.getConnection();
			PreparedStatement update = DB.dbcon.prepareStatement( "DELETE FROM shopping_cart WHERE customerId = ? ");
			PreparedStatement getCustId = DB.dbcon.prepareStatement("Select c.id,m.id FROM customers AS c, movies AS m WHERE c.id = ?");
			getCustId.setLong(1, DB.customerID);
			ResultSet custId = getCustId.executeQuery();
			
			while (custId.next()){
				update.setLong(1, DB.customerID);
				update.executeUpdate();

			}
		}
		

		public void updateQteCart(int movId, int qte) throws Exception{
			//Scanner input = new Scanner( System.in );	
			if( DB.dbcon == null ){ 
				
			}
	
			String qtUp = "UPDATE shopping_cart set quantity = ? where movieId = ? AND customerId =?";
			//dbconn = DatabaseConnection.getConnection();
			PreparedStatement update = DB.dbcon.prepareStatement( qtUp);
			update.setInt(1, qte);
			update.setLong(2, movId);
			update.setLong(3, DB.customerID);
			
			update.executeUpdate();
			
		}
			
	
	
	
	
	//to search basedon title only
	public List<Movie> searchByTtile(String keywords) {
		   
		   List<Movie> searchResults = new ArrayList<Movie>();
		   Movie movie =null;

		   try
		   {

			   if( DB.dbcon == null ){ 
					DB.dbcon = DB.getConnection();
				}

		   
		getMovies = DB.dbcon.prepareStatement(DB.qrygt);
		
		
		getMovies.setString(1, keywords);
		//getMovies.setLong(2, offset);
		//getMovies.setLong(3, numberRecords);
		ResultSet rs = getMovies.executeQuery();
	
		while (rs.next()){
		  Map<String,String> starAndPhoto = new HashMap<String,String>();
		  List<Map<String, String>> listStar = new ArrayList<Map<String, String>> ();
		/*
	   Integer id = rs.getInt(1);
       String m_Title = rs.getString(2);
       Integer m_Year = rs.getInt(3);
       String m_Director = rs.getString(4);
       String m_Banner = rs.getString(5);
       //String m_Trailer = rs.getString(6);
       String m_Genre = rs.getString(7);
       */
       
       String m_StarLName = rs.getString(8);
       String m_StarPhoto = rs.getString(9);
       starAndPhoto.put(m_StarLName,m_StarPhoto);
       listStar.add(starAndPhoto);
       
       movie = new Movie();
       movie.setMovieId(rs.getInt(1));
       movie.setMovieTitle(rs.getString(2));
       movie.setMovieYear(rs.getInt(3));
       movie.setMovieDirector(rs.getString(4));
       movie.setMovieBanner(rs.getString(5));
       movie.setMovieGenreName(rs.getString(7));
       
		 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre,listStar);
		 //movie = new Movie(id,m_Title,m_Year,m_Director,m_Banner,m_Genre);
		 searchResults.add(movie);
	}
	
	rs.close();
	
	//getMovies.close();
	//dbcon.close();
	//return searchResults;
	
		   } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }finally
	        {
	            try {
	                if(getMovies != null)
	                	getMovies.close();
	                //if(dbcon != null)
	                	//dbcon.close();
	                } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return searchResults;
	}
	
	
	public int getNoOfRecords() {
     return noOfRecords;
 }
	
}
