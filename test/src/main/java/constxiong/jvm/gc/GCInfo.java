package constxiong.jvm.gc;

/**
 * GC 信息打印
 * param: ：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class GCInfo {

    public static void main(String[] args) {
        byte[] a, b, c, d;
        a = new byte[2 * 1024 * 1024]; // 2M
        b = new byte[2 * 1024 * 1024]; // 2M
        c = new byte[2 * 1024 * 1024]; // 2M
        d = new byte[4 * 1024 * 1024]; // 4M
    }
}
