package com.bsuir.Zakharchenia.validator;

import com.bsuir.Zakharchenia.parameters.InputParameters;

public class Validator {

    public boolean isValid(InputParameters inputParameters) {
        Integer leftBound;
        Integer rightBound;
        try {
            Integer.valueOf(inputParameters.getSum());
            Integer.valueOf(inputParameters.getAddend());
            leftBound = Integer.valueOf(inputParameters.getLeftBound());
            rightBound = Integer.valueOf(inputParameters.getRightBound());
        } catch (NumberFormatException ex) {
            return false;
        }
        return rightBound >= leftBound;
    }
}
