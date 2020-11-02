package constxiong.jvm.oom;

import java.util.*;

/**
 * 堆内存溢出
 * jvm param：-Xms20m -Xmx20m
 */
public class HeapOOM {
	
	static class OOMObject {}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}
}