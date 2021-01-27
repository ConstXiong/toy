package constxiong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 的别名
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-bean-name-alias.xml");
		User user1 = context.getBean("user1", User.class);
		User user2 = context.getBean("user2", User.class);
		User user3 = context.getBean("user3", User.class);
		User user4 = context.getBean("user4", User.class);
		User user5 = context.getBean("user5", User.class);
		User user6 = context.getBean("user6", User.class);
		User user7 = context.getBean("user7", User.class);
		System.out.println("user1 == user2 ? " + (user1 == user2));
		System.out.println("user1 == user3 ? " + (user1 == user3));
		System.out.println("user1 == user4 ? " + (user1 == user4));
		System.out.println("user1 == user5 ? " + (user1 == user5));
		System.out.println("user6 == user7 ? " + (user6 == user7));
	}
	
	@Bean(name= {"user6","user7"})
	User getUer() {
		User user = new User();
		return user;
	}

}

