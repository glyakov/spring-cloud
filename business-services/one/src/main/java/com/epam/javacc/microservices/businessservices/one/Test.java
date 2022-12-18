package com.epam.javacc.microservices.businessservices.one;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Test {

    public static void main(String[] args) {
        final Scheduler single = Schedulers.single();
        final Flux<Long> interval = Flux.interval(Duration.of(100, ChronoUnit.MILLIS));
        //interval.subscribeOn(Schedulers.single());
        interval.subscribe(System.out::println);
    }
}
