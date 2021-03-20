package ua.com.alevel;

import java.math.BigInteger;

public interface CalcLibService {

    BigInteger sum(BigInteger first, BigInteger second);

    BigInteger multi(BigInteger first, BigInteger second);

    BigInteger subtract(BigInteger first, BigInteger second);

    BigInteger divide(BigInteger first, BigInteger second);

}
