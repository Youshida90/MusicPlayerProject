package Ex3;

public class Node {
private Song song;
private Node next;

public Node(){
	this.next = null;
	this.song = null;
}
public Node(Song song) {
	this.song = song;
	this.next = null;
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
}
