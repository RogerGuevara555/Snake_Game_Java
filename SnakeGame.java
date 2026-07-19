public class SnakeGame {
    
    Map map = new Map(20, 20);
    Snake snake = new Snake(map);
    
    boolean win = false;
    
    public void startGame () {
        while (snake.isAlive()) {
            snake.play();
        }
        if (win) {
            displayWin();
        } 
        else {
            displayGameOver();
        }
    }
    
    private void displayWin () {
        System.out.println("FELICIDADES, has ganado 😎🥳");
    }
    private void displayGameOver () {
        System.out.println("GAME OVER 💀");
    }
}


class Snake {
    
    final char BODY = 'S';
    final char FOOD = 'C';

    private boolean alive = true;
    private Map map;


    public Snake (Map map) {
        this.map = map;
    }
    
    public void play () {
        char front = getFront();
        
        if (front == BODY) {
            death();
        }
        else if (front == FOOD) {
            grow();
            walk();
        }
        else {
            walk();
        }
    }
    

    public boolean isAlive () {return alive;}
    private char getFront () {return 'C';} 
    
    private void walk () {}
    private void grow () {}
    private void death () {}
}
