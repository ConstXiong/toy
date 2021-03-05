package constxiong;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * 测试 JDK 1.8 资源协议扩展
 * 
 * file   sun.net.www.protocol.file.Handler
 * ftp    sun.net.www.protocol.ftp.Handler
 * http   sun.net.www.protocol.http.Handler
 * https  sun.net.www.protocol.https.Handler
 * jar    sun.net.www.protocol.jar.Handler
 * mailto sun.net.www.protocol.mailto.Handler
 * netdoc sun.net.www.protocol.netdoc.Handler
 * 
 * 默认 sun.net.www.protocol.${protocol}.Handler
 * 自定义通过Java Properties java.protocol.handler.pkgs 指定实现类包名，实现类名必须为“Handler”。如果存在多包名指定，通过分隔符 |
 * 
 * @author ConstXiong
 */
public class Test {

	public static void main(String[] args) throws IOException {
		URL url = new URL("xxx:///META-INF/a.txt");
		InputStream is = url.openStream();
		System.out.println(IOUtils.toString(is, "utf-8"));
		is.close();
	}
	
}
