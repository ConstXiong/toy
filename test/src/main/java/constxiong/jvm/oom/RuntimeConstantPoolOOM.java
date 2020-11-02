package constxiong.jvm.oom;

import java.util.*;

/**
 * 常量池 OOM
 * jvm param：-Xmx6M
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		short i = 0;
		while (true) {
			set.add(String.valueOf(i++).intern());
		}
	}
	
}