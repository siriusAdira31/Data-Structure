

public class SinglyLinkedList<T> {

	// Node inner class 
	private class Node{   
		public T data;  // any type of data to store
		public Node nextNode;  // pointer to next node
	}

	private Node headNode;   // head of the list
	private int size;   // maintain size of the list

	public SinglyLinkedList(){
		headNode = null;
		size = 0;
	}

	// SLL basic operations

	/*
	 1) isEmpty() : if list is Empty -> return true ; Else return false.

	 	- Base condition to check : whether head is NULL or not !
	 	- time complexity  =  O(1)
	*/
	public boolean isEmpty(){
		if(headNode == null)
			return true;

		return false;
	}

	/*
	 1) insertAtHead() : 
		- create new Node, set input data to it.
	 	- point next of newNode to head
	 	- point head to newNode
	 	- time complexity  =  O(1)
	*/

	public void insertAtHead(T data){
		Node newNode = new Node();
		newNode.data = data;

		newNode.nextNode = headNode;
		headNode = newNode;
		size++;
	} 


	/*
	 1) insertAtEnd() : 
		- base case - list is empty --> insertAtHead()
	 	- Else - traverse whole list , till node's next not null --> assign next of last node to newNode.
	 	- time complexity  =  O(n)
	*/

	public void insertAtEnd(T data){

		if(isEmpty()){
			insertAtHead(data);
			return;
		}


		Node newNode = new Node();
		newNode.data = data;
		newNode.nextNode = null;

		Node lastNode = headNode;

		while(lastNode.nextNode != null)
			lastNode = lastNode.nextNode;

		lastNode.nextNode = newNode;
		size++;
	} 


	/*
	 1) insertAfter() : 
		- base case - list is empty --> insertAtHead()
	 	- Else - traverse whole list , till current node's reaches the input postition 
	 	- set newNode's next to currentNode's next
	 	- set currentNode's next to newNode

	 	- time complexity  =  O(n)
	*/

	public void insertAfter(T data, int position){

		if(isEmpty()){
			insertAtHead(data);
			return;
		}


		Node newNode = new Node();
		newNode.data = data;
		newNode.nextNode = null;

		Node currentNode = headNode;
		int currentPosition = 1;


		while(currentNode.nextNode != null && currentPosition < position){
			currentNode = currentNode.nextNode;
			currentPosition++;

		}

		newNode.nextNode = currentNode.nextNode;
		currentNode.nextNode = newNode;
		size++;
	} 	
	


	/*
		complexity = O(n)
	*/
	public void printSLL(){
		if(isEmpty()){
			System.out.println("List is empty !");
			return;
		}

		Node currentNode = headNode;
		while(currentNode != null){
			if(currentNode.nextNode == null)
				System.out.println(currentNode.data+" -> null");
			else
				System.out.print(currentNode.data+" -> ");

			currentNode = currentNode.nextNode;
		}
	}

	public static void main(String[] args){
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();

		sll.insertAtHead(3);
		sll.insertAtHead(2);
		sll.insertAtHead(1);

		sll.printSLL();

		sll.insertAtEnd(4);
		sll.insertAtEnd(6);

		sll.printSLL();

		sll.insertAfter(5,4);
		sll.insertAfter(7,6);

		sll.printSLL();



	}


}