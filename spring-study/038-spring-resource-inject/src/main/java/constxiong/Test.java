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
 * 输入流          org.springframework.core.io.InputStreamSource
 * 只读资源      org.springframework.core.io.Resource
 * 可写资源      org.springframework.core.io.WritableResource
 * 编码资源      org.springframework.core.io.support.EncodedResource
 * 上下文资源  org.springframework.core.io.ContextResource
 * 
 * Bean 定义                               org.springframework.beans.factory.support.BeanDefinitionResource
 * 数组                                              org.springframework.core.io.ByteArrayResource
 * 类路径(classpath:/) org.springframework.core.io.ClassPathResource
 * 文件系统(file:/)     org.springframework.core.io.FileSystemResource
 * URL               org.springframework.core.io.UrlResource
 * ServletContext    org.springframework.web.context.support.ServletContextResource
 *
 * 可写资源接口
 * org.springframework.core.io.WritableResource
 *   org.springframework.core.io.FileSystemResource
 *   org.springframework.core.io.FileUrlResource（@since 5.0.2）
 *   org.springframework.core.io.PathResource（@since 4.0 & @Deprecated）
 * 编码资源接口
 * org.springframework.core.io.support.EncodedResource
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
