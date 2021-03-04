package constxiong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;


public class User implements InitializingBean {
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("@PostConstruct");
	}
	
	public void init() {
		System.out.println("init");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean#afterPropertiesSet");
	}
	
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

}
