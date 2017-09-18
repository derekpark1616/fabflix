package fabflix;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;

public class Movie {
	public int id = 999999;
	public String title = Tools.ukw;;
	public Integer year = 1900;
	public String director = Tools.ukw;
	public String bannerUrl;
	public String genre =Tools.ukw;
	public Star star;
	
	//public List <Map<String, String>> star = new ArrayList<Map<String, String>>();
	//public List<String> stars = new ArrayList<String>();

	/*
	public Movie (int id_, String title_, int year_, String director_, String bannerUrl_, String genre_, List <Map<String, String>> star_){
	id = id_;
	title = title_;
	year = year_;
	director = director_;
	bannerUrl = bannerUrl_;
	genre = genre_;
	star = star_;
	}
	*/
	
	public Movie(){
		
	}
	
	 	public int getMovieId() {
	        return id;
	    }
	    public void setMovieId(Integer id_) {
	        this.id = id_;
	    }
	    public String getMovieTitle() {
	        return title;
	    }
	    public void setMovieTitle(String title_) {
	        this.title = title_;
	    }
	    public double getMovieYear() {
	        return year;
	    }
	    public void setMovieYear(Integer year_) {
	        this.year = year_;
	    }
	    public String getMovieDirector() {
	        return director;
	    }
	    public void setMovieDirector(String director_) {
	        this.director = director_;
	    }
	    
	    public String getMovieBanner() {
	        return bannerUrl;
	    }
	    public void setMovieBanner(String bannerUrl_) {
	        this.bannerUrl = bannerUrl_;
	    }
	    public String getMovieGenre() {
	        return director;
	    }
	    public void setMovieGenre(String genre_) {
	        this.genre = genre_;
	    }

	public Movie (Integer id_, String title_, Integer year_, String director_, String bannerUrl_, String genre_){
		id = id_;
		title = title_;
		year = year_;
		director = director_;
		bannerUrl = bannerUrl_;
		genre = genre_;
		}
	
	public Movie (String title_, Integer year_, String director_, String bannerUrl_){
		
		title = title_;
		year = year_;
		director = director_;
		bannerUrl = bannerUrl_;

		}
	
	
}