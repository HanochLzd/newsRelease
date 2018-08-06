package cn.soft.news.service.impl;

import cn.soft.news.dao.UserDao;
import cn.soft.news.po.TbUser;
import cn.soft.news.service.UserService;

/**
 * @author Hanoch
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public TbUser getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }
}
