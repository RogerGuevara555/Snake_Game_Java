import java.util.List;
import java.util.ArrayList;

public class SnakeGameSimple {
    public static void main(String[] args) { 
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Board board = new Board(20, 10);
        board.centerSnakeCoord();
        board.displaySnake();
        board.displayBoard();

        board.walk();
        
        board.displaySnake();
        board.displayBoard();
        
        System.out.println();
    }
}


class Board {

    final char CEIL_ICON = '=';
    final char WALL = '|';
    final char CORNER = ':';
    final char SNAKE_HEAD = 'O';
    final char SNAKE_BODY = 'o';
    final char FOOD = 'à';

    final int X;
    final int Y;
    
    char[][] board;
    ArrayList<int[]> snakeCoords = new ArrayList<>(
        List.of(   //{X,Y},
            new int[]{0,0},
            new int[]{1,0},
            new int[]{2,0},
            new int[]{3,0}
        )
    );
    int snakeSize = snakeCoords.size();


    public Board (int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.board = createBoard();
    }



    char[][] createBoard () {
        int yWithBorders = Y + 2;
        int xWithBorders = X + 2;
        char[][] new_board = new char[yWithBorders][xWithBorders];

        for (int row = 1; row < yWithBorders-1; row++) {
            for (int item = 0; item < xWithBorders; item++) {
                new_board[row][item] = ' ';
            //Arrays.fill(row, ' '); 
            }
        }

        for (int i = 0; i < xWithBorders; i++) {
            new_board[0][i] = CEIL_ICON;
            new_board[yWithBorders - 1][i] = CEIL_ICON;
        }
        for (int i = 1; i < yWithBorders - 1; i++) {
            new_board[i][0] = WALL;
            new_board[i][xWithBorders - 1] = WALL;
        }
        new_board[0][0] = CORNER;
        new_board[yWithBorders - 1][0] = CORNER;
        new_board[0][xWithBorders - 1] = CORNER;
        new_board[yWithBorders - 1][xWithBorders - 1] = CORNER;
        return new_board;
    }



    public void displayBoard () {
        for (char[] row : board) {
            System.out.println(row);
        }
    }


    public void displaySnake () {
        for (int[] coord : snakeCoords){
            setBoardSlot(coord, SNAKE_BODY);
        }
        //setBoardSlot(snakeCoords.get(0), SNAKE_HEAD);
    }


    public void centerSnakeCoord () {
        for (int[] coord : snakeCoords){
            coord[0] += X/2;
            coord[1] += Y/2;
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
        return board[coord_Y][coord_X];
    }


    int[] direction = {-1,0};  //left
  //int[] direction = {1,0};   //right
  //int[] direction = {0,1};   //up
  //int[] direction = {0,-1};  //down

    public void walk () {
        grow();
        snakeCoords.remove(snakeSize-1);
    }
    
    
    public void grow () {
        int[] headPosition = snakeCoords.get(0);
        int[] newHeadPosition = sum_vec(headPosition, direction);
        snakeCoords.add(0, newHeadPosition);
    }


    int[] sum_vec (int[] v1, int[] v2) {
        int v3_x = v1[0] + v2[0];
        int v3_y = v1[1] + v2[1];
        int[] v3 = {v3_x, v3_y};
        return v3;
    }




    public void gameLoop () {
        while (true) {
            getInput();
            char front = getFront();

            if (front == ' ') {
                walk();
            } else if (front == FOOD) {
                grow();
            } else if (front == SNAKE_BODY || front == WALL) {
                printGameOver();
                break;
            }

            reloadBoard();
        }
    }



    void getInput () {

    }
    
    
    char getFront () {
        int[] headPosition = snakeCoords.get(0);
        int[] frontCoord = sum_vec(headPosition, direction);
        char front = getBoardSlot(frontCoord);
        return front;
    }


    void printGameOver () {

    }


    void reloadBoard () {
        
    }


}

