package Project;

import Node.Node;

public class CircularLinkedList {
    private Node head;
    private Node tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    boolean isEmpty() {
        return this.head == null;
    }

    void insertAtBack(Node node) {
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.tail.setNext(head); // to maintain the circularity
    }

    public Node deleteFromFront() {
        if (isEmpty()) {
            System.out.println("The LinkedList is empty");
            return null;
        } else {
            Node temp = this.head;
            if (head == tail) { // only one node
                head = null;
                tail = null;
            } else {
                this.head = this.head.getNext();
                this.tail.setNext(head); // to maintain the circularity
            }
            return temp;
        }
    }

    void deleteFromBack() {
        if (isEmpty()) {
            System.out.println("The LinkedList is empty");
        } else {
            Node current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            tail = current;
            tail.setNext(head); // to maintain the circularity
        }
    }
}