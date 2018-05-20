package com.alankzh.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.alankzh.annotation.ThreadSafe;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 数据库连接池，目前采用‘光’数据库技术
 * @author alankzh
 *
 */
@ThreadSafe
public class ConnectionPool {
    private static ConnectionPool instance;
    private HikariDataSource dataSourcePool;
    
    public static ConnectionPool getInstance(){
        if(instance==null) {
            synchronized (ConnectionPool.class) {
                if(instance==null) {
                    instance=new ConnectionPool();
                }
            }
        }
        return instance;
    }
    
    /**
     * 初始化数据库连接池
     */
    private ConnectionPool(){
        try {
            Properties pros=new Properties();
            //获取WEB-INF/classes/jdbc.properties的路径
            String path=ConnectionPool.class.getClassLoader().getResource("jdbc.properties").getPath();
            InputStream in = new BufferedInputStream(new FileInputStream(  
                    new File(path)));  
            pros.load(in);
            
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(pros.getProperty("mysqlDriver"));
            config.setJdbcUrl(pros.getProperty("mysqlURL"));
            config.setUsername("root");
            config.setPassword("123456");
            config.setConnectionTestQuery("SELECT 1");
            config.setMaximumPoolSize(15);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.setConnectionTimeout(1000);
            config.setMaxLifetime(600000);
            dataSourcePool= new HikariDataSource(config);
        } catch (IOException e) {
            System.out.println("jdbc配置文件读取错误");
            e.printStackTrace();
        }
    }
    
    /**
     * 销毁连接池
     */
    public static void shutDown() {
        if(ConnectionPool.getInstance().dataSourcePool!=null) {
            ConnectionPool.getInstance().dataSourcePool.shutdown();
        }
    }
    
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        try {
         //这句是加锁的
         return ConnectionPool.getInstance().dataSourcePool.getConnection();
        } catch (SQLException e) {
            System.out.println("获取数据库连接错误");
            e.printStackTrace();
            return null;
        }
    }
    
    //关闭连接和statement,可根据需要无限重载下去
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Statement st,Connection conn) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
