package cn.soft.news.dao;

import cn.soft.news.po.TbUser;
import cn.soft.news.utils.C3P0Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Hanoch
 */
public class UserDao {
    private Connection con = C3P0Util.getConnection();

    public TbUser getUserByUserName(String username) {
        String sql = "select * from tb_user where user_name='" + username + "'";
        Statement stmt = null;
        ResultSet rs = null;
        TbUser user = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user = new TbUser();
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                assert rs != null;
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
