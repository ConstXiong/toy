package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试 spring bean definition 合并
 * 
 * AbstractApplicationContext#invokeBeanFactoryPostProcessors
 * DefaultListableBeanFactory#doGetBeanNamesForType
 * AbstractBeanFactory#getMergedLocalBeanDefinition
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lifecycle-bd-merge.xml");
		// 合并 superUser[id=1] -> user[name=constxiong] -> user[id=1,name=constxiong]
		System.out.println(context.getBean("user", User.class));
	}

}
