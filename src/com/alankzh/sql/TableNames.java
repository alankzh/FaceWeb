package com.alankzh.sql;

import com.alankzh.annotation.Immutable;

/**
 * 数据库FaceClockingIn中全部表 表名
 * @author alankzh
 *
 */
@Immutable
public class TableNames {
    public static final String DEPARTMENT_TABLE_NAME="department";//部门表 表名
    public static final String DEPARTMENT_RELATION_TABLE_NAME="departmentRelation";//部门关系表 表名
    public static final String EMPLOYEE_TABLE_NAME="employee";//员工表 表名
    public static final String EMPLOYEE_ALTERATION_TABLE_NAME="employeeAlteration";
    public static final String EMPLOYEE_CLOCKING_ARRANGE_TABLE_NAME="employeeClockingArrange";//员工排班表 表名
    public static final String CLOCKING_TEMPLATE_TABLE_NAME="clockingTemplate";//排班模板表 表名
    public static final String ATTENDANCE_RECORD_TABLE_NAME="attendanceRecord";//考勤表 表名
}
