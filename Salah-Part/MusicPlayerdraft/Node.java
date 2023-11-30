package Node;

import java.io.File;


public class Node {
    private String data;
    private File file;
    public Node next;
    private Node Left, Right;

    public Node() {
        this.next = null;
        this.file = null;
        this.Left = this.Right = null;
    }

    public Node(File file) {
        this();
        this.file = file;
    }

    public Node(String fileName, File selectedFile) {
        this.data = fileName;
        this.file = selectedFile;
        this.Left = this.Right = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public File getFile() {
        return file;
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