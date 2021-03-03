package constxiong;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 测试 spring bean definition 元信息配置方式
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		xml();
		properties();
		annotation();
		api();
	}

	/**
	 * properties 方式
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
	 * xml 方式
	 */
	private static void xml() {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lifecycle-bd-meta.xml");
		User user = context.getBean("user", User.class);
		System.out.println(user);
	}
	
	/**
	 * 注解方式
	 */
	private static void annotation() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(Test.class);
		applicationContext.refresh();
		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);
	}
	
	/**
	 * api 方式
	 */
	private static void api() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.rootBeanDefinition(User.class).addPropertyValue("name", "api方式").getBeanDefinition();
		beanFactory.registerBeanDefinition("user", beanDefinition);
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}
	
	@Bean
	static User user() {
		User u = new User();
		u.setName("annotation 方式");
		return u;
	}
	
}
