package com.alankzh.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.AttendanceRecordDaoImplement;
import com.alankzh.dao.DaoFactory;
import com.alankzh.dao.EmployClockingArrangeDaoImplement;
import com.alankzh.domain.AttendanceRecord;

import com.alankzh.domain.ClockingTemplate;
import com.alankzh.domain.ClockingTemplateCache;
import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeClockingArrange;
import com.alankzh.domain.WorkTypeEnum;
import com.alankzh.domain.EmployeeClockingArrange.CircleTypeEnum;
import com.alankzh.domain.OffWorkTypeEnum;
import com.alankzh.util.HolidayUtil;


/**
 * 午夜12点执行的操作
 * @author alankzh
 *
 */
@Immutable
public class MidnightQuartz implements Job{
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        LocalDate localDate=LocalDate.now();
        int dayNum=0;
        try {
            dayNum=HolidayUtil.isHoliday(localDate);
            System.out.println("day num:"+dayNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dayNum==0) {
            HolidayUtil.setCacheHoliday(false);
            /*如果是工作日，那么在考勤安排上加入全部员工的安排，为未打卡*/
            List<Employee> list=DaoFactory.getEmployeeDao().queryAll();
            AttendanceRecordDaoImplement recordDaoImplement=(AttendanceRecordDaoImplement) DaoFactory.getAttendanceRecordDao();

            ClockingTemplate clockingTemplate=null;

            clockingTemplate = ClockingTemplateCache.getInstance().getDefaultClocking();

            for(Employee oneEmployee:list) {
                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setClockingDate(localDate);
                attendanceRecord.setEmployeeId(oneEmployee.getId());
                attendanceRecord.setEmployeeName(oneEmployee.getName());
                
                attendanceRecord.setWorkType(WorkTypeEnum.unClocking);
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.unClocking);
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.unClocking);
                attendanceRecord.setAfternoonWorkType(WorkTypeEnum.unClocking);
                
                attendanceRecord.setShouldWorkTime(clockingTemplate.getWorkTime());
                attendanceRecord.setShouldNoonBreakTime(clockingTemplate.getNoonBreakTime());
                attendanceRecord.setShouldAfternoonWorkTime(clockingTemplate.getAfternoonWorkTime());
                attendanceRecord.setShouldOffWorkTime(clockingTemplate.getOffWorkTime());
                
                try {
                    recordDaoImplement.insert(attendanceRecord);
                } catch (SQLException e) {
                    //这个失败几率太低了
                }
            }
        }else {
            HolidayUtil.setCacheHoliday(true);
            /*如果是假日，那么在排班安排和考勤安排上加入全部员工的安排，为假日*/
            List<Employee> list=DaoFactory.getEmployeeDao().queryAll();
            EmployClockingArrangeDaoImplement arrangeDaoImplement=(EmployClockingArrangeDaoImplement) DaoFactory.getEmployeeClockingArrangeDao();
            AttendanceRecordDaoImplement recordDaoImplement=(AttendanceRecordDaoImplement) DaoFactory.getAttendanceRecordDao();
            for(Employee oneEmployee:list) {
                EmployeeClockingArrange employeeClockingArrange=new EmployeeClockingArrange();
                employeeClockingArrange.setEmployeeId(oneEmployee.getId());
                employeeClockingArrange.setCircleType(CircleTypeEnum.holidayArrange);
                employeeClockingArrange.setClockingDate(localDate);

                arrangeDaoImplement.insert(employeeClockingArrange);

                AttendanceRecord attendanceRecord=new AttendanceRecord();
                attendanceRecord.setClockingDate(localDate);
                attendanceRecord.setEmployeeId(oneEmployee.getId());
                attendanceRecord.setEmployeeName(oneEmployee.getName());
                attendanceRecord.setWorkType(WorkTypeEnum.statutoryHoliday);
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.statutoryHoliday);
                attendanceRecord.setOffWorkType(OffWorkTypeEnum.statutoryHoliday);
                attendanceRecord.setNoonBreakType(OffWorkTypeEnum.statutoryHoliday);
                
                try {
                    recordDaoImplement.insert(attendanceRecord);
                } catch (SQLException e) {
                    //失败几率太低
                }
            }
        }

    }

}
