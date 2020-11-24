package constxiong.jvm.gc;

/**
 * 测试分配担保
 */
public class AllocatePromotion {

    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024]; //1M
        byte[] array2 = new byte[2 * 1024 * 1024]; //1M
        byte[] array3 = new byte[2 * 1024 * 1024]; //1M
        byte[] array4 = new byte[2 * 1024 * 1024]; //1M
        byte[] array5 = new byte[2 * 1024 * 1024]; //1M
        byte[] array6 = new byte[2 * 1024 * 1024]; //1M
    }

}
