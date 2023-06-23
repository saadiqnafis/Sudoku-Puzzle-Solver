/*
File name: Cell.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/




import java.awt.Graphics;
import java.awt.Color;



public class Cell {


    int rowIndex;
    int columnIndex; 
    int value;
    boolean locked;

    public Cell() {

        this.rowIndex = 0;
        this.columnIndex = 0;
        this.value = 0;
        this.locked = false;
    }

    public Cell(int row, int col, int value) {

        this.rowIndex = row;
        this.columnIndex = col;
        this.value = value;
        this.locked = false;

    }

    public Cell(int row, int col, int value, boolean locked) {

        this.rowIndex = row;
        this.columnIndex = col;
        this.value = value;
        this.locked = locked;
    }
    
    // return the Cell's row index.
    public int getRow()  {
        return this.rowIndex;
       
    }   
    //return the Cell's column index.    
    public int getCol() {
        return this.columnIndex;

    } 

    //return the Cell's value.
    public int getValue() {

        return this.value;
    } 

    //set the Cell's value
    public void setValue(int newval) {

        this.value = newval;
    }
    
    //return the value of the locked field
    public boolean isLocked() {
        return this.locked;

    } 

    //set the cell as the parameter given

    public void setLocked(boolean lock) {

        this.locked = lock;
    }

    public String toString() {

        String result = "";
        result += this.value;
        
        //result += "Row = "+this.rowIndex+ ", Column = " + this.columnIndex+ ", Cell value = "+ this.value + ", Locked = " + this.locked;
        return result;
    }


    public void draw(Graphics g, int x, int y, int scale){
        g.setColor(locked? Color.BLUE : Color.RED);
        char[] out = (""+getValue()).toCharArray();
        g.drawChars(out, 0, out.length, x, y);
    }

    // public void draw(Graphics g, int x, int y, int scale){
    //     char toDraw = (char) ((int) '0' + getValue());
    //     g.setColor(isLocked()? Color.BLUE : Color.RED);
    //     g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    // }

}
