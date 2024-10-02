package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FizzBuzz {

    private List<Integer> numbers;
    private LinkedList<String> queue;
    private final int n =15;

    public FizzBuzz(List<Integer> numbers) {
        this.numbers = numbers;
        this.queue = new LinkedList<>();
    }

    public synchronized void fizz() {
        for (int num : numbers) {
            if (num % 3 == 0 && num % 5 != 0) {
                queue.add("fizz");
                System.out.println("fizz test");
                notifyAll();
            }
            else {
                try
                {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz()  {
        for (int num : numbers) {
            if (num % 5 == 0 && num % 3 != 0) {
                queue.add("buzz");
                notifyAll();
            }
            else
            {
                try
                {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizzbuzz() {
        for (int num : numbers) {
            if (num % 3 == 0 && num % 5 == 0) {
                queue.add("fizzbuzz");
                notifyAll();
            }
            else
            {
                try
                {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void number()  {
        for (int num : numbers) {
            if (num % 3 != 0 && num % 5 != 0) {
                queue.add(String.valueOf(num));
                System.out.println("test num");
                notifyAll();
            }
            else {
                try
                {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printResults() {
        int counter =0;
        while (coun)
        {
            if (queue!=null)
            {
                System.out.println(queue.poll());
                counter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }

        FizzBuzz fizzBuzz = new FizzBuzz(numbers);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(()-> fizzBuzz.number());
        Thread threadOutput = new Thread(fizzBuzz::printResults);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();

        threadOutput.start();
        threadOutput.join();
    }
}