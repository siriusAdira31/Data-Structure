import java.lang.reflect.*;

public class StackUsingArray<T>{
	private int maxSize;
	private int top;
	private T[] stack;

	@SuppressWarnings("unchecked")   // --> as Generic Array type gives runtime error as - generic is non-reifiable  & type erasure. (A reifiable type is a type whose type information is fully available at runtime.)
	public StackUsingArray(Class<T> clazz, int maxSize){
		this.maxSize = maxSize;
		this.top = -1;
		this.stack = (T[]) Array.newInstance(clazz,maxSize);
	}

	public boolean isEmpty(){
		return top == -1;
	}

	public boolean isFull(){
		return maxSize-1 == top;
	}

	public int getMaxSize(){
		return maxSize;
	}

	public T top(){
		if(isEmpty()){
			System.out.println("Stack is empty !!");
			return null;
		}

		return stack[top];
	}

	public void push(T data){
		if(isFull()){
			System.out.println("Stack is full !!");
			return;
		}

		stack[top++] = data;
	}

	public T pop(){
		if(isEmpty()){
			System.out.println("Stack is empty !!");
			return null;
		}

		return stack[top--];
	}


	public void printStack(){
		if(isEmpty()){
			System.out.println("Stack is empty !!");
			return;
		}


		for(T in : stack) {
           	System.out.print(in+" , ");
        }
	}

	public static void main(String[] args){
		StackUsingArray stack1 = new StackUsingArray(Integer.class,5);

		System.out.println("Check maxSize() :: "+ stack1.getMaxSize());

		// Empty stack
		stack1.printStack(); 

		// insert data
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(4);
		stack1.push(5);
		stack1.printStack();
		System.out.println("Check isFull()  :: " + stack1.isFull());

		stack1.pop();
		stack1.pop();

		System.out.println("Check top  :: "+ stack1.top());

		stack1.pop();
		stack1.pop();
		stack1.pop();
		System.out.println("Check top  :: " + stack1.top());
		System.out.println("Check isEmpty()  :: " + stack1.isEmpty());
	}
}