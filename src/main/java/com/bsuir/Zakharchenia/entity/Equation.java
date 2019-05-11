package com.bsuir.Zakharchenia.entity;


import lombok.Data;

import java.util.Objects;


@Data
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

    @Override
    public String toString() {
        return "Equation{" +
                "id=" + id +
                ", solution=" + solution +
                ", isInGap=" + isInGap +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    public boolean isInGap() {
        return isInGap;
    }

    public void setInGap(boolean inGap) {
        isInGap = inGap;
    }
}





