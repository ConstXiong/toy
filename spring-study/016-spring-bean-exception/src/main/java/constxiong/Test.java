package constxiong;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 生命周期内的异常
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-bean-exception.xml");
		//NoSuchBeanDefinitionException，Bean 不存在
		exec(() -> context.getBean("user"));
		Thread.sleep(100L);

		//NoUniqueBeanDefinitionException，多个 Bean
		exec(() -> context.getBean(User.class));
		Thread.sleep(100L);

		//BeanInstantiationException，注册了无法实例化的 Bean
		exec(() -> {
			AnnotationConfigApplicationContext annotionApplicationContext = new AnnotationConfigApplicationContext();
			RootBeanDefinition beanDefinition = new RootBeanDefinition();
			beanDefinition.setBeanClass(UserInterface.class);
			annotionApplicationContext.registerBeanDefinition("userInter", beanDefinition);
			annotionApplicationContext.refresh();
			annotionApplicationContext.getBean("userInter");
		});
		Thread.sleep(100L);

		//BeanCreationException，Bean 的初始化过程报错，这里是 init 方法内
		exec(() -> {
			AnnotationConfigApplicationContext annotionApplicationContext = new AnnotationConfigApplicationContext();
			annotionApplicationContext.registerBeanDefinition("userImpl",
					BeanDefinitionBuilder.rootBeanDefinition(UserImpl.class).setInitMethodName("init").getBeanDefinition());
			annotionApplicationContext.refresh();
			context.getBean("userImpl");
		});
		Thread.sleep(100L);

		//BeanDefinitionStoreException，BeanFactory 遇到非法的 BeanDefinition，这里 xml 路径不对
		exec(() -> {
			ApplicationContext context1 = new ClassPathXmlApplicationContext("/META-INF/spring-bean-exception1.xml");
			context1.getBean("user1");
		});
	}

	private static void exec(Runnable r) {
		new Thread(r).start();
	}
}