package cn.soft.news.dao;

import cn.soft.news.po.TbTheme;
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
}
