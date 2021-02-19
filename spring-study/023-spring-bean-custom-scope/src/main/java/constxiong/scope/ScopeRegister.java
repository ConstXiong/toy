package constxiong.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 注册 ThreadLocalScope
 * 
 * @author ConstXiong
 */
@Component
public class ScopeRegister implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		WebApplicationContext context = (WebApplicationContext)applicationContext;
		DefaultListableBeanFactory beanFactory = ((DefaultListableBeanFactory)context.getAutowireCapableBeanFactory());
		beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
	}

}
