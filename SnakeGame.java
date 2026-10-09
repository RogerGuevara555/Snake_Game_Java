import java.util.*;

public class SnakeGame {

    public static void main (String[] args) {

        char[][] board = makeBoard(10, 5);
        String pBoard = makePrintableBoard(board);

        System.out.println(pBoard);
        
    }


    static char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {
            for (int slot = 0; slot < x; slot++) {
                newBoard[row][slot] = '-';
            }
        }
        
        return newBoard;
    }

    
    static String makePrintableBoard (char[][] board) {
        String printableBoard = "";
        List<String> prePrintableBoard = new ArrayList<>();
        
        for (char[] row : board) {
            String rowString = new String(row);
            prePrintableBoard.add(rowString + "\n");
        } 
        for (String row : prePrintableBoard) {
            printableBoard = printableBoard.concat(row);
        }

        return printableBoard;
    }
}