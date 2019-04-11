package com.bsuir.Zakharchenia.controller;

import com.bsuir.Zakharchenia.Counter.CounterService;
import com.bsuir.Zakharchenia.Counter.CounterServiceImpl;
import com.bsuir.Zakharchenia.entity.Equation;
import com.bsuir.Zakharchenia.service.EquationService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;


@RestController
public class EquationController {


    private CounterService counterService = CounterServiceImpl.getInstance();
    private EquationService service;
    private static final Logger log = Logger.getLogger(EquationController.class);

    @Autowired
    public EquationController(EquationService service) {
        this.service = service;
    }

    @RequestMapping("/solveEquation")
    public ResponseEntity get_solution(@RequestParam(value = "addend") String addend,
                                       @RequestParam(value = "sum") String sum,
                                       @RequestParam(value = "leftBoard") String leftBoard,
                                       @RequestParam(value = "rightBoard") String rightBoard) {
        counterService.increment();
        log.info("Counter of requests on server:" + counterService.getCounter().toString());
        try {
            Equation equation = service.solveEquetion(addend, sum,
                                                      leftBoard, rightBoard);
            if (equation == null) {
                log.info("Incorrect parameters(right board < left board)");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect parameters(right board < left board)");

            }
            log.info("HTTP status 200, response :" + equation.toString());
            return ResponseEntity.ok(equation);
        } catch (NumberFormatException exception) {
            log.info("Bad parameters (not a number)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameters (not a number)");
        }

    }

}
