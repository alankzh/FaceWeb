package com.alankzh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.domain.Department;

/**
 * 部门dao实现
 * 
 * @author alankzh
 *
 */
@Immutable
public class DepartmentDaoImplement implements DepartmentDao {

    @Override
    public void insert(Department department) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "insert into department(name) values(?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }
    }

    @Override
    public void update(Department department) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "update department set name=? where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }

    }

    @Override
    public void delete(Department department) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "delete from department where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, department.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(preparedStatement, connection);
        }
    }

    @Override
    public Department queryById(int id) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from department where id=?";
        PreparedStatement preparedStatement = null;
        Department department = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return department;
    }

    @Override
    public Department queryByName(String name) {
        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from department where name=?";
        PreparedStatement preparedStatement = null;
        Department department = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return department;
    }

    @Override
    public List<Department> queryAll() {
        Connection connection = ConnectionPool.getConnection();
        String sql = "select * from department";
        PreparedStatement preparedStatement = null;
        ArrayList<Department> list = new ArrayList<Department>();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                list.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

}
