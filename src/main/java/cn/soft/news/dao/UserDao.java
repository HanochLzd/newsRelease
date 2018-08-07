package cn.soft.news.dao;

import cn.soft.news.po.TbUser;

/**
 * @author Hanoch
 */
public interface UserDao {

    /**
     * 通过username查询user
     *
     * @param username
     * @return
     */
    TbUser getUserByUserName(String username);
}
