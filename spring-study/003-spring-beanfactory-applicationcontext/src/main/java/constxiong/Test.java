package constxiong;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 BeanFactory 与 ApplicationContext 的区别
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		String xmlPath = "META-INF/spring-beanfactory-applicationcontext.xml";
		getBeanByBeanFactory(xmlPath);
		getBeanByApplicationContext(xmlPath);
	}

	/**
	 * 通过 ClassPathXmlApplicationContext 获取 bean
	 */
	private static void getBeanByApplicationContext(String xmlPath) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		User user = applicationContext.getBean(User.class);
		System.out.println(user);
	}

	/**
	 *  通过 DefaultListableBeanFactory 获取 bean
	 */
	private static void getBeanByBeanFactory(String xmlPath) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(beanFactory);
		xmlReader.loadBeanDefinitions(xmlPath);
		User user = beanFactory.getBean(User.class);
		System.out.println(user);
	}

}
