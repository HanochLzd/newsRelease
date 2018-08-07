package cn.soft.news.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池C3p0工具
 * (需要在classpath路径下配置c3p0-config.xml文件)
 *
 * @author Hanoch
 */
public class C3P0Util {

    public static DataSource dataSource;

    /**
     * ThreadLocal容器
     */
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();

    static {
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获得数据库连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        Connection con = null;
        con = t1.get();
        if (con == null) {
            try {
                con = getDataSource().getConnection();
                //将获取的连接放入到该线程中
                t1.set(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * 从线程中移除连接
     */
    public static void remove() {
        t1.remove();
    }
}
