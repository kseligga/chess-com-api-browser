package pl.edu.pw.mini.projekt.ratings;

public class RatingWithTimestamp {

	private long timestamp;
	private int rating;

	public long getTimestamp() {
		return timestamp;
	}

	public int getRating() {
		return rating;
	}

	public RatingWithTimestamp(long timestamp, int rating) {
		this.timestamp = timestamp;
		this.rating = rating;
	}
}
