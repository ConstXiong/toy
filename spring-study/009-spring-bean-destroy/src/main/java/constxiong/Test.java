package constxiong;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 的销毁
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		// xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-destroy.xml");
		User user1 = context.getBean("user1", User.class);

		// @Bean
		User user2 = context.getBean("user2", User.class);

		//BeanDefinition
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("user3",
				BeanDefinitionBuilder.rootBeanDefinition(User.class).setDestroyMethodName("beanDestroy").addPropertyValue("source", "BeanDefinition").getBeanDefinition());
		context.getBean("user3");
		
		context.close();
	}

	@Bean(destroyMethod = "beanDestroy", name = "user2")
	public User getUser() {
		User user = new User();
		user.setSource("@Bean");
		return user;
	}

}
