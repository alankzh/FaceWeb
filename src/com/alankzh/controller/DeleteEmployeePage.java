package com.alankzh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.Employee;


@WebServlet(name="deleteEmployeePage",urlPatterns="/deleteEmployeePage")
public class DeleteEmployeePage extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/deleteEmployee.jsp");
        List<Employee> employeeList=DaoFactory.getEmployeeDao().queryAll();
        request.setAttribute("employeeList", employeeList);
        dispatcher.forward(request, response);
    }
}
