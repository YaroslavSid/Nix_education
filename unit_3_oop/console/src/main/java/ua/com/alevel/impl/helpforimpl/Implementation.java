package ua.com.alevel.impl.helpforimpl;

public class Implementation {
    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public String getCondition() {
        return condition;
    }

    int firstValue;
    int secondValue;
    String condition;

    public Implementation(int firstValue, String condition, int secondValue){
        this.firstValue = firstValue;
        this.condition = condition;
        this.secondValue = secondValue;

    }
}
