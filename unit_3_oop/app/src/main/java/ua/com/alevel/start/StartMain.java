package ua.com.alevel.start;

import ua.com.alevel.CalcLibService;
import ua.com.alevel.ConsoleService;
import ua.com.alevel.factory.CalcFactory;
import ua.com.alevel.factory.ConsoleFactory;
import ua.com.alevel.impl.helpforimpl.Implementation;

import java.math.BigInteger;

public class StartMain {
    public final CalcLibService calcLib = CalcFactory.getInstance().getCalcLibService();
    public final ConsoleService service = ConsoleFactory.getInstance().getCalcLibService();


    public void run(){
        Implementation implementation =  service.check();
        int first = implementation.getFirstValue();
        int second = implementation.getSecondValue();
        String condition = implementation.getCondition();

        BigInteger firstValue = new BigInteger(String.valueOf(first));
        BigInteger secondValue = new BigInteger(String.valueOf(second));

        switch (condition){
            case "+":
                BigInteger a = calcLib.sum(firstValue,secondValue);
                System.out.println("Answer = " + a);
                break;
            case "*":
                BigInteger b = calcLib.multi(firstValue,secondValue);
                System.out.println("Answer = " + b);
                break;
            case "-":
                BigInteger c =calcLib.subtract(firstValue,secondValue);
                System.out.println("Answer = " + c);
                break;
            case "/":
                BigInteger d =calcLib.divide(firstValue,secondValue);
                System.out.println("Answer = " + d);
                break;
        }

    }
}
