package constxiong.mapper;

import java.util.List;

import constxiong.po.User;

/**
 * UserMapper 接口，映射对数据库的操作
 */
public interface UserMapper {

    List<User> selectUsers();

    int insertUser(User user);

    int deleteUsers();

}
