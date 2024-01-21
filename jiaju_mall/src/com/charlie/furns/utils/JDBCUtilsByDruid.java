package com.charlie.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    // 定义属性ThreadLocal。这里存放一个Connection
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            // 因为是web项目，工作目录在out，文件的加载需要使用类加载器
            // 找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
//            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 从ThreadLocal获取Connection，从而保证在同一个线程中，获取的是同一个Connection
    public static Connection getConnection() {
        Connection connection = threadLocalConn.get();
        if (connection == null) {   // 说明当前的threadLocalConn没有连接
            // 就从数据库连接池中，取出一个连接放入
            try {
                connection = ds.getConnection();
                // 将连接设置为手动提交，即取消自动提交，默认每执行一次数据库操作都会自动提交，
                // 取消自动提交保证数据统一管理
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 将连接与线程关联
            threadLocalConn.set(connection);
        }
        return connection;
    }

    //原方法
    //public static Connection getConnection() throws SQLException {
    //    return ds.getConnection();
    //}

    // 提交事务(成功)
    public static void commit() {
        Connection connection = threadLocalConn.get();
        if (connection != null) {   // 确保该链接非空/有效
            try {
                connection.commit();    // 事务提交
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 关闭连接
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 注意：
        // 1. 当提交后，需要把connection从threadLocalConn中清除掉
        // 2. 否则会造成threadLocalConn长时间持有该连接，会影响效率
        // 3. 也因为Tomcat底层使用的线程池技术
        threadLocalConn.remove();
    }

    // 事务回滚(失败)，所谓回滚是撤销和某个connection关联的操作
    public static void rollback() {
        Connection connection = threadLocalConn.get();
        if (connection != null) {   // 保证当前连接有效
            try {
                connection.rollback();  // 回滚
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 关闭
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocalConn.remove();
    }

    //关闭连接, 强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
