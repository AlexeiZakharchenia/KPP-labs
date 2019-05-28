package com.bsuir.Zakharchenia.repository;

import com.bsuir.Zakharchenia.answers.EquationsList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponsesRepository extends CrudRepository<EquationsList, String> {

}
