package com.alankzh.domain;

import java.time.LocalTime;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 考勤模板，以一天为单位
 * @author alankzh
 *
 */
@NotThreadSafe
public class ClockingTemplate {
    private int id;
    private LocalTime workTime;//舍去日期部分 上班时间 HH:mm:ss
    private LocalTime noonBreakTime;//午休时间
    private LocalTime afternoonWorkTime;//中午上班时间
    private LocalTime offWorkTime;//舍去日期部分 下班时间 HH:mm:ss
    private boolean defaultClocking;//是否为全公司的默认排班
    
    public ClockingTemplate() {
        defaultClocking=false;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalTime getWorkTime() {
        return workTime;
    }
    public void setWorkTime(LocalTime workTime) {
        this.workTime = workTime;
    }
    public LocalTime getOffWorkTime() {
        return offWorkTime;
    }
    public void setOffWorkTime(LocalTime offWorkTime) {
        this.offWorkTime = offWorkTime;
    }
    public boolean isDefaultClocking() {
        return defaultClocking;
    }
    public void setDefaultClocking(boolean defaultClocking) {
        this.defaultClocking = defaultClocking;
    }
    public LocalTime getAfternoonWorkTime() {
        return afternoonWorkTime;
    }
    public void setAfternoonWorkTime(LocalTime afternoonWorkTime) {
        this.afternoonWorkTime = afternoonWorkTime;
    }
    public LocalTime getNoonBreakTime() {
        return noonBreakTime;
    }
    public void setNoonBreakTime(LocalTime noonBreakTime) {
        this.noonBreakTime = noonBreakTime;
    }
}
