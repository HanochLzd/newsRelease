package cn.soft.news.dao;

import cn.soft.news.annocation.Tx;
import cn.soft.news.po.TbTheme;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hanoch
 */
public interface ThemeDao {


    /**
     * 查询所有新闻主题
     *
     * @return List<TbTheme>
     */
    List<TbTheme> queryAllTheme();

    /**
     * 查询等级最高的 X个主题
     *
     * @param count X
     * @return List<TbTheme>
     */
    List<TbTheme> firstTwoTheme(int count);

    /**
     * 查询指定ID的名字
     *
     * @param id id
     * @return String
     */
    String getThemeNameById(int id);

    /**
     * 查询一条主题信息
     *
     * @param themesId
     * @return
     */
    TbTheme queryOneTheme(int themesId);

    /**
     * 更改theme
     *
     * @param theme
     * @return
     */
    @Tx
    int updateTheme(TbTheme theme) throws SQLException;

    /**
     * 根据主题名查询
     *
     * @param themeName
     * @return
     */
    TbTheme qureyThemeByName(String themeName);

    /**
     * 添加一主题
     *
     * @param theme
     * @return
     */
    @Tx
    int addTheme(TbTheme theme) throws SQLException;

    /**
     * 删除主题 包括主题下的所有新闻
     *
     * @param themeId
     * @return
     * @throws SQLException
     */
    @Tx
    int deleteOneTheme(int themeId) throws SQLException;
}
