package zal.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zal.boot.entity.Book;
import zal.boot.entity.Chapter;

import java.util.List;

@Mapper
public interface ChapterMapper extends BaseMapper<Book> {

    @Select("select chapter_id,order,title from chapter where book_id = #{bookId};")
    List<Chapter> selectTitles(@Param("bookId") Integer bookId);





}
