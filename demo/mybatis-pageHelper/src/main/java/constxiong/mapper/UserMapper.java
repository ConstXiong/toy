package constxiong.mapper;

import constxiong.po.User;

import java.util.List;

/**
 * UserMapper 接口，映射对数据库的操作
 */
public interface UserMapper {

    List<User> selectUsers();

    int insertUser(User user);

    int deleteUsers();
}
