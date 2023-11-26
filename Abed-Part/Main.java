
import java.util.Scanner;

import Project.Song;
import Project.Playlist;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Playlist p = new Playlist();

        loadPlaylistFromFile(p);

        performPlaylistOperations(p);

        savePlaylistToFile(p);
    }

    private static void loadPlaylistFromFile(Playlist p) {
        File file = new File("playlist.txt");

        if (!file.exists()) {
            System.out.println("The playlist file does not exist. Creating a new file.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Song song = new Song(parts[0], parts[1]);
                    p.addSong(song);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePlaylistToFile(Playlist p) {
        try (FileWriter writer = new FileWriter("playlist.txt")) {
            for (Song song : p.getSongs()) {
                writer.write(song.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performPlaylistOperations(Playlist p) {
        Scanner console = new Scanner(System.in);

        System.out.print("Do you want to clear the Added Songs print y for yes and n for no: ");
        char c = console.next().charAt(0);
        if (c == 'y') {
            p.clear();
            p.play();
        } else {
            System.out.print("Add a song at a specific index: ");
            int y = console.nextInt();
            String s1 = console.next();
            String s3 = console.next();
            Song song1 = new Song(s1, s3);
            p.addSongAt(y, song1);
            p.play();
        }

        console.close();
    }
}
