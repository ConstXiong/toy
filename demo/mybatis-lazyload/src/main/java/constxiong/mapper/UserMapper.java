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

    User selectUserWithLazyInfo();

}
