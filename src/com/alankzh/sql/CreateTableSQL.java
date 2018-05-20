package com.alankzh.sql;


import com.alankzh.annotation.Immutable;

/**
 * 建表语句，用于版本1的数据库建立阶段
 * @author alankzh
 *
 */
@Immutable
public class CreateTableSQL {
    //建表语句，建立部门表
    public static final String CREATE_TABLE_DEPARTMENT_SQL="create table department("
            +"id int primary key auto_increment,"
            + "name varchar(20)" 
            +")";
    
    //建表语句，建立部门关系表
    public static final String CREATE_TABLE_DEPARTMENT_RELATION_SQL="create table departmentRelation("
            + "id int primary key auto_increment,"
            + "parentId int,"
            + "subId int,"
            + "isDefaultSub bool not null default 0,"
            + "constraint parent_deparmentId_fk foreign key(parentId) references department(id),"
            + "constraint sub_deparmentId_fk foreign key(subId) references department(id)"
            + ")";
    
    //建表语句，建立员工表
    public static final String CREATE_TABLE_EMPLOYEE_SQL="create table employee("
            + "id int primary key auto_increment,"
            + "name varchar(32),"
            + "departmentId int,"
            + "departmentName varchar(20),"
            + "age int,"
            + "gender varchar(8),"
            + "photoUrl varchar(255),"
            + "birthday DATE,"
            + "hobby varchar(127),"
            + "phoneNum varchar(16),"
            + "email varchar(32),"
            + "qq varchar(16),"
            + "weChat varchar(32),"
            + "marryed boolean,"
            + "hasChildren boolean,"
            + "constraint employee_deparmentId_fk foreign key(departmentId) references department(id)"
            + ")";
    
    //建表语句，建员工变动表
    public static final String CREATE_TABLE_EMPLOYEE_ALTERATION_SQL="create table employeeAlteration("
            + "versionNum int primary key auto_increment,"
            + "employeeId int,"
            + "alterType int,"
            + "constraint alter_employeeId_fk foreign key(employeeId) references employee(id)"
            + ")";
    
    //建表语句，建立考勤模板表
    public static final String CREATE_TABLE_CLOCKING_TEMPLATE_SQL="create table clockingTemplate("
            + "id int primary key auto_increment,"
            + "workTime TIME not null,"
            + "noonBreakTime TIME not null,"
            + "afternoonWorkTime TIME not null,"
            + "offWorkTime TIME not null,"
            + "defaultClocking boolean"
            + ")";
    
    //建表语句，建立考勤时间安排表
    public static final String CREATE_TABLE_EMPLOYEE_CLOCKING_ARRANGE_SQL="create table employeeClockingArrange("
            + "id int primary key auto_increment,"
            + "employeeId int,"
            + "clockingId int,"
            + "clockingDate Date,"
            + "circleType int,"
            + "constraint arrange_employeedId_fk foreign key(employeeId) references employee(id)"
            + ")";
    
    //建表语句，建立考勤结果表
    public static final String CREATE_TABLE_ATTENDANCE_RECORD_SQL="create table attendanceRecord("
            + "id int primary key auto_increment,"
            + "employeeId int,"
            + "employeeName varchar(32),"
            + "clockingDate Date not null,"
            + "workType int,"
            + "offWorkType int,"
            + "noonBreakType int,"
            + "afternoonType int,"
            + "shouldWorkTime TIME,"
            + "workTime TIME,"
            + "shouldNoonBreakTime TIME,"
            + "noonBreakTime TIME,"
            + "shouldAfternoonWorkTime TIME,"
            + "afternoonWorkTime TIME,"
            + "shouldOffWorkTime TIME,"
            + "offWorkTime TIME,"
            + "constraint record_employeeId_fk foreign key(employeeId) references employee(id),"
            + "INDEX(clockingDate)"
            + ")";
}
