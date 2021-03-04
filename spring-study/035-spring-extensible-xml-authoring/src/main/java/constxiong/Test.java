package constxiong;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 测试 spring xml 扩展机制
 * 
 * 1、spring.schemas 文件中定义 url和对应的 xsd
 * 2、创建配置 xsd 文件
 * 3、spring.handlers 文件定义 url和对应的 handler类
 * 4、创建对应的 NamespaceHandler 和 BeanDefinitionParser 类
 * 5、处理 spring xml namespace，配置对应的节点
 * 6、获取对应的 bean
 * 
 * 触发：AbstractApplicationContext#obtainFreshBeanFactory
 *       AbstractXmlApplicationContext#loadBeanDefinitions
 *     XmlBeanDefinitionReader#doLoadBeanDefinitions
 *       BeanDefinitionParserDelegate#parseCustomElement
 * 
 * @author ConstXiong
 * @date 2021年3月4日 下午3:41:15
 */
public class Test {
	
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("META-INF/spring-extensible-xml-authoring.xml");
		System.out.println(beanFactory.getBean(User.class));
	}

}
