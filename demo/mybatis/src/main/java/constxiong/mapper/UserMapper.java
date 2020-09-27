package constxiong.mapper;

import constxiong.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper 接口，映射对数据库的操作
 */
public interface UserMapper {

    public User selectUser(int id);

    public int insertUser(User user);

    @Select("select * from user")
    public List<User>  selectAllUsers();
}
