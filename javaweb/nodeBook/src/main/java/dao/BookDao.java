package dao;


import pojo.Book;
import utils.Druid;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


public class BookDao {

    private DataSource dataSource = Druid.getDruid();

    /**
     *@description 根据id获取书籍
     *@Date 2022/5/21 8:58
     */
    public Book selectById(Integer bookId) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "" +
                "SELECT b.id bookId,b.name bookName,w.nickname writer,t.name tag,b.img_url img " +
                "FROM book b, writer w,tag t " +
                "WHERE t.id = b.tag_id AND w.id = b.writer_id AND b.id ="+ bookId;

        ResultSet resultSet1 = stmt.executeQuery(sql);
        /*书籍信息*/
        int id=0;
        String name="";
        String img="";
        String writer="";
        String tag="";
        /*查书籍*/
        if (resultSet1.next()) {
            id = resultSet1.getInt("bookId");
            name = resultSet1.getString("bookName");
            writer = resultSet1.getString("writer");
            tag = resultSet1.getString("tag");
            img = resultSet1.getString("img");
        }
        return new Book(id,name,writer,tag,img);
    }


    /**
     *@description 根据书名获取书籍
     *@Date 2022/5/21 8:58
     */
    public ArrayList<Integer> selectByName(String bookName) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "select id from book where name like '%"+bookName+"%'";

        ResultSet resultSet = stmt.executeQuery(sql);

        ArrayList<Integer> bookIds = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            bookIds.add(id);
        }
        return bookIds;
    }

    /**
     *@description 根据标签获取书籍
     *@Date 2022/5/21 8:58
     */
    public ArrayList<Integer> searchByTag(String tag) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "SELECT book_id FROM view_book_tag WHERE tag_name = '"+tag+"'";

        ResultSet resultSet = stmt.executeQuery(sql);

        ArrayList<Integer> bookIds = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            bookIds.add(id);
        }
        return bookIds;
    }

    /**
     *@description
     *@Date 2022/5/27 10:55
     */
    public ArrayList<Integer> getTopBook(String tag,Integer number) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "SELECT book_id FROM view_book_tag WHERE tag_name = '"+tag+"'";

        ResultSet resultSet = stmt.executeQuery(sql);

        ArrayList<Integer> bookIds = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            bookIds.add(id);
        }
        return bookIds;
    }
}
