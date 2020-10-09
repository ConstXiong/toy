package constxiong;

import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试 Mybatis xml 与 注解的使用
 */
public class Test {

    public static void main(String[] args) throws IOException {
        testXmlConfig();
//        testJavaConfig();
    }

    /**
     * 测试 xml 配置方式
     */
    private static void testXmlConfig() throws IOException {
        String resource = "mybatis-config.xml";//xml配置文件路径
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//构建 SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//通过 SqlSessionfactory 获取 SqlSession

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//通过 SqlSession 获取 Mapper 接口

        //插入 user
        userMapper.insertUser(new User(null, "ConstXiong1"));

        //查询该 user
        User user = userMapper.selectUser(1);
        System.out.println(user);

        //使用 resultMap 查询 user
        user = userMapper.selectUserByResultMap(1);
        System.out.println(user);

        //查询所有 user 数据
        List<User> users = userMapper.selectAllUsers();
        System.out.println(users);

        System.out.println("------ selectUserByParamIndex ------");
        user = userMapper.selectUserByParamIndex(31, "ConstXiong1");
        System.out.println(user);

        System.out.println("------ selectUserByAnnotation ------");
        user = userMapper.selectUserByAnnotation(31, "ConstXiong1");
        System.out.println(user);

        System.out.println("------ selectUserByPo ------");
        user = userMapper.selectUserByPo(new User(31, "ConstXiong1"));
        System.out.println(user);

        System.out.println("------ selectUserByMap ------");
        Map<String, Object> param = new HashMap<>();
        param.put("id", 31);
        param.put("name", "ConstXiong1");
        user = userMapper.selectUserByMap(param);
        System.out.println(user);

        //关闭 SqlSession
        sqlSession.close();
    }

    /**
     * 测试 Java 方式
     */
    private static void testJavaConfig() {
        // 创建数据源
        DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver",
                "jdbc:mysql://172.31.32.184:3306/constxiong?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "root",
                "constxiong@123");
        //事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //配置
        Configuration configuration = new Configuration(environment);
        //注册
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //插入 user
        userMapper.insertUser(new User(2, "ConstXiong2"));

        //查询该 user
        User user = userMapper.selectUser(2);
        System.out.println(user);

        //使用 resultMap 查询 user
        user = userMapper.selectUserByResultMap(1);
        System.out.println(user);

        //查询所有 user 数据
        List<User> users = userMapper.selectAllUsers();
        System.out.println(users);

        //关闭 SqlSession
        sqlSession.close();
    }
}
