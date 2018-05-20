package com.alankzh.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.ConnectionPool;
import com.alankzh.sql.DataBaseMaintenance;

/**
 * 监听web应用的启动和销毁
 * @author alankzh
 *
 */
@Immutable
@WebListener
public class WebApplicationListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBaseMaintenance.establish();//建立数据库表
        QuartzManager.startJob();//开启定时任务
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        QuartzManager.stopJob();
        ConnectionPool.shutDown();
    }
    
}
