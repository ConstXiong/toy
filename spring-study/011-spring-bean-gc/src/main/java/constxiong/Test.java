package constxiong;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
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
		System.out.println(context.getBean("user1", User.class));
		BeanDefinitionRegistry registry = (DefaultListableBeanFactory)context.getBeanFactory();
		registry.removeBeanDefinition("user1");
		Thread.sleep(3000L);
		//从注册中心移除 BeanDefinition 并不能让 Bean 被 GC 掉
		//关闭 ioc 容器
		context.close();
		System.out.println("ioc 容器关闭");
		System.gc();
		Thread.sleep(3000L);
	}
}
