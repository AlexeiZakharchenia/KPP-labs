package com.bsuir.Zakharchenia.counter;


import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


@Service
public class CounterServiceImpl implements CounterService {
    private static volatile AtomicLong counter = new AtomicLong(0);

    public CounterServiceImpl() {

    }

    @Override
    public synchronized void incrementAndPrint() {
        System.out.println(("Counter of requests on server: " + counter.incrementAndGet()));
    }

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public Long getCounter() {
        return counter.get();
    }
}
