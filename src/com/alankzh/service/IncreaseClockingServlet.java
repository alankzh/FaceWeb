package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.ClockingTemplate;
import com.alankzh.util.JsonUtil;

/**
 * 新增排班
 * @author alankzh
 *
 */
@WebServlet(name="increaseClocking",urlPatterns="/increaseClocking")
public class IncreaseClockingServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm");
        
        String workTime=request.getParameter("workTime");
        String offWorkTime=request.getParameter("offWorkTime");
        
        PrintWriter outer=response.getWriter();
        
        try {
            ClockingTemplate clockingTemplate=new ClockingTemplate();
            clockingTemplate.setWorkTime(LocalTime.parse(workTime, dateTimeFormatter));
            clockingTemplate.setOffWorkTime(LocalTime.parse(offWorkTime, dateTimeFormatter));
            DaoFactory.getClockingTemplateDao().insert(clockingTemplate);
            outer.print(JsonUtil.commonResultCodeJson(0));
        } catch (Exception e) {
            e.printStackTrace();
            outer.print(JsonUtil.commonResultCodeJson(1));
        }
    }
}
