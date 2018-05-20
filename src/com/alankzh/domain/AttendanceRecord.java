package com.alankzh.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 考勤记录
 * @author alankzh
 *
 */
@NotThreadSafe
public class AttendanceRecord {
    private int id;//保留关键字
    private int employeeId;
    private String employeeName;
    private LocalDate clockingDate;//考勤时间 yyyy-MM-dd
    /**
     * 上班考勤状态
     * 0:未打卡
     * 1:正常上班
     * 2:迟到
     * 3:旷工
     * 4:病假
     * 5:事假
     * 6:法定假期
     */
    private int workType;
    /**
     * 下班考勤状态
     * 0:未打卡
     * 1:正常下班
     * 2:早退
     * 3:旷工
     * 4:病假
     * 5:事假
     * 6:法定假期
     */
    private int offWorkType;
    
    private int noonBreakType;//中午下班
    
    private int afternoonWorkType;//中午上班
    
    private LocalTime shouldWorkTime;//应该上班的时间 HH:mm:ss
    private LocalTime workTime;//实际上班时间 HH:mm:ss
    
    private LocalTime shouldNoonBreakTime;
    private LocalTime noonBreakTime;
    
    private LocalTime shouldAfternoonWorkTime;
    private LocalTime afternoonWorkTime;
    
    private LocalTime shouldOffWorkTime;//应该下班的时间 HH:mm:ss
    private LocalTime offWorkTime;//实际下班时间 HH:mm:ss
    
    public AttendanceRecord() {
        workType=0;
        offWorkType=0;
        noonBreakType=0;
        afternoonWorkType=0;
    }   
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public WorkTypeEnum getWorkType() {
        return WorkTypeEnum.valueOf(this.workType);
    }
    public void setWorkType(WorkTypeEnum workTypeEnum) {
        this.workType = workTypeEnum.getType();
    }
    public OffWorkTypeEnum getOffWorkType() {
        return OffWorkTypeEnum.valueOf(this.offWorkType);
    }
    public void setOffWorkType(OffWorkTypeEnum offWorkTypeEnum) {
        this.offWorkType = offWorkTypeEnum.getType();
    }
    public LocalDate getClockingDate() {
        return clockingDate;
    }
    public void setClockingDate(LocalDate clockingDate) {
        this.clockingDate = clockingDate;
    }
    public LocalTime getShouldWorkTime() {
        return shouldWorkTime;
    }
    public void setShouldWorkTime(LocalTime shouldWorkTime) {
        this.shouldWorkTime = shouldWorkTime;
    }
    public LocalTime getWorkTime() {
        return workTime;
    }
    public void setWorkTime(LocalTime workTime) {
        this.workTime = workTime;
    }
    public LocalTime getShouldOffWorkTime() {
        return shouldOffWorkTime;
    }
    public void setShouldOffWorkTime(LocalTime shouldOffWorkTime) {
        this.shouldOffWorkTime = shouldOffWorkTime;
    }
    public LocalTime getOffWorkTime() {
        return offWorkTime;
    }
    public void setOffWorkTime(LocalTime offWorkTime) {
        this.offWorkTime = offWorkTime;
    }
    public OffWorkTypeEnum getNoonBreakType() {
        return OffWorkTypeEnum.valueOf(noonBreakType);
    }
    public void setNoonBreakType(OffWorkTypeEnum noonBreakType) {
        this.noonBreakType = noonBreakType.getType();
    }
    public WorkTypeEnum getAfternoonWorkType() {
        return WorkTypeEnum.valueOf(afternoonWorkType);
    }
    public void setAfternoonWorkType(WorkTypeEnum afternoonWorkType) {
        this.afternoonWorkType = afternoonWorkType.getType();
    }
    public LocalTime getShouldNoonBreakTime() {
        return shouldNoonBreakTime;
    }
    public void setShouldNoonBreakTime(LocalTime shouldNoonBreakTime) {
        this.shouldNoonBreakTime = shouldNoonBreakTime;
    }
    public LocalTime getNoonBreakTime() {
        return noonBreakTime;
    }
    public void setNoonBreakTime(LocalTime noonBreakTime) {
        this.noonBreakTime = noonBreakTime;
    }
    public LocalTime getShouldAfternoonWorkTime() {
        return shouldAfternoonWorkTime;
    }
    public void setShouldAfternoonWorkTime(LocalTime shouldAfternoonWorkTime) {
        this.shouldAfternoonWorkTime = shouldAfternoonWorkTime;
    }
    public LocalTime getAfternoonWorkTime() {
        return afternoonWorkTime;
    }
    public void setAfternoonWorkTime(LocalTime afternoonWorkTime) {
        this.afternoonWorkTime = afternoonWorkTime;
    }
}
