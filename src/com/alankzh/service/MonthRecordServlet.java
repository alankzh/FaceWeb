package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.VoidInputFilter;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.DaoFactory;
import com.alankzh.dao.EmployeeDaoImplement;
import com.alankzh.domain.AttendanceRecord;
import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeMonthRecord;
import com.alankzh.domain.OffWorkTypeEnum;
import com.alankzh.domain.WorkTypeEnum;
import com.alankzh.util.JsonUtil;

@WebServlet(name="monthRecord",urlPatterns="/monthRecord")
@Immutable
public class MonthRecordServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter outer=response.getWriter();

        String yearAndMonth=request.getParameter("yearAndMonthAndDay");
        if(yearAndMonth==null||yearAndMonth.isEmpty()) {
            outer.print(JsonUtil.commonResultCodeJson(1));
            return;            
        }
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showAttendanceRecord.jsp");
        ArrayList<EmployeeMonthRecord> monthRecordList = new ArrayList<EmployeeMonthRecord>();

        try {
            LocalDate localDate = LocalDate.parse(yearAndMonth, dateTimeFormatter);
            List<AttendanceRecord> recordList = DaoFactory.getAttendanceRecordDao().queryByThisMonth(localDate);

            if (recordList.size() == 0) {
                outer.println(localDate.getYear()+"年 "+localDate.getMonthValue()+"月还没有考勤数据");
                return;
            }

            // 根据employeeId升序排列recordList
            Collections.sort(recordList, new Comparator<AttendanceRecord>() {
                @Override
                public int compare(AttendanceRecord left, AttendanceRecord right) {
                    return Integer.compare(left.getEmployeeId(), right.getEmployeeId());
                }
            });

            Employee temp = new Employee();
            temp.setId(0);
            EmployeeMonthRecord employeeMonthRecord = null;
            EmployeeDaoImplement employeeDao = (EmployeeDaoImplement) DaoFactory.getEmployeeDao();
            for (int i = 0; i < recordList.size(); i++) {
                AttendanceRecord oneRecord = recordList.get(i);

                if (oneRecord.getEmployeeId() == temp.getId()) {
                    //同一个员工的考勤数据
                } else {
                    /* 先将上一个员工的记录加入列表 */
                    if (employeeMonthRecord != null) {
                        monthRecordList.add(employeeMonthRecord);
                    }
                    /* 开始下一个员工的记录 */
                    temp = employeeDao.queryById(oneRecord.getEmployeeId());
                    if (temp == null) {
                        // 这个员工已经被删除了
                        temp = new Employee();
                        temp.setId(oneRecord.getEmployeeId());
                        temp.setName(oneRecord.getEmployeeName());
                    }
                    employeeMonthRecord=new EmployeeMonthRecord();//新记录
                    employeeMonthRecord.setEmployee(temp);
                }

                WorkTypeEnum workTypeEnum = oneRecord.getWorkType();
                OffWorkTypeEnum noonBreakTypeEnum=oneRecord.getNoonBreakType();
                WorkTypeEnum afternoonWorkTypeEnum=oneRecord.getAfternoonWorkType();
                OffWorkTypeEnum offWorkTypeEnum = oneRecord.getOffWorkType();

                if(workTypeEnum==WorkTypeEnum.normal
                        &&noonBreakTypeEnum==OffWorkTypeEnum.normal
                        &&afternoonWorkTypeEnum==WorkTypeEnum.normal
                        &&offWorkTypeEnum==OffWorkTypeEnum.normal){
                    /*这天是正常打卡*/
                    employeeMonthRecord.setNormalWorkDayNum(employeeMonthRecord.getNormalWorkDayNum()+1);
                }else {
                    /*这是非正常打卡*/
                    employeeMonthRecord.setAbnormalWorkDayNum(employeeMonthRecord.getAbnormalWorkDayNum()+1);
                    if(workTypeEnum!=WorkTypeEnum.normal) {
                        setAbnormalWorkType(workTypeEnum, employeeMonthRecord);
                    }
                    if(noonBreakTypeEnum!=OffWorkTypeEnum.normal) {
                        setAbnormalNoonBreakType(noonBreakTypeEnum, employeeMonthRecord);
                    }
                    if(afternoonWorkTypeEnum!=WorkTypeEnum.normal) {
                        setAbnormalAfterNoonWorkType(afternoonWorkTypeEnum, employeeMonthRecord);
                    }
                    if(offWorkTypeEnum!=OffWorkTypeEnum.normal) {
                        setAbnormalOffWorkType(offWorkTypeEnum, employeeMonthRecord);
                    }
                }
            }

            //最后一个员工记录加入列表
            if(employeeMonthRecord!=null) {
                monthRecordList.add(employeeMonthRecord);
            }

            request.setAttribute("recordOfMonth",monthRecordList);
            request.setAttribute("year", localDate.getYear());
            request.setAttribute("month", localDate.getMonthValue());
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(JsonUtil.commonResultCodeJson(1));
        }
    }

    //上班非正常打卡
    private void setAbnormalWorkType(WorkTypeEnum workTypeEnum,EmployeeMonthRecord employeeMonthRecord) {
        switch (workTypeEnum) {
        case unClocking:
            employeeMonthRecord.setWorkUnClockingNum(employeeMonthRecord.getWorkUnClockingNum()+1);
            break;
        case beLate:
            employeeMonthRecord.setWorkDelayNum(employeeMonthRecord.getWorkDelayNum()+1);
            break;
        case absent:
            employeeMonthRecord.setWorkAbsentNum(employeeMonthRecord.getWorkAbsentNum()+1);
            break;
        default:
            break;
        }
    }

    //午休非正常打卡
    private void setAbnormalNoonBreakType(OffWorkTypeEnum noonBreakTypeEnum,EmployeeMonthRecord employeeMonthRecord) {
        switch (noonBreakTypeEnum) {
        case unClocking:
            employeeMonthRecord.setNoonBreakUnclockingNum(employeeMonthRecord.getNoonBreakUnclockingNum()+1);
            break;
        case leaveEarly:
            employeeMonthRecord.setNoonBreakEarlyNum(employeeMonthRecord.getNoonBreakEarlyNum()+1);
            break;
        case absent:
            employeeMonthRecord.setNoonBreakAbsentNum(employeeMonthRecord.getNoonBreakAbsentNum()+1);
            break;
        default:
            break;
        }
    }

    //下午上班非正常打卡
    private void setAbnormalAfterNoonWorkType(WorkTypeEnum afternoonWorkTypeEnum,EmployeeMonthRecord employeeMonthRecord) {
        switch (afternoonWorkTypeEnum) {
        case unClocking:
            employeeMonthRecord.setAfternoonWorkUnclockingNum(employeeMonthRecord.getAfternoonWorkUnclockingNum()+1);
            break;
        case beLate:
            employeeMonthRecord.setAfternoonWorkDelayNum(employeeMonthRecord.getAfternoonWorkDelayNum()+1);
            break;
        case absent:
            employeeMonthRecord.setAfternoonWorkAbsentNum(employeeMonthRecord.getAfternoonWorkAbsentNum()+1);
            break;
        default:
            break;
        }
    }
    
    //下班非正常打卡
    private void setAbnormalOffWorkType(OffWorkTypeEnum offWorkTypeEnum,EmployeeMonthRecord employeeMonthRecord) {
        switch (offWorkTypeEnum) {
        case unClocking:
            employeeMonthRecord.setOffWorkUnclockNum(employeeMonthRecord.getOffWorkUnclockNum()+1);
            break;
        case leaveEarly:
            employeeMonthRecord.setOffWorkEarlyNum(employeeMonthRecord.getOffWorkEarlyNum()+1);
            break;
        case absent:
            employeeMonthRecord.setOffWorkAbsentNum(employeeMonthRecord.getOffWorkAbsentNum()+1);
            break;
        default:
            break;
        }
    }
}
