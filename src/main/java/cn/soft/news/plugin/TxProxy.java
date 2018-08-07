package cn.soft.news.plugin;

import cn.soft.news.annocation.Tx;
import cn.soft.news.utils.C3P0Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author Hanoch
 */
public class TxProxy implements InvocationHandler {

    /**
     * 声明被代理类对象
     */
    private Object obj;

    /**
     * 在私有构造器中给成员变量赋值
     *
     * @param obj obj
     */
    private TxProxy(Object obj) {
        this.obj = obj;
    }

    public static Object bind(Object obj) {
        //生成被代理类接口的子类
        return Proxy.newProxyInstance(TxProxy.class.getClassLoader(),
                obj.getClass().getInterfaces(), new TxProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //判断是否有事务注解
        if (!method.isAnnotationPresent(Tx.class)) {
            //直接返回被代理类
            return method.invoke(obj, args);
        }
        //声明数据库连接
        Connection con = null;
        Object returnValue = null;
        try {
            //获取连接
            con = C3P0Util.getConnection();
            //开启事务
            con.setAutoCommit(false);
            //before
            //调用目标类的方法
            returnValue = method.invoke(obj, args);
            //after
            //调用如果成功提交事务
            con.commit();
        } catch (Exception e) {
            assert con != null;
            con.rollback();
            System.err.println(method.getName() + "方法中事务回滚");
        }
//        finally {
//            assert con != null;
//            con.close();
//            C3P0Util.remove();
//        }
        return returnValue;
    }
}
