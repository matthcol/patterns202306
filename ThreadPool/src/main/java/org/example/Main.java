package org.example;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    static void task(String name, int n) {
        try {
            System.out.println("Start task: " + name);
            TimeUnit.SECONDS.sleep(n);
            System.out.println("End task: " + name);
        } catch (InterruptedException e){
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    static int taskWithResult(int numTask, int n) {
        try {
            System.out.println("Start task: " + numTask);
            TimeUnit.SECONDS.sleep(n);
            System.out.println("End task: " + numTask);
            return numTask;
        } catch (InterruptedException e){
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    static void scenarioPoolThread(){
        ForkJoinPool pool = new ForkJoinPool(5);
        int nbTask = 20;
        // submit 20 tasks
        var tasks = IntStream.range(0,nbTask)
                .mapToObj(i -> pool.submit(
                        () -> task("Task_" + i, 3)
                ))
                .toList();
        // wait for completion
        System.out.println(nbTask + " tasks launched");
        tasks.forEach(ForkJoinTask::join);
    }

    static void scenarioPoolThreadImplicit(){
        int nbTask = 20;
        // submit 20 tasks
        var numTasks = IntStream.range(0,nbTask)
                .mapToObj(i -> i)
                .toList();
        var results = numTasks.parallelStream()
                .map(i -> taskWithResult(i, 3))
                .toList();
        System.out.println("Results: " + results);
    }

    public static void main(String[] args) {
//        scenarioPoolThread();
        scenarioPoolThreadImplicit();
    }
}