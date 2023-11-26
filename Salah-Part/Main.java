import java.io.File;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        // Add songs to the playlist
        playlist.addSong(new File("/path/to/song1.mp3"));
        playlist.addSong(new File("/path/to/song2.mp3"));
        playlist.addSong(new File("/path/to/song3.mp3"));

        // Shuffle the playlist
        playlist.shuffle();

        // Play the playlist
        playlist.play();
    }
}