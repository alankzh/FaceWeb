<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,com.alankzh.domain.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>删除某员工</title>  
</head>  
<%! 
public List<Employee> employeeList;
%>

<body>  
<form name="uploadForm" method="post" action="fireEmployee">
选择要删除的员工  <br/>

<select name="deleteChosen">
<% 
employeeList=(List<Employee>)request.getAttribute("employeeList");
for(Employee oneEmployee:employeeList){
    out.println(
            "<option value=\""+oneEmployee.getId()+"\">"
            +"姓名："+oneEmployee.getName()+"   所属部门:"+oneEmployee.getDepartmentName()+"  电话："+oneEmployee.getPhoneNum()
            +"</option>"
            );
}
%>
</select><hr/>

<input type="submit" value="提交">
</form>  
</body>  
</html>