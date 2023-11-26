package Project;

public class Song {
	private String title;
	private String artist;

	public Song() {
		this.title = "";
		this.artist = "";
	}

	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String toString() {
		return "Song [title = " + title + " , artist = " + artist + "]";
	}
}