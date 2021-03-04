package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 spring yaml 文件作为 PropertySource
 * 
 * @author ConstXiong
 * @date 2021年3月4日 下午3:41:15
 */
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-property-source-yaml.xml");
		System.out.println(context.getBean("user"));
		System.out.println(context.getBean("yaml"));
	}
	
}
