public class DoublyLinkedList<T>{
	private class Node{
		private T data;
		private Node prevNode;
		private Node nextNode;
	}

	private int size;
	private Node headNode;
	private Node tailNode;

	public DoublyLinkedList(){
			this.headNode = null;
			this.tailNode = null;
	}

	public Node getHeadNode(){
		return headNode;
	}

	public Node getTailNode(){
		return tailNode;
	}

	public int getSize(){
		return size;
	}

	public boolean isEmpty(){
		return (headNode == null && tailNode == null);
	}

	public void printDLL(){
		if(isEmpty()){
			System.out.println("DLL is empty !!!");
			return;
		}

		Node currentNode = headNode;

		while(currentNode != null){
			if(currentNode.nextNode == null)
				System.out.print(currentNode.data +" -> null");
			else
				System.out.print(currentNode.data +" <-> ");

			currentNode = currentNode.nextNode;
		}
	}

	public void printReversedDLL(){
		if(isEmpty()){
			System.out.println("DLL is empty !!!");
			return;
		}

		Node currentNode = tailNode;

		while(currentNode != null){
			if(currentNode.prevNode == null)
				System.out.print(currentNode.data +" -> null");
			else
				System.out.print(currentNode.data +" <-> ");

			currentNode = currentNode.prevNode;
		}
	}

	public void insertAtHead(T data){
		Node newNode = new Node();
		newNode.data = data;
		newNode.prevNode = null;
		newNode.nextNode = this.headNode;

		if(headNode == null)
			tailNode = newNode;
		else
			headNode.prevNode = newNode;

		headNode = newNode;
		size++;
	}

	public void insertAtEnd(T data){
		if(isEmpty()){
			insertAtHead(data);
			return;
		}

		Node newNode = new Node();
		newNode.data = data;
		newNode.prevNode = this.tailNode;
		newNode.nextNode = null;
		
		tailNode.nextNode = newNode;
		tailNode = newNode;
		
		size++;
	}

	public void deleteFromHead(){
		if(isEmpty())
			return;

		headNode = headNode.nextNode;
				
		if(headNode == null)
			tailNode = null;
		else
			headNode.prevNode = null;
		

		size--;
	}

	public void deleteFromEnd(){
		if(isEmpty())
			return;

		tailNode = tailNode.prevNode;

		if(tailNode == null)
			headNode = null;
		else
			tailNode.nextNode = null;

		size--;
	}
}