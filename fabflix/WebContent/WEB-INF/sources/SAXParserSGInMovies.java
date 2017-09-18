package fabflix;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SAXParserSGInMovies extends DefaultHandler{
	public static HashMap<String,Integer> MovieIdStageMap =null;
	List<SGInMovies> sgMovies =null;
	private String tempVal;
	//to maintain context
	private SGInMovies tempSG;
	
	
	public SAXParserSGInMovies(){
		sgMovies = new ArrayList<>();
	}
	
	public void runSG() {
		parseSGXML();
		printSG();
	}

	
//Compare Movied Id to the one from main124.xml before adding to the database	
private void parseSGXML() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			
			String xmlsCats = Tools.loc+"casts124.xml";
			//get a new instance of parser
			spf.setValidating(true);
			SAXParser spCats = spf.newSAXParser();
			//parse the file and also register this class for call backs
			
			spCats.parse(xmlsCats, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents
	 */
//To populate stars table
	private void printSG(){
		
		//DBInteraction.loadMovies();
		//DBInteraction.loadStars2();
		MovieIdStageMap = new HashMap<String,Integer>();
		
		int sId = 999999;
		String ttle = Tools.ukw, stgName = Tools.ukw, dir = Tools.ukw, fn, ln;

		System.out.println("No of Movies '" + sgMovies.size() + "'.");
		
		for (SGInMovies sg:sgMovies){
			
			sId = sg.id;
			ttle = sg.title;
			stgName = sg.StageName;
	
			fn = Tools.SetF(stgName);
			ln =Tools.SetL(stgName);
			dir = sg.director;
			ttle = Tools.cleanString(ttle);
			
			System.out.println("\nMovie Id: " +sId +"\n" +"Title: " +ttle +"\n" 
			+"First Name: " +fn +"\n" +"Last Name: " +ln +"\n"  +"Director: " +dir +"\n" +"Stage: " +stgName +"\n"  );	
			MovieIdStageMap.put(stgName, sId);
			DBInteraction.InsertMoreMv(sId,ttle,dir);
		}
		
		System.out.println("Now updating stars and stars_in_movies tables...Please wait");	
		DBInteraction.InsertStarsInMovies();
		
		//System.out.println("Now updating genres and genres_in_movies tables...Please wait");
		//DBInteraction.InsertGenre();
		
	}

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attb) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("is")) {
			//create a new instance of employee
			tempSG = new SGInMovies();
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("m")) {
			//add it to the list
			sgMovies.add(tempSG);
			
		}else if (qName.equalsIgnoreCase("is")) {//Stage Name, same as on actors63.xml
			tempSG.setMovieDir(tempVal);
			
		}else if (qName.equalsIgnoreCase("f")) {
			//tempVal = null;
			int p = Tools.StrToIntConversion(tempVal);
			tempSG.setMovieId(p);
			
		}else if (qName.equalsIgnoreCase("t")) {
			tempSG.setMovieTitle(tempVal);
			
		}else if (qName.equalsIgnoreCase("a")) {//Stage Name, same as on actors63.xml
			tempSG.setStarStageName(tempVal);
			
		}
		
	}
	
	
	
}
