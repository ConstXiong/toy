package constxiong;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 测试 spring bean definition 元信息解析
 * 
 * 注册：
 * PropertiesBeanDefinitionReader#registerBeanDefinitions
 * XmlBeanDefinitionReader#registerBeanDefinitions
 * AnnotatedBeanDefinitionReader#register...
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		xml();
		properties();
		annotation();
	}

	/**
	 * PropertiesBeanDefinitionReader 间接实现 BeanDefinitionReader 接口
	 */
	private static void properties() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
		ClassPathResource resource = new ClassPathResource("META-INF/user.properties"); 
		reader.loadBeanDefinitions(new EncodedResource(resource, "utf-8"));
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}

	/**
	 * XmlBeanDefinitionReader 间接实现 BeanDefinitionReader 接口
	 */
	private static void xml() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("META-INF/spring-bean-lifecycle-bd-parse.xml"));
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}
	
	/**
	 * AnnotatedBeanDefinitionReader
	 */
	private static void annotation() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(); 
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(applicationContext);
		reader.register(Test.class);
		applicationContext.refresh();
		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);
	}
	
	@Bean
	static User user() {
		User u = new User();
		u.setName("annotation 方式");
		return u;
	}
	
}
