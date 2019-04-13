package com.bsuir.Zakharchenia.answers;

import com.bsuir.Zakharchenia.entity.Equation;
import lombok.Data;

import java.util.ArrayList;


@Data
public class EquationsList {
    private ArrayList<Equation> equations = new ArrayList<>();

    public EquationsList(ArrayList<Equation> equations) {
        this.equations = equations;
    }

    public EquationsList() {

    }

    public ArrayList<Equation> getEquations() {
        return equations;
    }

    public void setEquations(ArrayList<Equation> equations) {
        this.equations = equations;
    }

    @Override
    public String toString() {
        return "EquationsList{" +
                "equations=" + equations +
                '}';
    }
}
