import java.util.Scanner;

public class Main {

    static DualNumber applyOperation(String name, DualNumber x) {
        switch (name.toLowerCase()) {

            case "sin":
                return DualNumber.sin(x);

            case "cos":
                return DualNumber.cos(x);

            case "exp":
                return DualNumber.exp(x);

            case "square":
                return DualNumber.pow(x, 2);

            case "cube":
                return DualNumber.pow(x, 3);

            default:
                throw new IllegalArgumentException("Неизвестная операция: " + name);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите real часть: ");
        double real = Double.parseDouble(input.nextLine());

        System.out.print("Введите dual часть: ");
        double dual = Double.parseDouble(input.nextLine());

        System.out.print("Введите операцию (sin, cos, exp, square, cube): ");
        String name = input.nextLine();

        DualNumber x = new DualNumber(real, dual);

        DualNumber result = applyOperation(name, x);

        System.out.println("Результат: " + result);
        System.out.println("Значение функции = " + result.real);
        System.out.println("Производная = " + result.dual);
    }
}