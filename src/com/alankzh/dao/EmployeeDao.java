package com.alankzh.dao;

import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.Employee;

/**
 * 员工DAO接口
 * @author alankzh
 *
 */
@Immutable
public interface EmployeeDao {
    public void insert(Employee employee)throws Exception;
    public void update(Employee employee);
    public void delete(Employee employee);
    
    public Employee queryById(int employeeId);
    public Employee queryByNoId(Employee employee);
    public List<Employee> queryAll(); 
}
