/*
File name: Sudoku.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/
import java.util.HashMap;
import java.util.Random;

public class Sudoku {
    private CellStack mystack;
    private Board board;
    private LandscapeDisplay ld;

    //This constructor creates a new Board object using the parameter of locked cells, and also a landscape display using the parameter of board.

    public Sudoku(int locked) {

        board = new Board(locked);
        ld = new LandscapeDisplay(board);

    }

    //This method uses a stack to keep track of the solution and allow backtracking when it gets stuck. It takes a parameter delay which helps us visualize the board while the board is being solved.

    public boolean solve(int delay) throws InterruptedException {
        mystack = new CellStack();
        
        while (mystack.size() < board.getRows() * board.getCols() - board.numLocked()) {
            
            if (delay > 0)
            Thread.sleep(delay);
            if (ld != null)
                ld.repaint();
            Cell nextCell = this.findNextCell();
            if (nextCell != null) {
                mystack.push(nextCell);
                board.set(nextCell.getRow(), nextCell.getCol(), nextCell.getValue());
            }
            else {

                while (!mystack.empty()) {
                   
                    Cell popped = mystack.pop();
                    
                    boolean stuck = true;
                    for (int i = popped.getValue() + 1; i <= 9; i++) {
                        if (board.validValue(popped.getRow(), popped.getCol(), i)) {
                            popped.setValue(i);
                            mystack.push(popped);
                            board.set(popped.getRow(), popped.getCol(), popped.getValue());
                            stuck = false;
                            break;
                        }
                        
                    }
                    if (stuck){
                        popped.setValue(0);
                    } 
                    else {
                        break;
                    }
                    
                }
                if (mystack.empty()) {
                    System.out.println("There's no solution");
                    board.finished = true;
                    return false;
                }  
            }
        }
        System.out.println("The board contains the solution");
        board.finished = true;
        return true;    
    }

    // This method returns the Cell that satisfies the rules of the game. If there's no available cells to return, it returns null.

 public Cell findNextCell() {

        for (int i = 0; i < board.getRows(); i++) {

            for (int j = 0; j < board.getCols(); j++) {
                if (board.get(i, j).getValue() == 0){
                    for (int m = 1; m < board.getCols() + 1; m++) {
                        if (board.validValue(i, j, m) == true) {
                            board.set(i, j, m);
                            return board.get(i, j);
                            
                        }
                    } 
                    return null;
                } 
                
            }
        }
        return null;
    }



    // public Cell findNextCell() {
    //     //HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    //     Random r = new Random();
    //     /*
    //      *  Get a random coordinate and try that
    //      * 
    //      */
    //    // HashMap<<Integer,Integer>, Boolean> map = new HashMap<Integer, Boolean>();



    //     for (int i = 0; i < board.getRows(); i++) {

    //         for (int j = 0; j < board.getCols(); j++) {
    //             if (board.get(i, j).getValue() == 0){

    //     /**
    //      * Try a Hashmap, check if you ahve gone through all values from 1 to 10 of the hashmap. The reason is that trying a 
    //      * random value can be a better approach rather going doing brute force from 1 to 10.
    //      */         HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    //                 //whike
    //                 int m = r.nextInt(board.getRows()+1);
    //                 while (!map.containsKey(m) && map.size() <board.getCols()) {

    //                     if (board.validValue(i, j, m) == true) {
    //                         board.set(i, j, m);
    //                         map.put(m, true);
    //                         return board.get(i, j);
                            
    //                     }
    //                     else {
    //                         map.put(m,false);
    //                         /*
    //                          * Try another value from 1 to 9 that previously has not been visited
    //                          */
    //                         if (map.size () < board.getCols() ) {
    //                             m = r.nextInt(board.getRows());
    //                             while (map.containsKey(m)){
    //                                 m = r.nextInt(board.getRows());
    //                             }
                                
    
    //                         }
    //                     }
    //                 }
                    
    //                 // for (int m = 1; m < 10; m++) {
    //                 //     if (board.validValue(i, j, m) == true) {
    //                 //         board.set(i, j, m);
    //                 //         return board.get(i, j);
                            
    //                 //     }
    //                 // } 
    //                 return null;
    //             } 
                
    //         }
    //     }
    //     return null;
    // }

    //Creates a string representation for the sudoku

    public String toString() {

        return board.toString();
    }


    /*
     We keep track of the time through which our program runs, and run the sudoku by changing the number of locked cells. We also change the delay from this method.
     */
    public static void main(String[] args) throws InterruptedException   {
        
        long startTime = System.nanoTime();

    
        Sudoku ourSudoku = new Sudoku(10);
        System.out.println("The sudoku board is ");
        
        System.out.println(ourSudoku);
        
        ourSudoku.solve(2);
        
        System.out.println(ourSudoku);

    
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.println("Time required to solve the sudoku is " + elapsedTime / 1000000 + " milisecond");
        
        
    }
    
    
}
