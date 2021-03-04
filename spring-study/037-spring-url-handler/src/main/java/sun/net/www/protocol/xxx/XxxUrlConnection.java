package sun.net.www.protocol.xxx;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.core.io.ClassPathResource;

public class XxxUrlConnection extends URLConnection {
	
	private final ClassPathResource resource;

	protected XxxUrlConnection(URL url) {
		super(url);
		resource = new ClassPathResource(url.getPath());
	}

	@Override
	public void connect() throws IOException {
		
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return resource.getInputStream();
	}

}
