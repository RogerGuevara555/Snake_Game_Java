import java.util.List;
import java.util.ArrayList;

public class SnakeGame {
    public static void main(String[] args) { 

        board = makeBoard(50, 20);
        centerSnakeCoord();
        
        gameLoop();
        
        System.out.println();
    }


    static final char CEIL_ICON = '=';
    static final char WALL = '|';
    static final char CORNER = ':';
    static final char SNAKE_HEAD = 'O';
    static final char SNAKE_BODY = 'o';
    static final char FOOD = 'à';
    static final char SPACE = '-';

    
    static char[][] board;
    static ArrayList<int[]> snakeCoords = new ArrayList<>(
        List.of(   //{X,Y},
            new int[]{0,0},
            new int[]{1,0},
            new int[]{2,0},
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

            if (front == SPACE) {
                walk(false);
            } else if (front == FOOD) {
                walk(true);
            } else if (front == SNAKE_BODY || front == WALL) {
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
        displayHead();
        Interface.displayBoard(board);
    }


    static void updateStates () {
        //direction = getIntput();
        snakeSize = snakeCoords.size();
        headCoord = snakeCoords.get(0);
        tailCoord = snakeCoords.get(snakeSize-1);
        frontCoord = sum_vec(headCoord, direction);
        front = getBoardSlot(frontCoord);
    }


    static void getInput () {

    }


    static void walk (boolean grow) {
        snakeCoords.add(0, frontCoord);
        if (!grow) {
            snakeCoords.remove(snakeSize);
            setBoardSlot(tailCoord, SPACE);
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
            return WALL;
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
            for (int slot = 0; slot < x; slot++) {
                newBoard[row][slot] = SPACE;
            }
        }
        
        return newBoard;
    }

    
    static void centerSnakeCoord () {
        for (int[] coord : snakeCoords){
            coord[0] += board[0].length/2;
            coord[1] += board.length/2;
        }
    }


    static void displaySnake () {
        for (int[] coord : snakeCoords){
            setBoardSlot(coord, SNAKE_BODY);
        }
    }


    static void displayHead () {
        int[] neckCoord = snakeCoords.get(1);
        setBoardSlot(headCoord, SNAKE_HEAD);
        setBoardSlot(neckCoord, SNAKE_BODY);
    }

}

