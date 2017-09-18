package fabflix;

import java.sql.Date;



public class Star {
	public int mid = 999999;
	public String fname = Tools.ukw;
	public String lname = Tools.ukw;
	public Date dob;
	public Integer yDob = 1900;
	public String photo;
	public String stageNm = Tools.ukw;
	
	public Star(){
		
	}
	
	 	public int getStarId() {
	        return mid;
	    }
	    public void setStarId(int mid_) {
	        this.mid = mid_;
	    }
	    public String getStarFName() {
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
	    }
	    
	    public String getStarStageName() {
	        return stageNm;
	    }
	    public void setStarStageName(String stageNm_) {
	        this.stageNm = stageNm_;
	    }
 
	    
	    public Date setStarDob() {
	        return dob;
	    }
	    
	    public void setStarDob(Date dob_) {
	        this.dob = dob_;
	    }
	    public void setStarYearBirth(Integer yDob_) {
	        this.yDob = yDob_;
	    }
	    public void setStarPhoto(String photo_) {
	        this.photo = photo_;
	    }
	    

	public Star (int mid_, String fname_, String lname_, Date dob_, String photo_){
		mid = mid_;
		fname = fname_;
		lname = lname_;
		dob = dob_;
		photo = photo_;
		}
	
	public Star (int mid_, String fname_, String lname_, Integer yDob_, String stageNm_){
		mid = mid_;
		fname = fname_;
		lname = lname_;
		yDob = yDob_;
		//photo = photo_;
		stageNm = stageNm_;
		}
	
	
	
}
