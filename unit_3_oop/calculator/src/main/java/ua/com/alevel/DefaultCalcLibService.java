package ua.com.alevel;

import ua.com.alevel.CalcLibService;

import java.math.BigInteger;

public class DefaultCalcLibService implements CalcLibService {

    @Override
    public BigInteger sum(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger multi(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    @Override
    public BigInteger divide(BigInteger first, BigInteger second) {
        try {
            return first.divide(second);
        } catch (Exception e) {
            System.err.println("Don't divide by zero");
            throw e;
        }
    }

}
