package constxiong;

import java.io.IOException;
import java.util.Locale;


/**
 * 测试 spring 自定义 MessageResource 自动更新监听的文件
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		AutoUpdateMessageResource mr = new AutoUpdateMessageResource();
		//5 秒后停止监听
		mr.stopListen(5 * 1000L);
		//for 循环的时候手动修改 044-auto-update-message-resource\target\classes\msg.properties 文件 name 属性值，即可看到打印的值发生变化
		for (int i = 0; i < 10; i++) {
			System.out.println(mr.getMessage("name", null, Locale.getDefault()));
			Thread.sleep(1 * 1000L);
		}
	}
	
}
