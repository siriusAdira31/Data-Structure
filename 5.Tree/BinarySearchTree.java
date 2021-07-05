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
	}
}