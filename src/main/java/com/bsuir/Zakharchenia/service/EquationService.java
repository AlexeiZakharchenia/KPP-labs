package com.bsuir.Zakharchenia.service;


import com.bsuir.Zakharchenia.cache.CacheService;
import com.bsuir.Zakharchenia.cache.CacheServiceImpl;
import com.bsuir.Zakharchenia.cache.InputParameters;
import com.bsuir.Zakharchenia.entity.Equation;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class EquationService {

    private CacheService cacheService = CacheServiceImpl.getInstance();

    private final AtomicLong counter = new AtomicLong();

    public Equation solveEquetion(String addend, String sum, String leftBoard, String rightBoard) throws NumberFormatException {
        Integer addend1 = Integer.valueOf(addend);
        Integer sum1 = Integer.valueOf(sum);
        Integer leftBoard1 = Integer.valueOf(leftBoard);
        Integer rightBoard1 = Integer.valueOf(rightBoard);
        if (leftBoard1 > rightBoard1)
            return null;
        InputParameters inputParameters = new InputParameters(sum1, addend1, leftBoard1, rightBoard1);
        Equation result = cacheService.getFromCache(inputParameters);
        if (result != null) {
            return result;
        } else {
            boolean isInGap = ((sum1 - addend1) >= leftBoard1 && (sum1 - addend1) <= rightBoard1);
            cacheService.add(inputParameters, new Equation(counter.incrementAndGet(), sum1 - addend1, isInGap));
            return new Equation(counter.incrementAndGet(), sum1 - addend1, isInGap);
        }
    }
}
