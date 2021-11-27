package com.prince.sparrow.CompletableFutureTest.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
//@Log4j2
public class calcAsync {

  @Async
  public Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();


    System.out.println("inside caclAsync. before sleep");

    Thread.sleep(500);// do a rest call

    System.out.println("inside caclAsync. after sleep");
    completableFuture.complete("Hello");

    return completableFuture;
  }
}
