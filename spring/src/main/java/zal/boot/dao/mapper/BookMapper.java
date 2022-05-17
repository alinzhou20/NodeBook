package zal.boot.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zal.boot.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    @Select("select * from book where name like #{bookName};")
    List<Book> searchByName(@Param("bookName") String bookName);

    @Select("select * from book where id = #{bookId};")
    Book selectBybookId(@Param("bookId") Integer bookId);

    @Select("select nickname from writer where id = #{writerId};")
    String selectWriter(@Param("writerId") Integer writerId);

    @Select("select book_id from user_book where user_id = #{userId};")
    Integer[] selectBookIdByUserId(@Param("userId") Integer userId);


}
