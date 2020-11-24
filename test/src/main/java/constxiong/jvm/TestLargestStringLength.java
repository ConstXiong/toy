package constxiong.jvm;

public class TestLargestStringLength {

    public static void main(String[] args) {
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "0";
            System.out.println(s.length());
        }
        System.out.println(s);
    }
}
