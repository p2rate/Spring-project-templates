package com.prince.sparrow.CompletableFutureTest.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * creating a thread pool for async calls. functions anotated with @Async
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(25);
    executor.setQueueCapacity(200);
    executor.initialize();
    return executor;
  }

//  @Override
//  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//    return new CustomAsyncExceptionHandler();
//  }

}
