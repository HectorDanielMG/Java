import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GameBoard extends JPanel implements ActionListener {

    private final int B_WIDTH = 600;  // Aumentar ancho
    private final int B_HEIGHT = 600; // Aumentar altura
    private final int DOT_SIZE = 10;  // Tamaño de los puntos (serpiente y manzana)
    private final int ALL_DOTS = 3600;  // Número total de puntos
    private final int RAND_POS = 59;    // Ajustar la posición aleatoria
    private final int DELAY = 140;      // Velocidad del juego

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image apple;
    private Image head;

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon iapple = new ImageIcon("resurse/apple.png");
        apple = iapple.getImage();

        ImageIcon ihead = new ImageIcon("resurse/cabeza.png");
        head = ihead.getImage();
    }

    private void initGame() {
        dots = 3;

        // Ajustar las posiciones iniciales para que la serpiente no empiece cerca de las paredes
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * DOT_SIZE;  // Alejar del borde izquierdo
            y[z] = 50;                 // Alejar del borde superior
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    drawHead(g, x[z], y[z]);
                } else {
                    g.setColor(Color.green);  // Cuerpo de la serpiente en verde
                    g.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    private void drawHead(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        if (rightDirection) {
            // Cabeza original, sin rotación
            g2d.drawImage(head, x, y, this);
        } else if (leftDirection) {
            // Rotar 180 grados para que la cabeza apunte a la izquierda
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(180), x + DOT_SIZE / 2, y + DOT_SIZE / 2);
            g2d.setTransform(at);
            g2d.drawImage(head, x, y, this);
        } else if (upDirection) {
            // Rotar 270 grados para que la cabeza apunte hacia arriba
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(270), x + DOT_SIZE / 2, y + DOT_SIZE / 2);
            g2d.setTransform(at);
            g2d.drawImage(head, x, y, this);
        } else if (downDirection) {
            // Rotar 90 grados para que la cabeza apunte hacia abajo
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90), x + DOT_SIZE / 2, y + DOT_SIZE / 2);
            g2d.setTransform(at);
            g2d.drawImage(head, x, y, this);
        }

        // Restaurar la transformación original
        g2d.setTransform(old);
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        g.setColor(Color.white);
        g.drawString(msg, (B_WIDTH / 2) - 40, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 3) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
