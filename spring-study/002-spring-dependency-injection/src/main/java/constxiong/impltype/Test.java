package constxiong.impltype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入的实现方式
 */
public class Test {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/spring-dependency-injection-impltype.xml");
		User constxiong = (User)factory.getBean("ConstXiong");
		System.out.println(constxiong);
    }

}
