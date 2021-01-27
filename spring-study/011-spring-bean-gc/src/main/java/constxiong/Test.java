package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean gc
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-gc.xml");
		//刷新 ioc 容器
		context.refresh();
		User user1 = context.getBean("user1", User.class);
		System.out.println(user1);
		//关闭 ioc 容器
		context.close();
		System.gc();
		Thread.sleep(3000L);
	}
}
