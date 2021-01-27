package constxiong;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Friend {

	@Bean
	public User getUser() {
		User user = new User();
		return user;
	}

	private Integer id;

	private String name;

	public Friend() {
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

	@Override
	public String toString() {
		return "Friend{" +
				"id=" + id +
				", name='" + name + '\'' +
				"}";
	}
}
