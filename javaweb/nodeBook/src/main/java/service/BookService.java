package service;

import dao.BookDao;
import pojo.Book;
import utils.Druid;
import utils.Result;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookDao bookDao = new BookDao();
    private Result res = null;

    /**
     *@description 根据id获取书籍
     *@Date 2022/5/21 8:58
     */
    public Result getById(Integer bookId) throws Exception {
        Book book = bookDao.selectById(bookId);
        return Result.success(book);
    }

    /**
     *@description 获取随机的书籍
     *@Date 2022/5/21 8:58
     */
    public Result getCasualBook(Integer number) throws Exception {
        Integer[] bookIds = new Integer[number];
        Integer max = 9;
        Integer min = 1;

        /*取number个不同的随机数*/
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

        List<Book> books = new ArrayList<Book>();
        for (int i = 0;i < bookIds.length; i++) {
            Book book = bookDao.selectById(bookIds[i]);
            books.add(book);
        }

        return Result.success(books);

    }

    /**
     *@description 根据书名搜索
     *@Date 2022/5/21 8:58
     */
    public Result searchByName(String bookName) throws Exception {

        ArrayList<Integer> bookIds = bookDao.selectByName(bookName);
        List<Book> books = new ArrayList<Book>();
        for (int i = 0;i < bookIds.size(); i++) {
            Book book = bookDao.selectById(bookIds.get(i));
            books.add(book);
        }


        return Result.success(books);
    }

    /**
     *@description 根据标签获取书籍
     *@Date 2022/5/21 8:58
     */
    public Result searchByTag(String tag) throws Exception {

        ArrayList<Integer> bookIds = bookDao.searchByTag(tag);
        List<Book> books = new ArrayList<Book>();
        for (int i = 0;i < bookIds.size(); i++) {
            Book book = bookDao.selectById(bookIds.get(i));
            books.add(book);
        }
        return Result.success(books);
    }
    /**
     *@description
     *@Date 2022/5/27 10:54
     */
    public Result getTopBook(String tag) throws Exception {


        ArrayList<Integer> bookIds = bookDao.getTopBook(tag,5);
        List<Book> books = new ArrayList<Book>();
        for (int i = 0;i < bookIds.size(); i++) {
            Book book = bookDao.selectById(bookIds.get(i));
            books.add(book);
        }
        return Result.success(books);
    }

}
