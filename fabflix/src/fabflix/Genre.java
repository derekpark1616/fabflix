package fabflix;

public class Genre {

	public int gid = 999999;
	public String gname = Tools.ukw;

	public Genre() {

	}

	public int getGenreId() {
		return gid;
	}

	public void setGenreId(int gid_) {
		this.gid = gid_;
	}

	public String getGenreName() {
		return gname;
	}

	public void setGenreName(String gname_) {
		this.gname = gname_;
	}

	public Genre(int gid_, String gname_) {
		gid = gid_;
		gname = gname_;
	}

}
