
public class Film {

	private String title;
	private String genre;
	private double distance;
	
	public Film(String title, String genre, double distance) {
		this.title = title;
		this.genre = genre;
		this.distance = distance;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public double getDistance() {
		return distance;
	}
	
}
