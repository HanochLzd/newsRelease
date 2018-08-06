package cn.soft.news.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc获取连接
 *
 * @author Hanoch
 */
public class ConnectionUtil {
    private static final String PATH = "db.properties";

    private static String url;

    private static String user;

    private static String password;

    static {
        Properties properties = new Properties();
        InputStream br = null;
        try {
            br = ConnectionUtil.class.getClassLoader().getResourceAsStream(PATH);
            properties.load(br);
            String driver = properties.getProperty("jdbc.driverClass");
            url = properties.getProperty("jdbc.jdbcUrl");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
            Class.forName(driver);
            System.out.println("加载驱动成功！");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取数据库连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("获取数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
