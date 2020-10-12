package constxiong.mapper;

import constxiong.po.Info;
import org.apache.ibatis.annotations.*;

/**
 * InfoMapper 接口，映射对数据库的操作
 */
public interface InfoMapper {

    @Select("select * from info where user_id = #{userId}")
    @Results(value = {@Result(column="user_id", property = "userId")})
    Info selectInfoByUserId(int userId);

}
