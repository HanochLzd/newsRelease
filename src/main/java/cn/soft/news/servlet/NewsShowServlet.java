package cn.soft.news.servlet;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.dao.PraiseDao;
import cn.soft.news.dao.ThemeDao;
import cn.soft.news.po.Praise;
import cn.soft.news.service.NewsService;
import cn.soft.news.service.impl.NewsServiceImpl;
import cn.soft.news.utils.NetUtil;
import cn.soft.news.utils.PageUtil;
import cn.soft.news.vo.NewsVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Hanoch
 */
@WebServlet(name = "NewsShowServlet", urlPatterns = {"/showNews", "/addPraise", "/newsPage", "/newsInGroup"})
public class NewsShowServlet extends HttpServlet {

    private NewsDao newsDao = new NewsDao();

    private ThemeDao themeDao = new ThemeDao();

    private PraiseDao praiseDao = new PraiseDao();

    private NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath();
        switch (url) {
            case "/showNews":
                showNews(request, response);
                break;
//            case "/admin/news/delete":
//                delete(request, response);
//                break;
            case "/addPraise":
                addPraise(request, response);
                break;
            case "/newsPage":
                showNewsByPage(request, response);
                break;
            case "/newsInGroup":
                newsInGroup(request, response);
                break;
//            case "/admin/news/editPage":
//                editPage(request, response);
//                break;
            default:
                break;
        }
    }

    /**
     * 显示某一类型的所有新闻
     *
     * @param request  request
     * @param response response
     */
    private void newsInGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int themeId = Integer.parseInt(request.getParameter("themeId"));
        request.setAttribute("themeName", themeDao.getThemeNameById(themeId));
        request.setAttribute("themeId", themeId);
        //TODO 此处重复于TestServlet中的请求（可换成其它显示）
        Map<String, List<NewsVo>> newsMap = newsDao.newsInTheme(2);
        request.setAttribute("newsMap", newsMap);
        request.setAttribute("themes", themeDao.queryAllTheme());
        request.setAttribute("totalPageCount", newsService.getTotalPageCount(themeId));
        //获取分组分页数据
        request.setAttribute("pageInfo", "newsInGroup.jsp");
        request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
    }

    /**
     * 分页显示
     *
     * @param request  request
     * @param response response
     * @throws IOException IOException
     */
    private void showNewsByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int newsType = Integer.parseInt(request.getParameter("newsType"));
        PageUtil<NewsVo> pageUtil = newsService.queryByPage(pageNo, newsType);
        List<NewsVo> newsVos = pageUtil.getNewsList();
        JSONArray jsonArray = new JSONArray();
        for (NewsVo newsVo : newsVos) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("newsId", newsVo.getNewsId());
            jsonObject.put("newsThemeId", newsVo.getNewsThemeId());
            jsonObject.put("newsTitle", newsVo.getNewsTitle());
            jsonObject.put("newsCreateTime", newsVo.getNewsCreateTime());
            jsonArray.add(jsonObject);
        }
        response.getWriter().print(jsonArray);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    /**
     * @param request  request
     * @param response response
     */
    private void addPraise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newsId = request.getParameter("newsId");
        int up = Integer.parseInt(request.getParameter("up"));
        int down = Integer.parseInt(request.getParameter("down"));
        int type = Integer.parseInt(request.getParameter("type"));
        String ip = NetUtil.getIpAddress(request);
        newsDao.updateNewsInPraise(newsId, up, down);
        praiseDao.addOnePraise(newsId, type, ip);
        JSONObject result = new JSONObject();
        result.put("data", "success");
        response.getWriter().write(result.toJSONString());
    }


    private void showNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newsId = request.getParameter("newsId");
        NewsVo newsVo = newsDao.queryOneNews(newsId);
        request.setAttribute("newsVo", newsVo);
        request.setAttribute("pageInfo", "newsShow.jsp");
        String ip = NetUtil.getIpAddress(request);
        List<Praise> praiseList = praiseDao.queryOneNews2OneIp(newsId, ip);
        request.setAttribute("praiseList", praiseList);

        request.getRequestDispatcher("/").forward(request, response);
    }
}
