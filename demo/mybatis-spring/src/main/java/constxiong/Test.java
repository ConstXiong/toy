package constxiong;

import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试 Spring 整合 MyBatis
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserMapper userMapper = context.getBean(UserMapper.class);

        System.out.println("------userMapper.deleteUsers()------");
        //删除 user
        userMapper.deleteUsers();

        System.out.println("------userMapper.insertUser()------");
        //插入 user
        for (int i = 1; i <= 5; i++) {
            userMapper.insertUser(new User(i, "ConstXiong" + i));
        }

        System.out.println("------userMapper.selectUsers()------");
        //查询所有 user
        List<User> users = userMapper.selectUsers();
        System.out.println(users);

    }

}
