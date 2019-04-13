package com.bsuir.Zakharchenia.Parameters;


public class InputParameters {
    private String sum;
    private String addend;
    private String leftBound;
    private String rightBound;

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
    public String toString() {
        return "InputParameters{" +
                "sum='" + sum + '\'' +
                ", addend='" + addend + '\'' +
                ", leftBound='" + leftBound + '\'' +
                ", rightBound='" + rightBound + '\'' +
                '}';
    }
}



