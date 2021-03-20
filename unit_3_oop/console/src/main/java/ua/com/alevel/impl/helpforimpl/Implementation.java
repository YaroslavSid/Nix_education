package ua.com.alevel.impl.helpforimpl;

public class Implementation {
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getCondition() {
        return condition;
    }

    int a;
    int b;
    String condition;

    public Implementation(int a1, String c, int b1){
        this.a = a1;
        this.condition = c;
        this.b = b1;

    }
}
