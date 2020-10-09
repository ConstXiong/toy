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
 * 测试 Mybatis 批量插入
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

        System.out.println("------userMapper.deleteUsers()------");
        //删除 user
        userMapper.deleteUsers();
        System.out.println();

        System.out.println("------userMapper.insertUser()------");
        //插入 user
        for (int i = 1; i <= 35; i++) {
            userMapper.insertUser(new User(i, "ConstXiong" + i));
        }
        System.out.println();

        System.out.println("------insert user batch by session------");
        insertUserBatchBySession(sqlSessionFactory);
        System.out.println();

        System.out.println("------insert user batch by sql------");
        List<User> userList = new ArrayList<>();
        for (int i = 46; i <= 55; i++) {
            userList.add(new User(i,"ConstXiong" + i));
        }
        userMapper.insertUserBatch(userList);
        System.out.println();

        System.out.println("------userMapper.selectUsers()------");
        //查询所有 user
        List<User> users = userMapper.selectUsers();
        System.out.println(users);
        System.out.println();

        //关闭 SqlSession
        sqlSession.close();
    }

    /**
     * 批量插入用户
     */
    private static void insertUserBatchBySession(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        for (int i = 36; i <= 45; i++) {
            userMapper.insertUser(new User(i, "ConstXiong" + i));
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
