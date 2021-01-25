package constxiong;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 测试 Bean 注册
 * @author ConstXiong
 */
@Configuration
@Import(Other.class)
public class Test {
	
	public static void main(String[] args) {
		// xml <bean> 注册
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-register.xml");
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);

		// @Bean 注册
		User user2 = context.getBean("user2", User.class);
		System.out.println(user2);

		// @Component 注册
		Friend friend = context.getBean("test.Friend", Friend.class);
		System.out.println(friend);

		// @Import 注册
		Other other = context.getBean("constxiong.Other", Other.class);
		System.out.println(other);

		// BeanDefinitionRegistry#registerBeanDefinition() 注册
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(User.class);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("id", 3);
		propertyValues.addPropertyValue("name", "custom");
		beanDefinition.setPropertyValues(propertyValues);
		((DefaultListableBeanFactory)context.getBeanFactory()).registerBeanDefinition("custom", beanDefinition);
		User custom = context.getBean("custom", User.class);
		System.out.println(custom);

		// BeanDefinitionReaderUtils#registerWithGeneratedName() 注册
		beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(User.class);
		propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("id", 4);
		propertyValues.addPropertyValue("name", "util");
		beanDefinition.setPropertyValues(propertyValues);
		beanDefinition.setPrimary(true);
		BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, (DefaultListableBeanFactory)context.getBeanFactory());
		System.out.println(context.getBean(User.class));

		// AnnotatedBeanDefinitionReader#registerBean() 注册
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader((DefaultListableBeanFactory)context.getBeanFactory());
		reader.registerBean(User.class, "annotated");
		System.out.println(context.getBean("annotated", User.class));

		// SingletonBeanRegistry#registerBean() 注册
		context.getBeanFactory().registerSingleton("singleton", new User(6, "singleton"));
		System.out.println(context.getBean("singleton"));
	}

	@Bean
	static User user2() {
		return new User(1, "constxiong");
	}

	@Component
	static class Friend {
		private int id = 10;
		private String name = "friend";
		@Override
		public String toString() {
			return "Friend{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}
	}

}

class Other {
	private int id = 10;
	private String name = "other";
	@Override
	public String toString() {
		return "Other{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}