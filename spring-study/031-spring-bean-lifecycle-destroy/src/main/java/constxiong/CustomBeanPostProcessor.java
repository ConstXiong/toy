package constxiong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * 自定义 BeanPostProcessor，处理销毁
 * 
 * @author ConstXiong
 * @date 2021年3月4日 上午9:05:25
 */
public class CustomBeanPostProcessor implements DestructionAwareBeanPostProcessor {

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName)
			throws BeansException {
		if (bean.getClass().equals(User.class)) {
			System.out.println("DestructionAwareBeanPostProcessor#postProcessBeforeDestruction");
		}
	}


}
