package fabflix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SrchMovieServlet
 */
@WebServlet(description = "Movie Search with wildcards", urlPatterns = { "/SrchMovieServlet" })
public class SrchMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrchMovieServlet() {
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
		long startTime = System.nanoTime();
		String rows = request.getParameter("nRows");
		int nRowsToDisplay = Integer.parseInt(rows);
		
		String Y = "year", T = "title", sort =null, sp=" ";
		String Up = "ASC", Dw = "DESC", m= "m."; 
		 
		
		 if(request.getParameter("s") != null){
			 sort = request.getParameter("s");
			 
			 if (sort.equals("m.title ASC")){
				 sort = m + T + sp + Up;
			 } else if (sort.equals("m.title DESC")){
				 sort = m + T + sp + Dw;
			 } else if (sort.equals("m.year ASC")){
				 sort = m + Y + sp + Up;
			 } else  sort = m + Y + sp + Dw;
			
			 
		 }else sort = m + T + sp + Up;
			 		
		
		
		
		//Changes to accommodate more options for search by Drc
		String mT, yr, dr, sr;
		mT = request.getParameter("Title");
		yr = request.getParameter("Year");
		
		dr = request.getParameter("Director");
		sr = request.getParameter("stare");
		String mTitle = null, director = null, stare = null;
		String year =null;
		/*System.out.println(yr);
		System.out.println(director);
		System.out.println(star);
		*/
		
		if(!mT.equals("")){
			mTitle = mT;
		}
		if(!yr.equals("")){
			year = yr;
		}
		if(!dr.equals("")){
			director = dr;
		}
		if(!sr.equals("")){
			stare = sr;
		}
		
		
		/*
		if(!mT.equals("")){
			mTitle = "%" + mT + "%";
		}
		if(!yr.equals("")){
			year = "%" + yr + "%";
		}
		if(!dr.equals("")){
			director = "%" + dr + "%";
		}
		if(!sr.equals("")){
			stare = sr;
		}
		*/

		System.out.println("Now serving This title: " +mTitle);
		int pg = 1;
		
		
		 if(request.getParameter("pg") != null)
	        	pg = Integer.parseInt(request.getParameter("pg"));	

        
        MovieProcess g = new MovieProcess();  
        List<Movie> movieList = g.searchMovies(mTitle, year, director, stare,(pg - 1) * nRowsToDisplay, nRowsToDisplay);
        
        //List<Movie> movieList = g.searchMovies(mTitle,(pg-1)*nRowsToDisplay,nRowsToDisplay);
        System.out.println("Here the value of pge:" );
        for (Movie mv:movieList){
        	System.out.println("Here the value of pge:" +pg);
        	//System.out.println("Here the value of sort:" +sort);
        	System.out.println(mv.id +" " +mv.title);
        }
        
        
        int noOfRecords = g.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / nRowsToDisplay);
    
        //String mTitle = null, year = null, director = null, star = null;
        
        request.getSession().setAttribute("search-results",movieList);
        request.getSession().setAttribute("noOfPages", noOfPages);
        request.getSession().setAttribute("currentPage", pg);
        request.getSession().setAttribute("nRows", nRowsToDisplay);
        request.getSession().setAttribute("Year", year);
        request.getSession().setAttribute("Director", director);
        request.getSession().setAttribute("stare", stare);
        request.getSession().setAttribute("ttle", mTitle);
        //request.getSession().setAttribute("s", sort);
        
        //RequestDispatcher show = request.getRequestDispatcher("/displayMovieList.jsp");
        RequestDispatcher show = request.getRequestDispatcher("/movieListView.jsp");
        //RequestDispatcher show = request.getRequestDispatcher("movie.go");
        
        long stopTime = System.nanoTime();
        long total = stopTime-startTime; 
        System.out.println("Search Servlet time run (TS): " +total +" " +"nano seconds");
        
        //Performance calculation...
        //String TS = "C:\\TestJava\\TestResults\\DBCPts.txt"; //This is on a window system
        String TS = "/home/shares/uci/cs122b/prj5/rs/DBCPts.txt";

         try{ 

         	File file = new File(TS);
             FileWriter output = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(output);	
             writer.println(total);
             writer.close();
         	
         	}
         	catch(Exception exc){
         	exc.printStackTrace(); // If there was an error, print the info.
         	}
        
        
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
