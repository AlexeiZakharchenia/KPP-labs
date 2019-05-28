package com.bsuir.Zakharchenia.answers;

import com.bsuir.Zakharchenia.entity.Equation;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "responses")
public class EquationsList {


    @Id
    @Column(name = "response_id")
    private String responseId;

    @ManyToMany
    @JoinTable(
            name = "response_equations",
            joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "equation_id")
    )
    private List<Equation> equations;

    public EquationsList(List<Equation> equations) {
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

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @Override
    public String toString() {
        return "EquationsList{" +
                "equations=" + equations +
                '}';
    }
}
