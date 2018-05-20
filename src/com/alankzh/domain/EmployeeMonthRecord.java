package com.alankzh.domain;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 每个员工这个月得考勤
 * 
 * @author alankzh
 *
 */
@NotThreadSafe
public class EmployeeMonthRecord {
    private Employee employee;
    private int normalWorkDayNum;// 工作多少天
    private int holidayNum;//假期天数
    private int abnormalWorkDayNum;//打卡不正常天数
    
    private int workDelayNum;// 上班迟到多少次
    private int workAbsentNum;//上班旷班多少次
    private int workUnClockingNum;//上班未打卡多少次
    
    private int noonBreakEarlyNum;//中午休息早退多少次
    private int noonBreakAbsentNum;//中午休息旷班多少次
    private int noonBreakUnclockingNum;//中午休息
    
    private int afternoonWorkDelayNum;//下午上班迟到
    private int afternoonWorkAbsentNum;//下午上班旷班
    private int afternoonWorkUnclockingNum;//下午上班未打卡
    
    private int offWorkEarlyNum;//下班早退
    private int offWorkAbsentNum;//下班旷班
    private int offWorkUnclockNum;//下班未打卡
    
    public EmployeeMonthRecord() {
       
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNormalWorkDayNum() {
        return normalWorkDayNum;
    }

    public void setNormalWorkDayNum(int normalWorkDayNum) {
        this.normalWorkDayNum = normalWorkDayNum;
    }

    public int getHolidayNum() {
        return holidayNum;
    }

    public void setHolidayNum(int holidayNum) {
        this.holidayNum = holidayNum;
    }

    public int getAbnormalWorkDayNum() {
        return abnormalWorkDayNum;
    }

    public void setAbnormalWorkDayNum(int abnormalWorkDayNum) {
        this.abnormalWorkDayNum = abnormalWorkDayNum;
    }

    public int getWorkDelayNum() {
        return workDelayNum;
    }

    public void setWorkDelayNum(int workDelayNum) {
        this.workDelayNum = workDelayNum;
    }

    public int getWorkAbsentNum() {
        return workAbsentNum;
    }

    public void setWorkAbsentNum(int workAbsentNum) {
        this.workAbsentNum = workAbsentNum;
    }

    public int getWorkUnClockingNum() {
        return workUnClockingNum;
    }

    public void setWorkUnClockingNum(int workUnClockingNum) {
        this.workUnClockingNum = workUnClockingNum;
    }

    public int getNoonBreakEarlyNum() {
        return noonBreakEarlyNum;
    }

    public void setNoonBreakEarlyNum(int noonBreakEarlyNum) {
        this.noonBreakEarlyNum = noonBreakEarlyNum;
    }

    public int getNoonBreakAbsentNum() {
        return noonBreakAbsentNum;
    }

    public void setNoonBreakAbsentNum(int noonBreakAbsentNum) {
        this.noonBreakAbsentNum = noonBreakAbsentNum;
    }

    public int getNoonBreakUnclockingNum() {
        return noonBreakUnclockingNum;
    }

    public void setNoonBreakUnclockingNum(int noonBreakUnclockingNum) {
        this.noonBreakUnclockingNum = noonBreakUnclockingNum;
    }

    public int getAfternoonWorkDelayNum() {
        return afternoonWorkDelayNum;
    }

    public void setAfternoonWorkDelayNum(int afternoonWorkDelayNum) {
        this.afternoonWorkDelayNum = afternoonWorkDelayNum;
    }

    public int getAfternoonWorkAbsentNum() {
        return afternoonWorkAbsentNum;
    }

    public void setAfternoonWorkAbsentNum(int afternoonWorkAbsentNum) {
        this.afternoonWorkAbsentNum = afternoonWorkAbsentNum;
    }

    public int getAfternoonWorkUnclockingNum() {
        return afternoonWorkUnclockingNum;
    }

    public void setAfternoonWorkUnclockingNum(int afternoonWorkUnclockingNum) {
        this.afternoonWorkUnclockingNum = afternoonWorkUnclockingNum;
    }

    public int getOffWorkEarlyNum() {
        return offWorkEarlyNum;
    }

    public void setOffWorkEarlyNum(int offWorkEarlyNum) {
        this.offWorkEarlyNum = offWorkEarlyNum;
    }

    public int getOffWorkAbsentNum() {
        return offWorkAbsentNum;
    }

    public void setOffWorkAbsentNum(int offWorkAbsentNum) {
        this.offWorkAbsentNum = offWorkAbsentNum;
    }

    public int getOffWorkUnclockNum() {
        return offWorkUnclockNum;
    }

    public void setOffWorkUnclockNum(int offWorkUnclockNum) {
        this.offWorkUnclockNum = offWorkUnclockNum;
    }

   

}
