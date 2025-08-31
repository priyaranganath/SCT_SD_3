//A simple Sudoku solver using backtracking
public class Sudoku {
    static final int SIZE = 9;

    //Checks whether it is safe to place a digit at the given position.
    public static boolean isSafeToPlace(int sudokuGrid[][],int row,int col,int digit){
        // Check column
        for(int i=0;i<SIZE;i++){
            if(sudokuGrid[i][col]==digit){
                return false;
            }
        }
        // Check row
        for(int j=0;j<SIZE;j++){
            if(sudokuGrid[row][j]==digit){
                return false;
            }
        }
        // Check 3x3 subgrid
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(sudokuGrid[i][j]==digit){
                    return false;
                }
            }
        }
        return true;
    }

    //Prints the Sudoku grid in a formatted manner.
    public static void printGrid(int sudokuGrid[][]){
        for(int i=0;i<SIZE;i++){
            if(i%3==0 && i!=0){
                System.out.println("------+------+------");
            }
            for(int j=0;j<SIZE;j++){
                if(j%3==0&& j!=0)
                    System.out.print("|");
                System.out.print(sudokuGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Recursively solves the Sudoku puzzle using backtracking.
    public static boolean solve(int sudokuGrid[][],int row,int col){
        if(row==SIZE){
            return true;
        }
        int nextRow=row,nextCol=col+1;
        if(col+1==SIZE){
            nextRow=row+1;
            nextCol=0;
        }
        if(sudokuGrid[row][col]!=0){
           return  solve(sudokuGrid,nextRow,nextCol);
        }
        for(int digit=1;digit<=SIZE;digit++){
            if(isSafeToPlace(sudokuGrid,row,col,digit)){
                sudokuGrid[row][col]=digit;
                if(solve(sudokuGrid,nextRow,nextCol)){
                    return true;
                }
            }
            sudokuGrid[row][col]=0;
        }
        return false;
    }

    //The main method to run the Sudoku solver.
    public static void main(String[] args) {
        int sudokuGrid[][]={{0, 0, 8, 0, 0, 0, 0, 0, 0},
                {4, 9, 0, 1, 5, 7, 0, 0, 2},
                {0, 0, 3, 0, 0, 4, 1, 9, 0},
                {1, 8, 5, 0, 6, 0, 0, 2, 0},
                {0, 0, 0, 0, 2, 0, 0, 6, 0},
                {9, 6, 0, 4, 0, 5, 3, 0, 0},
                {0, 3, 0, 0, 7, 2, 0, 0, 4},
                {0, 4, 9, 0, 3, 0, 0, 5, 7},
                {8, 2, 7, 0, 0, 9, 0, 1, 3}};
        if(solve(sudokuGrid,0,0)){
            System.out.println("Solution exists");
            printGrid(sudokuGrid);
        }else{
            System.out.println("Solution does not exist");
        }
    }
}
