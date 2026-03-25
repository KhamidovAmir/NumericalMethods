import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class SumArrayTask implements Runnable {

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
    public void run() {
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
                    int sleep = ThreadLocalRandom.current().nextInt(1, 6);
                    Thread.sleep(sleep);
                    sleepTimeMs += sleep;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        endTime = System.nanoTime();
        long totalTimeMs = endTime - startTime;
        long calculationTimeMs = totalTimeMs - sleepTimeMs;

        System.out.printf(
                "Поток №%d закончил работу. Начал: %s, закончил: %s, спал: %d мс, считал: %d мс, всего: %d мс, сумма: %d%n",
                threadNum,
                startTime,
                endTime,
                sleepTimeMs,
                calculationTimeMs,
                totalTimeMs,
                sum
        );
    }
}