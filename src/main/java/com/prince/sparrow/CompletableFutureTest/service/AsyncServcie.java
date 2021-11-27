package com.prince.sparrow.CompletableFutureTest.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncServcie {
  String name = null;

  public CompletableFuture<String> getStringAsync(){
    CompletableFuture<String> completableFuture
        =  CompletableFuture.supplyAsync(() -> {
      if (name == null) {
        throw new RuntimeException("Computation error!");
      }
      return "Hello, " + name;
    });

    CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(this::AsyncTask)
        .handle((s,t) -> s != null ? s : "Hello, Stranger!");
    return completableFuture;
  }

  private String AsyncTask() {
    System.out.println("test2 inside completableFuture before sleep");

    try {
      Thread.sleep(500); // call rest endpoint
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("test2 inside completableFuture after sleep");
    return "hello";
  }

  public CompletableFuture<String> processRequest(String id) {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::processAsync)
        .completeOnTimeout("Request Timed Out", 3000, TimeUnit.MILLISECONDS)
        .handle((s,t) -> s != null ? s : "Hello, Stranger!");
    return completableFuture;
  }

  private String processAsync() {
    String val;
    //call rest endpoint
    return "hello";
  }


}
