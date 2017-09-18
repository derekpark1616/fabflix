package fabflix;

import java.sql.SQLException;
import java.util.Date;
//import java.util.Scanner;


public class Main {

	public static void main(String[] args){
		final long startTime = new Date().getTime();
		/*
		Scanner input = new Scanner( System.in );		
		System.out.print( "\nEnter the location where XML Files are located: \nOn Windows you should enter for example:" +"C://TestJava//xmFiles//" +"\nOn Linux it should be //home//xml// or enter x to exit\n>>>\n");
		String xmlFilesFolder = input.nextLine();
		
		if (xmlFilesFolder.equalsIgnoreCase("x")){
			System.exit(0);
		}else Tools.loc = xmlFilesFolder;

		*/
		System.out.println("\nAttempting to connect to the Database... relax...");
		if( DB.dbcon == null ){ 
			try {
				DB.dbcon = DB.getConnection();

				DBInteraction.loadMovies();	
				DBInteraction.loadStars2();
				
				SAXParserMovies spe = new SAXParserMovies();
				SAXParserStars strs = new SAXParserStars();
				SAXParserSGInMovies sg = new SAXParserSGInMovies();
				spe.runMovies();
				strs.runStars();
				sg.runSG();
				
				System.out.println("\nNow Updating all records... please wait...");//Now Updating all records...

				DBInteraction.loadMovies();	
				DBInteraction.loadStars2();
				spe.runMovies();
				strs.runStars();
				sg.runSG();	


				
				} catch (SQLException e) {
					System.out.println("\nMySQL Driver problem detected. Check it... for now I'm giving up...");
					e.printStackTrace();
					System.exit(0);
				} catch (ClassNotFoundException e) {
					System.out.println("\nDatabse is not up, check it... for now I'm giving up ...");
					e.printStackTrace();
					System.exit(0);
				} finally {
					try {
						
						if(DB.dbcon != null)
						DB.dbcon.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
		//input.close();
		
		
		
		final long duration = new Date().getTime() - startTime;
		System.out.println("\n================================");
		System.out.println("XML Parsing Performance Summary: ");
		System.out.println("================================");
		
		System.out.println();
		System.out.println("It took " +duration +" MilliSeconds or " +duration/1000 +" Seconds to complete ");
		
		
	}

}
