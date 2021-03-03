package constxiong;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;


/**
 * 测试 spring bean 实例化前、中、后、属性设置
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions("META-INF/spring-bean-lifecycle-instantiation.xml");
		//新增 bean 实例化前后处理逻辑
		beanFactory.addBeanPostProcessor(new CustomInstantiationAwareBeanPostProcessor());
		//AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation，实例化前
		//AbstractAutowireCapableBeanFactory#instantiateBean，实例化中
		//beanInstance = getInstantiationStrategy().instantiate(mbd, beanName, parent); 获取实例化策略进行实例化
		//AbstractAutowireCapableBeanFactory#populateBean，实例化后
		//AbstractAutowireCapableBeanFactory#populateBean，属性设置
		System.out.println(beanFactory.getBean("user", User.class));
	}

}
