/*
学习之所以会想睡觉，是因为那是梦开始的地方。
 */
package cn.soft.news.servlet;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.dao.ThemeDao;
import cn.soft.news.vo.NewsVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Hanoch
 */
@WebServlet(name = "NewsServlet", urlPatterns = {"/admin/news/queryAll", "/admin/news/delete",
        "/admin/news/add", "/admin/news/edit", "/admin/news/editPage", "/admin/news/addPage"})
public class NewsServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();

    private ThemeDao themeDao = new ThemeDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath();
        switch (url) {
            case "/admin/news/queryAll":
                queryAll(request, response);
                break;
            case "/admin/news/delete":
                delete(request, response);
                break;
            case "/admin/news/add":
                add(request, response);
                break;
            case "/admin/news/addPage":
                addPage(request, response);
                break;
            case "/admin/news/edit":
                edit(request, response);
                break;
            case "/admin/news/editPage":
                editPage(request, response);
                break;
            default:
                break;
        }
    }

    private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageInfo", "newsAdd.jsp");
        request.setAttribute("themes", themeDao.queryAllTheme());
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newId = request.getParameter("newsId");
        NewsVo newsVo = newsDao.queryOneNews(newId);
        request.setAttribute("news", newsVo);
        request.setAttribute("themes", themeDao.queryAllTheme());
        request.setAttribute("pageInfo", "newsEdit.jsp");
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    /**
     * 编辑新闻
     *
     * @param request  request
     * @param response response
     * @throws IOException IOException
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newsId = request.getParameter("newsId");
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        String newsAuthor = request.getParameter("newsAuthor");
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");
        Long newsUp = Long.valueOf(request.getParameter("newsUp"));
        Long newsDown = Long.valueOf(request.getParameter("newsDown"));
        NewsVo newsVo = new NewsVo(newsId, themeId, newsTitle, newsAuthor, newsContent, newsUp, newsDown);
        newsDao.updateNews(newsVo);
        response.sendRedirect(request.getContextPath() + "/admin/news/queryAll");
    }

    /**
     * 发布新闻
     *
     * @param request  request
     * @param response response
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int newsThemeId = Integer.parseInt(request.getParameter("themeId"));
        String newsAuthor = request.getParameter("newsAuthor");
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");

        String newsId = UUID.randomUUID().toString().replace("-", "");
        Date newsCreateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        NewsVo newsVo = new NewsVo(newsId, newsThemeId, newsTitle, newsAuthor, newsContent, sdf.format(newsCreateTime));
        newsDao.addOneNews(newsVo);
        response.sendRedirect(request.getContextPath() + "/admin/news/queryAll");
    }

    /**
     * 删除一条news
     *
     * @param request  request
     * @param response response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        newsDao.deleteNews(newsId);
        request.getRequestDispatcher("/admin/news/queryAll").forward(request, response);
    }

    /**
     * 查询所有news
     *
     * @param request  request
     * @param response response
     */
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NewsVo> newsVoList = newsDao.queryAllNewsAll();
        request.setAttribute("newsVoList", newsVoList);
        request.setAttribute("pageInfo", "adminnews.jsp");
        request.getRequestDispatcher("/WEB-INF/view/adminjsps/adminindex.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
