package constxiong;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Bean 的命名规则
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-bean-name-generator.xml");
		// 指定名称：xml <bean> 指定 id、name；注解指定 name；BeanDefinition 指定 name；register Bean 指定名称
		// 比较容易理解不举例

		/**
		 * 常见的 Bean 命名规则
		 */
		// xml <bean> 未指定 id、name, 生成规则：package.类名、package.类名#编号
		User user1 = context.getBean("constxiong.User#0", User.class);
		System.out.println(user1);
		user1 = context.getBean("constxiong.User", User.class);
		System.out.println(user1);

		//@Component 未指定名称，生成规则：类名首字母小写 Test -> test;连续大写字母开头位置不变 TTest -> TTest
		Friend friend = context.getBean("friend", Friend.class);
		System.out.println(friend);

		//静态内部类 + @Configuration|@Component 未指定名称，生成规则：外部类的名称首字母小写.静态内部类名称
		Config config = context.getBean("test.Config", Config.class);
		System.out.println(config);

		//@Bean 未指定名称，生成规则：方法名
		User user2 = context.getBean("getUser", User.class);
		System.out.println(user2);

		// ListableBeanFactory 具有根据类型获取 bean 集合的能力，通过此方法可以查到所有注册的 Bean 名称
		System.out.println(context.getBeansOfType(User.class));
		System.out.println(context.getBeansOfType(Config.class));

		/**
		 * 详细的 Bean 命名规则，见 DefaultBeanNameGenerator、AnnotationBeanNameGenerator 两个类的 generateBeanName 方法
		 * DefaultBeanNameGenerator 涉及了 parent Bean - $child 的拼接；Factory Bean - $created 的拼接；相同类进行编号 - #0... 等操作
		 * AnnotationBeanNameGenerator 涉及了 获取注解指定的 name；去除包路径；类名首字母小写 的逻辑
		 * 往深了还有 bean name 覆盖问题
		 */

		// <context:component-scan base-package="constxiong"/> 可以添加 name-generator="constxiong.CustomBeanNameGenerator" 属性指定自定义 bean name 生成器
	}

	@Configuration
	static class Config {

	}
}

