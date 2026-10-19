/* 
import org.jline.reader.*;
import org.jline.reader.impl.*;
import org.jline.terminal.*;
import org.jline.terminal.impl.*;
*/

public class Main {
    
    public static void main(String[] args) { 
        gameLoop();
        System.out.println();
    }
    
    
    static Board board = new Board(20, 10);
    static Snake snake = new Snake(board);        
    
    
    static void gameLoop () {
        while (snake.isAlive()) {
            updateBoard();
            
            int[] frontCoord = snake.getFrontCoord();
            char front = board.getBoardSlot(frontCoord);
            if      ( Slot.VOID.isThis (front) ) {snake.walk(false);}
            else if ( Slot.APPLE.isThis(front) ) {snake.walk( true);} 
            else if ( Slot.SNAKE.isThis(front) || Slot.WALL.isThis(front) ) {snake.die();}
            
            waitCoolDown();
        }
        Interface.printGameOver();
    }
    

    static void updateBoard () {
        Interface.cleanTerminal();

        board.reset();
        board.putSnakeInBoard(snake);
        
        String[][] decoratedBoard = Restile.decorateBoard(board.getBoard(), snake);
        String printableBoard = Restile.makeStringBoard(decoratedBoard);
        
        Interface.printFrame(printableBoard);
    }


    static void waitCoolDown () {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



class Interface {
    
    static void cleanTerminal () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    static void printFrame(String printableBoard) {
        System.out.println(printableBoard);
    }


    public static void printGameOver () {
        String gameOver = """
                 _____                        _____                
                |  __ \\                      |  _  |               
                | |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __ 
                | | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|
                | |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |   
                 \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   
                """;;
        System.out.println(gameOver);
    }

    /*
    public static void inputRaw () {
        Terminal terminal = TerminalBuilder.builder()
        .jna(true)
        .system(true)
        .build();
        try {
            terminal.enterRawMode();
            Reader reader = terminal.reader();
            int charCode = reader.read(); // Leer carácter en tiempo real
        } finally {
            terminal.close();
        }
    }
    */
}
