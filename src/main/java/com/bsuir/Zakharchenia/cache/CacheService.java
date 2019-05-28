package com.bsuir.Zakharchenia.cache;

import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;


public interface CacheService {
    void add(InputParameters inputParameters, Equation equation);
    Equation getFromCache(InputParameters inputParameters);
}
