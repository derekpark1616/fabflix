package fabflix;

import java.sql.Date;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;

public class Movie {
	public int id = 999999;
	public String title = Tools.ukw;
	public Integer year = 1900;
	public String director = Tools.ukw;
	public String bannerUrl= Tools.ukw;
	public String trailer = Tools.ukw;
	//public String genre =Tools.ukw;
	public Genre genre;
	public Star star =null;
	
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
	    public Integer getMovieYear() {
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
	   
	    public String getMovieTrailer() {
	        return trailer;
	    }
	    public void setMovieTrailer(String trailerUrl_) {
	        this.trailer= trailerUrl_;
	    }
	    
	    
	    
	    Genre g = new Genre();
	    
	    public int getMovieGenreId() {
	        return g.gid;
	    }
	    public void setMovieGenreId(int gid_) {
	        this.g.gid = gid_;
	    }
	    
	    public String getMovieGenreName() {
	        return g.gname;
	    }
	    public void setMovieGenreName(String nm_) {
	        this.g.gname = nm_;
	    }
	    
	    

	    Star s = new Star();
	    
	    public int getMovieStarId() {
	        return s.mid;
	    }
	    public void setMovieActorId(int stid_) {
	        this.s.mid = stid_;
	    }
	    
	    public String getMovieStarLn() {
	        return s.lname;
	    }
	    public void setMovieActorLn(String ln_) {
	        this.s.lname = ln_;
	    }
	    
	    public String getMovieStarFn() {
	        return s.fname;
	    }
	    public void setMovieActorFn(String fn_) {
	        this.s.fname = fn_;
	    }
	    
	    
	    public int getStarYearBirth() {
	        return s.yDob;
	    }
	    public void setStarYearBirth(int db_) {
	        this.s.yDob = db_;
	    }
	    
	    public int getStarYearBirthl() {
	        return s.yDob;
	    }
	    public void setStarYearBirthl(Date db_) {
	        this.s.dob = db_;
	    }
	    
	    public String getMovieStarPhoto() {
	        return s.photo;
	    }
	    public void setMovieActorPhoto(String ph_) {
	        this.s.photo = ph_;
	    }
	    
	    public String getMovieStarStageName() {
	        return s.stageNm;
	    }
	    public void setMovieActorStageName(String stg_) {
	        this.s.stageNm = stg_;
	    }
	    
 
	public Movie (Integer id_, String title_, Integer year_, String director_, String bannerUrl_, Genre genre_,Star star_){
		id = id_;
		title = title_;
		year = year_;
		director = director_;
		bannerUrl = bannerUrl_;
		genre = genre_;
		star = star_;
		}
	
	 public Movie (Integer id_, String title_, Integer year_, String director_, String bannerUrl_, String genre_){
		 	id = id_;
			title = title_;
			year = year_;
			director = director_;
			bannerUrl = bannerUrl_;
			genre.gname = genre_;
			}
	 public Movie (Integer id_, String title_){
		 	id = id_;
			title = title_;
			}
	        
	    
	public Movie (String title_, Integer year_, String director_, String bannerUrl_,String trailerURL_){
		title = title_;
		year = year_;
		director = director_;
		bannerUrl = bannerUrl_;
		trailer = trailerURL_;
		}
	
	public Movie (Integer year_, String bannerUrl_,String stFn_,String stLn_){
		year = year_;
		bannerUrl = bannerUrl_;
		star.fname = stFn_;
		star.lname = stLn_;
		}
}