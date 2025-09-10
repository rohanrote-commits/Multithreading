package ExecutorFrameWork;

import java.util.concurrent.*;

public class CountDown {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfThreads = 5;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);


        Future<String>[] futures = new Future[numberOfThreads];
       for (int i = 0; i < numberOfThreads; i++) {
           futures[i] = executorService.submit(new Dependent(latch),"Completed "+i+"th task");

       }


       //executorService.awaitTermination(6,TimeUnit.SECONDS);
       latch.await();
       for (Future<String> future : futures) {
           System.out.println(future.get());
       }
        executorService.shutdown();


    }



}
class Dependent implements Runnable {
    private final CountDownLatch countdownLatch;
    public Dependent(CountDownLatch countdownLatch) {
        this.countdownLatch = countdownLatch;
    }
    @Override
    public void run() {
        try {
            System.out.println("Executing " + Thread.currentThread().getName());
            Thread.sleep(100);
            countdownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}