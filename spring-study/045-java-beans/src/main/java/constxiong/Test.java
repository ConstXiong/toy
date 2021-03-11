package constxiong;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * 测试 Java beans 
 * 
 * JavaBeans 文档：https://www.oracle.com/java/technologies/javase/javabeans-spec.html
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(Bean.class);
		System.out.println("===============beanDescriptor===============");
		//Bean 的描述信息
		BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
		System.out.println(beanDescriptor);
		
		System.out.println("===============methodDescriptors===============");
		//方法描述信息，会查出 parent
		MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
		Stream.of(methodDescriptors).forEach(System.out::println);
		
		System.out.println("===============propertyDescriptors===============");
		//属性描述信息，会查出 parent
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		Stream.of(propertyDescriptors).forEach(System.out::println);
	}
	
}
