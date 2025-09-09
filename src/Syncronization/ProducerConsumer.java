package Syncronization;

class SharedResource{
    private int data;
    boolean hasData;


    public synchronized void produce(int data){
       while(hasData){
           try{
               wait();
           }catch(InterruptedException e) {
               e.printStackTrace();
           }
       }
       this.data = data;
        System.out.println("Data "+ this.data+ " has been produced");
       hasData = true;
     notify();
    }

    public synchronized void consume(){
        while(!hasData){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Data counsumed : " + this.data);
        hasData = false;


notify();
    }

}

class Producer implements Runnable{
    SharedResource sr;
    public Producer(SharedResource sr){
        this.sr = sr;

    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            sr.produce(i);
        }
    }
}
class Consumer implements Runnable{
    SharedResource sr;
    public Consumer(SharedResource sr){
        this.sr = sr;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            sr.consume();
        }
    }
}


//for thread communication
public class ProducerConsumer {


    public static void main(String[] args) {
        SharedResource sr = new SharedResource();
        Thread producer = new Thread(new Producer(sr));
        Thread consumer = new Thread(new Consumer(sr));
        producer.start();
        consumer.start();
    }





}
