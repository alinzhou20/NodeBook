package controller;


import com.alibaba.fastjson.JSON;
import service.BookService;
import utils.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/book")
public class BookController extends HttpServlet {

    private BookService bookService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        bookService = new BookService();
        String method = req.getParameter("method");
        Result res = null;
        switch (method) {
            case "getById":
                try {
                    res = bookService.getById(Integer.valueOf(req.getParameter("bookId")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getCasualBook":
                try {
                    res = bookService.getCasualBook(Integer.valueOf(req.getParameter("number")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "searchByName":
                try {
                    String bookName = req.getParameter("bookName");
                    bookName = new String(bookName.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                    res = bookService.searchByName(bookName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getBookByTag":
                try {
                    String tag = req.getParameter("tag");
                    tag = new String(tag.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                    res = bookService.searchByTag(tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getTopBook":
                try {
                    String tag = req.getParameter("tag");
                    res = bookService.getTopBook(tag);
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
