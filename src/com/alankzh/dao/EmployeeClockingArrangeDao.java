package com.alankzh.dao;

import java.util.List;

import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeClockingArrange;

/**
 * 员工排班安排表DAO接口
 * @author alankzh
 *
 */
public interface EmployeeClockingArrangeDao {
    public void insertWorkTimeArrange(List<Employee> list,int clockingId);
    public void insert(EmployeeClockingArrange clockingArrange);
    public void update(EmployeeClockingArrange clockingArrange);
    public void delete(EmployeeClockingArrange clockingArrange);
    
    public EmployeeClockingArrange queryById(int id);
    
    public List<EmployeeClockingArrange> queryByEmployeeId(int employeeId);
    
    public List<EmployeeClockingArrange> queryAllWorkArrange();
}
