package constxiong;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import constxiong.mapper.UserMapper;
import constxiong.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

        System.out.println("------userMapper.selectUsers()------");
        //查询所有 user
        List<User> users = userMapper.selectUsers();
        System.out.println(users);
        System.out.println();

        int pageNo = 2;
        int pageSize = 10;

        System.out.println("------RowBounds------");
        //未打开 mybatis-config.xml 中 plugins 标签使用内存分页；打开标签使用插件的物理分页
        users = sqlSession.selectList("constxiong.mapper.UserMapper.selectUsers", null, new RowBounds((pageNo - 1) * pageSize, pageSize));
        System.out.println(users);
        System.out.println();

        System.out.println("------startPage------");
        //使用静态方法 startPage
        PageHelper.startPage(pageNo, pageSize);
        users = userMapper.selectUsers();
        System.out.println(users);
        System.out.println();

        System.out.println("------offsetPage------");
        //使用静态方法 offsetPage
        PageHelper.offsetPage(pageNo, pageSize);
        users = userMapper.selectUsers();
        System.out.println(users);
        System.out.println();

        System.out.println("------startPage ISelect------");
        //匿名类
        Page<User> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                userMapper.selectUsers();
            }
        });
        System.out.println(page);
        System.out.println();

        System.out.println("------startPage lambda------");
        // lambda
        page = PageHelper.startPage(pageNo, pageSize).doSelectPage(() -> {
            userMapper.selectUsers();
        });
        System.out.println(page);
        System.out.println();

        System.out.println("------PageHelper.count ISelect------");
        //仅合计
        long count = PageHelper.count(new ISelect() {
            @Override
            public void doSelect() {
                userMapper.selectUsers();
            }
        });
        System.out.println(count);
        System.out.println();

        System.out.println("------PageHelper.count lambda------");
        //lambda
        count = PageHelper.count(() -> {
            userMapper.selectUsers();
        });
        System.out.println(count);

        //关闭 SqlSession
        sqlSession.close();
    }

}
