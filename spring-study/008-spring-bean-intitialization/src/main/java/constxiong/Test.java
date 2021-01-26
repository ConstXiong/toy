package constxiong;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 的初始化
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		// xml init
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-initialization.xml");
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);

		// @Bean
		User user2 = context.getBean("user2", User.class);
		System.out.println(user2);

		//BeanDefinition
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("user3",
				BeanDefinitionBuilder.rootBeanDefinition(User.class).setInitMethodName("init").addPropertyValue("source", "BeanDefinition").getBeanDefinition());
		User user3 = context.getBean("user3", User.class);
		System.out.println(user3);
	}

	@Bean(initMethod = "init", name = "user2")
	public User getUser() {
		User user = new User();
		user.setSource("@Bean");
		return user;
	}

}
