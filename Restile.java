import java.util.ArrayList;
import java.util.List;

public class Restile {
    
    static final String CEIL_ICON = "=";
    static final String WALL = "|";
    static final String CORNER = ":";
    static final String SNAKE_HEAD = "O";
    static final String SNAKE_BODY = "o";
    static final String FOOD = "à";
    static final String SPACE = "-";


    public static void displayBoard (char[][] board, Snake snake) {
        int[] headCoord = snake.getCoords().getFirst();
        String[][] sBoard = changeMatrixType(board);
        String[][] shBoard = displayHead(sBoard, headCoord);
        String pBoard = makePrintableBoard(shBoard);
        System.out.println(pBoard);
    }

    
    
    static String[][] displayHead (String[][] board, int[] headCoord) {
        String[][] newBoard = board;
        newBoard[headCoord[1]][headCoord[0]] = SNAKE_HEAD;
        return newBoard;
    }
    

    static String[][] changeMatrixType (char[][] board) {
        String[][] newStringBoard = new String[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                newStringBoard[i][j] = String.valueOf(board[i][j]);
            }
        }
        return newStringBoard;
    }
    
    
    static String makePrintableBoard (String[][] board) {
        String printableBoard = "";
        List<String> prePrintableBoard = new ArrayList<>();
    
        String ceil = CORNER + CEIL_ICON.repeat(board[0].length) + CEIL_ICON + "\n";
        
        for (String[] row : board) {
            String rowString = String.join("", row);
            prePrintableBoard.add(rowString);
        } 
        printableBoard += ceil;
        for (String row : prePrintableBoard) {printableBoard += WALL + row + WALL + "\n";}
        printableBoard += ceil;
    
        return "\n" + printableBoard;
    }
}
