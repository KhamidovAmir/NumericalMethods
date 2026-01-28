import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        double a = 1;
        double b = 5;
        int n = 10;
        Function<Double, Double> function = x -> x * x;

        System.out.println(rectangleMethod(a,b,n,function));
        System.out.println(trapezoidMethod(a,b,n,function));
        System.out.println(simpsonMethod(a,b,n,function));

    }

    public static double rectangleMethod(double a, double b, int n, Function<Double, Double> function) {

        double h = (b - a) / n;
        double sum = 0;

        for (int i = 0; i < n; i++) {

            double x = a + (i + 0.5) * h;
            sum += function.apply(x) * h;

        }
        return sum;
    }
    public static double trapezoidMethod(double a, double b, int n, Function<Double, Double> function) {

        double h = (b - a) / n;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            double x1 = a + i * h;
            double x2 = x1 + h;
            sum += (function.apply(x1) + function.apply(x2)) / 2 * h;
        }

        return sum;
    }
    public static double simpsonMethod(double a, double b, int n, Function<Double, Double> function) {

        if (n % 2 != 0) return -1.0;

        double h = (b - a) / n;
        double sum = function.apply(a) + function.apply(b);

        for (int i = 1; i < n; i+=2) {
            double x = a + i * h;
            sum = sum + 4  * function.apply(x);

        }
        for (int i = 2; i < n; i+=2) {
            double x = a + i * h;
            sum = sum + 2  * function.apply(x);
        }

        return sum * h / 3;
    }


}
