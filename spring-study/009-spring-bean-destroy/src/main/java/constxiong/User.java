package constxiong;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;

public class User implements DisposableBean {

	private String source;

	private Integer id;
	
	private String name;
	
	public User() {
	}

	@PreDestroy
	public void preDestroy() {
		this.id = 1;
		this.name = "cx1";
		System.out.println(this);
		System.out.println(source + ": User#preDestroy executed\n");
	}

	@Override
	public void destroy() throws Exception {
		this.id = 2;
		this.name = "cx2";
		System.out.println(this);
		System.out.println(source + ": User implements DisposableBean#destroy executed\n");
	}

	public void beanDestroy() {
		this.id = 3;
		this.name = "cx3";
		System.out.println(this);
		System.out.println(source + ": User#beanDestroy executed\n");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "User{" +
				"source='" + source + '\'' +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
