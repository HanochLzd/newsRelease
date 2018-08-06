package cn.soft.news.utils;

import cn.soft.news.vo.NewsVo;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class DBUtilTest {

    private DBUtil dbUtil = new DBUtil();

    @Test
    public void resultSet2List() {
        String sql = "select news_id,theme_name,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t where n.news_theme_id=t.theme_id order by news_create_time desc";

        Object[] params = {};

        try {
            List<Map<String, Object>> mapList = dbUtil.resultSet2List(sql, Arrays.asList(params));
            System.out.println(mapList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void map2Object() {

        String sql = "select news_id,theme_name,news_author,news_title,news_content,news_up,news_down,news_create_time " +
                "from tb_news n,tb_theme t where n.news_theme_id=t.theme_id order by news_create_time desc";

        Object[] params = {};

        try {
            List<Object> list = dbUtil.map2Object(sql, Arrays.asList(params), "cn.soft.news.vo.NewsVo");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}