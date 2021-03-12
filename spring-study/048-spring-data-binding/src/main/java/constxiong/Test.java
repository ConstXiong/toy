package constxiong;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * 测试 Spring 数据绑定
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		User user = new User();
		DataBinder dataBinder = new DataBinder(user);
		Map<String, Object> map = new HashMap<>();
//		map.put("id", 1);
		map.put("name", "ConstXiong");
		map.put("age", 18);//User age 字段不存在
		map.put("favorite.name", "conding");
		PropertyValues pvs = new MutablePropertyValues(map);
		//DataBinder 配置
		dataBinder.setIgnoreUnknownFields(true);//是否忽略未知字段
		dataBinder.setIgnoreInvalidFields(true);//是否忽略非法字段
		dataBinder.setAutoGrowNestedPaths(true);//是否嵌套设置对象中的对象的值
		dataBinder.setRequiredFields("id");//需要字段
		dataBinder.bind(pvs);
		System.out.println(user);
		//获取绑定结果
		BindingResult bindingResult = dataBinder.getBindingResult();
		System.out.println(bindingResult.getAllErrors());
	}
	
}
