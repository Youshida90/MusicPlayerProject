package Node;

import java.io.File;

import Project.Song;

public class Node {
	private String data;
	private Song song;
	private Node next;
	private Node Left, Right;

	public Node() {
		this.next = null;
		this.song = null;
		this.Left = this.Right = null;
	}

	public Node(Song song) {
		this();
		this.song = song;
	}

	public Node(String data, Song song) {
		this.data = data;
		this.song = song;
		this.Left = this.Right = null;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Song getSong() {
		return song;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getLeft() {
		return Left;
	}

	public void setLeft(Node left) {
		Left = left;
	}

	public Node getRight() {
		return Right;
	}

	public void setRight(Node right) {
		Right = right;
	}

}
