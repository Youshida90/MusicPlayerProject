package Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Node.Node;
import Tree.BinarySearchTree;

public class Playlist extends Queue {
    private Node head;
    private Node tail;
    private int size;

    public Playlist() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void play() {
        if (isEmpty()) {
            System.err.println("The Playlist is Empty");
        } else {
            Node current = this.head;
            while (current != tail) {
                System.out.print(current.getFile().getName() + "\n");
                current = current.getNext();
            }
            System.out.print(current.getFile().getName() + "\n");
            System.out.println();
        }
    }

    void clear() {
        if (isEmpty()) {
            System.out.println("The LinkedList is empty");
        } else {
            head = null;
            tail = null;
            size = 0;
        }
    }
    
    
    public void deleteSong(File song) {
        if (isEmpty()) {
            System.err.println("The Playlist is empty");
            return;
        }
        if (head.getFile().equals(song)) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                tail.setNext(head);
            }
            size--;
            return;
        }
        Node current = head;
        while (current.getNext() != head && !current.getNext().getFile().equals(song)) {
            current = current.getNext();
        }
        if (current.getNext().getFile().equals(song)) {
            if (current.getNext() == tail) {
                tail = current;
            }
            current.setNext(current.getNext().getNext());
            size--;
        } else {
            System.err.println("Song not found in the Playlist");
        }
    }
    public List<String> getPlayOrderSongNames() {
        List<String> songNames = new ArrayList<>();
        Playlist tempPlaylist = new Playlist();
        Node current = this.head;
        do {
            songNames.add(current.getFile().getName());
            tempPlaylist.addSong(current.getFile());
            current = current.getNext();
        } while (current != this.head);
        this.head = tempPlaylist.head;
        this.tail = tempPlaylist.tail;
        this.size = tempPlaylist.size;
        return songNames;
    }
    public void addSong(File newSong) {
        Node p = new Node(newSong.getName(), newSong);
        BinarySearchTree bt = new BinarySearchTree();
        bt.insert(newSong.getName(), newSong);
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
    public void shuffle() {
        List<File> list = new ArrayList<>();
        Random rand = new Random();

        // Dequeue all elements from the queue and add them to the list
        Node current = this.head;
        do {
            list.add(current.getFile());
            current = current.getNext();
        } while (current != this.head);

        // Shuffle the list
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Collections.swap(list, i, j);
        }

        // Enqueue all elements from the list to the queue
        Playlist tempPlaylist = new Playlist();
        for (File file : list) {
            tempPlaylist.addSong(file);
        }
        this.head = tempPlaylist.head;
        this.tail = tempPlaylist.tail;
        this.size = tempPlaylist.size;
    }
}