public class Logic {

    public static void main (String[] args) {

        
        
    }

    public void initGameLoop () {
        while (true) {
            //updateBoard();

            if (front == ' ') {
                snake.walk();
            } else if (front == FOOD) {
                snake.grow();
            } else if (front == SNAKE_BODY || front == WALL) {
                snake.die();
                break;
            }

            //waitCoolDown();
        }
    }
}



