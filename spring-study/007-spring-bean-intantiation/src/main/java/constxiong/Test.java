package constxiong;

import java.util.ServiceLoader;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 的实例化
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-instantiation.xml");
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);
		
		User user2 = context.getBean("user2", User.class);
		System.out.println(user2);
		
		User user3 = context.getBean("user3", User.class);
		System.out.println(user3);
		
		User user4 = context.getBean("user4", User.class);
		System.out.println(user4);
		
		User user5 = (User) context.getBeanFactory().createBean(User.class, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		System.out.println(user5);
		
		// BeanDefinition
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("user6", BeanDefinitionBuilder.rootBeanDefinition(User.class).addPropertyValue("name", "BeanDefinition").getBeanDefinition());
		System.out.println(context.getBean("user6", User.class));
		
		// spi 方式
		ServiceLoader<UserFactoryInterface> serviceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
		System.out.println(serviceLoader.iterator().next().getUser());
		// spi JDK 原生代码
		ServiceLoader<UserFactoryInterface> loader = ServiceLoader.load(UserFactoryInterface.class, Thread.currentThread().getContextClassLoader());
		System.out.println(loader.iterator().next().getUser());
	}

}
