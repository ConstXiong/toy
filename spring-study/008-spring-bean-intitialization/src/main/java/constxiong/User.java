package constxiong;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class User implements InitializingBean {

	private String source;

	private Integer id;
	
	private String name;
	
	public User() {
	}

	@PostConstruct
	public void postConstruct() {
		this.id = 1;
		this.name = "cx1";
		System.out.println(this);
		System.out.println(source + ": User#postConstruct executed\n");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.id = 2;
		this.name = "cx2";
		System.out.println(this);
		System.out.println(source + ": User#afterPropertiesSet executed\n");
	}

	public void init() {
		this.id = 3;
		this.name = "cx3";
		System.out.println(this);
		System.out.println(source + ": User#init executed\n");
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
