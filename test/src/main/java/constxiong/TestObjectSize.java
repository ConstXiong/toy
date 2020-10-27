package constxiong;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * 测试对象占用的内存信息
 */
public class TestObjectSize {

    public static void main(String[] args) {
        System.out.println("对象内部信息");
        System.out.println(ClassLayout.parseInstance(new String("ConstXiong")).toPrintable());
        System.out.println(ClassLayout.parseInstance(new char[]{'C', 'o', 'n', 's', 't', 'X', 'i', 'o', 'n', 'g'}).toPrintable());

        System.out.println("对象整体信息");
        System.out.println(GraphLayout.parseInstance(new String("ConstXiong")).toPrintable());
        System.out.println(GraphLayout.parseInstance(new char[]{'C', 'o', 'n', 's', 't', 'X', 'i', 'o', 'n', 'g'}).toPrintable());

        System.out.println("对象占用总空间");
        System.out.println(GraphLayout.parseInstance(new String("ConstXiong")).totalSize());
        System.out.println(GraphLayout.parseInstance(new char[]{'C', 'o', 'n', 's', 't', 'X', 'i', 'o', 'n', 'g'}).totalSize());
    }
}
