package com.bsuir.Zakharchenia.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "equations")
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int solution;

    @Column(name = "in_gap")
    private boolean inGap;


    public Equation() {
    }

    public Equation(int solution, boolean inGap) {
        this.solution = solution;
        this.inGap = inGap;
    }

    public Equation(int id, int solution, boolean inGap) {
        this.id = id;
        this.solution = solution;
        this.inGap = inGap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return solution == equation.solution &&
                inGap == equation.inGap;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solution, inGap);
    }

    @Override
    public String toString() {
        return "Equation{" +
                "id=" + id +
                ", solution=" + solution +
                ", inGap=" + inGap +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    public boolean isInGap() {
        return inGap;
    }

    public void setInGap(boolean inGap) {
        this.inGap = inGap;
    }
}





