package constxiong;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 测试内建 Bean
 * @author ConstXiong
 */
@Configuration
public class Test {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-inner-bean.xml");
		//外部化配置以及 Profiles
		Environment environment = context.getBean("environment", Environment.class);
		System.out.println(environment);
		
		//Java 系统属性
		Properties systemProperties = context.getBean("systemEnvironment", Properties.class);
		System.out.println(systemProperties);
		
		//操作系统环境变量
		Map systemEnvironment = context.getBean("systemEnvironment", Map.class);
		System.out.println(systemEnvironment);
		
		//国际化文案
		MessageSource messageSource = context.getBean("messageSource", MessageSource.class);
		System.out.println(messageSource);
		
		//Lifecycle Bean 处理器
		LifecycleProcessor lifecycleProcessor = context.getBean("lifecycleProcessor", LifecycleProcessor.class);
		System.out.println(lifecycleProcessor);
		
		//Spring 事件广播器
		ApplicationEventMulticaster applicationEventMulticaster = context.getBean("applicationEventMulticaster", ApplicationEventMulticaster.class);
		System.out.println(applicationEventMulticaster);
		
		/**
		 * 注解驱动的 context
		 */
		AnnotationConfigApplicationContext annotatedContext = new AnnotationConfigApplicationContext();
		annotatedContext.refresh();
		
		//处理 Spring 配置类
		ConfigurationClassPostProcessor configurationClassPostProcessor = annotatedContext.getBean(ConfigurationClassPostProcessor.class);
		System.out.println(configurationClassPostProcessor);
		
		//处理 @Autowired、@Value 注解
		AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = annotatedContext.getBean(AutowiredAnnotationBeanPostProcessor.class);
		System.out.println(autowiredAnnotationBeanPostProcessor);
		
		//(条件激活)处理 JSR-250 注解， 如 @PostConstruct 等
		CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor = annotatedContext.getBean(CommonAnnotationBeanPostProcessor.class);
		System.out.println(commonAnnotationBeanPostProcessor);
		
		//处理标注 @EventListener 的 Spring 事件监听方法
		EventListenerMethodProcessor eventListenerMethodProcessor = annotatedContext.getBean(EventListenerMethodProcessor.class);
		System.out.println(eventListenerMethodProcessor);
		
		//@EventListener 事件监听方法适配为 ApplicationListener
		DefaultEventListenerFactory defaultEventListenerFactory = annotatedContext.getBean(DefaultEventListenerFactory.class);
		System.out.println(defaultEventListenerFactory);
		
		//JPA 注解
//		PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor = annotatedContext.getBean(PersistenceAnnotationBeanPostProcessor.class);
//		System.out.println(persistenceAnnotationBeanPostProcessor);
		
	}

}

