package cn.soft.news.servlet;

import cn.soft.news.dao.ThemeDao;
import cn.soft.news.dao.impl.ThemeDaoImpl;
import cn.soft.news.plugin.TxProxy;
import cn.soft.news.po.TbTheme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Hanoch
 */
@WebServlet(name = "ThemesServlet", urlPatterns = {"/admin/themes/queryAll", "/admin/themes/editPage",
        "/admin/themes/delete", "/admin/themes/edit", "/admin/themes/addPage", "/admin/themes/add"})
public class ThemesServlet extends HttpServlet {

    private ThemeDao themeDao = (ThemeDao) TxProxy.bind(new ThemeDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath();
        switch (url) {
            case "/admin/themes/queryAll":
                queryAll(request, response);
                break;
            case "/admin/themes/delete":
                delete(request, response);
                break;
            case "/admin/themes/add":
                add(request, response);
                break;
            case "/admin/themes/addPage":
                addPage(request, response);
                break;
            case "/admin/themes/edit":
                edit(request, response);
                break;
            case "/admin/themes/editPage":
                editPage(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * 添加
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        String themeName = request.getParameter("themeName");
        String themeDetail = request.getParameter("themeDetail");
        int themeLevel = Integer.parseInt(request.getParameter("themeLevel"));
        TbTheme theme = new TbTheme(themeId, themeName, themeDetail, themeLevel, new Date());
        try {
            themeDao.addTheme(theme);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/admin/themes/queryAll");
    }

    /**
     * 添加页面
     *
     * @param request  request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageInfo", "themeAdd.jsp");
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    /**
     * 编辑主题
     *
     * @param request  request
     * @param response response
     * @throws IOException IOException
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        String themeName = request.getParameter("themeName");
        String themeDetail = request.getParameter("themeDetail");
        int themeLevel = Integer.parseInt(request.getParameter("themeLevel"));
        TbTheme theme = new TbTheme(themeId, themeName, themeDetail, themeLevel, new Date());
        try {
            themeDao.updateTheme(theme);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/admin/themes/queryAll");
    }


    /**
     * 删除
     *
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        try {
            themeDao.deleteOneTheme(themeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/admin/themes/queryAll").forward(request, response);
    }

    private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        TbTheme theme = themeDao.queryOneTheme(themeId);
        request.setAttribute("theme", theme);
        request.setAttribute("pageInfo", "themesEdit.jsp");
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    /**
     * 显示所有主题
     *
     * @param request  request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException      IOException
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
