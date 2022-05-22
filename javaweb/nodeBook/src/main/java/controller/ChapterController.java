package controller;


import com.alibaba.fastjson.JSON;
import service.BookService;
import service.ChapterService;
import utils.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/chapter")
public class ChapterController extends HttpServlet {

    private ChapterService chapterService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        chapterService = new ChapterService();
        String method = req.getParameter("method");
        Result res = null;
        switch (method) {
            case "searchByBookId":
                try {
                    res = chapterService.getByBookId(Integer.valueOf(req.getParameter("bookId")));
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
