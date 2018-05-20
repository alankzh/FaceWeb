<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>新增员工</title>  
</head>  
<body>  
<form name="uploadForm" method="post" action="increaseEmployee" enctype="multipart/form-data">  
    员工姓名：<input type="text" name="name" value=""/><br/>  
    头像图片：<input type="file" name="headPhoto"/><br/>
    员工部门： <input type="text" name="departmentName" value=""/><br/>
    员工生日： <input type="date" name="birthday" value="1990-09-09"/><br/>
    电话号码： <input type="text" name="phoneNum" value=""/><br/>
  qq号码： <input type="text" name="qq" value=""/><br/>
    微信： <input type="text" name="weChat" value=""/><br/>
    <input type="submit" value="上传"/>  
</form>  
</body>  
</html> 