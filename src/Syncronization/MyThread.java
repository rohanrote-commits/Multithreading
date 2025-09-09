package Syncronization;

import java.util.concurrent.CountDownLatch;

public class MyThread  extends Thread {

    Counter counter;
    MyThread(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            this.counter.increment();
//
            System.out.println(Thread.currentThread().getName() + " " + i);
//            Thread.yield();
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread t1= new MyThread(counter);
        MyThread t2= new MyThread(counter);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {


        }
        System.out.println("Total Count : "+counter.getCount());


    }
}


class Counter{
    private int count = 0;
    public synchronized void increment(){
        count++;
    }
    public int getCount(){
        return count;
    }
}