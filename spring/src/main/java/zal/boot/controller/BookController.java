package zal.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import zal.boot.common.Result;
import zal.boot.entity.Book;
import zal.boot.dao.mapper.BookMapper;
import zal.boot.dao.mapper.TagMapper;

import javax.annotation.Resource;
import java.util.List;

/*相当于业务层*/
@RestController //等价@Controller 和@ResponseBody
@RequestMapping("/book")
public class BookController {

    @Resource
    BookMapper bookMapper;

    /*定义书籍查询条件*/
    private QueryWrapper<Book> queryWrapper = null;

    /*根据书籍id获得信息*/
    @GetMapping("/getById")
    public Result getById(@RequestParam Integer bookId){
        Book book = bookMapper.selectBybookId(bookId);
        book.setWriter(bookMapper.selectWriter(book.getWriterId()));
        return Result.success(book);
    }

    /*从数据库查询书籍*/
    @GetMapping("/searchByName")
    public Result searchBook(@RequestParam String bookName){
        List<Book> books = bookMapper.searchByName('%'+bookName+'%');
        System.out.println(books);
        return Result.success(books);
    }


    @GetMapping("/getUserBook")
    public Result getUserBook(@RequestParam String userId){

        Integer[] booksIds = bookMapper.selectBookIdByUserId(Integer.valueOf(userId));

        queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name","img_url");
        queryWrapper.in("id",booksIds);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return Result.success(books);
    }

    @GetMapping("/getCasualBook")
    public Result getCasualBook(@RequestParam Integer number){
        Integer[] bookIds = new Integer[number];
        Integer max = bookMapper.selectCount(null);
        Integer min = 1;

        /*取三个不同的随机数*/
        for (int i = 0; i < bookIds.length; i++) {
            bookIds[i] = (int) (Math.random()*max+min);
            if (i>0) {
                for (int j = i-1; j >= 0; j--) {
                    if (bookIds[i] == bookIds[j]){
                        i--;
                        break;
                    }
                }
            }
        }

        System.out.println(bookIds);
        queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name","img_url");
        queryWrapper.in("id",bookIds);
        List<Book> books = bookMapper.selectList(queryWrapper);
        System.out.println(books);
        return Result.success(books);
    }

}
