package constxiong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

public class User implements BeanNameAware,
							 BeanClassLoaderAware,
							 BeanFactoryAware,
							 EnvironmentAware,
							 EmbeddedValueResolverAware,
							 ResourceLoaderAware,
							 ApplicationEventPublisherAware,
							 MessageSourceAware,
							 ApplicationContextAware {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware#setApplicationContext callback");
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		System.out.println("MessageSourceAware#setMessageSource callback");
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("ApplicationEventPublisherAware#setApplicationEventPublisher callback");
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("ResourceLoaderAware#setResourceLoader callback");
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		System.out.println("EmbeddedValueResolverAware#setEmbeddedValueResolver callback");
	}

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("EnvironmentAware#setEnvironment callback");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryAware#setBeanFactory callback");
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("BeanClassLoaderAware#setBeanClassLoader callback");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware#setBeanName callback");
	}

}
