package com.bsuir.Zakharchenia.repository;

import com.bsuir.Zakharchenia.entity.Equation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquationRepository extends CrudRepository<Equation, Integer> {
    Equation findById(int id);

    Equation findBySolutionAndInGap(int solution, Boolean inGap);
}
