package service;

import dao.BookDao;
import dao.ChapterDao;
import pojo.Book;
import pojo.Chapter;
import utils.Result;

import java.util.ArrayList;
import java.util.List;

public class ChapterService {

    private ChapterDao chapterDao = new ChapterDao();
    private Result res = null;

    /**
     *@description 根据id获取章节
     *@Date 2022/5/21 8:58
     */
    public Result getById(String msg,Integer chapterId) throws Exception {
        Chapter chapter = chapterDao.selectById(chapterId);
        return Result.success(msg,chapter);
    }

    /**
     *@description 根据书籍获取所有章节id
     *@Date 2022/5/21 8:58
     */
    public Result getByBookId(Integer bookId) throws Exception {
        ArrayList<Chapter> chapters = chapterDao.selectByBookId(bookId);
        return Result.success(chapters);
    }


    /**
     *@description 根据章节id删除
     *@Date 2022/5/21 8:58
     */
    public Result deleteChapter(Integer chapterId) throws Exception {
        if (chapterDao.deleteChapter(chapterId)>0){
            return Result.success();
        }else {
            return Result.error("未知错误");
        }
    }

    /**
     *@description 添加章节
     *@Date 2022/5/21 8:58
     */
    public Result addChapter(Chapter chapter) throws Exception {
        if (chapterDao.addChapter(chapter)>0){
            return Result.success();
        }else {
            return Result.error("重复添加");
        }
    }
}
