package controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import service.UserService;
import service.WriterService;
import utils.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/writer")
public class WriterController extends HttpServlet {

    private WriterService writerService = null;
    private Result res = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        writerService = new WriterService();
        String method = req.getParameter("method");

        switch (method) {
            case "getWriterBook":
                try {
                    res = writerService.getBooks(Integer.valueOf(req.getParameter("writerId")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "buyChapter":
                try {
                    res = writerService.transaction(Integer.valueOf(req.getParameter("userId")),Integer.valueOf(req.getParameter("chapterId")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


        }

        String toJSONString = JSON.toJSONString(res);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(toJSONString);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writerService = new WriterService();
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String line = reader.readLine();
        JSONObject json = JSON.parseObject(line);
        String method = json.getString("method");

        switch (method) {
            case "login":
                try {
                    String username = json.getString("username");
                    String password = json.getString("password");
                    res = writerService.login(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "register":
                try {
                    String username = json.getString("username");
                    String password = json.getString("password");
                    String nickname = json.getString("nickname");
                    res = writerService.register(username, password,nickname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        String toJSONString = JSON.toJSONString(res);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(toJSONString);


    }
}
