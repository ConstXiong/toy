package constxiong;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;


/**
 * 测试 spring bean property source
 * 
 * @author ConstXiong
 * @date 2021年3月4日 下午3:41:15
 */
public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-property-source.xml");
		// api org.springframework.core.env.PropertySource 方式扩展 PropertySrouce，替换过个相同属性
		Map<String, Object> propertiesExt = new HashMap<String, Object>();
		propertiesExt.put("id", -1);
		PropertySource<Map<String, Object>> propertySource = new MapPropertySource("first", propertiesExt);
		context.getEnvironment().getPropertySources().addFirst(propertySource);
		
		context.refresh();
		User user = context.getBean("user", User.class);
		System.out.println(user);
		System.out.println(context.getEnvironment().getPropertySources());
	}

}
