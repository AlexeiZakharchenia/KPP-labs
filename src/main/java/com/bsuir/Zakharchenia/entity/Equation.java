package com.bsuir.Zakharchenia.entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
@Data
@ToString
public class Equation {

    private long id;
    private int solution;
    private boolean isInGap;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return solution == equation.solution &&
                isInGap == equation.isInGap;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solution, isInGap);
    }

    public Equation(long id, int solution, boolean isInGap) {
        this.id = id;
        this.solution = solution;
        this.isInGap = isInGap;
    }
}





