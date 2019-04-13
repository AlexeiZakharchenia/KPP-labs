package com.bsuir.Zakharchenia.service;

import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EquationServiceTest {
    private EquationService equationService = new EquationService();


    @Test
    public void solveEquetionTest(){
        Equation expected = new Equation(1, 1,  true);
        Equation actual = equationService.solveEquetion(new InputParameters("10", "11", "1", "100"));
        assertEquals(expected, actual);
        assertNull(equationService.solveEquetion(new InputParameters("1", "2", "5", "4")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void solveEquetionTestException(){
        equationService.solveEquetion(new InputParameters("gsd", "gsd", "fds", "gdsf"));
    }
}