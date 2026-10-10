import java.util.ArrayList;
import java.util.List;

public class Board {
    
    final String CEIL_ICON = "=";
    final String WALL = "|";
    final String CORNER = ":";
    final String SNAKE_HEAD = "O";
    final String SNAKE_BODY = "o";
    final String FOOD = "à";

    char[][] board;

    public Board (int x, int y) {
        this.board = makeBoard(x, y);
    }

    
    char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {
            for (int slot = 0; slot < x; slot++) {
                newBoard[row][slot] = '-';
            }
        }
        
        return newBoard;
    }


    public String makePrintableBoard (char[][] board) {
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


    
    //public char[][] displaySnake (char[][] board) {return}

    
    //public char[][] updateSnakePosition (char[][] board) {return}


    void setBoardSlot (int[] coord, char icon) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        board[coord_Y][coord_X] = icon;
    }
    char getBoardSlot (int[] coord) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        return board[coord_Y][coord_X];
    }
}
