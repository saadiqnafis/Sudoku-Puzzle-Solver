
/*
File name: SudokuExtension4.java
Author: Nafis Saadiq Bhuiyan
Colby ID: 778267
Course: CS231B
Lab Section: CS231LC
Project 4 
*/

//We have changed this class and used an ArrayStack instead of a node stack

public class SudokuExtension4 {
    private ArrayStack mystack;
    private Board board;
    private LandscapeDisplay ld;

    public SudokuExtension4(int locked) {

        board = new Board(locked);
        ld = new LandscapeDisplay(board);


    }

    public boolean solve(int delay) throws InterruptedException {
        mystack = new ArrayStack();
        
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
                    return false;
                }  
            }
        }
        System.out.println("The board contains the solution");
        return true;    
    }

    public Cell findNextCell() {

        for (int i = 0; i < board.getRows(); i++) {

            for (int j = 0; j < board.getCols(); j++) {
                if (board.get(i, j).getValue() == 0){
                    for (int m = 1; m < 10; m++) {
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

    public String toString() {

        return board.toString();
    }

    /*
     * The main method here is almost the same as the main method of our Sudoku.java. We created his to compare the time required in a nodestack and an arraystack.
     */

    public static void main(String[] args) throws InterruptedException   {

        
        long startTime = System.nanoTime();

       
        SudokuExtension4 ourSudoku = new SudokuExtension4(10);
        System.out.println("The sudoku board is ");
        
        System.out.println(ourSudoku);
        
        ourSudoku.solve(0);
        
        System.out.println(ourSudoku);

        
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.println("Time required to solve the sudoku is " + elapsedTime / 1000000 + " milisecond");

    }
    
    
}
