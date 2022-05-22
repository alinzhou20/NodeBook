package dao;

import pojo.Chapter;
import pojo.User;
import utils.Druid;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private DataSource dataSource = Druid.getDruid();


    /**
     *@description 获取用户书架上的书籍
     *@Date 2022/5/21 8:54
     */
    public ArrayList<Integer> getBooks(Integer userId) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        String sql = "select book_id from view_user_book where user_id = "+ userId;

        ResultSet resultSet = stmt.executeQuery(sql);
        ArrayList<Integer> bookIds = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            bookIds.add(id);
        }
        return bookIds;
    }

    /**
     *@description 登录
     *@Date 2022/5/21 8:54
     */
    public User login(String username) throws Exception {

        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();

        String sql = "select id,password from user where username ='"+username+"'";

        ResultSet resultSet = stmt.executeQuery(sql);


        /*用户信息*/
        User user = new User();
        int id = -1;
        String password="";

        /*查章节*/

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            password = resultSet.getString("password");
            user.setId(id);
            user.setPassword(password);
        }

        return user;
    }


    /**
     *@description 注册
     *@Date 2022/5/21 8:53
     */
    public List register(String username, String password, String nickname) throws Exception {

        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);//开启事务

        int userId = -1;
        String msg = null;
        CallableStatement callableStatement = null;
        try {

            callableStatement = con.prepareCall("{CALL do_add_user(?,?,?,?)}");
            callableStatement.setObject(1, username);
            callableStatement.setObject(2, password);
            callableStatement.registerOutParameter(3, Types.CHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.executeUpdate();
            userId = callableStatement.getInt(4);
            msg = callableStatement.getString(3);

            System.out.println(userId);
            System.out.println(msg);

            Statement stmt = con.createStatement();
            String sql = "UPDATE user SET nickname = '"+nickname+"' WHERE id = "+userId;
            stmt.executeUpdate(sql);



        }catch (SQLException e) {
            System.out.println("error");
        }


        con.commit();
        callableStatement.close();
        con.close();


        List list = new ArrayList();
        list.add(userId);
        list.add(msg);

        System.out.println(list.toString());

        return list;
    }

    /**
     *@description 购买章节
     *@Date 2022/5/21 8:54
     */
    public String transaction(Integer userId, Integer chapterId) throws Exception {

        Connection con = dataSource.getConnection();
        CallableStatement callableStatement =null;
        con.setAutoCommit(false);//开启事务
        String msg = null;
        try {
            callableStatement = con.prepareCall("{CALL do_user_chapter_transaction(?,?,?)}");
            callableStatement.setObject(1, chapterId);
            callableStatement.setObject(2, userId);
            callableStatement.registerOutParameter(3, Types.CHAR);

            callableStatement.executeUpdate();
            msg = callableStatement.getString(3);
            System.out.println(msg);
        }catch (Exception e) {
            System.out.println("meiyou ");
        }
        con.commit();
        callableStatement.close();
        con.close();

        return msg;

    }
}
