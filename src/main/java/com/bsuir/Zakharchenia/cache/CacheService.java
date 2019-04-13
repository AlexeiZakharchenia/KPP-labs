package com.bsuir.Zakharchenia.cache;

import com.bsuir.Zakharchenia.Parameters.InputParameters;
import com.bsuir.Zakharchenia.entity.Equation;

public interface CacheService {
    void add(InputParameters inputParameters, Equation equation);
    Equation getFromCache(InputParameters inputParameters);
}
