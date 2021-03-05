package constxiong;

import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 测试 resource 注入
 * 
 * @author ConstXiong
 */
public class Test {
	
	@Value("classpath:META-INF/a.txt")
	private Resource resource;
	
	@Value("classpath:META-INF/**")
	private Resource[] resources;
	
	@PostConstruct
	public void print() {
		System.out.println("========resource======");
		print(resource);
		System.out.println();
		System.out.println("========resource array======");
		Stream.of(resources).forEach(r -> print(r));
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		new AnnotationConfigApplicationContext(Test.class);
	}
	
	private static void print(Resource resource) {
		try {
			System.out.println(IOUtils.toString(resource.getInputStream(), "utf-8"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
