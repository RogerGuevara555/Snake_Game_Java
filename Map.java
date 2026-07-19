import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


class Map {

    final char CEIL_ICON = '=';
    final char WALL = '|';
    final char CORNER = ':';
    final char SNAKE_HEAD = 'O';
    final char SNAKE_BODY = 'o';

    final char[] CEIL;

    final int X;
    final int Y;
    private MapSlot[][] map;

    private ArrayList<int[]> snakeCoords = new ArrayList<>(
        List.of(    //X,Y
            new int[]{-2,0},
            new int[]{-1,0},
            new int[]{0,0},
            new int[]{0,1},
            new int[]{0,2},
            new int[]{0,3},
            new int[]{1,3},
            new int[]{2,3}
        )
    );
    

    public Map (int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.map = configMap();
        this.CEIL = configCeil();
    }

    private MapSlot[][] configMap () {
        MapSlot[][] map = new MapSlot[this.Y][this.X];
        for (int i = 0; i < this.Y; i++) {
            for (int j = 0; j < this.X; j++) {
                map[i][j] = new MapSlot();
            }
        }
        return map;
    }
    private char[] configCeil () {
        final int CORNER_NUM = 2;
        char[] ceil = new char[this.X+CORNER_NUM];

        Arrays.fill(ceil, this.CEIL_ICON);
        ceil[0] = this.CORNER;
        ceil[ceil.length-1] = this.CORNER;

        return ceil;
    }

    public void displayMap ()
    {
        System.out.println(this.CEIL);
        for (MapSlot[] row : this.map) {
            System.out.print(this.WALL);
            for (MapSlot element : row) {System.out.print(element);}
            System.out.println(this.WALL);
        }
        System.out.println(this.CEIL);
    }

    public void displaySnake () {
        centerSnakeCoord();
        for (int[] coord : snakeCoords){
            getMapSlot(coord).setIcon(SNAKE_BODY);
        }
        getMapSlot(snakeCoords.get(0)).setIcon(SNAKE_HEAD);
    }

    private void centerSnakeCoord () {
        for (int[] coord : snakeCoords){
            coord[0] += X/2;
            coord[1] += Y/2;
        }
    }

    private MapSlot getMapSlot (int[] coord) {
        int coord_X = coord[0]; 
        int coord_Y = coord[1];
        return map[coord_Y][coord_X];
    }

}


class MapSlot {
    private char icon = ' ';

    public void setIcon (char icon) {this.icon = icon;}
    public String toString () {return String.valueOf(icon);}
}