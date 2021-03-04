package constxiong.ooo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Handler extends sun.net.www.protocol.xxx.Handler {

	// jvm 参数 -Djava.protocol.handler.pkgs=constxiong
	public static void main(String[] args) throws IOException {
		URL url = new URL("ooo:///META-INF/a.txt");
		InputStream is = url.openStream();
		System.out.println(IOUtils.toString(is, "utf-8"));
	}
	
}
