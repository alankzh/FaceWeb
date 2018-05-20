package com.alankzh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeClockingArrange;
import com.alankzh.domain.EmployeeClockingArrange.CircleTypeEnum;

@Immutable
public class EmployClockingArrangeDaoImplement implements EmployeeClockingArrangeDao {

    @Override
    public void insertWorkTimeArrange(List<Employee> list,int clockingId) {
        Connection connection = ConnectionPool.getConnection();
        
        String sql = "insert into employeeClockingArrange(employeeId,clockingId,circleType) values("
                + "?,?,?);";
        PreparedStatement preparedStatement = null;
        
        try {
            connection.setAutoCommit(false);//禁止自动提交，开始事务
            
            for(Employee employee:list) {
                
                if(preparedStatement!=null) {
                    ConnectionPool.close(preparedStatement);
                    preparedStatement=null;
                }
                
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setInt(2, clockingId);
                preparedStatement.setInt(3, CircleTypeEnum.workArrange.getType());

                preparedStatement.execute();    
            }
            
            connection.commit();//提交
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }

    }

    @Override
    public void insert(EmployeeClockingArrange clockingArrange) {
        Connection connection = ConnectionPool.getConnection();

        String sql = "insert into employeeClockingArrange(employeeId,clockingId,clockingDate,circleType) values("
                + "?,?,?,?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, clockingArrange.getEmployeeId());
            preparedStatement.setInt(2, clockingArrange.getClockingId());
            LocalDate clockDate = clockingArrange.getClockingDate();
            if (clockDate != null) {
                preparedStatement.setDate(3, Date.valueOf(clockDate));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            preparedStatement.setInt(4, clockingArrange.getCircleType().getType());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }
    }

    @Override
    public void update(EmployeeClockingArrange clockingArrange) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "update employeeClockingArrange set employId=?,clockingId=?,clockingDate=?,circleType=? where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, clockingArrange.getEmployeeId());
            preparedStatement.setInt(2, clockingArrange.getClockingId());
            LocalDate clockDate = clockingArrange.getClockingDate();
            if (clockDate != null) {
                preparedStatement.setDate(3, Date.valueOf(clockDate));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            preparedStatement.setInt(4, clockingArrange.getCircleType().getType());
            preparedStatement.setInt(5, clockingArrange.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }

    }

    @Override
    public void delete(EmployeeClockingArrange clockingArrange) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "delete from employeeClockingArrange where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, clockingArrange.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }

    }

    @Override
    public EmployeeClockingArrange queryById(int id) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from employeeClockingArrange where id=?";
        PreparedStatement preparedStatement = null;
        EmployeeClockingArrange employeeClockingArrange = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeClockingArrange=new EmployeeClockingArrange();
                employeeClockingArrange.setId(resultSet.getInt("id"));
                employeeClockingArrange.setEmployeeId(resultSet.getInt("employeeId"));
                employeeClockingArrange.setClockingId(resultSet.getInt("clockingId"));
                
                Date clockDate=resultSet.getDate("clockingDate");
                if(clockDate!=null) {
                    employeeClockingArrange.setClockingDate(clockDate.toLocalDate());                    
                }

                CircleTypeEnum circleTypeEnum = CircleTypeEnum.holidayArrange;
                circleTypeEnum.setType(resultSet.getInt("circleType"));

                employeeClockingArrange.setCircleType(circleTypeEnum);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return employeeClockingArrange;
    }

    @Override
    public List<EmployeeClockingArrange> queryByEmployeeId(int employeeId) {
        ArrayList<EmployeeClockingArrange> list = new ArrayList<EmployeeClockingArrange>();

        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from employeeClockingArrange where employeeId=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeClockingArrange employeeClockingArrange = new EmployeeClockingArrange();
                employeeClockingArrange.setId(resultSet.getInt("id"));
                employeeClockingArrange.setEmployeeId(resultSet.getInt("employeeId"));
                employeeClockingArrange.setClockingId(resultSet.getInt("clockingId"));
                
                Date clockDate=resultSet.getDate("clockingDate");
                if(clockDate!=null) {
                    employeeClockingArrange.setClockingDate(clockDate.toLocalDate());                    
                }

                CircleTypeEnum circleTypeEnum = CircleTypeEnum.holidayArrange;
                circleTypeEnum.setType(resultSet.getInt("circleType"));
                employeeClockingArrange.setCircleType(circleTypeEnum);

                list.add(employeeClockingArrange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

    @Override
    public List<EmployeeClockingArrange> queryAllWorkArrange() {
        ArrayList<EmployeeClockingArrange> list = new ArrayList<EmployeeClockingArrange>();

        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from employeeClockingArrange where circleType=1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeClockingArrange employeeClockingArrange = new EmployeeClockingArrange();
                employeeClockingArrange.setId(resultSet.getInt("id"));
                employeeClockingArrange.setEmployeeId(resultSet.getInt("employeeId"));
                employeeClockingArrange.setClockingId(resultSet.getInt("clockingId"));
                
                Date clockDate=resultSet.getDate("clockingDate");
                if(clockDate!=null) {
                    employeeClockingArrange.setClockingDate(clockDate.toLocalDate());                    
                }

                CircleTypeEnum circleTypeEnum = CircleTypeEnum.holidayArrange;
                circleTypeEnum.setType(resultSet.getInt("circleType"));
                employeeClockingArrange.setCircleType(circleTypeEnum);

                list.add(employeeClockingArrange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

}
