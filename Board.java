public class Board {
    
    private char[][] board;
    final int X;
    final int Y;

    public Board (int x, int y) {
        this.X = x;
        this.Y = y;
        this.board = makeBoard(x, y);
    }
    

    public char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {
            for (int slot = 0; slot < x; slot++) {
                newBoard[row][slot] = Slot.VOID.getRepr();
            }
        }
        return newBoard;
    }


    public void putSnakeInBoard (Snake snake) {     //( Board <- Snake )
        for (int[] coord : snake.getCoords()){
            setBoardSlot(coord, Slot.SNAKE.getRepr());
        }
    }

    void setBoardSlot (int[] coord, char icon) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        board[coord_Y][coord_X] = icon;
    }
    char getBoardSlot (int[] coord) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        boolean inRangeX = coord_X>=0 && coord_X<X;
        boolean inRangeY = coord_Y>=0 && coord_Y<Y;

        if (inRangeX && inRangeY)
            return board[coord_Y][coord_X];
        else
            return Slot.WALL.getRepr();
    }


    public char[][] getBoard () {return board;} 
    public void reset () {this.board = makeBoard(X, Y);}
}
