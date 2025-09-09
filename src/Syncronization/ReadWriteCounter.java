package Syncronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter  {

    private  int count;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock read = lock.readLock();
    private final Lock write = lock.writeLock();


    public void increment() {
write.lock();
        try{
            count++;
            Thread.sleep(50);
    } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            write.unlock();
        }
    }

    public int getCount() {
        read.lock();
        try{
            return count;
        }finally {
            read.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();
        Runnable write = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " incrementing : " + i);
                }
            }
        };
        Runnable read = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ", Count : "+counter.getCount());

                }
            }
        };
        Thread writeThread = new Thread(write);

        Thread readThread1 = new Thread(read);
        Thread readThread2 = new Thread(read);
        writeThread.start();
        Thread.sleep(80);
        readThread1.start();
        readThread2.start();

        writeThread.join();
        readThread1.join();
        readThread2.join();
    }
}
