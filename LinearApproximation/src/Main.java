public class Main {

    public static void main(String[] args) {


        double[] x = {1, 2, 3, 4};
        double[] y = {2, 3, 5, 4};

        approximation(x,y);


    }

    private static void approximation(double[] x, double[] y){
        int n = x.length;

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        double a = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double b = (sumY - a * sumX) / n;

        System.out.println("y = " + a + "x + " + b);
    }
}
