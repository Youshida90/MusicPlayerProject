import java.io.File;

public class Node {
private File song;
public Node next;
private File data;
public File getData() {
	return data;
}
public void setData(File data) {
	this.data = data;
}
public Node getNext() {
	return next;
}
public void setNext(Node next) {
	this.next = next;
}
public Node(){
	this.next = null;
	this.song = null;
}
public Node(File string) {
	this.song = string;
	this.next = null;
}

public File getSong() {
	return song;
}
}
