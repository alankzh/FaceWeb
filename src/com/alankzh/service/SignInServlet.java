package com.alankzh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.AttendanceRecord;
import com.alankzh.domain.ClockingTemplate;
import com.alankzh.domain.ClockingTemplateCache;
import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeClockingArrange;
import com.alankzh.domain.WorkTypeEnum;
import com.alankzh.domain.EmployeeClockingArrange.CircleTypeEnum;
import com.alankzh.domain.OffWorkTypeEnum;
import com.alankzh.util.HolidayUtil;
import com.alankzh.util.JsonUtil;
import com.alankzh.util.LocalTimeCompareUtil;

@WebServlet(name = "signIn", urlPatterns = "/signIn")
@Immutable
public class SignInServlet extends HttpServlet {
    private final int HALF_HOUR_TIME_SECONDS = 60 * 30;// 半小时的秒数

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        PrintWriter outer = response.getWriter();

        if(HolidayUtil.getCacheHoliday()) {
            outer.print(JsonUtil.commonResultCodeJson(1,"今天是假期"));
            return;
        }

        int employeeId = 0;
        Employee employee=null;
        try {
            employeeId = Integer.parseInt(request.getParameter("employeeId"));
            employee = DaoFactory.getEmployeeDao().queryById(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (employee == null || employee.getId() <= 0) {
                outer.println(JsonUtil.commonResultCodeJson(1,"没有此员工"));
                return;
            }            
        }

        /* 当前签到消息时间 */
        LocalDateTime signInDateTime = LocalDateTime.now();
        LocalTime signInTime = signInDateTime.toLocalTime();
        LocalDate signInDate = signInDateTime.toLocalDate();

        /* 应该上下班时间 */
        ClockingTemplate clockingTemplate=ClockingTemplateCache.getInstance().getDefaultClocking();
        LocalTime workTime = clockingTemplate.getWorkTime();
        LocalTime noonBreakTime=clockingTemplate.getNoonBreakTime();
        LocalTime afternoonWorkTime=clockingTemplate.getAfternoonWorkTime();
        LocalTime offWorkTime = clockingTemplate.getOffWorkTime();

        /* 查看签到的今天是否已经有排班记录,若没有则创建 */
        AttendanceRecord attendanceRecord=null;
        boolean hasRecord=false;
        try {
            attendanceRecord = DaoFactory.getAttendanceRecordDao().queryByEmployeeIdAndDate(employeeId,
                    signInDate);
        } catch (SQLException e) {
        }finally {
            if (attendanceRecord == null) {
                hasRecord=false;
                attendanceRecord = new AttendanceRecord();
                attendanceRecord.setClockingDate(signInDate);
                attendanceRecord.setEmployeeId(employeeId);
                attendanceRecord.setEmployeeName(employee.getName());
                attendanceRecord.setShouldWorkTime(workTime);
                attendanceRecord.setShouldOffWorkTime(offWorkTime);
                attendanceRecord.setShouldNoonBreakTime(noonBreakTime);
                attendanceRecord.setShouldAfternoonWorkTime(afternoonWorkTime);
            }else {
                hasRecord=true;
            }
        }

        LocalTime recordWorkTime = attendanceRecord.getWorkTime();
        LocalTime recordNoonBreakTime=attendanceRecord.getNoonBreakTime();
        LocalTime recordAfternoonWorkTime=attendanceRecord.getAfternoonWorkTime();
        LocalTime recordOffWorkTime = attendanceRecord.getOffWorkTime();
        
        Long ToWorkTimeSeconds=LocalTimeCompareUtil.getDurationSeconds(signInTime, workTime);
        Long ToNoonBreakSeconds=LocalTimeCompareUtil.getDurationSeconds(signInTime, noonBreakTime);
        Long ToAfternoonSeconds=LocalTimeCompareUtil.getDurationSeconds(signInTime, afternoonWorkTime);
        Long ToOffWorkSeconds=LocalTimeCompareUtil.getDurationSeconds(signInTime, offWorkTime);
        
        if(ToWorkTimeSeconds>0) {
            /* 未迟到 */
            if (recordWorkTime == null) {
                attendanceRecord.setWorkTime(signInTime);
                attendanceRecord.setWorkType(WorkTypeEnum.normal);
            } 
        }else if(ToWorkTimeSeconds<0&&(ToWorkTimeSeconds+ToNoonBreakSeconds)>0) {
            /*迟到*/
           if(ToWorkTimeSeconds+HALF_HOUR_TIME_SECONDS>=0) {
               /*迟到半小时以内*/
               if(recordWorkTime==null) {
                   attendanceRecord.setWorkTime(signInTime);
                   attendanceRecord.setWorkType(WorkTypeEnum.beLate);                   
               }
           }else{
               /*迟到超过半小时*/
               if(recordWorkTime==null) {
                   attendanceRecord.setWorkTime(signInTime);
                   attendanceRecord.setWorkType(WorkTypeEnum.absent);     
               }
           }
        }else if((ToWorkTimeSeconds+ToNoonBreakSeconds)<0&&(ToNoonBreakSeconds+ToAfternoonSeconds)>0) {
            /*中午休息签退*/
            if(ToNoonBreakSeconds<0) {
                /*正常午休*/
                if(recordNoonBreakTime==null||LocalTimeCompareUtil.leftBeforeRight(recordNoonBreakTime, signInTime)) {
                    attendanceRecord.setNoonBreakTime(signInTime);
                    attendanceRecord.setNoonBreakType(OffWorkTypeEnum.normal);
                }
            }else if((ToNoonBreakSeconds-HALF_HOUR_TIME_SECONDS)<0) {
                /*提前午休*/
                if(recordNoonBreakTime==null||LocalTimeCompareUtil.leftBeforeRight(recordNoonBreakTime, signInTime)) {
                    attendanceRecord.setNoonBreakTime(signInTime);
                    attendanceRecord.setNoonBreakType(OffWorkTypeEnum.leaveEarly);
                }
            }else if((ToNoonBreakSeconds-HALF_HOUR_TIME_SECONDS)>0) {
                /*中午提前溜走*/
                if(recordNoonBreakTime==null||LocalTimeCompareUtil.leftBeforeRight(recordNoonBreakTime, signInTime)) {
                    attendanceRecord.setNoonBreakTime(signInTime);
                    attendanceRecord.setNoonBreakType(OffWorkTypeEnum.absent);
                }
            }
        }else if((ToNoonBreakSeconds+ToAfternoonSeconds)<0&&(ToAfternoonSeconds+ToOffWorkSeconds)>0) {
            /*中午休息结束，下午上班签到*/
            if(ToAfternoonSeconds>0) {
                /*下午正常上班*/
                if(recordAfternoonWorkTime==null) {
                    attendanceRecord.setAfternoonWorkTime(signInTime);
                    attendanceRecord.setAfternoonWorkType(WorkTypeEnum.normal);
                }
            }else if((ToAfternoonSeconds+HALF_HOUR_TIME_SECONDS)>0) {
                /*下午上班迟到*/
                if(recordAfternoonWorkTime==null) {
                    attendanceRecord.setAfternoonWorkTime(signInTime);
                    attendanceRecord.setAfternoonWorkType(WorkTypeEnum.beLate);
                }
            }else if((ToAfternoonSeconds+HALF_HOUR_TIME_SECONDS)<0) {
                /*下午上班缺席*/
                if(recordAfternoonWorkTime==null) {
                    attendanceRecord.setAfternoonWorkTime(signInTime);
                    attendanceRecord.setAfternoonWorkType(WorkTypeEnum.absent);
                }
            }
        }else if((ToAfternoonSeconds+ToOffWorkSeconds)<0) {
            /*下班签退*/
            if(ToOffWorkSeconds<0) {
                /*正常签退*/
                if(recordOffWorkTime==null||LocalTimeCompareUtil.leftBeforeRight(recordOffWorkTime, signInTime)) {
                    attendanceRecord.setOffWorkTime(signInTime);
                    attendanceRecord.setOffWorkType(OffWorkTypeEnum.normal);
                }
            }else if((ToOffWorkSeconds-HALF_HOUR_TIME_SECONDS)<0) {
                /*早退*/
                if(recordOffWorkTime==null||LocalTimeCompareUtil.leftBeforeRight(recordOffWorkTime, signInTime)) {
                    attendanceRecord.setOffWorkTime(signInTime);
                    attendanceRecord.setOffWorkType(OffWorkTypeEnum.leaveEarly);                    
                }
            }else if((ToOffWorkSeconds-HALF_HOUR_TIME_SECONDS)>0) {
                /*旷班*/
                if(recordOffWorkTime==null||LocalTimeCompareUtil.leftBeforeRight(recordOffWorkTime, signInTime)) {
                    attendanceRecord.setOffWorkTime(signInTime);
                    attendanceRecord.setOffWorkType(OffWorkTypeEnum.absent);
                }
            }
        }
        
        try {
            if(hasRecord) {
                DaoFactory.getAttendanceRecordDao().update(attendanceRecord);
            }else {
                DaoFactory.getAttendanceRecordDao().insert(attendanceRecord);
            }   
        } catch (SQLException e) {
            outer.print(JsonUtil.commonResultCodeJson(1, "签到失败"));
        }

        outer.println(JsonUtil.commonResultCodeJson(0));
    }
}
