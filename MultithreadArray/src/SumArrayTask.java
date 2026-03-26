import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class SumArrayTask implements Callable<Long> {

    private long startTime;
    private long endTime;
    private final int startIndex;
    private final int endIndex;
    private final int[] arr;
    private final int threadNum;

    public SumArrayTask(int startIndex, int endIndex, int[] arr, int threadNum) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arr = arr;
        this.threadNum = threadNum;
    }

    @Override
    public Long call() {
        System.out.printf("Поток %d начал работу%n", threadNum);
        startTime = System.nanoTime();

        long sum = 0;
        long sleepTimeMs = 0;

        int currentChunkLength = endIndex - startIndex;
        int sleepStep = Math.max(1, currentChunkLength / 100);

        for (int i = startIndex; i < endIndex; i++) {
            sum += arr[i];

            int processed = i - startIndex + 1;
            if (processed % sleepStep == 0) {
                try {
                    int sleep = ThreadLocalRandom.current().nextInt(10, 60);
                    Thread.sleep(sleep);
                    sleepTimeMs += sleep;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return 0L;
                }
            }
        }

        endTime = System.nanoTime();

        long totalTimeNs = endTime - startTime;
        long totalTimeMs = totalTimeNs / 1_000_000;
        long calculationTimeMs = totalTimeMs - sleepTimeMs;

        System.out.printf(
                "Поток №%d закончил работу. Спал: %d мс, считал: %d мс, всего: %d мс, сумма: %d%n",
                threadNum,
                sleepTimeMs,
                calculationTimeMs,
                totalTimeMs,
                sum
        );

        return sum;
    }
}