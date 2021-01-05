package constxiong.datatype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.env.Environment;

public class SpecialUser {
	
	private BeanFactory beanFactory;
	
	private Environment environment;

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
