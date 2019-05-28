package com.bsuir.Zakharchenia.repository;

import com.bsuir.Zakharchenia.parameters.InputParameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputParametersRepository extends CrudRepository<InputParameters, Integer> {
    InputParameters findByAddendAndLeftBoundAndRightBoundAndSum
            (String addend, String leftBound, String rightBound, String sum);
}
