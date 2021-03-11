package constxiong;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

/**
 * 监听文件自动更新 Properties
 * 
 * @author ConstXiong
 * @date 2021年3月8日 上午11:58:25
 */
public class AutoUpdateMessageResource extends AbstractMessageSource {
	
	public static final String PROPERTIES_FILE_NAME = "msg.properties";

	private Resource resource;
	
	private Properties properties;
	
	private boolean listen = true;
	
	private WatchService watchService;
	
	public AutoUpdateMessageResource() throws IOException {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		resource = resourceLoader.getResource(PROPERTIES_FILE_NAME);
		properties = new Properties();
		properties.load(resource.getInputStream());
		listenProperteisChange();
	}

	/**
	 * 监听properties文件修改
	 */
	private void listenProperteisChange() {
		try {
			Path filePath = this.resource.getFile().toPath();
			Path dirPath = filePath.getParent();
			this.watchService = FileSystems.getDefault().newWatchService();
			dirPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
			dealFileChange(watchService);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 监听和处理文件修改
	 */
	private void dealFileChange(WatchService watchService) {
		new Thread(() -> {
			while (listen) {
				WatchKey watchKey = null;
				InputStream is = null;
				try {
					watchKey = watchService.take();
					if (watchKey.isValid()) {
						for (WatchEvent<?> event : watchKey.pollEvents()) {
							Watchable watchable = watchKey.watchable();
							Path dirPath = (Path)watchable;
							Path relativePath = (Path)event.context();
							if (PROPERTIES_FILE_NAME.equals(relativePath.getFileName().toString())) {
								Path absolutePath = dirPath.resolve(relativePath);
								Properties p = new Properties();
								is = new FileInputStream(absolutePath.toFile());
								p.load(is);
								synchronized (this.properties) {
									this.properties.clear();
									this.properties.putAll(p);
								}
							}
						}
					}
				} catch (ClosedWatchServiceException e)  {
					this.listen = false;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (watchKey != null) {
						watchKey.reset();
					}
					if (is != null) {
						try {
							is.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String message = this.properties.getProperty(code);
		if (!StringUtils.isEmpty(message)) {
			return new MessageFormat(message, locale);
		}
		return null;
	}

	public void stopListen(long delay) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				this.listen = false;
				this.watchService.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
}
