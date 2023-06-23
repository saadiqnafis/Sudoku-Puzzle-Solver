/*
File name: CellStack.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/


public class CellStack {

    private class Node {

        public Cell cell;
        public Node next;
    
        //a constructor that initializes next to null and the Cell field to cell

        public Node(Cell cell) {
            this.cell = cell;
            this.next = null;
    
        
        }

        //returns the value of the container field

        public Cell getCell() {
            return this.cell;

        }

        //sets next to the given node

        public Node getNext() {

            return this.next;
        }

        //returns the next field

        public void setNext(Node n) {
            this.next = n;

        }

    }

    Node head;
    int size;

    //initialize the stack's fields
    public CellStack() {
        this.head = null;
        this.size = 0;

    }
    
    //push c onto the stack
    public void push( Cell c ) {
        Node newNode = new Node(c);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;

    }
    
    //return the top Cell on the stack
    public Cell peek() {

        return this.head.getCell();

    } 
    
    //remove and return the top element from the stack; return null if the stack is empty.
    public Cell pop() {
        if (this.size == 0) {

            return null;
        }
        Cell cell = this.head.getCell();
        this.head = this.head.getNext();
        this.size--;
        return cell;



    } 

    //return the number of elements in the stack
    public int size(){
        return this.size;


    } 

    //return true if the stack is empty
    public boolean empty() {
        return (this.size == 0);


    }

    public String toString() {
        String result = "";
        Node temp = this.head;
        while (temp != null) {
            result += temp.getCell().toString() + ", ";
            temp = temp.getNext();
        }
        //result += head.toString() + size;
        return result;

    }


    public static void main(String[] args) {


        // CellStack mystack = new CellStack();
        // CellStack auxstack = new CellStack();
        // mystack.push(C1);

        
    }



}