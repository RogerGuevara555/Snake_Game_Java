public class Test {
    public static void main(String[] args) {

        Board board = new Board(20,20);
        Snake sn = new Snake(board.board);
        System.out.println(sn.getCoords());
        sn.walk(true);
        System.out.println(sn.getCoords());

    }
}
