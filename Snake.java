import java.util.ArrayList;
import java.util.List;

public class Snake {
    
    ArrayList<int[]> snakeCoords = new ArrayList<>(
        List.of(   //{X,Y},
            new int[]{0,0},
            new int[]{1,0},
            new int[]{2,0},
            new int[]{3,0}
        )
    );
    int snakeSize;
    char front;
    int[] frontCoord;
    int[] headCoord;
    int[] tailCoord;
    int[] direction = {-1,0};  //left
    //int[] direction = {1,0};   //right
    //int[] direction = {0,1};   //up
    //int[] direction = {0,-1};  //down

    boolean alive = true;

    public Snake (char[][] board) {
        centerSnakeCoord(board);
    }


    void centerSnakeCoord (char[][] board) {
        for (int[] coord : snakeCoords){
            coord[0] += board[0].length/2;
            coord[1] += board.length/2;
        }
    }


    public void walk () {
        updateStates();
        snakeCoords.add(0, frontCoord);
        snakeCoords.remove(snakeSize);
    }
    
    
    public void grow () {
        updateStates();
        snakeCoords.add(0, frontCoord);
    }


    public void die () {
        alive = false;
    }


    void updateStates () {
        //direction = getIntput();
        snakeSize = snakeCoords.size();
        headCoord = snakeCoords.get(0);
        tailCoord = snakeCoords.get(snakeSize-1);
        frontCoord = sum_vector(headCoord, direction);
        //front = getBoardSlot(frontCoord);
    }


    void getInput () {

    }


    int[] sum_vector (int[] v1, int[] v2) {
        int v3_x = v1[0] + v2[0];
        int v3_y = v1[1] + v2[1];
        int[] v3 = {v3_x, v3_y};
        return v3;
    }
}
