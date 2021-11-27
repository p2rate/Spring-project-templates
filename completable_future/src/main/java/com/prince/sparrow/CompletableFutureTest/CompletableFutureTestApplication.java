package com.prince.sparrow.CompletableFutureTest;

import com.prince.sparrow.CompletableFutureTest.service.AsyncServcie;
import com.prince.sparrow.CompletableFutureTest.service.calcAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
public class CompletableFutureTestApplication implements CommandLineRunner {

  @Autowired
  com.prince.sparrow.CompletableFutureTest.service.calcAsync calcAsync;
  @Autowired
  AsyncServcie asyncServcie;


  public static void main(String[] args) {
    SpringApplication.run(CompletableFutureTestApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

		test1();
//    test2();
//		test3();
//		test4();
//		test5();
//		test6();

  }

  /**
   * using @Async to implement functionality
   *
   * @throws InterruptedException
   * @throws ExecutionException
   */
  private void test1() throws InterruptedException, ExecutionException {
    System.out.println("test1. before calling calcAsync");
    Future<String> completableFuture = calcAsync.calculateAsync();
    System.out.println("test1. after calling calcAsync");
    while (!completableFuture.isDone()) {
      System.out.println("waiting");
      Thread.sleep(100);
    }
    String result = completableFuture.get();// blocking call. waits until result is returned
    System.out.println(result);
  }


  /**
   * using CompletableFuture.supplyAsync()
   *
   * @throws InterruptedException
   * @throws ExecutionException
   */
  private void test2() throws InterruptedException, ExecutionException {
    System.out.println("test2. before calling calcAsync");
    CompletableFuture<String> completableFuture = asyncServcie.getStringAsync();
    System.out.println("test2. after calling calcAsync");
    while (!completableFuture.isDone()) {
      System.out.println("waiting");
      Thread.sleep(100);
    }
    String result = completableFuture.get();
    System.out.println(result);
  }

  /**
   * thenApply() is useful when we want to transform the result of a CompletableFuture call. thenApply(s-> s + 1)
   *
   * @throws ExecutionException
   * @throws InterruptedException
   */
  private void test3() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
    CompletableFuture<String> completableFuture2 = completableFuture.thenApply(s -> s + " world");
    System.out.println(completableFuture2.get());
  }

  /**
   * thenAccept receives a consumer. get() method does not return anything. it is void
   *
   * @throws ExecutionException
   * @throws InterruptedException
   */
  private void test4() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello");
    CompletableFuture<Void> voidCompletableFuture = completableFuture.thenAccept(s -> System.out.println("computation returned: " + s));
    voidCompletableFuture.get();//
  }

  /**
   * thenCompose chains two Futures sequentially. The argument of chained function is the result of the previous computation step
   * I think the difference between thenApply() & thenCompose() is that the former is Sync and the later is Async
   *
   * @throws ExecutionException
   * @throws InterruptedException
   */
  private void test5() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture =
        CompletableFuture.supplyAsync(() -> {
          System.out.println("hello step");
          return "hello";
        })
            .thenCompose(
                s -> CompletableFuture.supplyAsync(() -> {
                  System.out.println("wolrd step");
                  return s + " world";
                })
                    .thenCompose(
                        s2 -> CompletableFuture.supplyAsync(() -> {
                          System.out.println("beautiful step");
                          return s2 + " beautiful";
                        }))
            )
            .thenCompose(s3 -> CompletableFuture.supplyAsync(() -> {
              System.out.println("extra step");
              return s3 + " extra";
            }));
    System.out.println(completableFuture.get());
  }

  /**
   * execute two independent Futures and do something with their results
   */
  private void test6() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("first task.before sleep");
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("first task.after sleep");
      return "hello";
    })
        .thenCombine(
            CompletableFuture.supplyAsync(() ->
            {
              System.out.println("2nd task.before sleep");
              try {
                Thread.sleep(300);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println("2nd task.after sleep");
              return "world";
            }), (s1, s2) -> s1 + s2);
    System.out.println(completableFuture.get());
  }
}
