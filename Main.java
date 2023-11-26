package Ex3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);
		Playlist p = new Playlist();
		System.out.print("Enter the Number of Songs: ");
		int x = console.nextInt();
		System.out.println("Enter the Name of the Song and the artist: ");
		for (int i = 0; i < x; i++) {
			String s = console.next();
			String s2 = console.next();
			Song song = new Song(s, s2);
			p.addSong(song);
		}
		p.play();
		System.out.print("Do you want to clear the Added Songs print y for yes and n for no: ");
		char c = console.next().charAt(0);
		if (c == 'y') {
			p.clear();
			p.play();
		} else {
			System.out.print("Add a song at a speacific index: ");
			int y = console.nextInt();
			String s1 = console.next();
			String s3 = console.next();
			Song song1 = new Song(s1, s3);
			p.addSongAt(y, song1);
			p.play();
			System.out.print("Search for the song by title: ");
			String s5 = console.next();
			p.getSongByTitle(s5);
			p.play();
			System.out.println("Get Random songs");
			p.getRandomSong();
			p.play();
			System.out.print("Remove the Song by title: ");
			String s6 = console.next();
			p.removeSong(s6);
			p.play();
			System.out.println("Removed Duplicates:");
			p.removeDupicate();
			p.play();
			System.out.println("Sorted playlist: ");
			p.sortPlayListByTitle();
			p.play();
			System.out.println("Shuffle playlist: ");
			p.shufflePlaylist();
			p.play();
		}

	}
}