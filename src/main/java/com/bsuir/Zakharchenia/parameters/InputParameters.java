package com.bsuir.Zakharchenia.parameters;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "input_parameters")
public class InputParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "equation_id")
    private int equationId;
    @Column(name = "left_bound")
    private String leftBound;


    private String sum;
    private String addend;
    @Column(name = "right_bound")
    private String rightBound;

    public InputParameters() {
    }

    public InputParameters(int equationId, String sum, String addend, String leftBound, String rightBound) {
        this.equationId = equationId;
        this.sum = sum;
        this.addend = addend;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquationId() {
        return equationId;
    }

    public void setEquationId(int equationId) {
        this.equationId = equationId;
    }

    public InputParameters(String sum, String addend, String leftBound, String rightBound) {
        this.sum = sum;
        this.addend = addend;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getAddend() {
        return addend;
    }

    public void setAddend(String addend) {
        this.addend = addend;
    }

    public String getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(String leftBound) {
        this.leftBound = leftBound;
    }

    public String getRightBound() {
        return rightBound;
    }

    public void setRightBound(String rightBound) {
        this.rightBound = rightBound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParameters that = (InputParameters) o;
        return Objects.equals(sum, that.sum) &&
                Objects.equals(addend, that.addend) &&
                Objects.equals(leftBound, that.leftBound) &&
                Objects.equals(rightBound, that.rightBound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, addend, leftBound, rightBound);
    }

    @Override
    public String toString() {
        return "InputParameters{" +
                "sum='" + sum + '\'' +
                ", addend='" + addend + '\'' +
                ", leftBound='" + leftBound + '\'' +
                ", rightBound='" + rightBound + '\'' +
                '}';
    }
}



