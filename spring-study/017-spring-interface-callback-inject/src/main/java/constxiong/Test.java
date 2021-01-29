package constxiong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 通过接口方式回调注入系统内置 bean
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-interface-callback-inject.xml");
		User user = context.getBean("user1", User.class);
		System.out.println(user);
	}

}

