import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите длину массива:");
        int arrLength = sc.nextInt();
        int[] arr = getArray(arrLength);

        System.out.println("Введите количество потоков:");
        int n = sc.nextInt();

        if (n <= 0 || n > arrLength) {
            System.out.println("Количество потоков должно быть от 1 до длины массива.");
            sc.close();
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(n);

        int chunkSize = arrLength / n;

        for (int i = 0; i < n; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == n - 1)
                    ? arrLength
                    : startIndex + chunkSize;

            executorService.submit(new SumArrayTask(startIndex, endIndex, arr, i + 1));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Все потоки завершили работу.");
        sc.close();
    }

    private static int[] getArray(int arrLength) {
        int[] arr = new int[arrLength];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        return arr;
    }
}