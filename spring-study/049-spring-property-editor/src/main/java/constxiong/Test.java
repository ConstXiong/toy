package constxiong;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 Spring 类型转换
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		testCustomPropertiesEditor();
		testCustomPropertiesEdirotRegistrar();
	}

	/**
	 * 测试自定义 PropertyEditor 的使用
	 */
	private static void testCustomPropertiesEditor() {
		CustomPropertyEditor propertyEditor = new CustomPropertyEditor();
		propertyEditor.setAsText("id=1\nname=constxiong");
		System.out.println(propertyEditor.getValue());
		System.out.println(propertyEditor.getAsText());
	}
	
	/**
	 * 测试自定义 PropertyEditorRegistrar 注册 PropertyEditor，自动转换 bean 的属性
	 */
	private static void testCustomPropertiesEdirotRegistrar() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-property-editor.xml");
		/*
		 * 设置与获取 ConversionService
		 * AbstractApplicationContext#finishBeanFactoryInitialization
		 * AbstractBeanFactory#setConversionService
		 * AbstractAutowireCapableBeanFactory#instantiateBean
		 * AbstractBeanFactory#getConversionService
		 */
		/*
		 * bean 创建过程中的属性转换
		 * AbstractAutowireCapableBeanFactory#populateBean -> applyPropertyValues
		 * BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertyValues）->
		 * setPropertyValues(PropertyValues) -> TypeConverter#convertIfNecessnary
		 * TypeConverterDelegate#convertIfNecessnary  -> PropertyEditor or ConversionService
		 */
		User user = context.getBean("user", User.class);
		System.out.println(user);
	}
	
}
