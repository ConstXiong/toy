package constxiong;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import constxiong.condition.LinuxCondition;
import constxiong.condition.WindowsCondition;



/**
 * 测试 spring bean Conditional
 * 
 * @author ConstXiong
 * @date 2021年3月4日 下午3:41:15
 */
@Configuration
public class Test {
	
	@Bean
	@Conditional(WindowsCondition.class)
	private static User user4Windows() {
		User user = new User();
		user.setName("windows");
		return user;
	}
	
	@Bean
	@Conditional(LinuxCondition.class)
	private static User user4Linux() {
		User user = new User();
		user.setName("linux");
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
		System.out.println(context.getBean("user4Windows"));
		System.out.println(context.getBean("user4Linux"));//报错 NoSuchBeanDefinitionException
	}

}
