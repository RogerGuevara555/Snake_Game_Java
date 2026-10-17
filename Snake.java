import java.util.ArrayList;
import java.util.List;

public class Snake {
    
    List<int[]> snakeCoords;
    int[] direction;
    boolean alive=true;


    public Snake () {
        List<int[]> newSnakeCoords = new ArrayList<int[]>();
        newSnakeCoords.add(new int[]{ 0,0});
        newSnakeCoords.add(new int[]{-1,0});
        newSnakeCoords.add(new int[]{-2,0});
        newSnakeCoords.add(new int[]{-3,0});

        this.snakeCoords = newSnakeCoords;
        this.direction = new int[]{-1,0};
    }
    public Snake (int[][] board) {
        List<int[]> newSnakeCoords = new ArrayList<int[]>();
        newSnakeCoords.add(new int[]{ 0,0});
        newSnakeCoords.add(new int[]{-1,0});
        newSnakeCoords.add(new int[]{-2,0});
        newSnakeCoords.add(new int[]{-3,0});

        this.snakeCoords = newSnakeCoords;
        this.snakeCoords = centerSnakeCoord(board);
        this.direction = new int[]{-1,0};
    }
    
    List<int[]> centerSnakeCoord (int[][] board) {
        List<int[]> newSnakeCoords = snakeCoords;
        for (int[] coord : newSnakeCoords){
            coord[0] += board[0].length/2;          // ya veré donde pongo mejor esto
            coord[1] += board.length/2;
        }
        return newSnakeCoords;
    }


    public void walk (boolean grow) {
        int snakeSize = snakeCoords.size();
        int[] frontCoord = getFrontCoord();
        snakeCoords.addFirst(frontCoord);
        if (!grow) {
            snakeCoords.remove(snakeSize); //* removing the tail
        }
    }

    int[] getFrontCoord () {
        int[] headCoord = snakeCoords.get(0);
        int[] frontCoord = new int[] {
            headCoord[0] + direction[0], 
            headCoord[1] + direction[1]
        }; // suma vectorial
        return frontCoord;
    }


    public void    dead    () {alive = false;}
    public boolean isAlive () {return alive;}
    public List<int[]> getCoords () {return snakeCoords;}
}
