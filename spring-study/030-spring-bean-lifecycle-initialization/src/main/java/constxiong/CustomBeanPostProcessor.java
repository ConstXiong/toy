package constxiong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自定义 BeanPostProcessor，处理初始化前、后、单例完成
 * 
 * @author ConstXiong
 * @date 2021年3月4日 上午9:05:25
 */
public class CustomBeanPostProcessor implements BeanPostProcessor, SmartInitializingSingleton {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean.getClass().equals(User.class)) {
			System.out.println("CustomBeanPostProcessor#postProcessBeforeInitialization");
		}
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean.getClass().equals(User.class)) {
			System.out.println("CustomBeanPostProcessor#postProcessAfterInitialization");
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("SmartInitializingSingleton#afterSingletonsIntantiated");
	}

}
