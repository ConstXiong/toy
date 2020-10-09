package constxiong;

import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试 Mybatis 获取自增主键
 */
public class Test {

    public static void main(String[] args) throws IOException {
        testBatchInsert();
    }

    /**
     * 测试 xml 配置方式分页
     */
    private static void testBatchInsert() throws IOException {
        String resource = "mybatis-config.xml";//xml配置文件路径
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//构建 SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//通过 SqlSessionfactory 获取 SqlSession

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//通过 SqlSession 获取 Mapper 接口

        System.out.println("------userMapper.insertUser()------");
        for (int i = 0; i < 10; i++) {
            User user = new User(null, "constxiong" + i);//这里 user.id = null
            userMapper.insertUser(user);
            System.out.println("id:" + user.getId());//插入数据库后，这里的 user.id 为主键值
        }

        //关闭 SqlSession
        sqlSession.close();
    }

}
