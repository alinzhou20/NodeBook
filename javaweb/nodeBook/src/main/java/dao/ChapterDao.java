package dao;

import pojo.Book;
import pojo.Chapter;
import utils.Druid;
import utils.Result;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChapterDao {

    private DataSource dataSource = Druid.getDruid();
    /**
     *@description 根据id获取章节
     *@Date 2022/5/21 8:58
     */
    public Chapter selectById(Integer chapterId) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "select title,content from chapter where id = "+chapterId;

        ResultSet resultSet = stmt.executeQuery(sql);


        Chapter chapter = new Chapter();
        /*章节信息*/
        String content = "";
        String title = "";

        /*查章节*/
        if (resultSet.next()) {
            content = resultSet.getString("content");
            chapter.setContent(content);
            title = resultSet.getString("title");
            chapter.setTitle(title);
        }
        return chapter;
    }

    /**
     *@description 根据书籍id获取章节
     *@Date 2022/5/21 8:58
     */
    public ArrayList<Chapter> selectByBookId(Integer bookId) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "select id,list,title from chapter where book_id = "+bookId;

        ResultSet resultSet = stmt.executeQuery(sql);


        /*章节信息*/
        int id = 0;
        int list = 0;
        String title="";

        /*查章节*/
        ArrayList<Chapter> chapters = new ArrayList<>();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            list = resultSet.getInt("list");
            title = resultSet.getString("title");
            chapters.add(new Chapter(id,list,title));
        }

        return chapters;
    }

    /**
     *@description 根据章节id删除
     *@Date 2022/5/21 8:58
     */
    public int deleteChapter(Integer chapterId) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = " DELETE FROM chapter WHERE id = "+chapterId;

        stmt.execute(sql);
        return stmt.executeUpdate(sql);
    }

    /**
     *@description 添加章节
     *@Date 2022/5/21 8:58
     */
    public int addChapter(Chapter chapter) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        Integer bookId = chapter.getBookId();
        String content = chapter.getContent();
        Integer list = chapter.getList();
        String price = chapter.getPrice();
        String title = chapter.getTitle();

        try{
            String sql = " INSERT INTO chapter(book_id, content, list, title, price) " +
                    "VALUES ("+bookId+",'"+content+"'," +list+",'"+title + "',"+ price+ ") ";
            stmt.executeUpdate(sql);
        }catch (Exception e){
            return 0;
        }

        return 1;
    }
}
