package constxiong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试 spring bean aware 接口回调
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
//		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions("META-INF/spring-bean-lifecycle-aware-callback.xml");
//		System.out.println(beanFactory.getBean("user", User.class));
		
		//ApplicationContext 内置的 Aware 对象多于 BeanFactory
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lifecycle-aware-callback.xml");
		System.out.println(context.getBean("user", User.class));
	}

}
