package com.bsuir.Zakharchenia.service;


import com.bsuir.Zakharchenia.answers.EquationsList;
import com.bsuir.Zakharchenia.cache.CacheService;
import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import com.bsuir.Zakharchenia.parameters.ParametersList;
import com.bsuir.Zakharchenia.repository.EquationRepository;
import com.bsuir.Zakharchenia.repository.ResponsesRepository;
import com.bsuir.Zakharchenia.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EquationService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    @Qualifier(value = "cacheServiceSqlImpl")
    private CacheService cacheService;

    @Autowired
    private EquationRepository equationRepository;

    @Autowired
    private ResponsesRepository responsesRepository;

    @Autowired
    private Validator validator;

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
            Equation equation = new Equation(solving, isInGap);
            cacheService.add(inputParameters, equation);
            return equation;
        }
    }

    public Future<EquationsList> processInputParams(ParametersList parametersList, String id) {
        return executorService.submit(() -> {
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
            answer.setResponseId(id);
            responsesRepository.save(answer);
            return answer;
        });
    }

    public String solveEquations(ParametersList parametersList) {
        String id = UUID.randomUUID().toString();
        processInputParams(parametersList, id);
        return id;
    }

    public EquationsList getEquationsByID(String id) {
        return responsesRepository.findById(id).orElse(null);
    }
}

