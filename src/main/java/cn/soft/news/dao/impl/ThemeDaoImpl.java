package cn.soft.news.dao.impl;

import cn.soft.news.dao.NewsDao;
import cn.soft.news.dao.ThemeDao;
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
public class ThemeDaoImpl implements ThemeDao {
    private Connection connection = C3P0Util.getConnection();
    private DBUtil dbUtil = new DBUtil();

    /**
     * 查询所有新闻主题
     *
     * @return List<TbTheme>
     */
    @Override
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
                theme.setThemeCreateTime(rs.getTimestamp(5));
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
    @Override
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
    @Override
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

    @Override
    public TbTheme queryOneTheme(int themeId) {
        String sql = "select * from tb_theme where theme_id=?";
        Object[] param = {themeId};
        ResultSet rs = dbUtil.getResultSet(sql, Arrays.asList(param));
        TbTheme theme = new TbTheme();
        try {
            if (rs.next()) {
                theme.setThemeId(themeId);
                theme.setThemeName(rs.getString(2));
                theme.setThemeDetail(rs.getString(3));
                theme.setThemeLevel(rs.getInt(4));
                theme.setThemeCreateTime(rs.getTimestamp(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theme;
    }

    @Override
    public int updateTheme(TbTheme theme) throws SQLException {
        String sql = "update tb_theme set theme_name=?,theme_detail=?,theme_level=?,theme_create_time=? " +
                "where theme_id=?";
        Object[] params = {theme.getThemeName(), theme.getThemeDetail(), theme.getThemeLevel(),
                theme.getThemeCreateTime(), theme.getThemeId()};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

    @Override
    public TbTheme qureyThemeByName(String themeName) {
        String sql = "select * from tb_theme where theme_name=?";
        Object[] param = {themeName};
        ResultSet rs = dbUtil.getResultSet(sql, Arrays.asList(param));
        TbTheme theme = new TbTheme();
        try {
            if (rs.next()) {
                theme.setThemeId(rs.getInt(1));
                theme.setThemeName(themeName);
                theme.setThemeDetail(rs.getString(3));
                theme.setThemeLevel(rs.getInt(4));
                theme.setThemeCreateTime(rs.getTimestamp(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theme;
    }

    @Override
    public int addTheme(TbTheme theme) throws SQLException {
        String sql = "insert into tb_theme values(?,?,?,?,?)";
        Object[] params = {theme.getThemeId(), theme.getThemeName(), theme.getThemeDetail(), theme.getThemeLevel(),
                theme.getThemeCreateTime()};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

    @Override
    public int deleteOneTheme(int themeId) throws SQLException {
        //删除主题
        String sql = "delete from tb_theme where theme_id=?";
        Object[] params = {themeId};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }

}
