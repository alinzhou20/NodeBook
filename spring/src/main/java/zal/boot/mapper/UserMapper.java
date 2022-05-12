package zal.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zal.boot.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{userName};")
    User login(@Param("userName") String userName);
}
