package constxiong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(name = "yaml", value = "META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
@Component("user")
public class User {

	@Value("${user.id}")
	private Integer id;

	//此处会注入 系统变量用户名
	@Value("${user.name}")
	private String name;
	
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

	public String toString() {
		return "User [id=" + id + ",name=" + name + "]";
	}

}
