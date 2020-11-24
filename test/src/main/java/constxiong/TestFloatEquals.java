package constxiong;

import java.math.BigDecimal;

/**
 * 测试 float 比较
 */
public class TestFloatEquals {

    public static void main(String[] args) {
        float f1 = 0.01f;
        float f2 = 0.01f;
        Float f3 = 0.01f;
        Float f4 = 0.01f;
        System.out.println(f1 == f2);
        System.out.println(f3.equals(f4));

        for (int i = 0; i < 9; i++) {
            f1 += 0.01f;
        }
        f2 = f2 * 10;

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);
        System.out.println(f1 - f2);
        System.out.println(Math.abs(f1 - f2) <= Float.MIN_VALUE);

        BigDecimal bd1 = new BigDecimal("0.01");
        for (int i = 0; i < 9; i++) {
            bd1.add(new BigDecimal("0.01"));
        }
        BigDecimal bd2 = new BigDecimal("0.01");
        bd2.multiply(new BigDecimal("10"));
        System.out.println(bd1.compareTo(bd2));
    }
}
