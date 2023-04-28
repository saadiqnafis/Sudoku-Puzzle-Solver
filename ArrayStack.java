/*
File name: ArrayStack.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/


public class ArrayStack {

	private int top;
	private Cell[] stk;
	private int capacity;

	public ArrayStack() {

		this.capacity = 4;
		this.stk = new Cell[this.capacity];
		this.top = -1;
	}

	public void push(Cell c) {

		if (this.top == this.capacity - 1) {

			Cell[] temp = new Cell[this.capacity * 2];

			for (int i = 0; i < this.capacity; i++) {
				temp[i] = this.stk[i];
			}

			this.stk = temp;
			this.capacity *= 2;
		}

		this.top++;
		this.stk[this.top] = c;
	}

	public Cell pop() {

        if (top == -1) {
            return null;
        }
        Cell cell = this.stk[this.top];
		
		this.top--;

		return cell;
	}

    public int size() {

        return this.top +1;
    }

    public boolean empty() {
        return (this.top == -1);
    }

	public Cell peek() {

	// 	int result = 
		return this.stk[this.top];
	 }

	public String toString() {

		String result = "";

		for (int i = 0; i <= this.top; i++) {

			result += this.stk[i].toString() + ", ";
		}

		return result;
	}


	public static void main(String[] args) {
	
		// ArrayStack myArrayStack = new ArrayStack();
		// myArrayStack.push(5);
		// myArrayStack.push(7);
		// myArrayStack.push(10);
		// myArrayStack.push(15);
		// myArrayStack.push(35);

		// System.out.println(myArrayStack);

		// System.out.println("pop: " + myArrayStack.pop());

		// System.out.println("peek: " + myArrayStack.peek());
		// System.out.println(myArrayStack);
        // System.out.println(myArrayStack.size());

	}

}