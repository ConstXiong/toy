package constxiong;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试依赖查找、依赖注入来源的区别
 * 
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		System.out.println("依赖查找");
		lookup();//依赖查找
		System.out.println();
		System.out.println("依赖注入");
		inject();//依赖注入
	}

	/**
	 * 依赖查找的来源
	 */
	private static void lookup() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-lookup-inject-source.xml");
		
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("user1", 
				BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition());
		
		context.getBeanFactory().registerSingleton("user2", new User());
		
		// xml <bean>
		User user = context.getBean("user", User.class);
		System.out.println(user);
		
		//BeanDefinitionRegistry#registerBeanDefinition
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);
		
		//SingletonBeanRegistry#registerSingleton
		User user2 = context.getBean("user2", User.class);
		System.out.println(user2);
		
		//注解 @Bean
		User user3 = context.getBean("user3", User.class);
		System.out.println(user3);
		
		//注解 @Configuration，@Component 派生出的注解
		Test test = context.getBean("test", Test.class);
		System.out.println(test);
		
		//IoC 容器内建 bean
		//ConfigurationClassPostProcessor
		//AutowiredAnnotationBeanPostProcessor
		//CommonAnnotationBeanPostProcessor
		//EventListenerMethodProcessor
		//Environment
		//systemProperties — java.util.Properties
		//systemEnvironment — java.util.Map
		//MessageSource
		//LifecycleProcessor
		//ApplicationEventMulticaster
		ConfigurationClassPostProcessor innerBean = context.getBean(ConfigurationClassPostProcessor.class);
		System.out.println(innerBean);
		
	}
	
	@Bean
	public static User user3() {
		return new User();
	}
	
	/**
	 * 依赖注入的来源
	 */
	private static void inject() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-lookup-inject-source.xml");
		context.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.registerResolvableDependency(String.class, "constxiong");
		});
		context.refresh();
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("user1", 
				BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition());
		
		context.getBeanFactory().registerSingleton("user2", new User());
		constxiong.Bean bean = context.getBean("bean", constxiong.Bean.class);
		System.out.println(bean);
	}

}
