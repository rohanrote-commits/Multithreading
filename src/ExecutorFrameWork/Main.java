package ExecutorFrameWork;

import java.util.concurrent.*;

public class Main {

    private static int factorial(int num){

        for (int i = num-1; i >= 1; i--){
            num *= i;
        }

        return num;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        Long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++){
            int finalI = i;

            executor.submit(() -> {
                System.out.println("Factorial of "+ finalI+" is "+ factorial(finalI));
            }
);

        }


        Future<String> future =  executor.submit(() -> {
//            System.out.println("Factorial of IntMax is : "+ factorial(Integer.MAX_VALUE/10000000));
            for(int i = 1; i <= Integer.MAX_VALUE; i++){

            }
        },"Completed");

        if(future.isDone()){
            System.out.println("Factorial of IntMax is done");
        }else{
            System.out.println("Factorial of IntMax is not done");
//            future.cancel(true);
            if(future.isCancelled()){
                System.out.println("Factorial of IntMax is cancelled");
            }
        }


executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();




        System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime));
    //    System.out.println("Factorial of IntMax is : "+ future.get(10,TimeUnit.SECONDS));
        System.out.println("Execution of Heavy operation: "+ future.isDone());
        System.out.println("Is executor service Terminated Successfully : " + executor.isTerminated());



    }

}




