package constxiong;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试 Bean 依赖注入 @Autowired 注解及其自定义注解
 *
 * @author ConstXiong
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("constxiong");
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}