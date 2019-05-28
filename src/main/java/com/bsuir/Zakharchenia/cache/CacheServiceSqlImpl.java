package com.bsuir.Zakharchenia.cache;

import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import com.bsuir.Zakharchenia.repository.EquationRepository;
import com.bsuir.Zakharchenia.repository.InputParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier(value = "cacheServiceSqlImpl")
public class CacheServiceSqlImpl implements CacheService {

    @Autowired
    private EquationRepository equationRepository;

    @Autowired
    private InputParametersRepository inputParametersRepository;

    @Override
    public void add(InputParameters inputParameters, Equation equation) {

        Equation tmp = equationRepository.findBySolutionAndInGap(equation.getSolution(), equation.isInGap());
        if (tmp == null) {
            equation = equationRepository.save(equation);
            inputParameters.setEquationId(equation.getId());
        } else {
            inputParameters.setEquationId(tmp.getId());
            equation.setId(tmp.getId());
        }
        inputParametersRepository.save(inputParameters);
    }

    @Override
    public Equation getFromCache(InputParameters inputParameters) {
        InputParameters parameters = inputParametersRepository.findByAddendAndLeftBoundAndRightBoundAndSum(
                inputParameters.getAddend(), inputParameters.getLeftBound(),
                inputParameters.getRightBound(), inputParameters.getSum());
        if (parameters != null) {
            return equationRepository.findById(parameters.getEquationId());
        }
        return null;
    }
}
