import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Thread1("Thread 1"));
        Thread t2 = new Thread(new Thread1("Thread 2"));
        Thread t3 = new Thread(new Thread1("Thread 3"));
        Thread t4 = new Thread(new Thread1("Thread 4"));

try{
t1.start();
t1.join();
t2.start();
t2.join();
t3.start();
t3.join();
t4.start();
t4.join();
        }
catch(InterruptedException e){
e.printStackTrace();
    }


int threadCount = 10;
int taskCount = 50;
ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    for(int i = 0; i < taskCount; i++){

        int taskId = i;
        executor.submit(() -> {
            System.out.println("Task " + taskId + " is running " + Thread.currentThread().getName());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        });
//        System.out.println("Queue size is " + executor.ge);




    }


    }
    }


    class Thread1 extends Thread {

        public Thread1(String s) {
            super(s);
        }

        @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println(this.getName());
    }
    }