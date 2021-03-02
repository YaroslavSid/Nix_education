package ua.com.alevel.main;

import org.apache.commons.math3.util.MathUtils;

public class AntOne {
    public String ant(){
        number();
        return "ant";
    }
    public void number() {
        Integer integer = 1;
        MathUtils.checkNotNull(integer);
    }
}
