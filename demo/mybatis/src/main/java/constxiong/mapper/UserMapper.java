package constxiong.mapper;

import constxiong.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * UserMapper 接口，映射对数据库的操作
 */
public interface UserMapper {

    User selectUser(int id);

    int insertUser(User user);

    @Select("select * from user")
    List<User>  selectAllUsers();

    User selectUserByResultMap(int id);

    User selectUserByParamIndex(int id, String name);

    User selectUserByAnnotation(@Param("id") int userId, @Param("name") String userName);

    User selectUserByPo(User user);

    User selectUserByMap(Map<String, Object> map);

    User selectUserWithInfo();

    User selectUserWithArticles();

}
