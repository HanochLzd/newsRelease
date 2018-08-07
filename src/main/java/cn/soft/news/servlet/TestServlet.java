package cn.soft.news.servlet;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.dao.ThemeDao;
import cn.soft.news.dao.impl.NewsDaoImpl;
import cn.soft.news.dao.impl.ThemeDaoImpl;
import cn.soft.news.plugin.TxProxy;
import cn.soft.news.service.NewsService;
import cn.soft.news.service.impl.NewsServiceImpl;
import cn.soft.news.vo.NewsVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Hanoch
 */
public class TestServlet extends HttpServlet {

    private NewsDao newsDao = (NewsDao) TxProxy.bind(new NewsDaoImpl());

    private ThemeDao themeDao = (ThemeDao) TxProxy.bind(new ThemeDaoImpl());
    private NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<NewsVo>> newsMap = newsDao.newsInTheme(2);
        req.setAttribute("newsMap", newsMap);
        req.setAttribute("themes", themeDao.queryAllTheme());
        req.setAttribute("totalPageCount", newsService.getTotalPageCount(0));
//        req.setAttribute("newsList", newsDao.queryAllNews());
        req.getRequestDispatcher("WEB-INF/view/index.jsp").forward(req, resp);
    }
}
