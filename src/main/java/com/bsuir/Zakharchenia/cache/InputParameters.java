package com.bsuir.Zakharchenia.cache;

import java.util.Objects;

public class InputParameters {
    private Integer sum;
    private Integer addend;
    private Integer leftBoad;
    private Integer rightBoard;

    public InputParameters(Integer sum, Integer addend, Integer leftBoad, Integer rightBoard) {
        this.sum = sum;
        this.addend = addend;
        this.leftBoad = leftBoad;
        this.rightBoard = rightBoard;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getAddend() {
        return addend;
    }

    public void setAddend(Integer addend) {
        this.addend = addend;
    }

    public Integer getLeftBoad() {
        return leftBoad;
    }

    public void setLeftBoad(Integer leftBoad) {
        this.leftBoad = leftBoad;
    }

    public Integer getRightBoard() {
        return rightBoard;
    }

    public void setRightBoard(Integer rightBoard) {
        this.rightBoard = rightBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParameters that = (InputParameters) o;
        return Objects.equals(sum, that.sum) &&
                Objects.equals(addend, that.addend) &&
                Objects.equals(leftBoad, that.leftBoad) &&
                Objects.equals(rightBoard, that.rightBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, addend, leftBoad, rightBoard);
    }
}


