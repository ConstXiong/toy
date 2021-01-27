package constxiong;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试 Bean 支持注解配置的 ApplicationContext
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("constxiong");
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);
		User user2 = context.getBean("user2", User.class);
		System.out.println(user2);
	}
}

@Configuration
class Config {

	@Bean("user2")
	User getUser() {
		User user = new User();
		user.setId(2);
		user.setName("user2");
		return user;
	}
}