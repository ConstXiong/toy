package constxiong;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * 测试 JDK 1.8 资源协议扩展
 * 
 * @author ConstXiong
 */
public class Test {

	public static void main(String[] args) throws IOException {
		URL url = new URL("constxiong:///META-INF/a.txt");
		InputStream is = url.openStream();
		System.out.println(IOUtils.toString(is, "utf-8"));
		is.close();
	}
	
}
