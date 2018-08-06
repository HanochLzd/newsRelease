package cn.soft.news.servlet;

import cn.soft.news.dao.ThemeDao;
import cn.soft.news.po.TbTheme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Hanoch
 */
@WebServlet(name = "ThemesServlet", urlPatterns = {"/admin/themes/queryAll", "/admin/themes/editPage"})
public class ThemesServlet extends HttpServlet {

    private ThemeDao themeDao = new ThemeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath();
        switch (url) {
            case "/admin/themes/queryAll":
                queryAll(request, response);
                break;
//            case "/admin/news/delete":
//                delete(request, response);
//                break;
//            case "/admin/news/add":
//                add(request, response);
//                break;
//            case "/admin/news/addPage":
//                addPage(request, response);
//                break;
//            case "/admin/news/edit":
//                edit(request, response);
//                break;
//            case "/admin/news/editPage":
//                editPage(request, response);
//                break;
            default:
                break;
        }
    }

    /**
     * 显示所有主题
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TbTheme> themes = themeDao.queryAllTheme();
        request.setAttribute("themes", themes);
        request.setAttribute("themesLength", themes.size());
        request.setAttribute("pageInfo", "themes.jsp");
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
