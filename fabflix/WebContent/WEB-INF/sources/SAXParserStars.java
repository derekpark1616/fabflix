package fabflix;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXParserStars  extends DefaultHandler{
	List<Star> mvStars =null;
	private String tpVal;
	//to maintain context
	private Star tpStars;
	public static Set<String> starFullNameSet =null;
	public static Set<String> starStageNameSet=null;
	
	public SAXParserStars(){
		mvStars = new ArrayList<>();
	}
	
	public void runStars() {
		parseMvStars();
		printMvStars();
	}

	private void parseMvStars() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			String xmlsStars = Tools.loc+"actors63.xml";
			spf.setValidating(true);
			//get a new instance of parser
			SAXParser spStars = spf.newSAXParser();
			//parse the file and also register this class for call backs

			spStars.parse(xmlsStars, this);

			
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
	private void printMvStars(){
		GregorianCalendar gc = new GregorianCalendar();
		
		DBInteraction.loadStars();
		int sId = 999999;
		String f = Tools.ukw, l = Tools.ukw, date = "1900-01-01";
		String FL_name= Tools.ukw, stgNme = Tools.ukw;
		
		int year = Tools.randBetween(1870, 1989);
		gc.set(gc.YEAR, year);
		
		int dayOfYear = Tools.randBetween(1, 28);
		gc.set(gc.DAY_OF_YEAR, dayOfYear);
		
		int monthOfYear = Tools.randBetween(1, 12);
		gc.set(gc.MONTH, monthOfYear);
		
		System.out.println("No of Stars '" + mvStars.size() + "'.");
		
		starFullNameSet = new HashSet<String>();
		starStageNameSet = new HashSet<String>();
		
		for (Star s:mvStars){
			
			if (s.yDob==null){
				s.yDob = gc.get(gc.YEAR);
			}
			sId = Tools.randBe(199999,999999);
		
			
			if (!Tools.isName(s.lname)){
				l = "Bad Boy";
			}else if(s.lname.equals("")){	
				l = Tools.ukw;
			}else l = s.lname;

			if (!Tools.isName(s.fname)){
				f = "Bad Boy";
			}else  if(s.fname.equals("")){
				f = Tools.ukw;
			}else f = s.fname;
			
			if (l.equals("")||l.isEmpty()||l.equals(" ")){
				if (f.equals(null)||f.equals("")||f.isEmpty()||f.equals(" ")){
					l = f= Tools.ukw;	
				}else {
					l = f;
				}
			}	
			
			l  = Tools.cleanString(l);
			f = Tools.cleanString(f);
			FL_name = f +" "+ l;

			stgNme = s.stageNm;
			stgNme = Tools.cleanString(stgNme);
			date = s.yDob +"-"+(gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
			starFullNameSet.add(FL_name);
			starStageNameSet.add(stgNme);
			
			System.out.println("\nStar Id: " +Tools.randBe(199999,999999) +"\n" +"First Name: " +f +"\n" 
			+"Last name: " +l +"\n" +"DOB " +date+"\n" +"Stage name: " +stgNme );

			try {
				DBInteraction.InsertStar(f,l,date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.print( "\nOperation complete...: \n" );
	}

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attb) throws SAXException {
		//reset
		tpVal = "";
		if(qName.equalsIgnoreCase("actor")) {
			//create a new instance of stars
			tpStars = new Star();
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tpVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("actor")) {
			//add it to the list
			mvStars.add(tpStars);
			
		}else if (qName.equalsIgnoreCase("familyname")) {
			tpStars.setStarLName(tpVal);
		}else if (qName.equalsIgnoreCase("firstname")) {
			tpStars.setStarFName(tpVal);
			
		}else if (qName.equalsIgnoreCase("dob")) {			
			tpStars.setStarYearBirth(Tools.StrToYearConversion(tpVal));
		}else if (qName.equalsIgnoreCase("stagename")) {
			tpStars.setStarStageName(tpVal);
		}
		
	}
	
}
