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

/**
 * 数据库查询实现
 * @author alankzh
 *  
 */
@Immutable
public class EmployeeDaoImplement implements EmployeeDao{

    @Override
    public void insert(Employee employee)throws Exception{
        Connection connection=ConnectionPool.getConnection();
        String sql="insert into employee(name,departmentId,departmentName,birthday,phoneNum,qq,weChat,photoUrl) values"
                + "(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getDepartmentId());
            preparedStatement.setString(3, employee.getDepartmentName());

            LocalDate birthday=employee.getBirthday();
            if(birthday!=null) {
                preparedStatement.setDate(4, Date.valueOf(birthday));    
            }else {
                preparedStatement.setNull(4, Types.DATE);
            }
            
            preparedStatement.setString(5, employee.getPhoneNum());
            preparedStatement.setString(6, employee.getQq());
            preparedStatement.setString(7, employee.getWeChat());
            preparedStatement.setString(8, employee.getPhotoUrl());

            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Employee插入错误");
            throw e;
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }        

    }

    @Override
    public void update(Employee employee){
        Connection connection=ConnectionPool.getConnection();
        String sql="update employee set name=?,departmentId=?,birthday=?,phoneNum=?,qq=?,weChat=?,photoUrl=? where id=?";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getDepartmentId());
            preparedStatement.setString(3, employee.getDepartmentName());

            LocalDate birthday=employee.getBirthday();
            if(birthday!=null) {
                preparedStatement.setDate(4, Date.valueOf(birthday));    
            }else {
                preparedStatement.setNull(4, Types.DATE);
            }
            
            preparedStatement.setString(5, employee.getPhoneNum());
            preparedStatement.setString(6, employee.getQq());
            preparedStatement.setString(7, employee.getWeChat());
            preparedStatement.setString(8, employee.getPhotoUrl());

            preparedStatement.setInt(8, employee.getId());//where语句中条件
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
    }

    @Override
    public void delete(Employee employee){
        Connection connection=ConnectionPool.getConnection();
        String sql="delete from employee where id=?";
        PreparedStatement preparedStatement=null;
        
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(preparedStatement,connection);
        }
        
    }

    @Override
    public Employee queryById(int employeeId){
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from employee where id=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Employee employee=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartmentId(resultSet.getInt("departmentId"));
                employee.setDepartmentName(resultSet.getString("departmentName"));
                
                Date birthday=resultSet.getDate("birthday");
                if(birthday!=null) {
                    employee.setBirthday(birthday.toLocalDate());                    
                }
                
                employee.setPhoneNum(resultSet.getString("phoneNum"));
                employee.setQq(resultSet.getString("qq"));
                employee.setWeChat(resultSet.getString("weChat"));
                employee.setPhotoUrl(resultSet.getString("photoUrl"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return employee;
    }

    @Override
    public Employee queryByNoId(Employee employee){
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from employee where name=? and birthday=? and phoneNum=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Employee returnEmployee=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            
            LocalDate birthday=employee.getBirthday();
            if(birthday!=null) {
                preparedStatement.setDate(2, Date.valueOf(birthday));    
            }else {
                preparedStatement.setNull(2, Types.DATE);
            }
            
            String phoneNum=employee.getPhoneNum();
            if(phoneNum!=null) {
                preparedStatement.setString(3, employee.getPhoneNum());                
            }else {
                preparedStatement.setNull(3, Types.VARCHAR);
            }
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                returnEmployee=new Employee();
                returnEmployee.setId(resultSet.getInt("id"));
                returnEmployee.setName(resultSet.getString("name"));
                returnEmployee.setDepartmentId(resultSet.getInt("departmentId"));
                returnEmployee.setDepartmentName(resultSet.getString("departmentName"));
                
                Date birthdayInDB=resultSet.getDate("birthday");
                if(birthdayInDB!=null) {
                    returnEmployee.setBirthday(birthdayInDB.toLocalDate());                    
                }
                
                returnEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                returnEmployee.setQq(resultSet.getString("qq"));
                returnEmployee.setWeChat(resultSet.getString("weChat"));
                returnEmployee.setPhotoUrl(resultSet.getString("photoUrl"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return returnEmployee;
    }

    @Override
    public List<Employee> queryAll(){
        ArrayList<Employee> list=new ArrayList<Employee>();
        
        Connection connection=ConnectionPool.getConnection();
        String sql="select * from employee";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                Employee employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartmentId(resultSet.getInt("departmentId"));
                employee.setDepartmentName(resultSet.getString("departmentName"));
                
                Date birthday=resultSet.getDate("birthday");
                if(birthday!=null) {
                    employee.setBirthday(birthday.toLocalDate());                    
                }
                
                employee.setPhoneNum(resultSet.getString("phoneNum"));
                employee.setQq(resultSet.getString("qq"));
                employee.setWeChat(resultSet.getString("weChat"));
                employee.setPhotoUrl(resultSet.getString("photoUrl"));
                
                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.close(resultSet,preparedStatement,connection);
        }
        
        return list;
    }

}
