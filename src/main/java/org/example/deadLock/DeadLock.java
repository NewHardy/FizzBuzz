package org.example.deadLock;

public class DeadLock {

    static Object resource1 = new Object();
    static Object resource2 = new Object();

    public static void deadLock() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            synchronized (resource1){
                System.out.println("resource 1 zanyat");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resource2){
                    System.out.println("resource 2 znyat");
                }

            }
        });

        Thread thread2 = new Thread(()->{
            synchronized (resource2){
                System.out.println("resource 2 zanyat");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resource1){
                    System.out.println("resource 1 znyat");
                }

            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock.deadLock();
    }
}
