

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
	 2) insertAtHead() : 
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
	 3) insertAtEnd() : 
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
	 4) insertAfter() : 
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
	 5) searchNode() 
	 	- base case : check list is empty or not.
	 	- iterate list till last node & check data
	 	-Time complexity -> O(n)
	*/
	public boolean searchNode(T data) {
        
        if(isEmpty())
            return false;
        Node currentNode = headNode;
        while(currentNode.nextNode != null){
            if(currentNode.data.equals(data))
                return true;
            currentNode = currentNode.nextNode;    
        }    
        return false; //value not found
    }

    /*
	 6) deleteAtHead() 
	 	- base case : check list is empty or not.
	 	- update head to head.next;
	 	- decrease size
	 	-Time complexity -> O(1)
	*/
	public boolean deleteAtHead() {
        
        if(isEmpty())
            return false;
        
        headNode = headNode.nextNode; // after function completion previous first node , will be handled by Garbage collection , memory will be deallocated.
        size--;
        return true;
    }

    /*
	 7) deleteByValue() 
	 	- base case : check list is empty or not.
	 	- update head to head.next;
	 	- loop the list with current & previous pointer to keep track of previous node to update 
	 	- decrease the size;
	 	-Time complexity -> O(n)
	*/
	public boolean deleteByValue(T data) {
        
        if(isEmpty())
            return false;
        
        if(headNode.data.equals(data)){
        	deleteAtHead();
        }

        Node currentNode = headNode.nextNode;
        Node previousNode = headNode;

        while(currentNode != null){
        	if(currentNode.data.equals(data)){
        		previousNode.nextNode = currentNode.nextNode;
        		size--;
        		return true;
        	}

        	previousNode = currentNode;
        	currentNode = currentNode.nextNode;
        }
        return false;
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

		System.out.println("Search 4 => " + sll.searchNode(4));
		System.out.println("Search 10 => " + sll.searchNode(10));

		sll.deleteAtHead();
		sll.printSLL();

		sll.deleteByValue(5);
		sll.printSLL();

		System.out.println("deleteByValue which doesn't exist in list :" + sll.deleteByValue(10));

	}
}