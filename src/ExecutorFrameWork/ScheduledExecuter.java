package ExecutorFrameWork;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecuter {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        ScheduledExecuterService scheduledExecuter = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("Priniting after 5 seconds");
        },0,5, TimeUnit.SECONDS);



        AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
        scheduledExecutorService.scheduleWithFixedDelay(()->{

            System.out.println("Printing after dealay after an  Execution of task ");
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Diffrence between Execution Time " + (System.currentTimeMillis() - startTime.getAndSet(System.currentTimeMillis())) + " ms");
                   // startTime.set(System.currentTimeMillis());
                    System.out.println(System.currentTimeMillis());
                },
                5,5,TimeUnit.SECONDS
                );

        scheduledExecutorService.schedule(()->{
            System.out.println("Shutting down Executer Service");
            scheduledExecutorService.shutdown();
        },20,TimeUnit.SECONDS);



    }
}
