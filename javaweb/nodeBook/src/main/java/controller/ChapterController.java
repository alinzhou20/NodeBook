package controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.Chapter;
import service.BookService;
import service.ChapterService;
import service.UserService;
import utils.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


@WebServlet("/chapter")
public class ChapterController extends HttpServlet {

    private ChapterService chapterService = null;
    private Result res = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        chapterService = new ChapterService();
        String method = req.getParameter("method");
        switch (method) {
            case "searchByBookId":
                try {
                    res = chapterService.getByBookId(Integer.valueOf(req.getParameter("bookId")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "changeChapter":
                try {
                    System.out.println(req.getParameter("chapterId"));;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "deleteChapter":
                try {
                    res = chapterService.deleteChapter(Integer.valueOf(req.getParameter("chapterId")));
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
        chapterService = new ChapterService();
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String line = reader.readLine();
        JSONObject json = JSON.parseObject(line);
        String method = json.getString("method");
        switch (method) {
            case "addNewChapter":
                try {
                    String content = json.getJSONObject("content").getString("_rawValue");
                    String title = json.getJSONObject("title").getString("_rawValue");
                    String list = json.getJSONObject("list").getString("_rawValue");
                    String price = json.getJSONObject("price").getString("_rawValue");
                    String bookId = json.getJSONObject("bookId").getString("_rawValue");
                    Chapter chapter = new Chapter();
                    chapter.setList(Integer.valueOf(list));
                    chapter.setTitle(title);
                    chapter.setPrice(price);
                    chapter.setContent(content);
                    chapter.setBookId(Integer.valueOf(bookId));

                    chapterService.addChapter(chapter);


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
