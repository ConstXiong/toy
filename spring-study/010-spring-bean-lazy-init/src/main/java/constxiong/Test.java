package constxiong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 延迟加载及区别
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lazy-init.xml");
//		User user1 = context.getBean("user1", User.class);
//		System.out.println(user1);
//		User user2 = context.getBean("user2", User.class);
//		System.out.println(user2);
//		User user3 = context.getBean("user3", User.class);
//		System.out.println(user3);
	}

	@Bean(name = "user3")
	@Lazy
	static User getUser() {
		User user = new User(3);
		return user;
	}
}
