<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,com.alankzh.domain.*,java.net.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>考勤结果查看(月)</title>  
</head>  
<%! 
public ArrayList<EmployeeMonthRecord> recordList;
public int year;
public int month;
%>

<body>
<form name="uploadForm" method="post" action="monthRecord">
查询其他月份  <br/>
    输入 年 月 日(格式：1999-09-09)：<input type="date" name="yearAndMonthAndDay" value="2018-05-01"/><br/>  
       <input type="submit" value="查询"/>  
</form>   
</br>

<%
year=(Integer)request.getAttribute("year");
month=(Integer)request.getAttribute("month");
out.print(year+"年 "+month+"月        考勤情况总合"); %>  <br/>  </br>

<% 
recordList=(ArrayList<EmployeeMonthRecord>)request.getAttribute("recordOfMonth");
year=(Integer)request.getAttribute("year");
month=(Integer)request.getAttribute("month");

for(EmployeeMonthRecord oneMonthRecord:recordList){
    out.print("姓名:"+"<a href=employeeMonthRecord?year="+year+"&month="+month+"&employeeName="+URLEncoder.encode(oneMonthRecord.getEmployee().getName(),"UTF-8")+"&employeeId="+oneMonthRecord.getEmployee().getId()+">"
            +oneMonthRecord.getEmployee().getName()
            +"</a>"
            +"&nbsp;&nbsp;&nbsp;&nbsp;正常工作天数(天):"+oneMonthRecord.getNormalWorkDayNum()
            +"&nbsp;&nbsp;&nbsp;&nbsp;不正常打卡天(天):"+oneMonthRecord.getAbnormalWorkDayNum()
            +"&nbsp;&nbsp;&nbsp;&nbsp;本月放假天数(天):"+oneMonthRecord.getHolidayNum()
            +"<br/>");
    if(oneMonthRecord.getAbnormalWorkDayNum()>0){
        out.print("不正常打卡内容包括:<br/>"
                +"&nbsp;&nbsp;&nbsp;&nbsp;"
                );
        if(oneMonthRecord.getWorkUnClockingNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;上班未打卡次数:"+oneMonthRecord.getWorkUnClockingNum());
        if(oneMonthRecord.getWorkDelayNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;上班迟到次数:"+oneMonthRecord.getWorkDelayNum());
        if(oneMonthRecord.getWorkAbsentNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;上午旷班次数:"+oneMonthRecord.getWorkAbsentNum());
        
        if(oneMonthRecord.getNoonBreakUnclockingNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;午休未打卡次数:"+oneMonthRecord.getNoonBreakUnclockingNum());
        if(oneMonthRecord.getNoonBreakEarlyNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;午休早退次数:"+oneMonthRecord.getNoonBreakEarlyNum());
        if(oneMonthRecord.getNoonBreakAbsentNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;午休旷班次数:"+oneMonthRecord.getNoonBreakAbsentNum());
        
        if(oneMonthRecord.getAfternoonWorkUnclockingNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;下午上班未打卡次数:"+oneMonthRecord.getAfternoonWorkUnclockingNum());
        if(oneMonthRecord.getAfternoonWorkDelayNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;下午上班迟到次数:"+oneMonthRecord.getAfternoonWorkDelayNum());
        if(oneMonthRecord.getAfternoonWorkAbsentNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;午休旷班次数:"+oneMonthRecord.getAfternoonWorkAbsentNum());
        
        if(oneMonthRecord.getOffWorkUnclockNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;下班未打卡次数:"+oneMonthRecord.getOffWorkUnclockNum());
        if(oneMonthRecord.getOffWorkEarlyNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;下班早退次数:"+oneMonthRecord.getOffWorkEarlyNum());
        if(oneMonthRecord.getOffWorkAbsentNum()>0)out.print("&nbsp;&nbsp;&nbsp;&nbsp;下午旷班次数:"+oneMonthRecord.getOffWorkAbsentNum());
    }else{
        out.print("员工勤勉可嘉，没有不正常打卡<br/>");
    }
    
}
%>


</body>  
</html>