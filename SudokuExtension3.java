
/*
File name: SudokuExtension3.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/
import java.util.HashMap;
import java.util.Random;

public class SudokuExtension3 {
    private CellStack mystack;
    private Board board;
    private LandscapeDisplay ld;

    public SudokuExtension3(int locked, int size) {

        board = new Board(locked, size);
        ld = new LandscapeDisplay(board);


    }

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
                    for (int i = popped.getValue() + 1; i <= board.getRows(); i++) {
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
                    return false;
                }  
            }
        }
        System.out.println("The board contains the solution");
        return true;    
    }

    // public Cell findNextCell() {

    //     for (int i = 0; i < board.getRows(); i++) {

    //         for (int j = 0; j < board.getCols(); j++) {
    //             if (board.get(i, j).getValue() == 0){
    //                 for (int m = 1; m < board.getCols() + 1; m++) {
    //                     if (board.validValue(i, j, m) == true) {
    //                         board.set(i, j, m);
    //                         return board.get(i, j);
                            
    //                     }
    //                 } 
    //                 return null;
    //             } 
                
    //         }
    //     }
    //     return null;
    // }


    public Cell findNextCell() {
        //HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        Random r = new Random();
        for (int i = 0; i < board.getRows(); i++) {

            for (int j = 0; j < board.getCols(); j++) {
                if (board.get(i, j).getValue() == 0){

        /**
         * Try a Hashmap, check if you ahve gone through all values from 1 to 10 of the hashmap. The reason is that trying a 
         * random value can be a better approach rather going doing brute force from 1 to 10.
         */         HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
                    //whike
                    int m = r.nextInt(board.getRows()+1);
                    while (!map.containsKey(m) && map.size() <board.getCols()) {

                        if (board.validValue(i, j, m) == true) {
                            board.set(i, j, m);
                            map.put(m, true);
                            return board.get(i, j);
                            
                        }
                        else {
                            map.put(m,false);
                            /*
                             * Try another value from 1 to 9 that previously has not been visited
                             */
                            if (map.size () < board.getCols() ) {
                                m = r.nextInt(board.getRows());
                                while (map.containsKey(m)){
                                    m = r.nextInt(board.getRows());
                                }
                                
    
                            }
                        }
                    }
                    
                    // for (int m = 1; m < 10; m++) {
                    //     if (board.validValue(i, j, m) == true) {
                    //         board.set(i, j, m);
                    //         return board.get(i, j);
                            
                    //     }
                    // } 
                    return null;
                } 
                
            }
        }
        return null;
    }


    public String toString() {

        return board.toString();
    }

    /*We have made use of the command line arguments for taking in the grid size and also the number of locked cells. We changed the Sudoku constructor so that it takes
    the grid size and the number of locked cells as the parameter.
    */

    public static void main(String[] args) throws InterruptedException   {

        
        long startTime = System.nanoTime();

        if (args.length != 2) {

            System.out.println("Please enter the size of your sudoku (e.g 1, 4, 9,...), and make sure it's a perfect square, also enter the number of locked cells, and this number should be less than the total number of cells in your sudoku grid");
            System.out.println("Enter the size and number of locked cells with just a space between them");
      
            return;
        }

        int size = Integer.parseInt(args[0]);
        int lockedCells = Integer.parseInt(args[1]);
        if (Math.sqrt(size) % 1 != 0) {
            System.out.println("Please enter a perfect square number as the grid size");
            return;
        }
        if (lockedCells >= size*size) {
            System.out.println("Number of locked cells must be less than the total number of cells");
            return;
        }


        SudokuExtension3 ourSudoku = new SudokuExtension3(lockedCells, size);
        System.out.println(ourSudoku);
        
        ourSudoku.solve(0);
        System.out.println(ourSudoku);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);

        System.out.println("Time required to solve the sudoku is " + elapsedTime / 1000000 + " milisecond");
         
       
    }
    
    
}
