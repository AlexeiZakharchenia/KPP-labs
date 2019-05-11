package com.bsuir.Zakharchenia.answers;

import com.bsuir.Zakharchenia.entity.Equation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class EquationsList {
    private List<Equation> equations = new ArrayList<>();

    public EquationsList(ArrayList<Equation> equations) {
        this.equations = equations;
    }

    public EquationsList() {
    }

    public List<Equation> getEquations() {
        return equations;
    }

    public void setEquations(List<Equation> equations) {
        this.equations = equations;
    }

    @Override
    public String toString() {
        return "EquationsList{" +
                "equations=" + equations +
                '}';
    }
}
