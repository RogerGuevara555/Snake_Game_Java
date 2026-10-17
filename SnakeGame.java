import java.util.List;

public class SnakeGame {    // dejar esta clase como main
    
    public static void main(String[] args) { 

        board = makeBoard(50, 20);
        Snake snake = new Snake(board);        
        gameLoop(snake);
        
        System.out.println();
    }

    
    static char[][] board;      // Refactorizar en una clase board
    
    
    static void gameLoop (Snake snake) {
        while (true) {
            updateBoard(snake);
            char front = getBoardSlot(snake.getFrontCoord());

            if ( Slot.VOID.isThis(front) ) {
                snake.walk(false);
            } else if ( Slot.APPLE.isThis(front) ) {
                snake.walk(true);
            } else if ( Slot.SNAKE.isThis(front) || Slot.WALL.isThis(front) ) {
                Restile.printGameOver();
                break;
            }

            waitCoolDown();
        }
    }
    

    static void updateBoard (Snake snake) {
        cleanTerminal();
        board = makeBoard(50, 20);
        displaySnake(snake);         
        displayHead(snake); // <-- Eso debería ser cosa del restile (no la interfaz ni el board). Sería mejor procesar 
        Restile.displayBoard(board);// el board como un char[][] con solo números y espacios o algo así
    }
    
    
    static void setBoardSlot (int[] coord, char icon) {
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
    }


    static void waitCoolDown () {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    static void cleanTerminal () {
        System.out.print("\033[H\033[2J");  //esto debería ser de la interafaz
        System.out.flush();
    }


    public static char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {
            for (int slot = 0; slot < x; slot++) {              // Quizas en cada iteración debería llamarse a 
                newBoard[row][slot] = Slot.VOID.getRepr();            //esta función para tener un tablero limpio cada vez 
            }
        }
        
        return newBoard;
    }


    static void displaySnake (Snake snake) {
        for (int[] coord : snake.getCoords()){              //esto debería recibir el tablero y devolver uno nuevo con la serpiente dentro
            setBoardSlot(coord, Slot.SNAKE.getRepr());//y debería estar dentro de una clase serpiente
        }
    }


    static final char SNAKE_HEAD = 'O';     
    static void displayHead (Snake snake) {
        List<int[]> snakeCoords = snake.getCoords();
        int[] headCoord = snakeCoords.getFirst();   // Probablemente meta esto en la clase estilo
        int[] neckCoord = snakeCoords.get(1);   // Probablemente meta esto en la clase estilo
        setBoardSlot(headCoord, SNAKE_HEAD);
        setBoardSlot(neckCoord, Slot.SNAKE.getRepr());
    }

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



