package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.AttendanceRecord;
import com.alankzh.util.JsonUtil;


@WebServlet(name = "employeeMonthRecord", urlPatterns = "/employeeMonthRecord")
public class EmployeeMonthRecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter outer = response.getWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showEmployeeMonthRecord.jsp");
        try {
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            
            String employeeName=URLDecoder.decode(request.getParameter("employeeName"),"UTF-8");
            LocalDate localDate=LocalDate.of(Integer.parseInt(request.getParameter("year")),Integer.parseInt(request.getParameter("month")),1);
            if(employeeId<=0) {
                outer.print(JsonUtil.commonResultCodeJson(1));
                return;
            }
                        
            List<AttendanceRecord> list=DaoFactory.getAttendanceRecordDao().queryByThisMonthOfEmployee(employeeId, localDate);
            /*根据日期，从低到高升序排列*/
            java.util.Collections.sort(list, new Comparator<AttendanceRecord>() {
                @Override
                public int compare(AttendanceRecord left, AttendanceRecord right) {
                    left.getClockingDate().compareTo(right.getClockingDate());
                    return 0;
                }
            });
            
            request.setAttribute("employeeName", employeeName);
            request.setAttribute("year", Integer.parseInt(request.getParameter("year")));
            request.setAttribute("month", Integer.parseInt(request.getParameter("month")));
            request.setAttribute("recordList", list);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            this.getServletContext().log("EmployeeMonthRecordServlet", e);
            Logger logger = Logger.getRootLogger();
            // 使用默认的配置信息，不需要写log4j.properties
            BasicConfigurator.configure();
            // 下面的消息将被输出
            logger.debug("EmployeeMonthRecordServlet", e);
            outer.print(JsonUtil.commonResultCodeJson(1));
        }
    }
}
