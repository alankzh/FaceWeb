package com.alankzh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.ClockingTemplate;
import com.alankzh.domain.Department;

@Immutable
public class ClockingTemplateImplement implements ClockingTemplateDao{

//    + "noonBreakTime TIME not null,"
//    + "afternoonWorkTime TIME not null,"
            
    @Override
    public void insert(ClockingTemplate clockingTemplate)throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="insert into clockingTemplate(workTime,noonBreakTime,afternoonWorkTime,offWorkTime,defaultClocking) values(?,?,?,?,?)";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setTime(1,Time.valueOf(clockingTemplate.getWorkTime()));
            preparedStatement.setTime(2, Time.valueOf(clockingTemplate.getNoonBreakTime()));
            preparedStatement.setTime(3, Time.valueOf(clockingTemplate.getAfternoonWorkTime()));
            preparedStatement.setTime(4,Time.valueOf(clockingTemplate.getOffWorkTime()));
            preparedStatement.setBoolean(5, clockingTemplate.isDefaultClocking());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
    }

    @Override
    public void update(ClockingTemplate clockingTemplate) throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="update clockingTemplate set workTime=?,noonBreakTime=?,afternoonWorkTime=?,offWorkTime=?,defaultClocking=? where id=?";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setTime(1, Time.valueOf(clockingTemplate.getWorkTime()));
            preparedStatement.setTime(2, Time.valueOf(clockingTemplate.getNoonBreakTime()));
            preparedStatement.setTime(3, Time.valueOf(clockingTemplate.getAfternoonWorkTime()));
            preparedStatement.setTime(4, Time.valueOf(clockingTemplate.getOffWorkTime()));
            preparedStatement.setBoolean(5, clockingTemplate.isDefaultClocking());
            preparedStatement.setInt(6, clockingTemplate.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
    }

    @Override
    public void delete(ClockingTemplate clockingTemplate)throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="delete from clockingTemplate where id=?";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,clockingTemplate.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
        
    }

    @Override
    public ClockingTemplate queryById(int id)throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from clockingTemplate where id=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        ClockingTemplate clockingTemplate=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                clockingTemplate=new ClockingTemplate();
                clockingTemplate.setId(resultSet.getInt("id"));
                clockingTemplate.setWorkTime(resultSet.getTime("workTime").toLocalTime());
                clockingTemplate.setNoonBreakTime(resultSet.getTime("noonBreakTime").toLocalTime());
                clockingTemplate.setAfternoonWorkTime(resultSet.getTime("afternoonWorkTime").toLocalTime());
                clockingTemplate.setOffWorkTime(resultSet.getTime("offWorkTime").toLocalTime());
                clockingTemplate.setDefaultClocking(resultSet.getBoolean("defaultClocking"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return clockingTemplate;
    }

    @Override
    public List<ClockingTemplate> queryAll()throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from clockingTemplate";
        PreparedStatement preparedStatement=null;
        ArrayList<ClockingTemplate> list=new ArrayList<ClockingTemplate>();
        ResultSet resultSet=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                ClockingTemplate clockingTemplate=new ClockingTemplate();
                clockingTemplate.setId(resultSet.getInt("id"));
                clockingTemplate.setWorkTime(resultSet.getTime("workTime").toLocalTime());
                clockingTemplate.setNoonBreakTime(resultSet.getTime("noonBreakTime").toLocalTime());
                clockingTemplate.setAfternoonWorkTime(resultSet.getTime("afternoonWorkTime").toLocalTime());
                clockingTemplate.setOffWorkTime(resultSet.getTime("offWorkTime").toLocalTime());
                clockingTemplate.setDefaultClocking(resultSet.getBoolean("defaultClocking"));
                list.add(clockingTemplate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

    @Override
    public ClockingTemplate queryDefaultClocking()throws SQLException{
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from clockingTemplate where defaultClocking=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        ClockingTemplate clockingTemplate=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                clockingTemplate=new ClockingTemplate();
                clockingTemplate.setId(resultSet.getInt("id"));
                clockingTemplate.setWorkTime(resultSet.getTime("workTime").toLocalTime());
                clockingTemplate.setNoonBreakTime(resultSet.getTime("noonBreakTime").toLocalTime());
                clockingTemplate.setAfternoonWorkTime(resultSet.getTime("afternoonWorkTime").toLocalTime());
                clockingTemplate.setOffWorkTime(resultSet.getTime("offWorkTime").toLocalTime());
                clockingTemplate.setDefaultClocking(resultSet.getBoolean("defaultClocking"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return clockingTemplate;
    }
    
}
