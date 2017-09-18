package fabflix;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class AvgCalc {

	public static void main(String[] args) throws IOException {
		
		PrintWriter out = null;
		double tsTotal = 0;
		double tjTotal = 0;
		double tsAvg = 0;
		double tjAvg = 0;
		int denom = 0;
		
		 //String TS = "C:\\TestJava\\TestResults\\DBCPts.txt"; //This is on a window system
	     String TS = "/home/shares/uci/cs122b/prj5/rs/DBCPts.txt";
		 
	     //String TJ = "C:\\TestJava\\TestResults\\DBCPDBtj.txt"; //This is on a window system
	     String TJ = "/home/shares/uci/cs122b/prj5/rs/DBCPDBtj.txt";	
		 
	     //String average = "C:\\TestJava\\TestResults\\averages.txt"; //This is on a window system
	     String average = "/home/shares/uci/cs122b/prj5/rs/averages.txt";
		 
		try {
			
			File ts1 = new File(TS);
			File tj1 = new File(TJ);
			
			Scanner scanner = new Scanner(ts1);
			Scanner scanner2 = new Scanner(tj1);
			
			out = new PrintWriter(average);
			
			while(scanner.hasNextDouble()){
				tsTotal += scanner.nextDouble();
				denom++;
			}
			scanner.close();
			tsAvg = (tsTotal/denom)/1000000; //in milliseconds
			
			denom = 0;
			while(scanner2.hasNextInt()){
				tjTotal += scanner2.nextInt();
				denom++;
			}
			scanner2.close();
			tjAvg = (tjTotal/denom)/1000000;//in milliseconds 
			
			BigDecimal bs = new BigDecimal(tsAvg).setScale(2, RoundingMode.CEILING);
			tsAvg = bs.doubleValue();
			
			BigDecimal bj = new BigDecimal(tjAvg).setScale(2, RoundingMode.CEILING);
			tjAvg = bj.doubleValue();

			System.out.println("The TS DBCP is " + tsAvg);
			System.out.println("The TJ DBCP is " + tjAvg);
			
			out.println("The TS DBCP is " + tsAvg);
			out.println("The TJ DBCP is " + tjAvg);
			
			if (ts1.exists()) {
			    @SuppressWarnings("resource")
				RandomAccessFile raf = new RandomAccessFile(ts1,"rw");
			    raf.setLength(0);
			}

			if (tj1.exists()) {
			    @SuppressWarnings("resource")
				RandomAccessFile raf = new RandomAccessFile(tj1,"rw");
			    raf.setLength(0);
			}
			
		}finally {
			if(out != null){
				out.close();
			}
		}

	}

}
