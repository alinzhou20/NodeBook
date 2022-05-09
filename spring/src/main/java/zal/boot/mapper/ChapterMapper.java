package zal.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zal.boot.entity.Book;
import zal.boot.entity.Chapter;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

    @Select("select id,list,title,book_id from chapter where book_id = #{bookId};")
    List<Chapter> searchByBookId(@Param("bookId") Integer bookId);





}
