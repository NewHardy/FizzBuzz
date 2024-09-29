package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FizzBuzz {

    private final List<Integer> numbers;
    private final LinkedList<String> queue;

    public FizzBuzz(List<Integer> numbers) {
        this.numbers = numbers;
        this.queue = new LinkedList<>();
    }

    public void fizz() throws InterruptedException {
        for (int num : numbers) {
            if (num % 3 == 0 && num % 5 != 0) {
                queue.add("fizz");
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int num : numbers) {
            if (num % 5 == 0 && num % 3 != 0) {
                queue.add("buzz");
                notifyAll();
            }
            else
            {
                wait();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int num : numbers) {
            if (num % 3 == 0 && num % 5 == 0) {
                queue.add("fizzbuzz");
                notifyAll();
            }
            else
            {
                wait();
            }
        }
    }

    public void number() throws InterruptedException {
        for (int num : numbers) {
            if (num % 3 != 0 && num % 5 != 0) {
                queue.add(String.valueOf(num));
                notifyAll();
            }
            else {
                wait();
            }
        }
    }

    public void printResults() {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(queue.poll());
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
        Thread threadD = new Thread(()-> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
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