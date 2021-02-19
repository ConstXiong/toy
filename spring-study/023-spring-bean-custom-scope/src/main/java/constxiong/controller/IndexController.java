package constxiong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import constxiong.User;
import constxiong.scope.ThreadLocalScope;

/**
 * controller
 * 
 * @author ConstXiong
 */
@Controller
public class IndexController {
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping("thread")
	@ResponseBody
	public String thread() {
		User user = context.getBean("user", User.class);
		return String.format("thread:%s, user:%s", Thread.currentThread().getId(), user);
	}
	
	@Bean
	@Scope(ThreadLocalScope.SCOPE_NAME)
	private static User user() {
		return new User();
	}
	
}
