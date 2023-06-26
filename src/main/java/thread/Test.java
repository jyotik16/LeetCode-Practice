package thread;

import java.util.concurrent.*;
import java.util.*;
public class Test {

    private static class ParallelTask implements Runnable {
        String name;

        public ParallelTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is executing this code");
            for (int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
            }

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
     Thread t1 = new Thread(new ParallelTask("Thread - T1"), "Thread - T1");
     Thread t2 = new Thread(new ParallelTask("Thread - T2"), "Thread - T2");
     Thread t3 = new Thread(new ParallelTask("Thread - T3"), "Thread - T3");
     // now, let's start all three threads
     //   t1.start(); t2.start(); t3.start();
     //   futureInterface();
     //   completablefutureInterface();
     //   completablefutureInterface2();
     //       executorService();
     //   executorService2();
        executorService3();
    }
    static  ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static void executorService3() throws ExecutionException, InterruptedException {
        Callable<String> callableTask = () -> {
            TimeUnit.SECONDS.sleep(3);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        //invoke Any
        String result = executorService.invokeAny(callableTasks);
        System.out.println(result);
        executorService.shutdown();
    }

    private static void executorService2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        future.get();
        executorService.shutdown();
    }

    static  int i= 1;
    private static void executorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        executorService.shutdown();
    }

    private static void completablefutureInterface2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFutureResultObj1 = CompletableFuture.supplyAsync(() -> "Hello").thenApply(hello -> hello + "World");
        CompletableFuture<String> completableFutureResultObj2 = getHello().thenCompose(hello -> getHelloWorld(hello));

        System.out.println(completableFutureResultObj1.get());
        System.out.println(completableFutureResultObj2.get());
    }

    private static CompletableFuture<String> getHello() {
        return CompletableFuture.supplyAsync(() -> "Hello");
    }

    private static CompletableFuture<String> getHelloWorld(String hello) {
        return CompletableFuture.supplyAsync(() -> hello + " World");
    }

    private static void completablefutureInterface() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureObj = new CompletableFuture<>();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futureObj.complete("Hello World");
        });

        while (!futureObj.isDone()) {
            System.out.println("Result hasn't yet returned");
        }

        System.out.println("Result -> " + futureObj.get());

    }

    private static void futureInterface() throws InterruptedException, ExecutionException {
        Future<String> futureInterface = Executors.newSingleThreadExecutor().submit(()->{
            TimeUnit.SECONDS.sleep(2);
            return "Hello World";
        });
        while (!futureInterface.isDone()){ System.out.println("Waiting");}
        System.out.println("result:"+futureInterface.get());
    }

}
