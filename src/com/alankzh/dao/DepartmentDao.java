package com.alankzh.dao;

import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.Department;

/**
 * 部门DAO接口
 * @author alankzh
 *
 */
@Immutable
public interface DepartmentDao {
    public void insert(Department department);
    public void update(Department department);
    public void delete(Department department);
    
    public Department queryById(int id);
    public Department queryByName(String name);
    public List<Department> queryAll();
}
