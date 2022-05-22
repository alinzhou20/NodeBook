package service;


import dao.BookDao;
import dao.UserDao;
import pojo.Book;
import pojo.User;
import utils.Result;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();
    private BookDao bookDao = new BookDao();
    private Result res = null;

    /**
     *@description 登录
     *@Date 2022/5/21 8:56
     */
    public Result login(String username,String password) throws Exception {
        User user = userDao.login(username);
        System.out.println(user);
        if (null == user.getId()) {
            return Result.error("用户名错误");
        }else {
            if (user.getPassword().equals(password)) {
                return Result.success(user.getId());
            }else {
                return Result.error("密码错误");
            }
        }
    }

    /**
     *@description 注册
     *@Date 2022/5/21 8:56
     */
    public Result register(String username,String password,String nickname) throws Exception {
        List list = userDao.register(username,password,nickname);
        if (null != list.get(1)) {
            return Result.error("用户名已存在");
        }else {
            return Result.success(list.get(0));
        }
    }

    /**
     *@description 获取用户书架
     *@Date 2022/5/21 8:56
     */
    public Result getBooks(Integer userId) throws Exception {
        ArrayList<Integer> bookIds = userDao.getBooks(userId);
        List<Book> books = new ArrayList<Book>();
        for (int i = 0;i < bookIds.size(); i++) {
            Book book = bookDao.selectById(bookIds.get(i));
            books.add(book);
        }
        return Result.success(books);
    }

    /**
     *@description 购买章节
     *@Date 2022/5/21 8:54
     */
    public Result transaction(Integer userId, Integer chapterId) throws Exception {
        ChapterService chapterService = new ChapterService();

        String msg = userDao.transaction(userId, chapterId);

        if (null==msg) {
            return chapterService.getById("已为您支付章节费用",chapterId);
        }else {
            if (msg.equals("已经购买")) return chapterService.getById("章节已购",chapterId);
            if (msg.equals("存款不足")) return Result.error("存款不足");
            return Result.error("未知错误");
        }

    }

}
