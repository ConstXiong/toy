package constxiong;

import constxiong.mapper.InfoMapper;
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
 * 测试 Mybatis 懒加载
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";//xml配置文件路径
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//构建 SqlSessionFactory
        sqlSessionFactory.getConfiguration().addMapper(InfoMapper.class);//添加 InfoMapper
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//通过 SqlSessionfactory 获取 SqlSession

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//通过 SqlSession 获取 Mapper 接口

        System.out.println("------ selectUserWithLazyInfo ------");
        User user = userMapper.selectUserWithLazyInfo();
        System.out.println(user);
        System.out.println(user.getInfo());

        //关闭 SqlSession
        sqlSession.close();
    }

}
