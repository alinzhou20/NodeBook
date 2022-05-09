package zal.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import zal.boot.entity.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
