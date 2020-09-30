package constxiong;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试 Mybatis 分页插件
 */
public class Test {

    public static void main(String[] args) throws IOException {
        testXmlConfigAndPager();
    }

    /**
     * 测试 xml 配置方式分页
     */
    private static void testXmlConfigAndPager() throws IOException {
        String resource = "mybatis-config.xml";//xml配置文件路径
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//构建 SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//通过 SqlSessionfactory 获取 SqlSession

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//通过 SqlSession 获取 Mapper 接口

        //删除 user
        userMapper.deleteUsers();

        //插入 user
        for (int i = 1; i <= 35; i++) {
            userMapper.insertUser(new User(i, "ConstXiong" + i));
        }

        //查询所有 user
        List<User> users = userMapper.selectUsers();
        System.out.println(users);

        //未打开 mybatis-config.xml 中 plugins 标签使用内存分页；打开标签使用插件的物理分页
        int pageNo = 2;
        int pageSize = 10;
        users = sqlSession.selectList("constxiong.mapper.UserMapper.selectUsers", null, new RowBounds((pageNo - 1) * pageSize, pageSize));
        System.out.println(users);

        //使用静态方法 startPage
        PageHelper.startPage(pageNo, pageSize);
        users = userMapper.selectUsers();
        System.out.println(users);

        //使用静态方法 offsetPage
        PageHelper.offsetPage(pageNo, pageSize);
        users = userMapper.selectUsers();
        System.out.println(users);

        //匿名类
        Page<User> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                userMapper.selectUsers();
            }
        });
        System.out.println(page);
        // lambda
        page = PageHelper.startPage(pageNo, pageSize).doSelectPage(() -> {
            userMapper.selectUsers();
        });
        System.out.println(page);

        //仅合计
        long count = PageHelper.count(new ISelect() {
            @Override
            public void doSelect() {
                userMapper.selectUsers();
            }
        });
        System.out.println(count);
        //lambda
        count = PageHelper.count(() -> {
            userMapper.selectUsers();
        });
        System.out.println(count);

        //关闭 SqlSession
        sqlSession.close();
    }

}
