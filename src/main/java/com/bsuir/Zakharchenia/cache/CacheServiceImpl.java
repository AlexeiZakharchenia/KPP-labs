package com.bsuir.Zakharchenia.cache;


import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;


@Service
@Qualifier(value = "cacheServiceImpl")
public class CacheServiceImpl implements CacheService {
    private static final Logger log = Logger.getLogger(CacheServiceImpl.class);

    static final Integer MAX_CACHE_RECORDS = 20;

    private ConcurrentHashMap<InputParameters, Equation> cacheMap = new ConcurrentHashMap<>();


    public CacheServiceImpl() {
    }

    public static Integer getMaxCacheRecords() {
        return MAX_CACHE_RECORDS;
    }

    public ConcurrentHashMap<InputParameters, Equation> getCacheMap() {
        return cacheMap;
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
