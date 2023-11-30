package Project;

import Node.Node;

public class Queue extends CircularLinkedList {
    Queue(){
        super();
    }
    
    public void enqueue(Node node) {
        System.out.println("Enqueued node: " + node); // Print out the node
        super.insertAtBack(node);
    }
    
    public Node dequeue() {
        Node node = super.deleteFromFront();
        System.out.println("Dequeued node: " + node); // Print out the node
        return node;
    }
}