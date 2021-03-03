package constxiong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * 自定义 bean 实例化前后干预 BeanPostProcessor
 * 
 * @author ConstXiong
 */
public class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanClass.equals(User.class)) {
			System.out.println("before instantiation");
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (bean.getClass().equals(User.class)) {
			System.out.println("after instantiation");
		}
		return true;
	}

	
	
}
