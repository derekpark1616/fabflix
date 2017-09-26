package fabflix;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class SAXParserMovies extends DefaultHandler {
	List<Movie> myMovies = null;
	private String tempVal;
	// to maintain context
	private Movie tempMov;
	public static HashMap<String, Integer> catMovieMap = null;
	public static Set<String> movieTitleSet = null;

	public SAXParserMovies() {
		myMovies = new ArrayList<>();
	}

	public void runMovies() {
		parseMovieXML();
		printMovie();
	}

	private void parseMovieXML() {

		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			String xmlsMovies = Tools.loc + "mains243.xml";
			spf.setValidating(true);
			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			// parse the file and also register this class for call backs
			sp.parse(xmlsMovies, this);
			// spStars.parse(xmlsStars, this);
			// spCats.parse(xmlsCats, this);

		} catch (SAXException se) {
			se.printStackTrace();
			// return;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			// return;
		} catch (IOException ie) {
			ie.printStackTrace();
			// return;
		}
	}

	/**
	 * Iterate through the list and print the contents
	 */
	private void printMovie() {

		// DBInteraction.loadMovies();

		int mId = 999999, yr = 1900;
		String ttle = "Unknown", dctor = "Bad Boy", gr = "Unknown";

		catMovieMap = new HashMap<String, Integer>();
		movieTitleSet = new HashSet<String>();

		System.out.println("No of Movies '" + myMovies.size() + "'.");

		for (Movie m : myMovies) {
			mId = m.id;
			ttle = m.title;
			ttle = Tools.cleanString(ttle);
			yr = m.year;
			dctor = m.director;

			if (m.g.gname.equals(null) || m.g.gname.equals("") || m.g.gname.equals(" ")) {
				gr = Tools.ukw;
			} else
				gr = m.g.gname;

			gr = Tools.cleanString(gr);
			catMovieMap.put(gr, mId);
			movieTitleSet.add(ttle);
			System.out.println("Movie Id: " + mId + "\n" + "Title: " + ttle + "\n" + "Director: " + dctor + "\n"
					+ "Year " + yr + "\n" + "Genre " + gr + "\n");
			DBInteraction.InsertMovie(mId, ttle, yr, dctor);
		}
		System.out.println("Now updating genres and genres_in_movies tables...Please wait");
		DBInteraction.InsertGenre();

	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attb) throws SAXException {
		// reset
		tempVal = "";
		if (qName.equalsIgnoreCase("film")) {
			// create a new instance of employee
			tempMov = new Movie();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("film")) {
			// add it to the list
			myMovies.add(tempMov);

		} else if (qName.equalsIgnoreCase("dirn")) {
			tempMov.setMovieDirector(tempVal);
		} else if (qName.equalsIgnoreCase("fid")) {
			// tempVal = null;

			int p = 0;
			p = Tools.StrToIntConversion(tempVal);
			tempMov.setMovieId(p);

		} else if (qName.equalsIgnoreCase("year") || qName.equalsIgnoreCase("released")) {
			tempMov.setMovieYear(Tools.StrToYearConversion(tempVal));
		} else if (qName.equalsIgnoreCase("t")) {
			tempMov.setMovieTitle(tempVal);
		} else if (qName.equalsIgnoreCase("cat") || qName.equalsIgnoreCase("cats")) {
			tempMov.setMovieGenreName(tempVal);
		}
	}
}
