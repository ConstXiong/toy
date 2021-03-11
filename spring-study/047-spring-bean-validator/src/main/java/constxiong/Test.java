package constxiong;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 测试 Spring bean Validator
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-bean-validator.xml"); 
		User user = context.getBean("user", User.class);
		user.setId(1);
		user.setName("aabbccddeeff");
		UserHolder userHolder = context.getBean("userHolder", UserHolder.class);
		userHolder.decorateUser(user);
		System.out.println(user);
	}

	@Component("userHolder")
	@Validated
	static class UserHolder {
		
		public void decorateUser(@Valid User user) {
			user.setName("constxiong");
		}
	}
	
	@Component("user")
	static class User {
		@NotNull
		private Integer id;
		
		@MaxLength(value=10, message="长度超过10")
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
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
	
}
