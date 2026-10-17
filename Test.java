public class Test {
    public static void main(String[] args) {

        char[][] board = SnakeGame.makeBoard(20,20);
        Snake sn = new Snake(board);
        System.out.println(sn.getCoords());
        sn.walk(true);
        System.out.println(sn.getCoords());

    }
}
