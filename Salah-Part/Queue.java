import java.io.File;

public class Queue extends CircularLinkedList {
    Queue(){
        super();
    }
    
    public void enqueue(File file) {
        super.insertAtBack(file);
    }
    
    public File dequeue() {
        return super.deleteFromFront();
    }
}
//	
//	void display() {
//		super.displayForward();
//	}
	
//	Queue getPerfectSquares() {
//		Queue q1 = new Queue();
//		Queue check = new Queue();
//		int x;
//		while(!super.isEmpty()) {
//			x = dequeue();
//			q1.enqueue(x);
//			double sqrt = Math.sqrt(x);
//			if(sqrt - (int)(sqrt) == 0) {
//				check.enqueue(x);
//			}
//		}
//		while(!q1.isEmpty()) {
//			this.enqueue(q1.dequeue());
//		}
//		return check;
//	}
	
//	Queue deleteEveryThirdElement() {
//		Queue q1 = new Queue();
//		Queue check = new Queue();
//		int x;
//		int i = 0;
//		while(!super.isEmpty()) {
//			x = dequeue();
//			q1.enqueue(x);
//			i++;  
//			if(i % 3 != 0) {
//				check.enqueue(x);
//			}
//		}
//		while(!q1.isEmpty()) {
//			this.enqueue(q1.dequeue());
//		}
//		return check;
//	}
	
//	Queue Merge2Queues(Queue q2) {
//		Queue q1 = new Queue();
//		Queue check = new Queue();
//		int x;
//		while(!super.isEmpty()) {
//			x = dequeue();
//			q1.enqueue(x);
//			check.enqueue(x);
//		}
//		while(!q1.isEmpty()) {
//			this.enqueue(q1.dequeue());
//		}
//		while(!q2.isEmpty()) {
//			int x1 = q2.dequeue();
//			q1.enqueue(x1);
//			check.enqueue(x1);
//		}
//		while(!check.isEmpty()) {
//			q2.enqueue(check.dequeue());
//		}
//		return check;
//	}