package cn.soft.news.dao;

import cn.soft.news.po.TbTheme;
import cn.soft.news.utils.C3P0Util;
import cn.soft.news.utils.DBUtil;
import cn.soft.news.vo.NewsVo;
import com.mysql.cj.jdbc.ConnectionGroupManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hanoch
 */
public class NewsDao {
    private Connection connection = C3P0Util.getConnection();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private ThemeDao themeDao = new ThemeDao();

    private DBUtil dbUtil = new DBUtil();


    /**
     * 查询所有新闻标题
     *
     * @return List<NewsVo>
     */
    public List<NewsVo> queryAllNews() {
        String sql = "select news_id,news_theme_id,news_title,news_create_time " +
                "from tb_news order by news_create_time desc";
        List<NewsVo> newsVoList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NewsVo newsVo = new NewsVo();
                newsVo.setNewsId(rs.getString(1));
                newsVo.setNewsThemeId(rs.getInt(2));
                newsVo.setNewsTitle(rs.getString(3));
                Date date = rs.getTimestamp(4);
                newsVo.setNewsCreateTime(sdf.format(date.getTime()));
                newsVoList.add(newsVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsVoList;
    }


    /**
     * 获得总记录数
     *
     * @return
     */
    public Long getCount() {
        String sql = "select  count(news_id) from tb_news";
        Object[] params = {};
        try {
            List<Map<String, Object>> mapList = dbUtil.resultSet2List(sql, Arrays.asList(params));
            return (Long) mapList.get(0).get("count(news_id)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;

    }

    /**
     * 查询当前页数据
     *
     * @param pageSize 每页记录数
     * @param pageNo   当前页数
     * @return 当前页记录集
     */
    public List<NewsVo> queryPage(int pageSize, long pageNo) {
        String sql = "select news_id,news_theme_id,news_title,news_create_time " +
                "from tb_news order by news_create_time desc limit ?,?;";
        List<NewsVo> newsVoList = new ArrayList<>();
        Object[] params = {(pageSize * (pageNo - 1)), pageSize};

        List<Map<String, Object>> mapList;
        try {
            mapList = dbUtil.resultSet2List(sql, Arrays.asList(params));
            for (Map<String, Object> objectMap : mapList) {
                NewsVo newsVo = new NewsVo();
                newsVo.setNewsId((String) objectMap.get("news_id"));
                newsVo.setNewsThemeId((Integer) objectMap.get("news_theme_id"));
                newsVo.setNewsTitle((String) objectMap.get("news_title"));
                Date date = (Date) objectMap.get("news_create_time");
                newsVo.setNewsCreateTime(sdf.format(date.getTime()));
                newsVoList.add(newsVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsVoList;
    }


    public List<NewsVo> queryAllNewsAll() {
        String sql = "select news_id,theme_name,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t where n.news_theme_id=t.theme_id order by news_create_time desc";
        List<NewsVo> newsVoList = new ArrayList<>();
        Object[] params = {};
        try {
            List<Map<String, Object>> mapList = dbUtil.resultSet2List(sql, Arrays.asList(params));
            for (Map<String, Object> objectMap : mapList) {
                NewsVo newsVo = new NewsVo();
                newsVo.setNewsId((String) objectMap.get("news_id"));
                newsVo.setNewsThemeName((String) objectMap.get("theme_name"));
                newsVo.setNewsAuthor((String) objectMap.get("news_author"));
                newsVo.setNewsTitle((String) objectMap.get("news_title"));

                newsVo.setNewsUp((Long) objectMap.get("news_up"));
                newsVo.setNewsDown((Long) objectMap.get("news_down"));
                Date date = (Date) objectMap.get("news_create_time");
                newsVo.setNewsCreateTime(sdf.format(date));
                newsVoList.add(newsVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsVoList;
    }

    /**
     * 主页中查询指定主题的新闻
     *
     * @return
     */
    public Map<String, List<NewsVo>> newsInTheme(int count) {
        List<TbTheme> themes = themeDao.firstTwoTheme(count);
        Map<String, List<NewsVo>> newsMap = new HashMap<>(count);
        for (TbTheme theme : themes) {
            newsMap.put(theme.getThemeName(), queryNewsByThemeId(theme.getThemeId()));
        }
        return newsMap;
    }

    /**
     * 查询指定主题下的新闻
     *
     * @param themeId
     * @return
     */
    public List<NewsVo> queryNewsByThemeId(int themeId) {
        String sql = "select news_id,theme_name,news_title,news_create_time " +
                "from tb_news n,tb_theme t " +
                "where n.news_theme_id=t.theme_id and t.theme_id=" + themeId + " order by news_create_time desc limit 0,10;";
        Statement stmt = null;
        ResultSet rs = null;
        List<NewsVo> newsVoList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                NewsVo newsVo = new NewsVo();
                newsVo.setNewsId(rs.getString(1));
                newsVo.setNewsThemeName(rs.getString(2));
                newsVo.setNewsTitle(rs.getString(3));
                Date date = rs.getTimestamp(4);
                newsVo.setNewsCreateTime(sdf.format(date.getTime()));
                newsVoList.add(newsVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                assert rs != null;
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newsVoList;
    }

    /**
     * 删除一条新闻
     *
     * @param id 新闻编号
     */
    public int deleteNews(String id) {
        String sql = "delete from tb_news where news_id='" + id + "'";
        int count = -1;
        try {
            Statement stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询一条新闻
     *
     * @param id
     * @return
     */
    public NewsVo queryOneNews(String id) {
        String sql = "select news_id,theme_name,news_theme_id,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t " +
                "where n.news_theme_id=t.theme_id and n.news_id='" + id + "'";
        NewsVo newsVo = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                newsVo = new NewsVo();
                newsVo.setNewsId(rs.getString(1));
                newsVo.setNewsThemeName(rs.getString(2));
                newsVo.setNewsThemeId(rs.getInt(3));
                newsVo.setNewsAuthor(rs.getString(4));
                newsVo.setNewsTitle(rs.getString(5));
                newsVo.setNewsContent(rs.getString(6));
                newsVo.setNewsUp(rs.getLong(7));
                newsVo.setNewsDown(rs.getLong(8));
                Date date = rs.getTimestamp(9);
                newsVo.setNewsCreateTime(sdf.format(date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsVo;
    }

    /**
     * 添加一条新闻
     *
     * @param newsVo
     * @return
     */
    public int addOneNews(NewsVo newsVo) {
        String sql = "insert into tb_news(news_id, news_theme_id, news_author, news_title, news_content, news_create_time) " +
                "VALUES(?,?,?,?,?,?) ";
        Object[] params = {newsVo.getNewsId(), newsVo.getNewsThemeId(), newsVo.getNewsAuthor(), newsVo.getNewsTitle(),
                newsVo.getNewsContent(), new Date()};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

    /**
     * 更新一条新闻
     *
     * @param newsVo newsVo
     * @return int
     */
    public int updateNews(NewsVo newsVo) {
        String sql = "update tb_news set news_theme_id=?,news_author=?,news_title=?,news_content=?,news_down=?,news_up=?," +
                "news_create_time=? where news_id=?";
        Object[] params = {newsVo.getNewsThemeId(), newsVo.getNewsAuthor(), newsVo.getNewsTitle(), newsVo.getNewsContent(), newsVo.getNewsDown(),
                newsVo.getNewsUp(), new Date(), newsVo.getNewsId()};

        return dbUtil.getCount(sql, Arrays.asList(params));
    }

    /**
     * 增加praise
     *
     * @param newsId
     * @param up
     * @param down
     * @return
     */
    public int updateNewsInPraise(String newsId, int up, int down) {
        String sql = "update tb_news set news_up=?,news_down=? where news_id=?";
        Object[] params = {up, down, newsId};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }
}
