import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> future1 = executorService.submit(new SumTask(1000,1,1000));
        Future<Integer> future2 = executorService.submit(new SumTask(1000,1,1000));
        Future<Integer> future3 = executorService.submit(new SumTask(1000,1,1000));

        int total = future1.get() + future2.get() + future3.get();

        System.out.println(total);
        executorService.shutdown();


    }

}
