package constxiong.datatype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入-可注入的数据类型
 */
public class Test {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/spring-dependency-injection-datatype.xml");
		User constxiong = (User)factory.getBean("ConstXiong");
		System.out.println(constxiong);
		
		SpecialUser specialUser = (SpecialUser)factory.getBean("specialUser");
		//Spring 自建 bean，并未在 spring-dependency-injection-datatype.xml 配置文件中申明，却可以注入与依赖查找
		System.out.println(specialUser.getEnvironment());//通过 autowire="byType" 可以注入成功
		System.out.println(factory.getBean(Environment.class));
		
		//非 bean，可以注入，但无法通过 getBean 方法依赖查找
		System.out.println(specialUser.getBeanFactory());//通过 autowire="byType" 可以注入成功
		System.out.println(factory.getBean(BeanFactory.class));//getBean 方法无法获取到 BeanFactory
    }

}
