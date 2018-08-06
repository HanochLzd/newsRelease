package cn.soft.news.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池C3p0工具
 * (需要在classpath路径下配置c3p0-config.xml文件)
 *
 * @author Hanoch
 */
public class C3P0Util {

    public static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 获得数据库连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
