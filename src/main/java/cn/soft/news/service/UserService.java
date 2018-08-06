package cn.soft.news.service;

import cn.soft.news.po.TbUser;

/**
 * @author Hanoch
 */
public interface UserService {

    /**
     *
     * @param username
     * @return
     */
    TbUser getUserByUserName(String username);

}
