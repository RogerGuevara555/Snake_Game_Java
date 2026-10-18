public class Test {
    public static void main(String[] args) {

        Board board = new Board(20,20);
        Snake snake = new Snake(board.board);
        System.out.println(snake.getCoords());
        snake.walk(true);
        System.out.println(snake.getCoords());

    }
}
