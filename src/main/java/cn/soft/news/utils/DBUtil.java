package cn.soft.news.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.*;

/**
 * @author Hanoch
 */
public class DBUtil {

    private Connection connection = C3P0Util.getConnection();
    private PreparedStatement pstm;

    /**
     * 数据库的结果集
     */
    private ResultSet resultSet;
    /**
     * Map<数据库字段名，字段对应的值>
     */
    private Map<String, Object> map = null;

    private void doSQL(String sql, List<Object> params) throws SQLException {
        //发送sql语句
        pstm = connection.prepareStatement(sql);
        //解析参数
        createParam(params);

        //执行SQL语句
        pstm.execute();
    }

    /**
     * 解析参数(参数填充占位符“？”)
     *
     * @param params params
     */
    private void createParam(List<Object> params) throws SQLException {
        if (params == null || params.size() < 1) {
            return;
        }
        for (int i = 0; i < params.size(); i++) {
            pstm.setObject(i + 1, params.get(i));
        }
    }

    /**
     * 获得操作条数
     *
     * @param sql    sql
     * @param params params
     * @return int
     */
    public int getCount(String sql, List<Object> params) throws SQLException {
        doSQL(sql, params);
        //返回执行结果（操作数据的条数）
        return pstm.getUpdateCount();
    }

    /**
     * 获得结果集
     *
     * @param sql   sql
     * @param param param
     */
    public ResultSet getResultSet(String sql, List<Object> param) {
        try {
            doSQL(sql, param);
            // 返回执行结果
            return resultSet = pstm.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数据库查询结果集
     *
     * @param sql   sql
     * @param param param
     * @return
     */
    public List<Map<String, Object>> resultSet2List(String sql, List<Object> param) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        Set<String> names = new HashSet<>();
        //获得结果集
        getResultSet(sql, param);
        //获得数据库的表的字段的信息类
        ResultSetMetaData rsmData = resultSet.getMetaData();
        //获得数据库的列数
        int count = rsmData.getColumnCount();
        for (int i = 0; i < count; i++) {
            //获得列名
            String name = rsmData.getColumnName(i + 1);
            names.add(name);
        }
        while (resultSet.next()) {
            map = new HashMap<>(16);
            for (String name : names) {
                Object obj = resultSet.getObject(name);
                map.put(name, obj);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 将数据库数据集转换成实体类数据集
     *
     * @param sql      sql
     * @param param    param
     * @param clazName clazName
     * @return objects
     * @throws Exception Exception
     */
    public List<Object> map2Object(String sql, List<Object> param, String clazName) throws Exception {
        List<Object> objects = new ArrayList<>();
        //获得结果集
        getResultSet(sql, param);
        //获得数据信息的类
        ResultSetMetaData data = resultSet.getMetaData();
        //获得数据库的列数
        int count = data.getColumnCount();
        //遍历集合
        while (resultSet.next()) {
            map = new HashMap<>(16);
            for (int i = 0; i < count; i++) {
                //获得列名
                String name = data.getColumnName(i + 1);
                //通过列名获得值
                map.put(name, resultSet.getObject(name));
            }
            //反射创建对象
            Object obj = Class.forName(clazName).getDeclaredConstructor().newInstance();
            BeanUtils.populate(obj, map);
            objects.add(obj);
        }
        return objects;
    }

}
