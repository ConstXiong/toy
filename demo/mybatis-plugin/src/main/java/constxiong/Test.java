package constxiong;

import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试 Mybatis 插件
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";//xml配置文件路径
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//构建 SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//通过 SqlSessionfactory 获取 SqlSession

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//通过 SqlSession 获取 Mapper 接口

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

        //关闭 SqlSession
        sqlSession.close();
    }

}
