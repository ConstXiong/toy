package constxiong;

public class TestPrimitiveAndBoxEquals {

    public static void main(String[] args) {
        Double d1 = 0.314;
        Double d2 = 0.314;
        Double d3 = new Double(0.314);
        Double d4 = new Double("0.314");
        System.out.println(d1 == d2);
        System.out.println(d1 == d2);
        System.out.println(d1 == d3);
        System.out.println(d1 == d4);
        System.out.println(d3 == d4);
        System.out.println(d1.equals(d2));
        System.out.println(d1.equals(d2));
        System.out.println(d1.equals(d3));
        System.out.println(d1.equals(d4));
        System.out.println(d3.equals(d4));
    }

}
