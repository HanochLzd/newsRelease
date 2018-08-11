package cn.soft.news.dao.impl;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.dao.ThemeDao;
import cn.soft.news.po.TbTheme;
import cn.soft.news.utils.C3P0Util;
import cn.soft.news.utils.DBUtil;
import cn.soft.news.vo.NewsVo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hanoch
 */
public class NewsDaoImpl implements NewsDao {

    private Connection connection = C3P0Util.getConnection();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private ThemeDao themeDao = new ThemeDaoImpl();

    private DBUtil dbUtil = new DBUtil();


    /**
     * 查询所有新闻标题
     *
     * @return List<NewsVo>
     */
    @Override
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
     * 获得总记录数(某类或全部)
     *
     * @return Long
     */
    @Override
    public Long getCount(int themeId) {
        String sql;
        Object[] params;
        if (0 == themeId) {
            sql = "select  count(news_id) from tb_news";
            params = new Object[]{};
        } else {
            sql = "select  count(news_id) from tb_news where news_theme_id=?";
            params = new Object[]{themeId};
        }
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
    @Override
    public List<NewsVo> queryPage(int pageSize, long pageNo, int newsType) {
        String sql;
        Object[] params;
        if (newsType == 0) {
            sql = "select news_id,news_theme_id,news_title,news_create_time " +
                    "from tb_news order by news_create_time desc limit ?,?;";
            params = new Object[]{(pageSize * (pageNo - 1)), pageSize};
        } else {
            sql = "select news_id,news_theme_id,news_title,news_create_time " +
                    "from tb_news where news_theme_id=? order by news_create_time desc limit ?,?;";
            params = new Object[]{newsType, (pageSize * (pageNo - 1)), pageSize};
        }
        List<NewsVo> newsVoList = new ArrayList<>();

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


    @Override
    public List<NewsVo> queryAllNewsAll() {
        String sql = "select news_id,theme_name,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t where n.news_theme_id=t.theme_id order by news_create_time desc";
        Object[] params = {};
        return getNewsVoList(sql, params);
    }

    @Override
    public List<NewsVo> queryByExample(String type, String searchContent) {
        String sql1 = "select news_id,theme_name,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t where n.news_theme_id=t.theme_id ";
        StringBuilder sql = new StringBuilder(sql1);
        switch (type) {
            case "theme":
                int themeId = themeDao.qureyThemeByName(searchContent).getThemeId();
                sql.append("and n.news_theme_id=").append(themeId);
                break;
            case "title":
                sql.append("and n.news_title like '%").append(searchContent).append("%'");
                break;
            case "author":
                sql.append("and n.news_author like '%").append(searchContent).append("%'");
                break;
            default:
                break;
        }
        sql.append(" order by news_create_time desc");
        Object[] params = {};
        return getNewsVoList(sql.toString(), params);
    }

    /**
     * 查询
     *
     * @param sql
     * @param params
     * @return
     */
    private List<NewsVo> getNewsVoList(String sql, Object[] params) {
        List<NewsVo> newsVoList = new ArrayList<>();
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public int addOneNews(NewsVo newsVo) throws SQLException {
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
    @Override
    public int updateNews(NewsVo newsVo) throws SQLException {
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
    @Override
    public int updateNewsInPraise(String newsId, int up, int down) throws SQLException {
        String sql = "update tb_news set news_up=?,news_down=? where news_id=?";
        Object[] params = {up, down, newsId};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

    @Override
    public int deleteNewsInGroup(int themeId) throws SQLException {
        String sql = "delete from tb_news where news_theme_id=?";
        Object[] params = {themeId};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

}
