package com.alankzh.service;

//job相关的builder
import static org.quartz.JobBuilder.*;

//trigger相关的builder
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DailyTimeIntervalScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;

import static org.quartz.DateBuilder.newDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.GregorianCalendar;

import org.quartz.impl.calendar.AnnualCalendar;

//日期相关的builder
import static org.quartz.DateBuilder.*;

import com.alankzh.annotation.ThreadSafe;

/**
 * 定时器任务管理
 * 
 * @author alankzh
 * 
 */
@ThreadSafe
public class QuartzManager {
    private static volatile boolean isStarted = false;
    private static Scheduler scheduler=null;
    
    
    /**
     * 开始任务
     */
    public static synchronized void startJob() {
        if(isStarted) {
            return;
        }
        
//        try {
//            StdSchedulerFactory.getDefaultScheduler().shutdown();
//            System.out.println("杀必死");
//            return;
//        } catch (SchedulerException e1) {
//            e1.printStackTrace();
//        }
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 创建scheduler
            // 创建jobDetail 1.job分组，2.job名，3.job执行类
            JobDetail jobDetail = newJob()
                    .ofType(MidnightQuartz.class)
                    .withIdentity("job1", "group1")
                    .build();
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 1 0 * * ?") //每天凌晨0点1分执行任务
                        )
                .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 停止任务
     */
    public static synchronized void stopJob() {
        if(!isStarted) {
            return;
        }
        try {
            if(scheduler!=null) {
                scheduler.shutdown();                
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
