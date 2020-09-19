package constxiong;

/**
 * 测试 finally return 执行位置为方法返回值的影响
 */
public class TestFinallyRetrun {

    public static void main(String[] args) {
        System.out.println(getString());
        System.out.println(getFinallyReturnString());
        System.out.println(getInt());
    }

    public static String getString() {
        String str = "A";
        try {
            str = "B";
            return str;
        } finally {
            System.out.println("finally change return string to C");
            str = "C";
        }
    }

    public static String getFinallyReturnString() {
        String str = "A";
        try {
            str = "B";
            return str;
        } finally {
            System.out.println("finally change return string to C");
            str = "C";
            return str;
        }
    }

    public static int getInt() {
        int i = 1;
        try {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }
}
