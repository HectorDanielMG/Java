import java.util.Scanner;

public class NumerosPrimos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un número: ");
        int limite = scanner.nextInt();
        
        System.out.println("Números primos hasta " + limite + ":");
        for (int i = 2; i <= limite; i++) {
            if (esPrimo(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
 
