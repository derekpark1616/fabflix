package fabflix;



public class SGInMovies {

	public int id = 999999;
	public String title = Tools.ukw;
	//public String fname =Tools.ukw;
	//public String lname = Tools.ukw;
	public String StageName = Tools.ukw;
	public String director= Tools.ukw;
			
		public SGInMovies(){}
	
		public SGInMovies (Integer id_, String title_, String StageName_, String director_){
			id = id_;
			title = title_;
			StageName = StageName_;
			director = director_;
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
	    public String getMovieDir() {
	        return director;
	    }
	    public void setMovieDir(String director_) {
	        this.director = director_;
	    }
	    
	    public String getStarStageName() {
	        return StageName;
	    }
	    public void setStarStageName(String StageName_) {
	        this.StageName = StageName_;
	    }
	    
	    
	  /*  public String getStarFName() {
	        return fname;
	    }
	    public void setStarFName(String fname_) {
	        this.fname = fname_;
	    }
	
	    public String getStarLName() {
	        return lname;
	    }
	    public void setStarLName(String lname_) {
	        this.lname = lname_;
	    }*/

}
