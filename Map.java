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
        this.map = createMapSlotMatrix();
        this.CEIL = createCeilCharArray();
    }

    private MapSlot[][] createMapSlotMatrix () {
        MapSlot[][] map = new MapSlot[this.Y][this.X];
        for (int i = 0; i < this.Y; i++) {
            for (int j = 0; j < this.X; j++) {
                map[i][j] = new MapSlot();
            }
        }
        return map;
    }
    private char[] createCeilCharArray () {
        final int CORNER_NUM = 2;
        final int LINE_JUMP = 1;
        char[] ceil = new char[this.X+CORNER_NUM+LINE_JUMP];

        Arrays.fill(ceil, this.CEIL_ICON);
        ceil[0] = this.CORNER;
        ceil[ceil.length-2] = this.CORNER;
        ceil[ceil.length-1] = '\n';

        return ceil;
    }

    public void displayMap () {
        String ceil = String.valueOf(this.CEIL);
        StringBuilder mapBuilder = new StringBuilder(
            "%s".formatted(this.WALL)
        );
        StringBuilder wallBuilder = new StringBuilder(
            "%s\n%s".formatted(this.WALL, this.WALL)
        );
        
        for (MapSlot[] row : this.map) {
            for (MapSlot element : row) {
                mapBuilder.append(element);
            }
            mapBuilder.append(wallBuilder.toString());
        }
        mapBuilder.insert(0, ceil);
        mapBuilder.replace(mapBuilder.length()-1, mapBuilder.length(), ceil);  // '|' -> ceil
        
        System.out.println(mapBuilder.toString());
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

    public void setIcon (char icon) {
        this.icon = icon;
    }
    public String toString () {
        return String.valueOf(icon);
    }
}