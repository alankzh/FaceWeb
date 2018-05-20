package com.alankzh.dao;

import com.alankzh.annotation.Immutable;

/**
 * 返回各类领域对象的DAO实现
 * 所有的DAO类应该把异常抛出，我没有这么做，是为了赶日子
 * 日后切记要把DAO的异常抛出，而不是内部处理错误
 * TODO
 * @author alankzh
 *
 */
@Immutable
public class DaoFactory {
    /**
     * 获取员工DAO实现类
     * @return
     */
    public static EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImplement();
    }
    
    /**
     * 获取部门DAO实现类
     * @return
     */
    public static DepartmentDao getDepartmentDao() {
        return new DepartmentDaoImplement();
    }
    
    /**
     * 获取排班模板DAO实现类
     * @return
     */
    public static ClockingTemplateDao getClockingTemplateDao(){
        return new ClockingTemplateImplement();
    }
    
    /**
     * 获取排班表DAO实现类
     * @return
     */
    public static EmployeeClockingArrangeDao getEmployeeClockingArrangeDao() {
        return new EmployClockingArrangeDaoImplement();
    }
    
    /**
     * 排班结果DAO实现类
     * @return
     */
    public static AttendanceRecordDao getAttendanceRecordDao() {
        return new AttendanceRecordDaoImplement();
    }
}
