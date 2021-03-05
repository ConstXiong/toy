package constxiong;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * 测试 resource loader 注入
 * 
 * Resource 加载器
 *   org.springframework.core.io.ResourceLoader
 *   org.springframework.core.io.DefaultResourceLoader
 *     org.springframework.core.io.FileSystemResourceLoader
 *     org.springframework.core.io.ClassRelativeResourceLoader
 *     org.springframework.context.support.AbstractApplicationContext
 * 
 * @author ConstXiong
 */
public class Test implements ResourceLoaderAware {
	
	private ResourceLoader resourceLoader;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	@Autowired
	private ResourceLoader injectedResourceLoader;
	
	@Autowired
	private ApplicationContext context;
	
	@PostConstruct
	public void print() {
		//系统将 ApplicationContext 作为 Resource Loader
		System.out.println(resourceLoader == injectedResourceLoader);
		System.out.println(resourceLoader == context);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		new AnnotationConfigApplicationContext(Test.class);
	}

}
