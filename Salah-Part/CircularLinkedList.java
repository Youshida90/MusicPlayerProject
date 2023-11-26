import java.io.File;

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

//	void display() {
//		Node current = this.head;
//		while (current != tail) {
//			System.out.print(current.data + " --> ");
//			current = current.next;
//		}
//		System.out.print(current.data + " --> ");
//		System.out.println();
//	}


	void insertAtBack(File file) {
		Node p = new Node(file);
		@SuppressWarnings("unused")
		Node z = p.getNext();
		if (isEmpty()) {
			this.head = p;
			this.tail = p;
		} else {
			
			z = this.tail;
			tail.next = p;
			this.tail = p;

		}

	}
	
	public File deleteFromFront() {
	    if (isEmpty()) {
	        System.out.println("The LinkedList is empty");
	        return null;
	    } else {
	        Node current = this.head;
	        while (current.getNext() != tail) {
	            current = current.getNext();
	        }
	        File file = this.head.getData(); // assuming getData() returns a File object
	        this.head = this.head.getNext();
	        return file;
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
			tail.next = head;
		}

	}
}