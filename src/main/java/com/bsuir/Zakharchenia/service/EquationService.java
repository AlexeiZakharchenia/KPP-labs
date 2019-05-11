package com.bsuir.Zakharchenia.service;


import com.bsuir.Zakharchenia.answers.EquationsList;
import com.bsuir.Zakharchenia.cache.CacheService;
import com.bsuir.Zakharchenia.cache.CacheServiceImpl;
import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import com.bsuir.Zakharchenia.parameters.ParametersList;
import com.bsuir.Zakharchenia.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EquationService {

    private CacheService cacheService = CacheServiceImpl.getInstance();

    private Validator validator = new Validator();

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
                .filter(p -> validator.isValid(p))
                .map(this::solveEquetion)
                .collect(Collectors.toList());

        System.out.println(list.size());
        EquationsList answer = new EquationsList(new ArrayList<>(list));
        System.out.println("Amount of input parameters:" + parametersList.getParameters().size());
        System.out.println("Amount of invalid input parameters" +
                parametersList.getParameters().stream().filter(param -> !validator.isValid(param)).count());
        if (!list.isEmpty()) {
            System.out.println("Max of results:" + list.stream().max(Comparator.comparing(Equation::getSolution)).get());
            System.out.println("Min of results:" + list.stream().min(Comparator.comparing(Equation::getSolution)).get());
            Equation mostPopular = new HashSet<>(list).stream()
                    .collect(Collectors.toMap(Function.identity(), result -> list.stream()
                            .filter(r -> r.equals(result)).count()))
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry<Equation, Long>::getValue).reversed())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
                    .get(0);
            System.out.println("Most popular result:" + mostPopular);
        }

        return answer;

    }
}

