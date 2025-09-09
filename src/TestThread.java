public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        ThreadLifeCycle thread1 = new ThreadLifeCycle("Thread-1");
        ThreadLifeCycle thread2 = new ThreadLifeCycle("Thread-2");
        ThreadDemoRunnable thread3 = new ThreadDemoRunnable("Thread-3");
        Thread thread4 = new Thread(new ThreadDemoRunnable("Thread-4"));
//        thread1.setDaemon(true);
        thread1.start();
        thread1.join();
        thread3.start();
//        try {
//            Thread.sleep(10);
//        }catch (InterruptedException e){
//            System.out.println("Thread "  + " : INTERRUPTED ");
//        }
        thread1.join();
        thread4.start();
        thread4.join();
        thread2.start();
    }
}
