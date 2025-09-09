package Syncronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locking {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Runnable sbi = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);

            }
        };

        Thread t1 = new Thread(sbi,"Thread1");
        Thread t2 = new Thread(sbi,"Thread2");
        Thread t3 = new Thread(sbi,"Thread3");
        t1.start();
        Thread.sleep(100);
        t2.start();
        t3.start();
        System.out.println("Main will intterupt the thread");
//        Thread.sleep(1000);
        Thread.sleep(2000);
        t2.interrupt();
        Thread.sleep(2000);
        t3.interrupt();


    }
}


class BankAccount{
    int balance = 1000;
    public final Lock lock = new ReentrantLock(true);






//    public synchronized void withdraw(int amount){
//
//        if(amount > 0){
//            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getName() + " is  withdrawing amount " + amount);
//            try{
//                Thread.sleep(3000);
//            }catch(InterruptedException e){}
//            System.out.println("Withdraw successfull");
//        }else{
//            System.out.println("Insufficient balance");
//
//        }
//
//    }


    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() +  " attempting to withdraw : " + amount);

        try{

            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance >= amount) {
                   try{
                    System.out.println(Thread.currentThread().getName() + " processing to withdraw : " + amount);
                    Thread.sleep(10000);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " withdraw completed: " + amount);
                    }catch(InterruptedException e){
                       Thread.currentThread().interrupt();
                   }finally{
                       lock.unlock();
                   }
                }else {
//                    System.out.println(Thread.currentThread().getName() + " processing to withdraw : " + amount);
                    System.out.println("Not enough balance");
                }
            }else{

                try {
                    lock.lockInterruptibly();

                    System.out.println(Thread.currentThread().getName() + " Could not acquire lock");
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName() + "Innterupted");
                }
//                withdraw(amount);
//                Thread.currentThread().interrupt();

            }
        }catch(Exception e){
            Thread.currentThread().interrupt();

        }


    }

//DeadLock Situation
    public void outerMethod(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() +  " processing outer method");
            innerMethod();
        }
        finally{
            lock.unlock();
        }
    }
    public void innerMethod(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() +  " attempting to inner method");

        }
        finally{
            lock.unlock();
        }
    }
}