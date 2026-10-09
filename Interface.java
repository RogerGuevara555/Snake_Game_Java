import java.util.ArrayList;
import java.util.List;

public class Interface {
    
    static final String CEIL_ICON = "=";
    static final String WALL = "|";
    static final String CORNER = ":";
    static final String SNAKE_HEAD = "O";
    static final String SNAKE_BODY = "o";
    static final String FOOD = "à";

    
    public static String makePrintableBoard (char[][] board) {
        String printableBoard = "";
        String ceil = CORNER + CEIL_ICON.repeat(board[0].length) + CORNER + "\n";
        List<String> prePrintableBoard = new ArrayList<>();
        
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
