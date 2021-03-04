package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;




/**
 * 测试 spring bean 销毁和gc
 * 
 * @author ConstXiong
 * @date 2021年3月3日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		//1、AbstractApplicationContext#destroyBeans
		//  DefaultListableBeanFactory#destroySingletons
		//  DefaultSingletonBeanRegistry#destroyBean
		
		//2、DefaultListableBeanFactory#destroySingletons
		//  DissposableBeanAdapter#destroy
		//  CommonAnnotationBeanPostProcessor(InitDestroyAnnotationBeanPostProcessor)#postProcessBeforeDestruction
		//  LifeCycleMetadata#invokeDestroyMethods
		//  LifeCycleElement#invoke
		
		//3、DefaultListableBeanFactory#destroySingletons
		//  DisposableBeanAdapter#destroy
		
		//4、DefaultListableBeanFactory#destroySingletons
		//  DsiposableBeanAdapter#destroy -> invokeCustomDestroyMethod
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-lifecycle-destroy.xml");
		context.close();
		System.gc();
	}

}
