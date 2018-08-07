package cn.soft.news.dao;

import cn.soft.news.annocation.Tx;
import cn.soft.news.po.Praise;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Hanoch
 */
public interface PraiseDao {
    /**
     * 得到某条新闻下的所有的点赞IP
     *
     * @param newsId
     * @param ip
     * @return
     */
    List<Praise> queryOneNews2OneIp(String newsId, String ip);

    /**
     * 增加一条点赞记录
     *
     * @param newsId newsId
     * @param ip     ip
     * @return
     */
    @Tx
    int addOnePraise(String newsId, int type, String ip) throws SQLException;
}
