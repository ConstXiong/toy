package constxiong;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;


public class User implements DisposableBean {
	
	@PreDestroy
	public void postConstruct() {
		System.out.println("@PreDestroy");
	}
	
	public void destroyMethod() {
		System.out.println("bean#destroyMethod");
	}
	
	public void destroy() {
		System.out.println("DisposableBean#destory");
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("will be gc");
		super.finalize();
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
