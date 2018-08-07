package cn.soft.news.dao;

import cn.soft.news.po.TbTheme;
import cn.soft.news.utils.C3P0Util;
import cn.soft.news.utils.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hanoch
 */
public class ThemeDao {

    private Connection connection = C3P0Util.getConnection();
    private DBUtil dbUtil = new DBUtil();

    /**
     * 查询所有新闻主题
     *
     * @return List<TbTheme>
     */
    public List<TbTheme> queryAllTheme() {
        String sql = "select * from tb_theme";
        List<TbTheme> themes = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TbTheme theme = new TbTheme();
                theme.setThemeId(rs.getInt(1));
                theme.setThemeName(rs.getString(2));
                theme.setThemeDetail(rs.getString(3));
                theme.setThemeLevel(rs.getInt(4));
                theme.setThemeCreateTime(rs.getDate(5));
                themes.add(theme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return themes;
    }

    /**
     * 查询等级最高的 X个主题
     *
     * @param count X
     * @return List<TbTheme>
     */
    public List<TbTheme> firstTwoTheme(int count) {
        String sql = "select * from tb_theme order by theme_level desc limit 0," + count;
        List<TbTheme> themes = new ArrayList<>(count);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TbTheme theme = new TbTheme();
                theme.setThemeId(rs.getInt(1));
                theme.setThemeName(rs.getString(2));
                theme.setThemeDetail(rs.getString(3));
                theme.setThemeLevel(rs.getInt(4));
                theme.setThemeCreateTime(rs.getDate(5));
                themes.add(theme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return themes;
    }

    /**
     * 查询指定ID的名字
     *
     * @param id id
     * @return String
     */
    public String getThemeNameById(int id) {
        String sql = "select theme_name from tb_theme where theme_id=?";
        Object[] params = {id};
        ResultSet rs = dbUtil.getResultSet(sql, Arrays.asList(params));
        String themeName = null;
        try {
            while (rs.next()) {
                themeName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return themeName;
    }
}
