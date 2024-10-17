import java.util.Scanner;

public class ConversionTemperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona la conversión de temperatura:");
        System.out.println("1. Celsius a Fahrenheit");
        System.out.println("2. Fahrenheit a Celsius");
        System.out.println("3. Celsius a Kelvin");
        System.out.println("4. Kelvin a Celsius");
        System.out.println("5. Kelvin a Fahrenheit");
        System.out.println("6. Fahrenheit a Kelvin");
        
        int opcion = scanner.nextInt();
        
        switch (opcion) {
            case 1:
                System.out.print("Introduce la temperatura en Celsius: ");
                double celsius = scanner.nextDouble();
                double fahrenheit = (celsius * 9/5) + 32;
                System.out.println("La temperatura en Fahrenheit es: " + fahrenheit);
                break;
            case 2:
                System.out.print("Introduce la temperatura en Fahrenheit: ");
                fahrenheit = scanner.nextDouble();
                celsius = (fahrenheit - 32) * 5/9;
                System.out.println("La temperatura en Celsius es: " + celsius);
                break;
            case 3:
                System.out.print("Introduce la temperatura en Celsius: ");
                celsius = scanner.nextDouble();
                double kelvin = celsius + 273.15;
                System.out.println("La temperatura en Kelvin es: " + kelvin);
                break;
            case 4:
                System.out.print("Introduce la temperatura en Kelvin: ");
                kelvin = scanner.nextDouble();
                celsius = kelvin - 273.15;
                System.out.println("La temperatura en Celsius es: " + celsius);
                break;
            case 5:
                System.out.print("Introduce la temperatura en Kelvin: ");
                kelvin = scanner.nextDouble();
                fahrenheit = (kelvin - 273.15) * 9/5 + 32;
                System.out.println("La temperatura en Fahrenheit es: " + fahrenheit);
                break;
            case 6:
                System.out.print("Introduce la temperatura en Fahrenheit: ");
                fahrenheit = scanner.nextDouble();
                kelvin = (fahrenheit - 32) * 5/9 + 273.15;
                System.out.println("La temperatura en Kelvin es: " + kelvin);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
        scanner.close();
    }
}
