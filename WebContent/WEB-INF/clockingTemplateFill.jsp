<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>新增排班安排</title>  
</head>  
<body>  
<form name="uploadForm" method="post" action="increaseClocking">
新增排班安排  <br/>
    上班时间24小时制(时时：分分)：<input type="time" name="workTime" value="09:00"/><br/>  
    下班时间24小时制(时时：分分)：<input type="time" name="offWorkTime" value="17:30"/><br/>    
       <input type="submit" value="新增"/>  
</form>  
</body>  
</html>