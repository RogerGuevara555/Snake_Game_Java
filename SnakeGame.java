import java.util.List;
import java.util.ArrayList;

public class SnakeGame {
    
    public static void main(String[] args) { 

        board = makeBoard(50, 20);
        centerSnakeCoord();
        
        gameLoop();
        
        System.out.println();
    }


    // static final char CEIL_ICON = '=';
    // static final char WALL = '|';
    // static final char CORNER = ':';
    static final char SNAKE_HEAD = 'O';     // Refactorizar en clase Slot que reciba un char y diga si 
    // static final char SNAKE_BODY = 'o';     //es comida, cuerpo o vacío, o que tenga métodos para comprobar
    // static final char FOOD = 'à';           //si un slot es alguno de esos (mejor la segunda opción)
    // static final char SPACE = '-';

    
    static char[][] board;
    static ArrayList<int[]> snakeCoords = new ArrayList<>(
        List.of(   //{X,Y},
            new int[]{0,0},
            new int[]{1,0},
            new int[]{2,0},     // Refactorizar en clase Snake
            new int[]{3,0}
        )
    );
    static int snakeSize;
    static char front;
    static int[] frontCoord;
    static int[] headCoord;
    static int[] tailCoord;
    static int[] direction = {-1,0};  //left
  //static int[] direction = {1,0};   //right
  //static int[] direction = {0,1};   //up
  //static int[] direction = {0,-1};  //down

    
    
    static void gameLoop () {
        while (true) {
            updateBoard();

            if ( SpaceSlot.isType(front) ) {  //en plan    Slot.isSpace(front)
                walk(false);
            } else if ( AppleSlot.isType(front) ) {
                walk(true);
            } else if ( SnakeSlot.isType(front) || WallSlot.isType(front) ) {
                Interface.printGameOver();
                break;
            }

            waitCoolDown();
        }
    }
    

    static void updateBoard () {
        cleanTerminal();
        updateStates();
        displaySnake();         
        displayHead(); // <-- Eso debería ser cosa de la interfaz. Sería mejor procesar 
        Interface.displayBoard(board);// el board como un char[][] con solo números y espacios o algo así
    }


    static void updateStates () {
        //direction = getIntput();
        snakeSize = snakeCoords.size();
        headCoord = snakeCoords.get(0);
        tailCoord = snakeCoords.get(snakeSize-1);   // estas variables deberían crearse y actualizarse solo en donde sean necesarias
        frontCoord = sum_vec(headCoord, direction);
        front = getBoardSlot(frontCoord);
    }


    static void getInput () {

    }


    static void walk (boolean grow) {
        snakeCoords.add(0, frontCoord);
        if (!grow) {
            snakeCoords.remove(snakeSize);
            setBoardSlot(tailCoord, SpaceSlot.getRepr());
        }
    }


    static int[] sum_vec (int[] v1, int[] v2) {
        int v3_x = v1[0] + v2[0];
        int v3_y = v1[1] + v2[1];
        int[] v3 = {v3_x, v3_y};
        return v3;
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
            return WallSlot.getRepr();
    }


    static void waitCoolDown () {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    static void cleanTerminal () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    static char[][] makeBoard (int x, int y) {
        char[][] newBoard = new char[y][x];

        for (int row = 0; row < y; row++) {
            for (int slot = 0; slot < x; slot++) {      // Quizas en cada iteración debería llamarse a 
                newBoard[row][slot] = SpaceSlot.getRepr();            //esta función para tener un tablero limpio cada vez 
            }
        }
        
        return newBoard;
    }

    
    static void centerSnakeCoord () {
        for (int[] coord : snakeCoords){
            coord[0] += board[0].length/2;          // ya veré donde pongo mejor esto
            coord[1] += board.length/2;
        }
    }


    static void displaySnake () {
        for (int[] coord : snakeCoords){
            setBoardSlot(coord, SnakeSlot.getRepr());
        }
    }


    static void displayHead () {
        int[] neckCoord = snakeCoords.get(1);   // Probablemente meta esto en la interfaz
        setBoardSlot(headCoord, SNAKE_HEAD);
        setBoardSlot(neckCoord, SnakeSlot.getRepr());
    }

}


enum Slot {
    VoidSlot('-'),
    SnakeSlot('1'),
    AppleSlot('2'),
    WallSlot('3');
    
    private final char repr;

    Slot (char icon) {
        this.repr = icon;
    }

    static char getRepr () {return repr;}
    
    static boolean isType (Slot slot, char icon) {
        return slot.repr == getRepr();
    }
}



