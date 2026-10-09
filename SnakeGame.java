public class SnakeGame {

    public static void main (String[] args) {

        char[][] board = makeBoard(5, 3);
        String pBoard = Interface.makePrintableBoard(board);

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

    
}