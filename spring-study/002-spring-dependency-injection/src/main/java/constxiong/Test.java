package constxiong;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入
 */
public class Test {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/spring-dependency-injection.xml");
		User constxiong = (User)factory.getBean("ConstXiong");
		System.out.println(constxiong);
		User user = (User)factory.getBean("user");
		System.out.println(user);
		
		System.out.println(constxiong.getObjectFacotory().getObject() == factory);
		
		System.out.println(constxiong.getBeanFactory());
		BeanFactory beanFactory = (BeanFactory)factory.getBean(BeanFactory.class);
		System.out.println(beanFactory);
    }

}
