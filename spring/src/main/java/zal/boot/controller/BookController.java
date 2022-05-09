package zal.boot.controller;

import org.springframework.web.bind.annotation.*;
import zal.boot.common.Result;
import zal.boot.entity.Book;
import zal.boot.entity.Chapter;
import zal.boot.entity.Tag;
import zal.boot.mapper.BookMapper;
import zal.boot.mapper.ChapterMapper;
import zal.boot.mapper.TagMapper;

import javax.annotation.Resource;
import java.util.List;

/*相当于业务层*/
@RestController //等价@Controller 和@ResponseBody
@RequestMapping("/book")
public class BookController {

    @Resource
    BookMapper bookMapper;

    @Resource
    TagMapper tagMapper;


    /*从数据库查询书籍*/
    @GetMapping("/searchByName")
    public Result searchBook(@RequestParam String bookName){
        List<Book> books = bookMapper.searchByName('%'+bookName+'%');
        System.out.println(books);
        return Result.success(books);
    }



    @GetMapping("/getContent")
    public Result getContent(@RequestParam Integer chapterId){
        return null;
    }

}
