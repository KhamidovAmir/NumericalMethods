
import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {

    private final int size;
    private final int max;
    private final int min;

    public SumTask(int size, int max, int min) {
        this.size = size;
        this.max = max;
        this.min = min;
    }


    @Override
    public Integer call() throws Exception {
        return sumArr(getRandomArray(size, max, min));
    }

    private static int[] getRandomArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1) + min);
        }
        return arr;
    }

    private static int sumArr(int[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }
}
