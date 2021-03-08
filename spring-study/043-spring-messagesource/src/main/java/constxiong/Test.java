package constxiong;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 测试 spring 国际化
 * 
 * ResourceBundleMessageSource = ResourceBundle + MessageSource
 * ReloadableResourceBundleMessageSource 可重载
 * StaticMessageSource 通过程序注册国际化msg
 * DelegatingMessageSource 层次 HierarchicalMessageSource 的实现 
 * 
 * @author ConstXiong
 */
public class Test {
	
	private static Map<Locale, String> titles = new HashMap<Locale, String>();
	static {
		titles.put(Locale.SIMPLIFIED_CHINESE, "测试 spring 国际化");
		titles.put(Locale.US, "spring i18n test");
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ResourceBundleMessageSource mbms = new ResourceBundleMessageSource();
		mbms.setBasename("msg");
		System.out.println(mbms.getMessage("name", null, Locale.SIMPLIFIED_CHINESE));
		System.out.println(mbms.getMessage("name", null, Locale.US));
		
		mbms.setBasename("title");
		System.out.println(mbms.getMessage("title", new Object[]{titles.get(Locale.SIMPLIFIED_CHINESE)}, Locale.SIMPLIFIED_CHINESE));
		System.out.println(mbms.getMessage("title", new Object[]{titles.get(Locale.US)}, Locale.US));
		
		ReloadableResourceBundleMessageSource rrbs = new ReloadableResourceBundleMessageSource();
		rrbs.setCacheMillis(1);//设置缓存 1 毫秒
		rrbs.setBasename("msg");
		System.out.println(rrbs.getMessage("name", null, Locale.US));
		
		//修改 properties 信息，验证获取 name 信息是否修改
		IOUtils.write("name=xj", new FileOutputStream(Test.class.getClassLoader().getResource("msg_en_US.properties").getFile()), "utf-8");
		Thread.sleep(1000L);
		System.out.println(rrbs.getMessage("name", null, Locale.US));
	}
	
}
