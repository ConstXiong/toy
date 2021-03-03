package constxiong;

import java.security.AccessController;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ClassUtils;


/**
 * 测试 spring bean class load
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions("META-INF/spring-bean-lifecycle-classloader.xml");
		//AbstractBeanFactory#resolveBeanClass
		//1、mbd.getBeanClass()，BeanDefinition beanClass 有值直接返回
		//2、判断是否需要进行class加载权限校验，System.getSecurityManager()、AccessController.doPrivileged
		//3、AbstractBeanFactory#getBeanClassLoader、AbstractBeanFactory#getTempClassLoader
		//4、AbstractBeanDefinition#resolveBeanClass、ClassUtils.forName(className, classLoader)
		System.out.println(beanFactory.getBean("user", User.class));
	}

}
