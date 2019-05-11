package com.bsuir.Zakharchenia.counter;


import com.bsuir.Zakharchenia.cache.CacheServiceImpl;

import java.util.concurrent.atomic.AtomicLong;

public class CounterServiceImpl implements CounterService {
    private static volatile AtomicLong counter = new AtomicLong(0);

    private static volatile CounterServiceImpl instance = null;

    private CounterServiceImpl() {
    }

    public static CounterServiceImpl getInstance() {
        CounterServiceImpl localInstance = instance;

        if (instance == null) {
            synchronized (CacheServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CounterServiceImpl();
                }

            }

        }
        return instance;
    }

    @Override
    public synchronized void incrementAndPrint() {
        System.out.println(("Counter of requests on server: " + counter.incrementAndGet()));
    }

    @Override
    public Long getCounter() {
        return counter.get();
    }
}
