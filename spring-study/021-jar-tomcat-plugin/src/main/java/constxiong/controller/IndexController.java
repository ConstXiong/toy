package constxiong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller
 * 
 * @author ConstXiong
 */
@Controller
public class IndexController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}
}
