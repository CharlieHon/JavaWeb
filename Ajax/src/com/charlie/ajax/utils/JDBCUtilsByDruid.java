package com.charlie.ajax.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtilsByDruid {

    private static final DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            /*
            1. 目前是使用JavaWeb方式启动
            2. 所以要获取src目录的文件，需要使用类加载器
            */
//            不能使用该这种方式，因为src是在JavaSE application上使用的路径
//            properties.load(new FileInputStream("src\\druid.properties"));
            properties.load(JDBCUtilsByDruid.class.getClassLoader().
                    getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // 关闭连接
    // 注意：在数据库连接池技术中，close不是真正地断掉连接，而是把使用地Connection对象放回到连接池
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
