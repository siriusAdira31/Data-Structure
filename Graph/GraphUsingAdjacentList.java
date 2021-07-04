class DoublyLinkedList<T>{
	class Node{
		T data;
		Node prevNode;
		Node nextNode;
	}

	int size;
	Node headNode;
	Node tailNode;

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

/*
 Below implementation is for Directed Graph

 - check commented code for undirected Graph. 

*/

@SuppressWarnings("unchecked")
public class GraphUsingAdjacentList{
	private int vertices;
	private DoublyLinkedList<Integer> adjacentList[];

	public GraphUsingAdjacentList(int vertices){
		this.vertices = vertices;
		this.adjacentList = new DoublyLinkedList[vertices];

		for(int i=0;i<vertices;i++){
			this.adjacentList[i] = new DoublyLinkedList<Integer>();
		}
	}


	public void addEdge(int source,int destination){

		if(source < this.vertices && destination < this.vertices){
			this.adjacentList[source].insertAtEnd(destination);


			// For Undirected Graph
			//this.adjacentList[destination].insertAtEnd(source);
		}
	}


	public void printGraph(){
		for(int i=0;i<this.vertices;i++){
			
			if(adjacentList[i] != null){

				System.out.print("| "+i+" | => ");

				DoublyLinkedList<Integer>.Node currentNode = this.adjacentList[i].getHeadNode();

				if(currentNode != null){
					while(currentNode != null){
						System.out.print("[ "+currentNode.data+" ]=>");
						currentNode = currentNode.nextNode;
					}
				}
				
				System.out.println("null");
				
			}else {
				System.out.println("| "+i+" | => null");
			}
		}	
	}

	public static void main(String[] args){
		GraphUsingAdjacentList graph = new GraphUsingAdjacentList(4);

		graph.addEdge(0,1);
		graph.addEdge(0,2);
		graph.addEdge(0,3);

		graph.addEdge(1,2);

		graph.addEdge(2,3);

		graph.printGraph();
	}
}