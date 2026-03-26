import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
        List<Future<Long>> futures = new ArrayList<>();

        int chunkSize = arrLength / n;

        for (int i = 0; i < n; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == n - 1)
                    ? arrLength
                    : startIndex + chunkSize;

            Future<Long> future = executorService.submit(
                    new SumArrayTask(startIndex, endIndex, arr, i + 1)
            );
            futures.add(future);
        }

        long totalSum = 0;

        for (Future<Long> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Главный поток был прерван.");
                return;
            } catch (ExecutionException e) {
                System.out.println("Ошибка в одном из потоков: " + e.getCause());
            }
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        double average = (double) totalSum / arrLength;

        System.out.println("Все потоки завершили работу.");
        System.out.println("Общая сумма: " + totalSum);
        System.out.printf("Общее среднее: %.2f%n", average);

    }

    private static int[] getArray(int arrLength) {
        int[] arr = new int[arrLength];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        return arr;
    }
}