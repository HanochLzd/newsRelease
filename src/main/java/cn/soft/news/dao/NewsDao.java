package cn.soft.news.dao;

import cn.soft.news.annocation.Tx;
import cn.soft.news.vo.NewsVo;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Hanoch
 */
public interface NewsDao {

    /**
     * 查询所有新闻标题
     *
     * @return List<NewsVo>
     */
    List<NewsVo> queryAllNews();


    /**
     * 获得总记录数(某类或全部)
     *
     * @param themeId themeId
     * @return
     */
    Long getCount(int themeId);

    /**
     * 查询当前页数据
     *
     * @param pageSize 每页记录数
     * @param pageNo   当前页数
     * @param newsType 新闻类型
     * @return 数据集
     */
    List<NewsVo> queryPage(int pageSize, long pageNo, int newsType);

    /**
     * @return
     */
    List<NewsVo> queryAllNewsAll();


    /**
     * 条件查询
     *
     * @param type
     * @param searchContent
     * @return
     */
    List<NewsVo> queryByExample(String type, String searchContent);

    /**
     * 主页中查询指定主题的新闻
     *
     * @param count
     * @return
     */
    Map<String, List<NewsVo>> newsInTheme(int count);

    /**
     * 查询指定主题下的新闻
     *
     * @param themeId
     * @return
     */
    List<NewsVo> queryNewsByThemeId(int themeId);

    /**
     * 删除一条新闻
     *
     * @param id id
     * @return
     */
    @Tx
    int deleteNews(String id);

    /**
     * 查询一条新闻
     *
     * @param id
     * @return
     */
    NewsVo queryOneNews(String id);

    /**
     * 添加一条新闻
     *
     * @param newsVo
     * @return
     */
    @Tx
    int addOneNews(NewsVo newsVo) throws SQLException;

    /**
     * 更新一条新闻
     *
     * @param newsVo
     * @return
     * @throws SQLException
     */
    @Tx
    int updateNews(NewsVo newsVo) throws SQLException;

    /**
     * 增加praise
     *
     * @param newsId
     * @param up
     * @param down
     * @return
     */
    @Tx
    int updateNewsInPraise(String newsId, int up, int down) throws SQLException;

    /**
     * 删除某主题下的所有新闻
     *
     * @param themeId
     * @return
     */
    @Tx
    int deleteNewsInGroup(int themeId) throws SQLException;

}
