package constxiong;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 测试 spring FileSystemResource FileSystemResourceLoader EncodedResource
 * 
 * @author ConstXiong
 */
public class Test {
	
	static String filePath = Test.class.getClassLoader().getResource("a.txt").getPath();
	
	public static void main(String[] args) throws IOException {
		printSystemFileResource();
		printSystemFileByEncodedResource();
		printSystemFileByLoader();
	}

	/**
	 * EncodedResource 包装指定字符集
	 * @throws IOException 
	 */
	private static void printSystemFileByEncodedResource() throws IOException {
		FileSystemResource resource = new FileSystemResource(filePath);
		EncodedResource er = new EncodedResource(resource, "utf-8");
		System.out.println(IOUtils.toString(er.getReader()));
	}

	/**
	 * FileSystemResourceLoader 打开系统文件资源
	 */
	private static void printSystemFileByLoader() throws IOException {
		FileSystemResourceLoader fsrl = new FileSystemResourceLoader();
		Resource resource = fsrl.getResource(filePath);
		System.out.println(IOUtils.toString(resource.getInputStream(), "utf-8"));
	}

	/**
	 * 直接打印系统文件资源
	 */
	private static void printSystemFileResource() throws IOException {
		FileSystemResource resource = new FileSystemResource(filePath);
		System.out.println(IOUtils.toString(resource.getInputStream(), "utf-8"));
	}

}
