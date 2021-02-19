package constxiong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Bean {
	
	// xml <bean>
	@Resource(name="user")
	private User user;
	
	//BeanDefinitionRegistry#registerBeanDefinition
	@Resource(name="user1")
	private User user1;
	
	//SingletonBeanRegistry#registerSingleton
	@Resource(name="user2")
	private User user2;
	
	//注解 @Bean
	@Resource(name="user3")
	private User user3;
	
	//注解 @Configuration，@Component 派生出的注解
	@Autowired
	private Test test;
	
	//IoC 容器内建 bean
	@Autowired
	private ConfigurationClassPostProcessor configurationClassPostProcessor;
	
	//ConfigurableListableBeanFactory#registerResolvableDependency
	@Autowired(required=false)
	private String who;
	
	//外部化配置，config.properties 文件中的配置值
	@Value("${echo}")
	private String echo;

	public String toString() {
		return "Bean [user=" + user + ",\n user1=" + user1 + ",\n user2=" + user2
				+ ",\n user3=" + user3 + ",\n test=" + test
				+ ",\n configurationClassPostProcessor="
				+ configurationClassPostProcessor + ",\n who=" + who + ",\n echo="
				+ echo + "]";
	}
	
}
