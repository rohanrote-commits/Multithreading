public class ThreadLifeCycle extends Thread {


    private Thread t;
    private String name;
    ThreadLifeCycle(String name) {
        this.name = name;
        System.out.println("Thread " + name + " created, State : NEW");
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " started, State : RUNNING");
//try {
    for (int i = 1; i <= 10; i++) {
        System.out.println("Thread " + name + ": " + i);
     //
//        System.out.println("Thread " + name + ": is WAITING ");
//        Thread.sleep(50);


    }
//}catch (Exception e) {
//    System.out.println("Thread " + name + ": got interrrupted" + e.getMessage() );
//}
        System.out.println("Thread " + name + " finished, State : DEAD");
    }

    @Override
    public synchronized void start() {
        System.out.println("Thread " + name + " , State : START");
        if(t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
//public class TestThread{
//    public static void main(String[] args) {
//        ThreadLifeCycle t = new ThreadLifeCycle("Thread-1");
//
//    }
//}
