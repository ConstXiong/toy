package constxiong.impltype;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入的实现方式
 */
public class Test {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-dependency-injection-impltype.xml");
		User constxiong = (User)context.getBean("ConstXiong");
		System.out.println(constxiong);
		
		//api 构造、注入、组装、注册 bean
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getBeanFactory();
		AbstractBeanDefinition userBeanDefinition = new RootBeanDefinition(User.class);
		//构造方法注入
		beanFactory.registerBeanDefinition("ApiUser", userBeanDefinition);
		ConstructorArgumentValues argValues = new ConstructorArgumentValues();
		argValues.addIndexedArgumentValue(0, 1);
		//set 方法注入
		userBeanDefinition.setConstructorArgumentValues(argValues);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "ApiUser"));
		userBeanDefinition.setPropertyValues(propertyValues);
		System.out.println(beanFactory.getBean("ApiUser"));
    }

}
