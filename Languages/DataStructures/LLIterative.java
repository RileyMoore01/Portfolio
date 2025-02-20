//This file executes the methods for creating a linked list in java
//This version is iterative, using as little built-in methodology as possible
public class LinkedList {

	public Node head;

	public LinkedList() {
		this.head = null;
	}

	public void addToHead(String data) {
		Node newHead = new Node(data);
		Node currentHead = this.head;
		this.head = newHead;
		if (currentHead != null) {
			this.head.setNextNode(currentHead);
		}
	}

	public void addToTail(String data) {
		Node tail = this.head;
		if (tail == null) {
			this.head = new Node(data);
		} else {
			while (tail.getNextNode() != null) {
				tail = tail.getNextNode();
			}
			tail.setNextNode(new Node(data));
		}
	}

	public String removeHead() {
		Node removedHead = this.head;
		if (removedHead == null) {
			return null;
		}
		this.head = removedHead.getNextNode();
		return removedHead.data;
	}

	public String toString() {
		Node currentNode = this.head;
		String output = "<head> ";
		while (currentNode != null) {
			output += currentNode.data + " ";
			currentNode = currentNode.getNextNode();
		}
		output += "<tail>";
		return output;
	}

	public Node findNodeIteratively(String data) {
		Node currentNode = this.head;

		while (currentNode != null) {
			if (currentNode.data == data) {
				return currentNode;
			}
			currentNode = currentNode.getNextNode();
		}

		return null;
	}

	public Node findNodeRecursively(String data, Node currentNode) {
		// Add code here
    if (currentNode == null) {
      return null;
    } else if (data == currentNode.data) {
      return currentNode;
    } else {
      return this.findNodeRecursively(data, currentNode.getNextNode());
    }

	}

  public static void main(String[] args) {
		LinkedList myList = new LinkedList();

		myList.addToHead("Node 1");
		myList.addToHead("Node 2");
		myList.addToHead("Node 3");
		myList.addToHead("Node 4");

		// Add code here
    Node foundNode = myList.findNodeRecursively("Node 5", myList.head);        
    System.out.println(foundNode.data); 
  }
}
