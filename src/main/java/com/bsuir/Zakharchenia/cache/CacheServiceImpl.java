package com.bsuir.Zakharchenia.cache;


import com.bsuir.Zakharchenia.controller.EquationController;
import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceImpl implements CacheService {
    private static final Logger log = Logger.getLogger(EquationController.class);

    static final Integer MAX_CACHE_RECORDS = 20;

    private ConcurrentHashMap<InputParameters, Equation> cacheMap = new ConcurrentHashMap<>();

    private static volatile CacheServiceImpl instance = null;

    private CacheServiceImpl() {
    }

    public static CacheServiceImpl getInstance() {
        CacheServiceImpl localInstance = instance;

        if (instance == null) {
            synchronized (CacheServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CacheServiceImpl();
                }

            }

        }
        return instance;
    }

    @Override
    public void add(InputParameters inputParameters, Equation equation) {
        if(cacheMap.size() > MAX_CACHE_RECORDS){
            cacheMap.clear();
            log.info("Cleared cache");
        }
        cacheMap.put(inputParameters, equation);
        log.info("Added entity to cache");
    }

    @Override
    public Equation getFromCache(InputParameters inputParameters) {
        log.info("Getted entity from cache");
        return cacheMap.get(inputParameters);
    }
}
