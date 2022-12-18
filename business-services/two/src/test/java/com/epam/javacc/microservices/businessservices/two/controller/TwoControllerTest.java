package com.epam.javacc.microservices.businessservices.two.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Slf4j
class TwoControllerTest {

    @Test
    void getHello() throws InterruptedException {
//        final Mono<String> string = Mono.just("String");
//        string
//                .doOnNext(str -> System.out.println(str));
//
//        string.subscribe(s -> log.debug("Result :: {}", s));
//
//        string.block();

        CountDownLatch latch = new CountDownLatch(1);
        Flux.interval(Duration.ofMillis(100))
                .take(2)
                .doOnComplete(latch::countDown)
                .log()
                .subscribe();
        latch.await(); // wait for doOnComplete


        final Flux<Integer> interval1 = Flux.just(1, 2, 3);
        interval1.subscribe(System.out::println);

//        final Flux<String> flux = Flux.just("1", "2", "3");
//        final Flux<Integer> map1 = flux
//                .doOnNext(s -> log.debug("doOnNext 1 :: {}", s))
//                .flatMap(s -> Flux.just("1" + s))
//                .doOnNext(s -> log.debug("doOnNext 2 :: {}", s))
//                .map(Integer::valueOf);
//        map1
//                .subscribe(System.out::println);
//
//
//        final Mono<String> just = Mono.just("1");
//        final Mono<Integer> map = just
//                .doOnNext(s -> log.debug("doOnNext :: {}", s))
//                .flatMap(s -> Mono.just("11" + s))
//                .map(Integer::valueOf);
//        map
//                .subscribeOn(Schedulers.single())
//                .subscribe(System.out::println);
//
//        final String result = new TwoController().getHello().block();
//        assertNotNull(result);
    }
}