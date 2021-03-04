package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试 spring bean profile
 * 
 * 新增 jvm 参数 -Dspring.profiles.active=dev
 * 
 * @author ConstXiong
 * @date 2021年3月4日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		//DefaultBeanDefinitionDocumentReader#doRegisterBeanDefinitions
		//if (!getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) 判断  <beans> 节点 profile 属性是否与环境变量中的匹配
		//AbstractEnvironment#doGetActiveProfiles
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/spring-bean-profile-dev.xml","META-INF/spring-bean-profile-test.xml");
		User user = context.getBean("user", User.class);
		System.out.println(user);
	}

}
