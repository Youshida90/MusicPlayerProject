import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MusicPlayer {
    List<String> songs;
    Random rand;

    public MusicPlayer() {
        songs = new ArrayList<>();
        rand = new Random();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public void shuffle() {
        for (int i = songs.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Collections.swap(songs, i, j);
        }
    }

    public void play() {
        for (String song : songs) {
            System.out.println("Playing: " + song);
        }
    }

    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        mp.addSong("Song 1");
        mp.addSong("Song 2");
        mp.addSong("Song 3");
        mp.addSong("Song 4");
        mp.addSong("Song 5");
        mp.shuffle();
        mp.play();
    }
}