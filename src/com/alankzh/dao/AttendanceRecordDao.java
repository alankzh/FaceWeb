package com.alankzh.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.alankzh.domain.AttendanceRecord;

/**
 * 考勤结果DAO接口  
 * @author alankzh
 *
 */
public interface AttendanceRecordDao {
    public void insert(AttendanceRecord attendanceRecord)throws SQLException;
    public void update(AttendanceRecord attendanceRecord)throws SQLException;
    public void delete(AttendanceRecord attendanceRecord)throws SQLException;
    
    public AttendanceRecord queryById(int id)throws SQLException;
    public List<AttendanceRecord> queryByEmployeeId(int employeeId)throws SQLException;
    public List<AttendanceRecord> queryByDate(LocalDate date)throws SQLException;
    public AttendanceRecord queryByEmployeeIdAndDate(int employeeId,LocalDate date)throws SQLException;
    public List<AttendanceRecord> queryByThisMonth(LocalDate date)throws SQLException;
    public List<AttendanceRecord> queryByThisMonthOfEmployee(int employeeId,LocalDate localDate)throws SQLException;
}
