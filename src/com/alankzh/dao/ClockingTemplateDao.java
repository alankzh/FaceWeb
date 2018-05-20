package com.alankzh.dao;

import java.sql.SQLException;
import java.util.List;

import com.alankzh.domain.ClockingTemplate;
import com.alankzh.domain.Department;

/**
 * 排班模板DAO接口
 * @author alankzh
 *
 */
public interface ClockingTemplateDao {
    public void insert(ClockingTemplate clockingTemplate)throws SQLException;
    public void update(ClockingTemplate clockingTemplate)throws SQLException;
    public void delete(ClockingTemplate clockingTemplate)throws SQLException;
    
    public ClockingTemplate queryById(int id)throws SQLException;
    public List<ClockingTemplate> queryAll()throws SQLException;
    public ClockingTemplate queryDefaultClocking()throws SQLException;
}
