package constxiong;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 测试 BeanFactory 与 FactoryBean 的区别
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		String xmlPath = "META-INF/spring-beanfactory-factorybean.xml";
		getBeanByBeanFactory(xmlPath);
	}

	/**
	 *  通过 DefaultListableBeanFactory 获取 bean
	 */
	private static void getBeanByBeanFactory(String xmlPath) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(beanFactory);
		xmlReader.loadBeanDefinitions(xmlPath);
		User user = beanFactory.getBean("constxiong", User.class);
		System.out.println(user);
		
		user = beanFactory.getBean("customUser", User.class);
		System.out.println(user);
	}

}
