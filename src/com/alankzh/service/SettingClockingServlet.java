package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.ClockingTemplateImplement;
import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.ClockingTemplate;
import com.alankzh.domain.ClockingTemplateCache;
import com.alankzh.util.JsonUtil;
import com.alibaba.fastjson.JSON;

/**
 * 设置排班服务
 * @author alankzh
 *
 */
@WebServlet(name="settingClocking",urlPatterns="/settingClocking")
@Immutable
public class SettingClockingServlet extends HttpServlet{
    private ClockingTemplateImplement clockingDao;

    @Override
    public void init() throws ServletException {
        clockingDao=(ClockingTemplateImplement) DaoFactory.getClockingTemplateDao();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter outer=response.getWriter();

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");
        LocalTime workTime=null;
        LocalTime noonBreakTime=null;
        LocalTime afternoonWorkTime=null;
        LocalTime offWorkTime=null;
        try {
            workTime=LocalTime.parse(request.getParameter("workTime"), formatter);
            noonBreakTime=LocalTime.parse(request.getParameter("noonBreakTime"),formatter);
            afternoonWorkTime=LocalTime.parse(request.getParameter("afternoonWorkTime"),formatter);
            offWorkTime=LocalTime.parse(request.getParameter("offWorkTime"),formatter);
        }catch (NullPointerException e) {
            outer.print(JsonUtil.commonResultCodeJson(1,"时间未填写"));
            return;
        }catch (DateTimeParseException e) {
            outer.print(JsonUtil.commonResultCodeJson(1, "日期格式错误"));
            return;
        }catch (RuntimeException e) {
            outer.print(JsonUtil.commonResultCodeJson(1, "日期格式错误"));
            return;
        }

        if(workTime.compareTo(noonBreakTime)>=0) {
            outer.print(JsonUtil.commonResultCodeJson(1,"午休时间比上班时间早,请重新调整时间安排"));
            return;
        }else if(noonBreakTime.compareTo(afternoonWorkTime)>=0) {
            outer.print(JsonUtil.commonResultCodeJson(1, "下午上班时间比午休早,请重新调整时间安排"));
            return;
        }else if(afternoonWorkTime.compareTo(offWorkTime)>=0) {
            outer.print(JsonUtil.commonResultCodeJson(1,"下班时间比下午上班时间早，请重新调整时间安排"));
            return;
        }
        
        ClockingTemplate defaultClock=ClockingTemplateCache.getInstance().getDefaultClocking();
        defaultClock.setWorkTime(workTime);
        defaultClock.setNoonBreakTime(noonBreakTime);
        defaultClock.setAfternoonWorkTime(afternoonWorkTime);
        defaultClock.setOffWorkTime(offWorkTime);
        try {
            ClockingTemplateCache.getInstance().updateDefaultClocking(defaultClock);
        } catch (SQLException e) {
            outer.print(JsonUtil.commonResultCodeJson(1,"数据库出现错误,稍后重试"));
            return;
        }
        outer.print(JsonUtil.commonResultCodeJson(0));
    }
}
