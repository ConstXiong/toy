package constxiong.jvm.gc;

/**
 * 通过 finilize 方法逃离被回收
 */
public class EscapeByFinilizeOnce {

    private static EscapeByFinilizeOnce fs = null;

    public static void main(String[] args) throws InterruptedException {
        fs = new EscapeByFinilizeOnce();

        fs = null;
        System.gc();
        Thread.sleep(3000L);
        if (fs != null) {
            System.out.println("成功逃离被回收");
        } else {
            System.out.println("对象被回收");
        }

        fs = null;
        System.gc();
        Thread.sleep(3000L);
        if (fs != null) {
            System.out.println("再次成功逃离被回收");
        } else {
            System.out.println("对象被回收");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finilize execute");
        fs = this;
    }
}
