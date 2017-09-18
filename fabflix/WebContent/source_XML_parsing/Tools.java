package fabflix;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tools {	
	public static String ukw = "Unknown";
	public static String ukw2 = ukw+" "+ukw;
	public static String dbs ="Database Exception Handled...";
	//public static String loc = "C://TestJava//xmFiles//";
	//public static String loc = "/home/shares/uci/cs122b/prj2/xmls/";
	public static String loc = "../xmls/";
	
	
	public static Integer StrToIntConversion(String s)	{

		if (s.length() ==0){
			return null;
		}

		if (s.equalsIgnoreCase("19yy")){
			s="1925";
		}
		
		if (s.equals("")){
			s = "0";
		}
	
		
	   StringBuilder result = new StringBuilder(s.length());
	   s= s.toUpperCase();
	   String r;
	   for(int i =0; i < s.length(); ++i) {
	     char c = s.charAt(i);

	     if (Character.isLetter(c)) {
	         result.append(c-0x0041+1);
	         
	         
	     } else if (Character.isDigit(c)) {
	         result.append(c);
	     } 
	 }
	   r = result.toString();
	   
	   
	   
	   if (r.equals("")){
		   return  Integer.parseInt("0");
	   }else {
		   
		   int len = r.length();
		   if (len>8)
			   r = r.substring(2,len -1);
		   return  Integer.parseInt(r);
	   }
	}

	
	
	public static Integer StrToYearConversion(String s)	{

		if (s.length() ==0){
			return null;
		}
		
		if (s.equals("")){
			s = "0";
		}
	
		
	   StringBuilder result = new StringBuilder(s.length());
	   s= s.toUpperCase();
	   String r;
	   for(int i =0; i < s.length(); ++i) {
	     char c = s.charAt(i);

	     if (Character.isLetter(c)) {
	         result.append(c-0x0041+1);
	         
	         
	     } else if (Character.isDigit(c)) {
	         result.append(c);
	     } 
	 }
	   r = result.toString();
	   
	   if (r.equals("")){
		   return  Integer.parseInt("1900");
	   }else {
		   
		   int len = r.length();
		   if (len>=4){
			   r = r.substring(0, 4);
			   return  Integer.parseInt(r);
		   }
			   return  Integer.parseInt(r);
		   }
	}
	
	public static String cleanString(String s){
		
		s =s.replaceAll("^\\s+|\\s+$", "");
		s= s.replaceAll("[^a-zA-Z0-9\\s.,/]", "");
		
		return s;
	}
	
	
	
	public static int returnNumbers(String fid)
	  {
	    char charArray[] = new char[0];
	    String strReturn="";
	    if (fid.length() > 0) // must have 1 digit at least
	    {
	      charArray = new char[fid.length()]; //instatiate char array.
	      fid.getChars(0, (fid.length()), charArray, 0); //put string into char array
	      for (int intIndex = 0; intIndex < fid.length(); intIndex++)
	      {
	        if (Character.isDigit(charArray[intIndex]) == true)
	        {
	          //if it's a number add it to the string.
	          strReturn = strReturn + charArray[intIndex];
	        }
	      }
	      return Integer.parseInt(strReturn); //return the integer value of the string.
	    }
	    return 0; //return 0 if there was a 0 length argument.
	  }
	
	
	 
    public static String SetF(String FLname){

    	if (FLname.contains(" ")){	
    		int firstSpace = FLname.indexOf(" ");
    		return  FLname.substring(0,firstSpace);
    	} else{
    		 return  FLname.substring(0,FLname.length());
    	 }
    	
    }     
    
    public static String SetL(String FLname){

    	 if (FLname.contains(" ")){	
	    		int firstSpace = FLname.indexOf(" ");
	    		return  FLname.substring(firstSpace+1,FLname.length());
	    	} else{
	    		 return FLname.substring(0,FLname.length());
	    	 }

    	}     
	
	
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	public static int randBe(int start, int end) {
		
		Star s = new Star();
        return s.mid = start + (int)Math.round(Math.random() * (end - start));
    }
	
	// date validation using SimpleDateFormat
		// it will take a string and make sure it's in the proper
		// format as defined by you, and it will also make sure that
		// it's a legal date
		public static boolean isValidDate(String date) {

			// set date format, this can be changed to whatever format
			// you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
			// you can read more about it here:
			// http://java.sun.com/j2se/1.4.2/docs/api/index.html
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// declare and initialize testDate variable, this is what will hold
			// our converted string
			Date testDate = null;
			String errorMessage;
			String correct;
			// we will now try to parse the string into date form
			try {

				testDate = sdf.parse(date);
			}
			// if the format of the string provided doesn't match the format we
			// declared in SimpleDateFormat() we will get an exception
			catch (ParseException e) {
				errorMessage = "the date you provided is in an invalid date" + " format.";
				System.out.print(errorMessage);
				return false;
			}
			// dateformat.parse will accept any date as long as it's in the format
			// you defined, it simply rolls dates over, for example, december 32
			// becomes jan 1 and december 0 becomes november 30
			// This statement will make sure that once the string
			// has been checked for proper formatting that the date is still the
			// date that was entered, if it's not, we assume that the date is
			// invalid
			if (!sdf.format(testDate).equals(date)) {
				errorMessage = "The date that you provided is invalid.";
				System.out.println(errorMessage);
				return false;
			}
			// if we make it here without getting an error it is assumed that
			// the date was a valid one and that it's in the proper format
			correct = "Good date entered... proceeding...";
			System.out.println(correct);
			return true;
		} // end isValidDate

		
		public static boolean isName(CharSequence name){
			return true;
		}
		
		public static String flattenToAscii(String string) {
			//if (string.isEmpty() ){
				//string = "Bad Boy";
			//}
				
	        StringBuilder sb = new StringBuilder(string.length());
	        string = Normalizer.normalize(string, Normalizer.Form.NFD);
	        for (char c : string.toCharArray()) {
	            if (c <= '\u007F') sb.append(c);
	        }
	        return sb.toString();
	    }
		
		
		public static void printColTypes(ResultSetMetaData rsmd) throws SQLException {
			int columns = rsmd.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				int jdbcType = rsmd.getColumnType(i);
				String name = rsmd.getColumnTypeName(i);
				System.out.print("Column " + i + " is JDBC type " + jdbcType);
				System.out.println(", which the DBMS calls " + name);
			}
		}

}
