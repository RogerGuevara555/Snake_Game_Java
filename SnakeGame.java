public class SnakeGame {    // dejar esta clase como main
    
    public static void main(String[] args) { 

        gameLoop(snake);
        System.out.println();
    }
    
    
    static Board board = new Board(20, 10);
    static Snake snake = new Snake(board.board);        
    //static char[][] board;      // Refactorizar en una clase board
    
    
    static void gameLoop () {
        while (snake.isAlive()) {
            updateBoard();
            
            int[] frontCoord = snake.getFrontCoord();
            char front = board.getBoardSlot(frontCoord);
            if      ( Slot.VOID.isThis (front) ) {snake.walk(false);}
            else if ( Slot.APPLE.isThis(front) ) {snake.walk( true);} 
            else if ( Slot.SNAKE.isThis(front) || Slot.WALL.isThis(front) ) {snake.die();}
            
            waitCoolDown();
        }
        Interface.printGameOver();
    }
    

    static void updateBoard () {
        Interface.cleanTerminal();

        board = makeBoard(20, 10);      //board.reset();
        displaySnake(snake);   // que sea board.putSnakeInBoard(snake);
        
        String[][] decoratedBoard = Restile.decorateBoard(board.board, snake);
        String printableBoard = Restile.makeStringBoard(decoratedBoard);
        
        System.out.println(printableBoard);
    }


    static void waitCoolDown () {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    
    /*static void setBoardSlot (int[] coord, char icon) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        board[coord_Y][coord_X] = icon;
    }
    static char getBoardSlot (int[] coord) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        boolean inRangeX = coord_X>=0 && coord_X<board[0].length;
        boolean inRangeY = coord_Y>=0 && coord_Y<board.length;

        if (inRangeX && inRangeY)
            return board[coord_Y][coord_X];
        else
            return Slot.WALL.getRepr();
    } */


    /*public static char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {                      //    LISTO!!
            for (int slot = 0; slot < x; slot++) {              // Quizas en cada iteración debería llamarse a 
                newBoard[row][slot] = Slot.VOID.getRepr();     //esta función para tener un tablero limpio cada vez 
            }
        }
        return newBoard;
    } */


    /*static void displaySnake (Snake snake) {                 //      Que se llame putSnakeInBoard() y que siga siendo static
        for (int[] coord : snake.getCoords()){              //Esto debería recibir un board y un snake, y retornar un board con snake
            setBoardSlot(coord, Slot.SNAKE.getRepr());     //    ( Board + Snake = BoardWithSnake )
        }
    } */
}


enum Slot {
    VOID('-'),
    SNAKE('o'),
    APPLE('2'),
    WALL('3');
    
    private final char repr;

    Slot (char icon) {
        this.repr = icon;
    }

    public char getRepr () {
        return this.repr;
    }
    
    public boolean isThis (char icon) {
        return icon == getRepr();
    }
}


class Interface {
    
    static void cleanTerminal () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
