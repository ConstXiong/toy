package constxiong;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;

/**
 * 监听文件自动更新 Properties
 * 
 * @author ConstXiong
 * @date 2021年3月8日 上午11:58:25
 */
public class AutoUpdateMessageResource extends AbstractMessageSource implements ResourceLoaderAware {

	

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		return null;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		
	}

}
