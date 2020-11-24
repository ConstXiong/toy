package constxiong.jvm.gc;

/**
 * 引用计数
 */
public class ReferenceCount {

    public ReferenceCount holder;

    /**
     * 占用 5 M
     */
    private byte[] appendMemory = new byte[5 * 1024 * 1024];

    public static void main(String[] args) {
        ReferenceCount obj1 = new ReferenceCount();
        ReferenceCount obj2 = new ReferenceCount();
        obj1.holder = obj2;//obj1 持有 obj2 对象
        obj2.holder = obj1;//obj2 持有 obj1 对象

        obj1 = null;
        obj2 = null;

        System.gc();
    }

}
