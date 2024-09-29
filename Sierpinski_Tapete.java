import javax.swing.*;
import java.awt.*;

/**
 * @author Héctor Daniel Marmolejo Garcia
 */
public class Sierpinski_Tapete extends JPanel {
    int nivelDeRecursividad = 4;  // Nivel de recursividad del fractal

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el tapete de Sierpinski, comenzando con un cuadrado grande
        drawCarpet(g, nivelDeRecursividad, 50, 50, 700);
    }

    // Función recursiva para dibujar el Tapete de Sierpinski
    private void drawCarpet(Graphics g, int nivel, int x, int y, int size) {
        if (nivel == 0) {
            g.fillRect(x, y, size, size);  // Dibuja un cuadrado lleno
        } else {
            int newSize = size / 3;  // Tamaño de los subcuadrados
            // Llama a la función recursiva para los 8 subcuadrados
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != 1 || j != 1) {  // Omitimos el cuadrado central
                        drawCarpet(g, nivel - 1, x + i * newSize, y + j * newSize, newSize);
                    }
                }
            }
        } 
    }

    // Método principal para iniciar la ventana con el Tapete de Sierpinski
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tapete de Sierpinski");
        Sierpinski_Tapete panel = new Sierpinski_Tapete();
        frame.add(panel);
        frame.setSize(800, 800);  // Tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
