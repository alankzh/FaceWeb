package com.alankzh.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.constant.Constant;
import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.Employee;
import com.alankzh.util.JsonUtil;

@WebServlet(name = "fireEmployee", urlPatterns = "/fireEmployee")
public class FireEmployeeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter outer=response.getWriter();
        try {
            int employeeId = Integer.parseInt(request.getParameter("deleteChosen"));
            if (employeeId > 0) {
                Employee employee = DaoFactory.getEmployeeDao().queryById(employeeId);
                DaoFactory.getEmployeeDao().delete(employee);//删除员工
                
                /*同时删除图片*/
                String realPath = this.getServletContext().getRealPath(Constant.HEADER_IMAGE_SAVE_PATH);
                File saveFile=new File(realPath,employee.getPhotoUrl());
                if(saveFile.exists()) {
                    saveFile.delete();
                }
                outer.println(JsonUtil.commonResultCodeJson(0));
            }else {
                outer.println(JsonUtil.commonResultCodeJson(1));                
            }
        } catch (Exception e) {
            e.printStackTrace();
            outer.println(JsonUtil.commonResultCodeJson(1));
        }

    }
}
