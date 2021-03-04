package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * 测试 spring bean 初始化前、中、后、单例完成
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
//		//beanFactory @PostConstruct 方法不会执行;CustomBeanPostProcessor的回到方法不会执行
//		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//		new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions("META-INF/spring-bean-lifecycle-initialization.xml");
//		System.out.println(beanFactory.getBean("user", User.class));

		
		
		//1、AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization
		//  CustomBeanPostProcessor#postProcessBeforeInitialization
		
		//2、AbstractAutowireCapableBeanFacotry#applyBeanPostProcessorsBeforeInitialization,
		//  CommonAnnotationBeanPostProcessor(InitDestroyAnnotationBeanPostProcessor)#postProcessBeforeInitialization
		//  InitDestroyAnnotationBeanPostProcessor#invokeInitMethods
		
		//3、AbstractAutowireCapableBeanFactory#createBean->doCreateBean->InitailizeBean->invokeInitMethods
		
		//4、AbstractAutowireCapableBeanFactory#invokeInitMethods->invokeCustomInitMethod
		
		//5、AbstractAutowireCapableBeanFactory#applyBeanPostProcessorAfterInitialization
		//  CustomBeanPostProcessor#postProcessAfterInitialization
		
		//6、AbstractApplicationContext#finishBeanFactoryInitialization Spring4.1+
		//  DefaultListableBeanFactory#preInstantiateSingletons
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lifecycle-initialization.xml");
		System.out.println(context.getBean("user", User.class));
	}

}
