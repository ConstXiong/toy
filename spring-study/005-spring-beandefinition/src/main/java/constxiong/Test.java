package constxiong;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 测试 BeanDefinition 构建
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// BeanDefinitionBuilder 构建 BeanDefinition
		BeanDefinitionBuilder builder = BeanDefinitionBuilder
				.genericBeanDefinition(User.class)
				.addPropertyValue("id", 1) //属性设置
				.addPropertyValue("name", "constxiong");
		beanFactory.registerBeanDefinition("user1", builder.getBeanDefinition());
		User user = beanFactory.getBean("user1", User.class);
		System.out.println(user);
		
		//直接 new 的方式，创建 BeanDefinitionBuilder 对象
		RootBeanDefinition beanDefinition = new RootBeanDefinition(User.class);
		//构造参数
		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addIndexedArgumentValue(0, 1);
		constructorArgumentValues.addIndexedArgumentValue(1, "user2");
		beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
		beanFactory.registerBeanDefinition("user2", beanDefinition);
		User user2 = beanFactory.getBean("user2", User.class);
		System.out.println(user2);
	}

}
