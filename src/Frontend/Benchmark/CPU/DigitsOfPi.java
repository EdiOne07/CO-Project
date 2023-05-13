package Frontend.Benchmark.CPU;

import java.math.*;

import Frontend.Benchmark.IBenchmark;

public class DigitsOfPi implements IBenchmark {

    @Override
    public void run() {
        //Gauss–Legendre algorithm

        int iterations = 10;
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1).divide(new BigDecimal(Math.sqrt(2)), iterations, RoundingMode.HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal p = new BigDecimal(1);

        for (int i = 0; i < iterations; i++) {
            BigDecimal aPrev = a;
            BigDecimal bPrev = b;
            BigDecimal tPrev = t;
            BigDecimal pPrev = p;

            a = aPrev.add(bPrev).divide(new BigDecimal(2), iterations, RoundingMode.HALF_UP);
            b = new BigDecimal(Math.sqrt(aPrev.multiply(bPrev).doubleValue())).setScale(iterations, RoundingMode.HALF_UP);
            t = tPrev.subtract(pPrev.multiply(aPrev.subtract(a).pow(2)));
            p = pPrev.multiply(new BigDecimal(2));
        }

        // System.out.println( a.add(b).pow(2).divide(t.multiply(new BigDecimal(4)), iterations, RoundingMode.HALF_UP));
    }


    @Override
    public void run(Object... params) {
        // TODO Auto-generated method stub

        int iterations = (Integer)params[0];
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1).divide(new BigDecimal(Math.sqrt(2)), iterations, RoundingMode.HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal p = new BigDecimal(1);

        for (int i = 0; i < iterations; i++) {
            BigDecimal aPrev = a;
            BigDecimal bPrev = b;
            BigDecimal tPrev = t;
            BigDecimal pPrev = p;

            a = aPrev.add(bPrev).divide(new BigDecimal(2), iterations, RoundingMode.HALF_UP);
            b = new BigDecimal(Math.sqrt(aPrev.multiply(bPrev).doubleValue())).setScale(iterations, RoundingMode.HALF_UP);
            t = tPrev.subtract(pPrev.multiply(aPrev.subtract(a).pow(2)));
            p = pPrev.multiply(new BigDecimal(2));
        }

        //BigDecimal pi = a.add(b).pow(2).divide(t.multiply(new BigDecimal(4)), iterations, RoundingMode.HALF_UP);
    }

    @Override
    public void initialize(Object... params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clean() {
        // TODO Auto-generated method stub

    }

    @Override
    public void cancel() {
        // TODO Auto-generated method stub

    }

    @Override
    public void warmUp() {
        // TODO Auto-generated method stub

        int iterations = 10000;
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1).divide(new BigDecimal(Math.sqrt(2)), iterations, RoundingMode.HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal p = new BigDecimal(1);

        for (int i = 0; i < iterations; i++) {
            BigDecimal aPrev = a;
            BigDecimal bPrev = b;
            BigDecimal tPrev = t;
            BigDecimal pPrev = p;

            a = aPrev.add(bPrev).divide(new BigDecimal(2), iterations, RoundingMode.HALF_UP);
            b = new BigDecimal(Math.sqrt(aPrev.multiply(bPrev).doubleValue())).setScale(iterations, RoundingMode.HALF_UP);
            t = tPrev.subtract(pPrev.multiply(aPrev.subtract(a).pow(2)));
            p = pPrev.multiply(new BigDecimal(2));
        }

        //BigDecimal pi = a.add(b).pow(2).divide(t.multiply(new BigDecimal(4)), iterations, RoundingMode.HALF_UP);

    }


    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

}
