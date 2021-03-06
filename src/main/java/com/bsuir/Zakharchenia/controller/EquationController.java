package com.bsuir.Zakharchenia.controller;

import com.bsuir.Zakharchenia.answers.EquationsList;
import com.bsuir.Zakharchenia.counter.CounterService;
import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.parameters.InputParameters;
import com.bsuir.Zakharchenia.parameters.ParametersList;
import com.bsuir.Zakharchenia.service.EquationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EquationController {


    @Autowired
    private CounterService counterService;

    private EquationService service;

    private static final Logger log = Logger.getLogger(EquationController.class);

    @Autowired
    public EquationController(EquationService service) {
        this.service = service;
    }

    @GetMapping("/solveEquation")
    public ResponseEntity getSolution(@RequestParam(value = "addend") String addend,
                                      @RequestParam(value = "sum") String sum,
                                      @RequestParam(value = "leftBound") String leftBound,
                                      @RequestParam(value = "rightBound") String rightBound) {
        counterService.incrementAndPrint();
        try {
            Equation equation = service.solveEquetion(new InputParameters(sum, addend, leftBound, rightBound));
            log.info("HTTP status 200, response :" + equation.toString());
            return ResponseEntity.ok(equation);
        } catch (NumberFormatException exception) {
            log.info("Bad parameters (not a number)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameters (not a number)");
        } catch (IllegalArgumentException exception) {
            log.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/solveEquations")
    public ResponseEntity getSolutions(@RequestBody ParametersList parametersList) {
        return ResponseEntity.ok(service.solveEquations(parametersList));
    }


    @GetMapping("/getResponseById")
    public ResponseEntity getResponseByID(@RequestParam(value = "responseId") String responseId) {
        EquationsList equationsList = service.getEquationsByID(responseId);
        if (equationsList == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found response with this id");
        }
        return ResponseEntity.ok(equationsList);
    }

}
