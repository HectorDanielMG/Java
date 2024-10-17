import java.util.Scanner;

public class ConversorTemperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Convertir de: ");
        System.out.println("1. Celsius a Fahrenheit");
        System.out.println("2. Fahrenheit a Celsius");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingresa la temperatura en Celsius: ");
                double celsius = scanner.nextDouble();
                double fahrenheit = (celsius * 9/5) + 32;
                System.out.println("Temperatura en Fahrenheit: " + fahrenheit);
                break;

            case 2:
                System.out.println("Ingresa la temperatura en Fahrenheit: ");
                fahrenheit = scanner.nextDouble();
                celsius = (fahrenheit - 32) * 5/9;
                System.out.println("Temperatura en Celsius: " + celsius);
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
 
