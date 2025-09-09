public class ThreadDemoRunnable implements Runnable {
    private Thread t;
    private String threadName;
    public ThreadDemoRunnable(String threadName) {
        this.threadName = threadName;
        System.out.println("Thread " + threadName + " State : NEW ");
    }




    @Override
    public void run() {
        System.out.println("Thread " + threadName + " State : RUNNING ");
//        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Thread " + threadName+ " : " + i);
//
//                //sleep
//                System.out.println(threadName + " is WAITING ");
//                Thread.sleep(50);
            }
//        }catch (InterruptedException e){
//            System.out.println("Thread " + threadName + " : INTERRUPTED ");
//        }

    }

    public void start() {
        System.out.println("Thread " + threadName + " State : STARTED ");
        if (t == null) {
            t = new Thread(this,threadName);
            t.start();

        }
    }

//
//    @override
//    public void join() {
//        if( t != null) {
//            try {
//                System.out.println("");
//                t.join();
//            } catch (InterruptedException e) {}
//        }
//    }
}
