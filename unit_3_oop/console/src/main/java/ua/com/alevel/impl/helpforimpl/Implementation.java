package ua.com.alevel.impl.helpforimpl;

public class Implementation {

    private final String condition;
    private final String firstValue;
    private final String secondValue;

    public Implementation(String firstValue, String condition, String secondValue){
        this.firstValue = firstValue;
        this.condition = condition;
        this.secondValue = secondValue;

    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public String getCondition() {
        return condition;
    }



}
