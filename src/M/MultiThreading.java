package M;

public class MultiThreading extends Thread {

    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MultiThreading mt = new MultiThreading();
        System.out.println(mt.getState());
        mt.start();
        System.out.println(mt.getState());
        Thread.sleep(1000);
        System.out.println(mt.getState());

        mt.join();
        System.out.println(mt.getState());

    }


}

class World extends Thread {

    @Override
    public void run() {
        for(int i=0;;i++) {
            System.out.println(Thread.currentThread().getName() );
        }
    }
}
