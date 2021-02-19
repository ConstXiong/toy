package constxiong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import constxiong.User;

/**
 * controller
 * 
 * @author ConstXiong
 */
@Controller
public class IndexController {
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping("singleton")
	@ResponseBody
	public String singlton() {
		User user1 = context.getBean("user1", User.class);
		return user1.toString();
	}
	
	@RequestMapping("prototype")
	@ResponseBody
	public String prototype() {
		User user2 = context.getBean("user2", User.class);
		return user2.toString();
	}
	
	@RequestMapping("request")
	@ResponseBody
	public String request() {
		User user3 = context.getBean("user3", User.class);
		return user3.toString();
	}
	
	@RequestMapping("session")
	@ResponseBody
	public String session() {
		User user4 = context.getBean("user4", User.class);
		return user4.toString();
	}
	
	@RequestMapping("application")
	@ResponseBody
	public String application() {
		User user5 = context.getBean("user5", User.class);
		return user5.toString();
	}
	
	@Bean
	@Scope(WebApplicationContext.SCOPE_REQUEST)
	private static User user3() {
		return new User();
	}
	
	@Bean
	@Scope(WebApplicationContext.SCOPE_SESSION)
	private static User user4() {
		return new User();
	}
	
	@Bean
	@Scope(WebApplicationContext.SCOPE_APPLICATION)
	private static User user5() {
		return new User();
	}
}
