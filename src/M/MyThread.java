package M;

public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        MyThread t1 = new MyThread();
//        t1.start();
//        t1.join();
//
//        System.out.println("Hello");


        //setPriority and innterupt
//        ThreadPriority l = new ThreadPriority("Low Priority");
//        ThreadPriority r = new ThreadPriority("High Priority");
//        ThreadPriority m = new ThreadPriority("Medium Priority");
//        ThreadPriority s = new ThreadPriority("Custom Priority");
//        l.setPriority(Thread.MIN_PRIORITY);
//        r.setPriority(Thread.MAX_PRIORITY);
//        m.setPriority(Thread.NORM_PRIORITY);
//        s.setPriority(7);
//        l.start();
//        r.start();
//        m.start();
//        m.interrupt();
//        s.start();

        YeildThread t1 = new YeildThread("First Thread");
        YeildThread t2 = new YeildThread("Second Thread");
        t1.start();
        t2.start();


    }
}

class ThreadPriority extends Thread {

    ThreadPriority(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String a = "";
            for (int j = 1; j <= 10000000; j++) {
                 a += a;
            }
            System.out.println("Thread name : "+ Thread.currentThread().getName() + ", Priority : " + Thread.currentThread().getPriority() + " count : " + i);
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Interrupted : " + Thread.currentThread().getName());
        }
    }
}


class YeildThread extends Thread {
    YeildThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread name : "+ Thread.currentThread().getName() +" "  + i);
            Thread.yield();
        }
    }
}