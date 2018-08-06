package cn.soft.news.dao;

import cn.soft.news.po.Praise;
import cn.soft.news.utils.C3P0Util;
import cn.soft.news.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author Hanoch
 */
public class PraiseDao {

    private DBUtil dbUtil = new DBUtil();

    /**
     * 得到某条新闻下的所有的点赞IP
     *
     * @param newsId
     * @param ip
     * @return
     */
    public List<Praise> queryOneNews2OneIp(String newsId, String ip) {
        String sql = "select * from tb_praise where praise_news_id=? and praise_ip=?;";
        List<Praise> praises = new ArrayList<>();
        Object[] params = {newsId, ip};
        try {
            List<Map<String, Object>> mapList = dbUtil.resultSet2List(sql, Arrays.asList(params));
            for (Map<String, Object> objectMap : mapList) {
                Praise praise = new Praise();
                praise.setPraiseId((String) objectMap.get("praise_id"));
                praise.setPraiseType((Integer) objectMap.get("praise_type"));
                praise.setPraiseNewsId((String) objectMap.get("praise_news_id"));
                praise.setPraiseIp((String) objectMap.get("praise_ip"));
                praises.add(praise);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return praises;
    }

    /**
     * 增加一条点赞记录
     *
     * @param newsId newsId
     * @param ip     ip
     * @return
     */
    public int addOnePraise(String newsId, int type, String ip) {
        String sql = "insert into tb_praise(praise_id, praise_type, praise_news_id, praise_ip) values(?,?,?,?)";
        String praiseId = UUID.randomUUID().toString().replace("-", "");
        Object[] params = {praiseId, type, newsId, ip};
        return dbUtil.getCount(sql, Arrays.asList(params));
    }
}
