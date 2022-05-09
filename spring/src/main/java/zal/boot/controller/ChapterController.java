package zal.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zal.boot.common.Result;
import zal.boot.entity.Book;
import zal.boot.entity.Chapter;
import zal.boot.mapper.ChapterMapper;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Resource
    ChapterMapper chapterMapper;

    /*根据id查询具体信息*/
    @GetMapping("/searchById")
    public Result searchById(@RequestParam Integer chapterId){
        Chapter chapter = chapterMapper.selectById(chapterId);
        return Result.success(chapter);
    }

    /*根据书籍id查询具体信息*/
    @GetMapping("/searchByBookId")
    public Result searchByBookId(@RequestParam Integer bookId){
        List<Chapter> chapters = chapterMapper.searchByBookId(bookId);
        return Result.success(chapters);
    }


}
