package Project;

import Tree.BinarySearchTree;
import Node.Node;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private Node head;
	private Node tail;
	private int size;

	public Playlist() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	boolean isEmpty() {
		return this.head == null;
	}

	public void play() {
		if (isEmpty()) {
			System.err.println("The Playlist is Empty");
		} else {
			Node current = this.head;
			while (current != tail) {
				System.out.print(current.getSong() + "\n");
				current = current.getNext();
			}
			System.out.print(current.getSong() + "\n");
			System.out.println();
		}
	}

	public void clear() {
		if (isEmpty()) {
			System.out.println("The LinkedList is empty");
		} else {
			head = null;
			tail = null;
			size = 0;
		}
	}

	public void addSong(Song d) {
		Node p = new Node(d);
		if (isEmpty()) {
			this.head = p;
			this.tail = p;
			size++;
		} else {
			tail.setNext(p);
			this.tail = p;
			this.tail.setNext(head);
			size++;
		}

	}

	public void addSongAt(int index, Song newSong) {
		Node p = new Node(newSong);
		BinarySearchTree b = new BinarySearchTree();
		b.insert(newSong.getTitle(), newSong);
		if (isEmpty()) {
			System.out.println("I will add this " + newSong + " to the playlist");
			head = p;
			this.tail.setNext(p);
		} else {
			
			if (index == 0) {
				p.setNext(head);
				head = p;
				tail.setNext(head);
				size++;
			} else if (index == size) {
				addSong(newSong);
			} else if (index > size) {
				System.err.println("Index error");
			} else {
				Node current = head;
				while (index > 1) {
					index--;
					current = current.getNext();
				}
				Node post = current.getNext();
				current.setNext(p);
				p.setNext(post);
				size++;
			}
		}
	}

	public Song getRandomSong() {
		if (isEmpty()) {
			System.err.println("The Playlist is empty");
			return null;
		} else {
			Node current = head;
			int random = (int) (Math.random() * size);
			while (random > 0) {
				random--;
				current = current.getNext();
			}
			return current.getSong();
		}
	}

	public Song getSongByTitle(String title) {
		if (isEmpty()) {
			System.out.println("Add A new Song");
			return null;
		} else {
			return getSongByTitleRecursive(head, title);
		}
	}

	Song getSongByTitleRecursive(Node current, String title) {
		if (current.getSong().getTitle().equals(title)) {
			return current.getSong();
		} else if (current == tail) {
			return null;
		} else {
			return getSongByTitleRecursive(current.getNext(), title);
		}
	}

	public void removeSong(String title) {
		if (isEmpty()) {
			System.out.println("The Playlist is empty");
		} else if (getSongByTitle(title) == null) {
			System.out.println("This song does not exist in the playlist");
		} else {
			Node current = head;
			if (head.getSong().getTitle().equals(title)) {
				head = head.getNext();
				tail.setNext(head);
			} else {
				while (!current.getNext().getSong().getTitle().equals(title)) {
					current = current.getNext();
				}
				if (current.getNext() == tail) {
					tail = current;
					tail.setNext(head);
				} else {
					current.setNext(current.getNext().getNext());
				}
			}
		}
	}

	public void sortPlayListByTitle() {
		Song[] song = new Song[size];
		Node current = head;
		for (int i = 0; i < size; i++) {
			song[i] = current.getSong();
			current = current.getNext();
		}
		for (int i = size - 1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (song[j].getTitle().compareTo(song[i].getTitle()) < 0) {
					Song t = song[j];
					song[j] = song[i];
					song[i] = t;
				}
			}
		}
		clear();
		for (int i = song.length - 1; i >= 0; i--) {
			addSong(song[i]);
		}
	}

	public void removeDupicate() {
		sortPlayListByTitle();
		if (!isEmpty()) {
			Node current = head;
			while (current != tail) {
				if (current.getSong().getTitle().equals(current.getNext().getSong().getTitle())) {
					removeSong(current.getSong().getTitle());
				}
				current = current.getNext();
			}
		}
	}

	public void shufflePlaylist() {
		if (isEmpty()) {
			System.out.println("The playlist is empty");
		} else {
			ArrayList<Song> s = new ArrayList<>();
			Node current = head;
			for (int i = 0; i < size; i++) {
				s.add(current.getSong());
				current = current.getNext();
			}
			int length = size;
			clear();
			while (size < length) {
				int i = (int) (Math.random() * s.size());
				addSong(s.get(i));
				s.remove(i);
			}
		}
	}

	public List<Song> getSongs() {
		List<Song> songsList = new ArrayList<>();
		if (!isEmpty()) {
			Node current = head;
			do {
				songsList.add(current.getSong());
				current = current.getNext();
			} while (current != tail);
		}
		return songsList;
	}
}
