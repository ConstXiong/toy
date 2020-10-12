package constxiong.mapper;

import constxiong.po.Info;
import org.apache.ibatis.annotations.Select;

/**
 * InfoMapper 接口，映射对数据库的操作
 */
public interface InfoMapper {

    @Select("select * from info where user_id = #{userId}")
    Info selectInfoByUserId(int userId);

}
