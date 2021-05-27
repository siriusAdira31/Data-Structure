
class SinglyLinkedList<T> {

	// Node inner class 
	class Node{   
		public T data;  // any type of data to store
		public Node nextNode;  // pointer to next node
	}

	Node headNode;   // head of the list
	int size;   // maintain size of the list

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
	 insertAtEnd() : 
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

	
}
public class ReverseSLLSolutions{

	/* 
	 1. Iterative Approach :  Time - O(n) , Space - O(constant)

	 - Use Previous, Next , current  (3 pointers) , 
	 - intialize prev = next = null , current = head
	 - Loop till current.next not NULL 
	 		# next = current->next;
	 		# current->next = prev;
	 		# prev = current;
	 		# current = next;
	 - set head to prev
	*/

	public static <T> void reverseSLLByIteration(SinglyLinkedList<T> list){

		SinglyLinkedList<T>.Node next = null, prev = null, current = list.headNode;
		
		while(current != null){
			next = current.nextNode;
			current.nextNode = prev;
			prev = current;
			current = next;

		}

		list.headNode = prev;
	}


	/*
	2. Recursive Approach : Time O(n)

	 - split head & rest part till last node recursively
	 - recursively update head.next.next by stacked calling head  & update stacked head.next to null 
	 - return rest , as atlast head.next = null , will result rest as reversed SLL.


	 SLL :: 1->2->3-4>X
						     <----| rest will be returned i.e. (4->3->2->1->X)		
	 	call 1	-	reverseRecursive(1)   
	                         head = 1  ,          <---| (4->3->2->1->X) , (1->X)  
	 			call 2   -   rest = reverseRecursive(2)  --->  head = 2 ,           <---| (4->3->2->X) , (1->2->X)
	 						 						call 3   - rest = reverseRecursive(3) ---> head = 3             <---| ( 4 -> 3 -> X ) , (1->2->3->X) 
	 						 								   					   call 4  -   rest = reverseRecursive(4)  ---|
 	*/

	 public static <T> SinglyLinkedList<T>.Node reverseSLLByRecusrsion(SinglyLinkedList<T>.Node head){

	 	if(head == null || head.nextNode == null)
	 		return head;

	 	SinglyLinkedList<T>.Node rest = reverseSLLByRecusrsion(head.nextNode);
	 	head.nextNode.nextNode = head;

	 	head.nextNode = null;

	 	return rest;
	 }						 


	 /*
		3. Tail Recursion : O(n)
		
		- Recusion with 2 pointers - 1.current (initially head), 2. previous (Initially null)
		- Base Condition : 1. head is null, return head , 2. current.next equals NULL , update head to current , update current.next to prev , return head
		- assign current.next to newNode
		- update current.next to previous, update previous to current
		- call recursively till reaches base condition.

	 */								   					   
	 public static <T> SinglyLinkedList<T>.Node reverseSLLByTailRecusrsion(SinglyLinkedList<T>.Node head, SinglyLinkedList<T>.Node current, SinglyLinkedList<T>.Node prev){

	 	if(head == null)
	 		return head;

	 	if(current.nextNode == null){
	 		head = current;
	 		current.nextNode = prev;
	 		return head;
	 	}

	 	SinglyLinkedList<T>.Node newNode = current.nextNode;
	 	current.nextNode = prev;
	 	head = reverseSLLByTailRecusrsion(head, newNode, current);
	 	return head;
	 }	



	public static void main(String[] args){
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();

		sll.insertAtEnd(1);
		sll.insertAtEnd(2);
		sll.insertAtEnd(3);
		sll.insertAtEnd(4);
		sll.insertAtEnd(5);

		sll.printSLL();

		// Test Iterative Approach
		reverseSLLByIteration(sll);
		sll.printSLL();

		sll.insertAtEnd(0);
		sll.headNode = reverseSLLByRecusrsion(sll.headNode);
		sll.printSLL();

		sll.insertAtEnd(6);
		sll.headNode = reverseSLLByTailRecusrsion(sll.headNode,sll.headNode,null);
		sll.printSLL();

	}

}