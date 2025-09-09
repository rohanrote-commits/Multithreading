package Syncronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
private Lock lock1 = new ReentrantLock(true);
private Lock lock2 = new ReentrantLock(true);
//
public void operation1() throws InterruptedException {
    lock1.lock();
    System.out.println("Lock1 has been acquired by operation1");
    try {
       lock2.lock();
       try {
           System.out.println("Lock2 has been acquired by operation1");
           System.out.println("Operation 1 has been executed");
       }finally {
           lock2.unlock();
       }

    }finally {
        lock1.unlock();
    }
}


public void operation2() throws InterruptedException {
    lock1.lock();
    System.out.println("Lock1 has been acquired by operation2");
    try {
       lock2.lock();
       try {
           System.out.println("Lock2 has been acquired by operation2");
       }finally {
           lock1.unlock();
       }

    }finally {
        lock2.unlock();
    }

}


////deadlock resolved using syncronised
//    public void operation1() throws InterruptedException {
//
//        synchronized (lock1) {
//            lock1.lock();
//            System.out.println("Lock1 has been acquired by operation1");
//            try {
//                lock2.lock();
//                try {
//                    System.out.println("Lock2 has been acquired by operation1");
//                    System.out.println("Operation 1 has been executed");
//                } finally {
//                    lock2.unlock();
//                }
//
//            } finally {
//                lock1.unlock();
//            }
//        }
//    }
//
//
//    public void operation2() throws InterruptedException {
//
//        synchronized (lock1) {
//            lock1.lock();
//            System.out.println("Lock1 has been acquired by operation2");
//            try {
//                lock2.lock();
//                try {
//                    System.out.println("Lock2 has been acquired by operation2");
//                    System.out.println("Operation 2 has been executed");
//                } finally {
//                    lock1.unlock();
//                }
//
//            } finally {
//                lock2.unlock();
//            }
//        }
//
//    }

    public static void main(String[] args) throws InterruptedException {
    DeadLockExample deadlock = new DeadLockExample();
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                try {
                    deadlock.operation1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                try {
                    deadlock.operation2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t2.start();
//        Thread.sleep(100);
        t1.start();
        t2.join();
    }
}
