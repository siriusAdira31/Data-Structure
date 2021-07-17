class Node{
	private int data;
	private Node left;
	private Node right;

	Node(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public int getData(){
		return this.data;
	}

	public void setData(int data){
		this.data = data;
	}

	public Node getLeft(){
		return this.left;
	}

	public void setLeft(Node left){
		this.left = left;
	}

	public Node getRight(){
		return this.right;
	}

	public void setRight(Node right){
		this.right = right;
	}
}

public class BinarySearchTree {
	private Node root;

	public Node getRoot(){
		return this.root;
	}

	public void setRoot(Node root){
		this.root = root;
	}

	public boolean isEmpty(){
		return this.root == null;
	}

	public void printBST(Node current){
		if(current == null){
			return;
		}

		System.out.print(current.getData() + "  ");
		printBST(current.getLeft());
		printBST(current.getRight());

	}

	/*
	
		Insertion in BST : 2 ways ==> 1. Iterative , 2. Recursive

	*/

	// Iterative Approach

	public boolean insertIteratively(int data){
		if(isEmpty()){
			this.root = new Node(data);
			return true;
		}

		Node currentNode = this.root;

		while(currentNode != null){
			Node leftChild = currentNode.getLeft();
			Node rightChild = currentNode.getRight();

			if(currentNode.getData() > data){
				if(leftChild == null){
					leftChild = new Node(data);
					currentNode.setLeft(leftChild);
					return true;
				}

				currentNode = leftChild;
			} else {
				if(rightChild == null){
					rightChild = new Node(data);
					currentNode.setRight(rightChild);
					return true;
				}

				currentNode = rightChild;
			}
		}

		return false;
	}

	// Recursive Approach

	public boolean insertRecursively(int data){

		this.root = insertValueRecursively(root, data);

		return true;
	}

	public Node insertValueRecursively(Node current, int data){
		if(current == null)
			return new Node(data);

		if(data < current.getData())
			current.setLeft(insertValueRecursively(current.getLeft(),data));
		else if(data > current.getData())
			current.setRight(insertValueRecursively(current.getRight(),data));
		else // data exist already
			return current;

		return current;		
	}




	/*

		Search in BST : Approach => 1. Iterative 2. Recursive

	*/


	// Iterative approach

	public Node searchIteratively(int searchData){
		if(isEmpty())
			return null;

		Node currentNode = this.root;
		
		while(currentNode != null){
			if(currentNode.getData() == searchData)
				return currentNode;
			else if(searchData < currentNode.getData())
				currentNode = currentNode.getLeft();
			else 
				currentNode = currentNode.getRight();
		}

		System.out.println("------ DATA NOT FOUND IN BST -------");
		return null;
	}


	// Recursive approach
	public Node searchRecursively(int searchData){
		Node node = searchDataRecursively(this.root, searchData);

		return (node != null) ? node : null;
	}

	public Node searchDataRecursively(Node current, int searchData){
		if(current == null || current.getData() == searchData)
			return current;

		if(searchData < current.getData()){
			current = searchDataRecursively(current.getLeft(),searchData);
		}else{
			current = searchDataRecursively(current.getRight(),searchData);
		}

		return current;
	}


	/*
		DELETE NODE => 

		3 cases :

		1. leaf Node (left & right child are NULL)
		2. Node with one child (either left or right)
		3. Node with both the children (left & right)
	*/

	
	public boolean deleteNode(int data, Node current){

		if(root == null) {
			return false;
		}

		Node parent = null;
		while(current != null && current.getData() != data){
			parent = current;

			if(current.getData() > data){
				current = current.getLeft();
			}else{
				current = current.getRight();
			}
		}

		if(current == null){
			return false;
		}else if (current.getLeft() == null && current.getRight() == null){
			if(root.getData() == current.getData()){
				setRoot(null);
			}else if(current.getData() < parent.getData()){
				parent.setLeft(null);
			}else{
				parent.setRight(null);
			}

			return true;
		}else if(current.getRight() == null){
			if(root.getData() == current.getData()){
				setRoot(current.getLeft());
			}else if(current.getData() < parent.getData()){
				parent.setLeft(current.getLeft());
			}else{
				parent.setRight(current.getLeft());
			}

			return true;
		}else if(current.getLeft() == null){
			if(root.getData() == current.getData()){
				setRoot(current.getRight());
			}else if(current.getData() < parent.getData()){
				parent.setLeft(current.getRight());
			}else{
				parent.setRight(current.getRight());
			}

			return true;
		}else {
			Node lefMostNode = findLeastNode(current.getRight());

			int leastNodeData = lefMostNode.getData();
			deleteNode(leastNodeData,root);

			current.setData(leastNodeData);
			return true;
		}
	}	


	public Node findLeastNode(Node current){

		Node temp = current;

		while(temp.getLeft() != null){
			temp = temp.getLeft();
		}

		return temp;
	}


	public void inOrderTraversal(Node current){

		if(current == null)
			return;
		
		inOrderTraversal(current.getLeft());
		System.out.print(current.getData()+", ");
		inOrderTraversal(current.getRight());
	}	

	public void preOrderTraversal(Node current){

		if(current == null)
			return;

		System.out.print(current.getData()+", ");
		preOrderTraversal(current.getLeft());
		preOrderTraversal(current.getRight());	
	}	

	public void postOrderTraversal(Node current){

		if(current == null)
			return;

		postOrderTraversal(current.getLeft());
		postOrderTraversal(current.getRight());	
		System.out.print(current.getData()+", ");
	}	


	public static void main(String[] args){
		BinarySearchTree bst = new BinarySearchTree();
		bst.insertIteratively(5);
		bst.insertIteratively(3);
		bst.insertIteratively(4);
		bst.insertIteratively(2);
		bst.insertIteratively(1);
		System.out.print("Iterative insertion done :::   ");
		bst.printBST(bst.root);

		bst.insertRecursively(8);
		bst.insertRecursively(6);
		bst.insertRecursively(7);
		bst.insertRecursively(10);
		bst.insertRecursively(9);

		System.out.println();
		System.out.println();
		System.out.print("Recursive insertion done :::   ");
		bst.printBST(bst.root);



	    //Traversal :

	    //1.in-order (left-root-right)
	    System.out.println();
		System.out.println();
		bst.inOrderTraversal(bst.getRoot());

		//1.pre-order (root-left-right)
		System.out.println();
		System.out.println();
		bst.preOrderTraversal(bst.getRoot());

		//1.post-order (left-right-root)
		System.out.println();
		System.out.println();
		bst.postOrderTraversal(bst.getRoot());

		// Search Iteratively :
		Node result1 = bst.searchIteratively(5);

		System.out.println();System.out.println();
		System.out.println("Search 5 => "+ ((result1 != null) ? "FOUND" : "NOT FOUND"));

		// Search Recursively :

		Node result2 = bst.searchRecursively(11);

		System.out.println();System.out.println();
		System.out.println("Search 11 => "+ ((result2 != null) ? "FOUND" : "NOT FOUND"));

		System.out.println();System.out.println();
		
		System.out.println("DELETE 10" + bst.deleteNode(10,bst.root));
		System.out.print("After Deletion of node with 1 child - 10 :::   ");
		bst.printBST(bst.root);

		System.out.println();System.out.println();
		
		System.out.println("DELETE 5" + bst.deleteNode(5,bst.root));
		System.out.print("After Deletion of Root - 5 :::   ");
		bst.printBST(bst.root);


		System.out.println("DELETE 5" + bst.deleteNode(1,bst.root));
		System.out.print("After Deletion of leaf node - 1 :::   ");
		bst.printBST(bst.root);
	}
}