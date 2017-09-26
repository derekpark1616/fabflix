package fabflix;

public class AndroidMovie {

	public int id = 999999;
	public String title = Tools.ukw;

	public AndroidMovie() {

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

	public AndroidMovie(Integer id_, String title_) {
		id = id_;
		title = title_;
	}
}
