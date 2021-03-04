package constxiong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("META-INF/user-id.properties")
@Component
public class User {
	
	@Value("${id}")
	private String id;
	
	@Value("${name}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
