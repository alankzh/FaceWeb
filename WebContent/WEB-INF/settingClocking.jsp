<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,java.time.*,com.alankzh.domain.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>公司排班设置</title>  
</head>  
<%! 
public ClockingTemplate clocking;
%>

<body>  

当前排班安排：
<%
clocking=(ClockingTemplate)request.getAttribute("nowClocking");
out.print("上班时间："+clocking.getWorkTime()+"<br/>");
out.print("午休时间："+clocking.getNoonBreakTime()+"<br/>");
out.print("下午上班时间："+clocking.getAfternoonWorkTime()+"<br/>");
out.print("下班时间："+clocking.getOffWorkTime()+"<br/>");
%>

<form name="uploadForm" method="post" action="settingClocking">
更改排班安排  <br/>
    上班时间：<input type="time" name="workTime" value="09:00"/><br/>
    午休时间：<input type="time" name="noonBreakTime" value="12:00"/><br/>
    下午上班时间：<input type="time" name="afternoonWorkTime" value="14:00"/><br/>  
    下班时间：<input type="time" name="offWorkTime" value="18:00"/><br/>    
       <input type="submit" value="设定"/>  
</form> 

</body>  
</html>