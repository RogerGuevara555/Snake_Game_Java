import java.util.*;

public class SnakeGame {

    public static void main (String[] args) {

        char[][] board = makeBoard(5, 3);
        String pBoard = makePrintableBoard(board);

        System.out.println(pBoard);
        
    }

    static final String CEIL_ICON = "=";
    static final String WALL = "|";
    static final String CORNER = ":";
    static final String SNAKE_HEAD = "O";
    static final String SNAKE_BODY = "o";
    static final String FOOD = "à";

    
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
        String ceil = CORNER + CEIL_ICON.repeat(board[0].length) + CORNER + "\n";
        
        for (char[] row : board) {
            String rowString = new String(row);
            prePrintableBoard.add(rowString);
        } 
        printableBoard += ceil;
        for (String row : prePrintableBoard) {printableBoard += WALL + row + WALL + "\n";}
        printableBoard += ceil;

        return "\n" + printableBoard;
    }
}