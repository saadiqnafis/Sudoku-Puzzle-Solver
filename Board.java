/*
File name: Board.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/


import java.io.*;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Board {

  private Cell[][] sudoku;
  public boolean finished;
  
  public static final int SIZE = 9;

  //We create a Board constructor by assigning all the cells with a value of zero.

  public Board() {

    sudoku = new Cell[Board.SIZE][Board.SIZE];
    for (int i = 0; i < Board.SIZE; i++) {

      for (int j = 0; j < Board.SIZE; j++) {
        sudoku[i][j] = new Cell(i, j, 0);

      }
    }
  }

  /*We write another Board constructor that takes the number of lockedcells as the parameter. First we call the Board() constructor by this().
   * Then we go randomly over the board and generate some random values for some random cells
  and set the cells as locked, so that it isn't changed further while solving the board.
   */

  public Board(int lockedCells) {
    this();

    int num = 0;
    Random r = new Random();
    while (num < lockedCells) {

      int rowrandom = r.nextInt(0, SIZE);
      int colrandom = r.nextInt(0, SIZE);
      int valrandom = r.nextInt(1, SIZE + 1);
      if (this.validValue(rowrandom, colrandom, valrandom) && sudoku[rowrandom][colrandom].isLocked() != true) {
        
        sudoku[rowrandom][colrandom].setValue(valrandom);
        sudoku[rowrandom][colrandom].setLocked(true);
        num++;

      }
    }
  }

  /*We create another Board constrcutor taking the number of locked cells and the grid size as the parameters of this constrcutor.
  Here, after creating a new array, we first assign the cells with a value of zero. Then we go randomly over the board and generate some random values for some random cells
  and set the cells as locked, so that it isn't changed further while solving the board.
   */ 

  public Board(int lockedCells, int size) {
    sudoku = new Cell[size][size];
    for (int i = 0; i < size; i++) {

      for (int j = 0; j < size; j++) {
        sudoku[i][j] = new Cell(i, j, 0);
    
      }

    }

    int num = 0;
    Random r = new Random();
    while (num < lockedCells) {

      int rowrandom = r.nextInt(0, size);
      int colrandom = r.nextInt(0, size);
      int valrandom = r.nextInt(1, size + 1);
      if (this.validValue(rowrandom, colrandom, valrandom) && sudoku[rowrandom][colrandom].isLocked() != true) {
        // sudoku[rowrandom][colrandom] = new Cell(rowrandom, colrandom, valrandom,
        // true);
        sudoku[rowrandom][colrandom].setValue(valrandom);
        sudoku[rowrandom][colrandom].setLocked(true);
        num++;

      }
    }
  }

 //returns the number of columns

  public int getCols() {

    return sudoku.length;
  }

  //returns the number of rows

  public int getRows() {

    return sudoku.length;
  }

  //returns the Cell at location r, c.

  public Cell get(int r, int c) {

    return sudoku[r][c];
  }

  //returns whether the Cell at r, c, is locked.

  public boolean isLocked(int r, int c) {

    return sudoku[r][c].isLocked();
  }

  //returns the number of locked Cells on the board.

  public int numLocked() {

    int num = 0;
    for (int i = 0; i < sudoku.length; i++) {

      for (int j = 0; j < sudoku.length; j++) {
        if (sudoku[i][j].isLocked()) {

          num++;
        }

      }
    }
    return num;
  }

  //returns the value at Cell r, c

  public int value(int r, int c) {

    return sudoku[r][c].getValue();
  }

  //sets the value of the Cell at r, c

  public void set(int r, int c, int value) {
    sudoku[r][c].setValue(value);

  }

  //sets the value and locked fields of the Cell at r, c

  public void set(int r, int c, int value, boolean locked) {

    sudoku[r][c].setValue(value);
    sudoku[r][c].setLocked(locked);

  }

  //Shows the board


  public String toString() {
    String result = "";
    double d = Math.sqrt(sudoku.length);
    int c = (int)d;
    for (int i = 0; i < this.sudoku.length; i++) {

      for (int j = 0; j < this.sudoku[i].length; j++) {
        

          result += sudoku[i][j].toString() + " ";
        
        
        
        if (j == c - 1 || j == c - 1 + c || j == c - 1 + c + c || j == 4*c - 1 || j == 5*c - 1 ) {
          
            result += "  ";
          
        }
      }
      if (i == c - 1 || i == c - 1 + c || i == c - 1 + c + c || i == 4*c - 1 || i == 5*c - 1) {
        result += "\n";
        result += "\n";
      } else {
        result += "\n";
      }
    }

    return result;
  }

  /*tests if the given value is a valid value at the given row and column of the board. It makes sure the value is unique in its row, in its column, and in its local 3x3 square. 
  We can figure out which local 3x3 square it is in by using integer division. 
   */

  public boolean validValue(int row, int col, int value) {
    if (value > sudoku.length || value < 1) {
      return false;
    }

    for (int i = 0; i < sudoku.length; i++) {
      if (i == row) {
        continue;
      }
      if (value == sudoku[i][col].getValue()) {
        return false;
      }
    }
    for (int j = 0; j < sudoku.length; j++) {

      if (j == col) {
        continue;
      }
      if (value == sudoku[row][j].getValue()) {
        return false;
      }
    }

    double d = Math.sqrt(sudoku.length);
    int c = (int)d;
    for (int i = row / c * c; i < row / c * c + c; i++) {

      for (int j = col / c * c; j < col / c * c + c; j++) {
        
        if (i == row && j == col) {
          continue;
        }
        if (value == sudoku[i][j].getValue()) {
          return false;

        }
      }
    }
    return true;

  }

  public boolean validSolution() {

    for (int i = 0; i < sudoku.length; i++) {

      for (int j = 0; j < sudoku.length; j++) {

        if (this.validValue(i, j, sudoku[i][j].getValue()) == false || sudoku[i][j].getValue() == 0) {

          return false;
        }
      }
    }
    return true;
  }

  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing
      // filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the
      // FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine
      // method of your BufferedReader object.

      String line = br.readLine();
      int row = 0;

      // start a while loop that loops while line isn't null

      while (line != null) {

        // assign to an array of type String the result of calling split on the line
        // with the argument "[ ]+"

        String[] strarr = line.split("[ ]+");

        for (int i = 0; i < 9; i++) {
          this.sudoku[row][i].setValue(Integer.parseInt(strarr[i]));
        }

        row++;
        line = br.readLine();

      }
      // call the close method of the BufferedReader
      br.close();
      return true;
    } catch (FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename);
    } catch (IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  public void draw(Graphics g, int scale){
    for(int i = 0; i< sudoku.length; i++){
        for(int j = 0; j< sudoku.length; j++){
            sudoku[i][j].draw(g, j*scale+5, i*scale+10, scale);
        }
    } if(finished){
        if(validSolution()){
            g.setColor(new Color(0, 127, 0));
            g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
        } else {
            g.setColor(new Color(127, 0, 0));
            g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
        }
    }
}

  public static void main(String[] args) {

    Board board = new Board();
    
    if (args.length != 1) {

      System.out.println("Please enter the filename");

      return;
    }

    String str = (args[0]);
    board.read(str);

    if (board.validSolution()) {
      System.out.println("Solved");
    } else {
      System.out.println("Not solved");
    }
  }

}
