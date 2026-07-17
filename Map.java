import java.util.Arrays;


class Map {

    final char CEIL_ICON = '=';
    final char WALL = '|';
    final char CORNER = ':';

    final char[] CEIL;

    final int X;
    final int Y;
    private char[][] map;


    public Map (int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.map = setMap();
        this.CEIL = setCeil();
    }

    private char[][] setMap() {
        char[][] map = new char[this.Y][this.X];
        for (char[] row : map){
            Arrays.fill(row, ' ');
        }
        return map;
    }
    private char[] setCeil () {
        final int CORNER_NUM = 2;
        char[] ceil = new char[this.X+CORNER_NUM];

        Arrays.fill(ceil, this.CEIL_ICON);
        ceil[0] = this.CORNER;
        ceil[ceil.length-1] = this.CORNER;

        return ceil;
    }

    public void displayMap () {
        System.out.println(this.CEIL);
        for (char[] row : this.map) {
            System.out.print(this.WALL);
            for (char element : row) {System.err.print(element);}
            System.out.println(this.WALL);
        }
        System.out.println(this.CEIL);
    }

}

