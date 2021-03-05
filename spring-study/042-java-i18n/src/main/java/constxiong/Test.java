package constxiong;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 测试 java 国际化
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) throws IOException {
		getLocaleI18nProperties(Locale.US);
		getLocaleI18nProperties(Locale.getDefault());
		message();
		formatMessage();
	}
	
	/**
	 * 根据 Locale 获取在 properties 文件中获取国际化文案
	 */
	private static void getLocaleI18nProperties(Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("msg", locale);
		System.out.println(bundle.getString("name"));
	}
	
	/**
	 * {} 占位替换
	 */
	private static void message() {
		String name = ResourceBundle.getBundle("msg", Locale.getDefault()).getString("name");
		String msg = MessageFormat.format("我是 {0}， {1} 在写代码", name, new Date());
		System.out.println(msg);
	}

	/**
	 * 占位格式化
	 */
	private static void formatMessage() {
		MessageFormat msgFormat = new MessageFormat("我是 {0}， {1} 在写代码，{2} 行");
		msgFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		msgFormat.setFormat(2, new DecimalFormat("0"));
		String name = ResourceBundle.getBundle("msg", Locale.getDefault()).getString("name");
		System.out.println(msgFormat.format(new Object[]{name, new Date(), 10.00}));
	}

}
