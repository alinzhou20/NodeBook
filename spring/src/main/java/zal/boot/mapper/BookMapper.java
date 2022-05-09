package zal.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zal.boot.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    @Select("select * from book where name like #{bookName};")
    List<Book> searchByName(@Param("bookName") String bookName);




}
