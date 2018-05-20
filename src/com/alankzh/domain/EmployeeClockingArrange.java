package com.alankzh.domain;

import java.time.LocalDate;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 排班表安排
 * @author alankzh
 *
 */
@NotThreadSafe
public class EmployeeClockingArrange {
    private int id;
    private int employeeId;
    private int clockingId;
    private LocalDate clockingDate;//排班时间 yyyy-MM-dd
    
    /**
     * 循环方式
     * 1:表示工作日排班，每天皆是如此
     * 2:表示自循环排班，按照多个EmployeeClockingArrange的顺序一直循环
     * 3:表示休假，优先级高于工作日排班和自循环排班
     * 4：表示强制排班安排,优先级高于休假
     */
    private int circleType;
    
    public enum CircleTypeEnum{
        //TODO 自循环排班和休假的优先级值得商定
        workArrange(1),//工作日排班
        selfCircleArrange(2),//自循环排班
        holidayArrange(3),//休假
        overtimeArrange(4);//强制加班安排
        
        private int type;
        private CircleTypeEnum(int type) {
            this.type=type;
        }
        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
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

    public int getClockingId() {
        return clockingId;
    }

    public void setClockingId(int clockingId) {
        this.clockingId = clockingId;
    }

    public LocalDate getClockingDate() {
        return clockingDate;
    }

    public void setClockingDate(LocalDate clockingDate) {
        this.clockingDate = clockingDate;
    }

    public CircleTypeEnum getCircleType() {
        CircleTypeEnum circleTypeEnum=CircleTypeEnum.holidayArrange;
        circleTypeEnum.setType(this.circleType);
        return circleTypeEnum;
    }

    public void setCircleType(CircleTypeEnum circleTypeEnum) {
        this.circleType = circleTypeEnum.getType();
    }
}
