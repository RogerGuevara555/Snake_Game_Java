import java.util.ArrayList;
import java.util.List;

public class Interface {
    
    static final char CEIL_ICON = '=';
    static final char WALL = '|';
    static final char CORNER = ':';
    static final char SNAKE_HEAD = 'O';
    static final char SNAKE_BODY = 'o';
    static final char FOOD = 'à';
    static final char SPACE = '-';


    public static void displayBoard (char[][] board) {
        String pBoard = makePrintableBoard(board);
        System.out.println(pBoard);
    }

    static String makePrintableBoard (char[][] board) {
        String printableBoard = "";
        List<String> prePrintableBoard = new ArrayList<>();
        String ceilIconString = String.valueOf(CEIL_ICON);
        String cornerString = String.valueOf(CORNER);

        String ceil = cornerString + ceilIconString.repeat(board[0].length) + cornerString + "\n";
        
        for (char[] row : board) {
            String rowString = new String(row);
            prePrintableBoard.add(rowString);
        } 
        printableBoard += ceil;
        for (String row : prePrintableBoard) {printableBoard += WALL + row + WALL + "\n";}
        printableBoard += ceil;

        return "\n" + printableBoard;
    }


    public static void printGameOver () {
        String gameOver = """
                 _____                        _____                
                |  __ \\                      |  _  |               
                | |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __ 
                | | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|
                | |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |   
                 \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   
                """;;
        System.out.println(gameOver);
    }

}
