package com.bsuir.Zakharchenia.service;


import com.bsuir.Zakharchenia.Parameters.InputParameters;
import com.bsuir.Zakharchenia.Parameters.ParametersList;
import com.bsuir.Zakharchenia.answers.EquationsList;
import com.bsuir.Zakharchenia.cache.CacheService;
import com.bsuir.Zakharchenia.cache.CacheServiceImpl;
import com.bsuir.Zakharchenia.entity.Equation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EquationService {

    private CacheService cacheService = CacheServiceImpl.getInstance();

    private final AtomicLong counter = new AtomicLong();

    public Equation solveEquetion(InputParameters inputParameters) throws NumberFormatException {
        Integer sum = Integer.valueOf(inputParameters.getSum());
        Integer addend = Integer.valueOf(inputParameters.getAddend());
        Integer leftBound = Integer.valueOf(inputParameters.getLeftBound());
        Integer rightBound = Integer.valueOf(inputParameters.getRightBound());
        if (rightBound < leftBound)
            throw new IllegalArgumentException("Invalid input(right bound < left bound)");
        Equation result = cacheService.getFromCache(inputParameters);
        if (result != null) {
            return result;
        } else {
            int solving = sum - addend;
            boolean isInGap = (solving >= leftBound && solving <= rightBound);
            Equation equation = new Equation(counter.incrementAndGet(), solving, isInGap);
            cacheService.add(inputParameters, equation);
            return equation;
        }
    }

    public EquationsList solveEquations(ParametersList parametersList) {
        List<Equation> list = parametersList.getParameters().stream()
                .map(this::solveEquetion)
                .collect(Collectors.toList());

        return new EquationsList(new ArrayList<>(list));

    }
}
