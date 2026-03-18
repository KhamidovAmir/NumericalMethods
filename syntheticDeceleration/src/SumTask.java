

import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {

    private final int size;
    private final int max;
    private final int min;
    private final int sleepTime;

    public SumTask(int size, int max, int min, int sleepTime) {
        this.size = size;
        this.max = max;
        this.min = min;
        this.sleepTime = sleepTime;
    }


    @Override
    public Integer call() throws Exception {
        return sumArr(getRandomArray(size, max, min), sleepTime);
    }

    private static int[] getRandomArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1) + min);
        }
        return arr;
    }

    private static int sumArr(int[] arr, int sleepTime){
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        try {
            System.out.printf("Поток спит: %d милисекунд %n", sleepTime);
            Thread.sleep(sleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return sum;
    }
}
