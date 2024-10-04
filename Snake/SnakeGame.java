import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        add(new GameBoard()); // Añadir el tablero del juego
        setResizable(false);
        pack();
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame game = new SnakeGame();
        game.setVisible(true);
    }
}
