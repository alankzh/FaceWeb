package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.Employee;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name="employeeUpdate",urlPatterns="/employeeUpdate")
@Immutable
public class EmployeeUpdateServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter outer=response.getWriter();
        
        /*TODO 暂时先返回全部的员工信息*/
        List<Employee> list=DaoFactory.getEmployeeDao().queryAll();//全部员工
        
        String jsonStr=JSONObject.toJSONString(list);
        
        String resultCodeZero="{\"resultCode\":0,\"Data\":"+jsonStr+"}";
        outer.println(resultCodeZero);
    }
}
