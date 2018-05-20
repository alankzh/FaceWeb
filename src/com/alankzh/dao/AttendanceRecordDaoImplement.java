package com.alankzh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.AttendanceRecord;
import com.alankzh.domain.OffWorkTypeEnum;
import com.alankzh.domain.WorkTypeEnum;

import java.lang.Exception;

/**
 * 考勤结果表DAO实现类
 * @author alankzh
 *
 */
@Immutable
public class AttendanceRecordDaoImplement implements AttendanceRecordDao{

    
    @Override
    public void insert(AttendanceRecord attendanceRecord) throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="insert into attendanceRecord(employeeId,employeeName,clockingDate"
                + ",workType,offWorkType"
                + ",noonBreakType,afternoonType"
                + ",shouldWorkTime,workTime"
                + ",shouldNoonBreakTime,noonBreakTime"
                + ",shouldAfternoonWorkTime,afternoonWorkTime"
                + ",shouldOffWorkTime,offWorkTime) values"
                + "(?,?,?,?,?"
                + ",?,?,?,?,?"
                + ",?,?,?,?,?)";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, attendanceRecord.getEmployeeId());
            preparedStatement.setString(2, attendanceRecord.getEmployeeName());
            preparedStatement.setDate(3,Date.valueOf(attendanceRecord.getClockingDate()));
            preparedStatement.setInt(4, attendanceRecord.getWorkType().getType());
            preparedStatement.setInt(5, attendanceRecord.getOffWorkType().getType());
            preparedStatement.setInt(6, attendanceRecord.getNoonBreakType().getType());
            preparedStatement.setInt(7, attendanceRecord.getAfternoonWorkType().getType());
            
            LocalTime shouldWorkTime=attendanceRecord.getShouldWorkTime();
            LocalTime workTime=attendanceRecord.getWorkTime();
            
            LocalTime shouldNoonBreakTime=attendanceRecord.getShouldNoonBreakTime();
            LocalTime noonBreakTime=attendanceRecord.getNoonBreakTime();
            
            LocalTime shouldAfternoonWorkTime=attendanceRecord.getShouldAfternoonWorkTime();
            LocalTime afternoonWorkTime=attendanceRecord.getAfternoonWorkTime();
            
            LocalTime shouldOffworkTime=attendanceRecord.getShouldOffWorkTime();
            LocalTime offWorkTime=attendanceRecord.getOffWorkTime();
            
            if(shouldWorkTime!=null) {
                preparedStatement.setTime(8,Time.valueOf(shouldWorkTime));
            }else {
                preparedStatement.setNull(8, Types.TIME);
            }
            if(workTime!=null) {
                preparedStatement.setTime(9,Time.valueOf(workTime));
            }else {
                preparedStatement.setNull(9, Types.TIME);
            }
            
            if(shouldNoonBreakTime!=null) {
                preparedStatement.setTime(10,Time.valueOf(shouldNoonBreakTime));
            }else {
                preparedStatement.setNull(10, Types.TIME);
            }
            if(noonBreakTime!=null) {
                preparedStatement.setTime(11,Time.valueOf(noonBreakTime));
            }else {
                preparedStatement.setNull(11, Types.TIME);
            }
            
            if(shouldAfternoonWorkTime!=null) {
                preparedStatement.setTime(12,Time.valueOf(shouldAfternoonWorkTime));
            }else {
                preparedStatement.setNull(12, Types.TIME);
            }
            if(afternoonWorkTime!=null) {
                preparedStatement.setTime(13,Time.valueOf(afternoonWorkTime));
            }else {
                preparedStatement.setNull(13, Types.TIME);
            }
            
            if(shouldOffworkTime!=null) {
                preparedStatement.setTime(14,Time.valueOf(shouldOffworkTime));
            }else {
                preparedStatement.setNull(14, Types.TIME);
            }
            if(offWorkTime!=null) {
                preparedStatement.setTime(15, Time.valueOf(offWorkTime));
            }else {
                preparedStatement.setNull(15, Types.TIME);
            }
            
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
        
    }

    @Override
    public void update(AttendanceRecord attendanceRecord) {
        Connection connection=ConnectionPool.getConnection();
        String sql="update attendanceRecord set employeeId=?,employeeName=?,clockingDate=?"
                + ",workType=?,offWorkType=?"
                + ",noonBreakType,afternoonType"
                + ",shouldWorkTime=?,workTime=?"
                + ",shouldNoonBreakTime=?,noonBreakTime=?"
                + ",shouldAfternoonWorkTime=?,afternoonWorkTime=?"
                + ",shouldOffWorkTime=?,offWorkTime=? where id=?";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, attendanceRecord.getEmployeeId());
            preparedStatement.setString(2, attendanceRecord.getEmployeeName());
            preparedStatement.setDate(3,Date.valueOf(attendanceRecord.getClockingDate()));
            preparedStatement.setInt(4, attendanceRecord.getWorkType().getType());
            preparedStatement.setInt(5, attendanceRecord.getOffWorkType().getType());
            preparedStatement.setInt(6, attendanceRecord.getNoonBreakType().getType());
            preparedStatement.setInt(7, attendanceRecord.getAfternoonWorkType().getType());
            
            LocalTime shouldWorkTime=attendanceRecord.getShouldWorkTime();
            LocalTime workTime=attendanceRecord.getWorkTime();
            
            LocalTime shouldNoonBreakTime=attendanceRecord.getShouldNoonBreakTime();
            LocalTime noonBreakTime=attendanceRecord.getNoonBreakTime();
            
            LocalTime shouldAfternoonWorkTime=attendanceRecord.getShouldAfternoonWorkTime();
            LocalTime afternoonWorkTime=attendanceRecord.getAfternoonWorkTime();
            
            LocalTime shouldOffworkTime=attendanceRecord.getShouldOffWorkTime();
            LocalTime offWorkTime=attendanceRecord.getOffWorkTime();
            
            if(shouldWorkTime!=null) {
                preparedStatement.setTime(8,Time.valueOf(shouldWorkTime));
            }else {
                preparedStatement.setNull(8, Types.TIME);
            }
            if(workTime!=null) {
                preparedStatement.setTime(9,Time.valueOf(workTime));
            }else {
                preparedStatement.setNull(9, Types.TIME);
            }
            
            if(shouldNoonBreakTime!=null) {
                preparedStatement.setTime(10,Time.valueOf(shouldNoonBreakTime));
            }else {
                preparedStatement.setNull(10, Types.TIME);
            }
            if(noonBreakTime!=null) {
                preparedStatement.setTime(11,Time.valueOf(noonBreakTime));
            }else {
                preparedStatement.setNull(11, Types.TIME);
            }
            
            if(shouldAfternoonWorkTime!=null) {
                preparedStatement.setTime(12,Time.valueOf(shouldAfternoonWorkTime));
            }else {
                preparedStatement.setNull(12, Types.TIME);
            }
            if(afternoonWorkTime!=null) {
                preparedStatement.setTime(13,Time.valueOf(afternoonWorkTime));
            }else {
                preparedStatement.setNull(13, Types.TIME);
            }
            
            if(shouldOffworkTime!=null) {
                preparedStatement.setTime(14,Time.valueOf(shouldOffworkTime));
            }else {
                preparedStatement.setNull(14, Types.TIME);
            }
            if(offWorkTime!=null) {
                preparedStatement.setTime(15, Time.valueOf(offWorkTime));
            }else {
                preparedStatement.setNull(15, Types.TIME);
            }
            
            preparedStatement.setInt(16, attendanceRecord.getId());
            
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
        
    }

    @Override
    public void delete(AttendanceRecord attendanceRecord) {
        Connection connection=ConnectionPool.getConnection();
        String sql="delete from attendanceRecord where id=?";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
        
    }

    @Override
    public AttendanceRecord queryById(int id) {
        AttendanceRecord attendanceRecord=null;
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where id=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return attendanceRecord;
    }

    @Override
    public List<AttendanceRecord> queryByDate(LocalDate date) {
        List<AttendanceRecord> list=new ArrayList<AttendanceRecord>();
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where clockingDate=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(date));
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
                
                list.add(attendanceRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

    @Override
    public List<AttendanceRecord> queryByEmployeeId(int employeeId) {
        List<AttendanceRecord> list=new ArrayList<AttendanceRecord>();
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where employeeId=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
                
                list.add(attendanceRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

    @Override
    public AttendanceRecord queryByEmployeeIdAndDate(int employeeId, LocalDate date) {
        AttendanceRecord attendanceRecord=null;
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where employeeId=? and clockingDate=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDate(2, Date.valueOf(date));
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return attendanceRecord;
    }

    @Override
    public List<AttendanceRecord> queryByThisMonth(LocalDate date) throws SQLException{
        List<AttendanceRecord> list=new ArrayList<AttendanceRecord>();
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where clockingDate>=? and clockingDate<=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(date.with(TemporalAdjusters.firstDayOfMonth())));
            preparedStatement.setDate(2, Date.valueOf(date.with(TemporalAdjusters.lastDayOfMonth())));
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
                
                list.add(attendanceRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

    @Override
    public List<AttendanceRecord> queryByThisMonthOfEmployee(int employeeId,LocalDate localDate) throws SQLException {
        List<AttendanceRecord> list=new ArrayList<AttendanceRecord>();
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from attendanceRecord where clockingDate>=? and clockingDate<=? and employeeId=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(localDate.with(TemporalAdjusters.firstDayOfMonth())));
            preparedStatement.setDate(2, Date.valueOf(localDate.with(TemporalAdjusters.lastDayOfMonth())));
            preparedStatement.setInt(3, employeeId);
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setId(resultSet.getInt("id"));
                attendanceRecord.setEmployeeId(resultSet.getInt("employeeId"));
                attendanceRecord.setEmployeeName(resultSet.getString("employeeName"));
                attendanceRecord.setClockingDate(resultSet.getDate("clockingDate").toLocalDate());
                
                attendanceRecord.setWorkType(WorkTypeEnum.valueOf(resultSet.getInt("workType")));
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.valueOf(resultSet.getInt("offWorkType")));
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.valueOf(resultSet.getInt("noonBreakType")));
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.valueOf(resultSet.getInt("afternoonType")));
                
                Time shouldWorkTime=resultSet.getTime("shouldWorkTime");
                Time workTime=resultSet.getTime("workTime");
                if(shouldWorkTime!=null) {
                    attendanceRecord.setShouldWorkTime(shouldWorkTime.toLocalTime());                    
                }
                if(workTime!=null) {
                    attendanceRecord.setWorkTime(workTime.toLocalTime());                    
                }
                
                Time shouldNoonBreakTime=resultSet.getTime("shouldNoonBreakTime");
                Time noonBreakTime=resultSet.getTime("noonBreakTime");
                if(shouldNoonBreakTime!=null) {
                    attendanceRecord.setShouldNoonBreakTime(shouldNoonBreakTime.toLocalTime());                    
                }
                if(noonBreakTime!=null) {
                    attendanceRecord.setNoonBreakTime(noonBreakTime.toLocalTime());                    
                }
                
                Time shouldAfternoonWorkTime=resultSet.getTime("shouldAfternoonWorkTime");
                Time afternoonWorkTime=resultSet.getTime("afternoonWorkTime");
                if(shouldAfternoonWorkTime!=null) {
                    attendanceRecord.setShouldAfternoonWorkTime(shouldAfternoonWorkTime.toLocalTime());                    
                }
                if(afternoonWorkTime!=null) {
                    attendanceRecord.setAfternoonWorkTime(afternoonWorkTime.toLocalTime());                    
                }
                
                Time shouldOffWorkTime=resultSet.getTime("shouldOffWorkTime");
                Time offWorkTime=resultSet.getTime("offWorkTime");
                if(shouldOffWorkTime!=null) {
                    attendanceRecord.setShouldOffWorkTime(shouldOffWorkTime.toLocalTime());                    
                }
                if(offWorkTime!=null) {
                    attendanceRecord.setOffWorkTime(offWorkTime.toLocalTime());                    
                }
                
                list.add(attendanceRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

}
