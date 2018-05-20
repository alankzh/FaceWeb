package com.alankzh.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.alankzh.annotation.Immutable;
import com.alankzh.constant.Constant;
import com.alankzh.dao.DaoFactory;
import com.alankzh.domain.Department;
import com.alankzh.domain.Employee;
import com.alankzh.domain.EmployeeClockingArrange;
import com.alankzh.util.JsonUtil;
import com.alankzh.util.UuidUtil;

/**
 * 新增员工服务
 * 
 * @author alankzh
 *
 */
@WebServlet(name = "increaseEmployee", urlPatterns = "/increaseEmployee")
@Immutable
public class IncreaseEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        Employee newEmployee = new Employee();// 新员工

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(4096);
        // 设置临时存储目录 System.getProperty("java.io.tmpdir")是操作系统临时目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        // 设置最大请求大小 (包含文件和表单数据)
        upload.setSizeMax(4194304);// 4MB

        // 如果目录不存在则创建
        File uploadDir = new File(Constant.HEADER_IMAGE_SAVE_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuid = UuidUtil.getUUID();
        String realPath = this.getServletContext().getRealPath(Constant.HEADER_IMAGE_SAVE_PATH);
        String filePath = null;
        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // System.out.println("参数名" + item.getFieldName());

                    if (item.isFormField() && item.getSize() > 0) {
                        // 处理表单中的字段
                        String name = item.getFieldName(); // input标签的name
                        String value = item.getString("UTF-8"); // input表单的value

                        if ("name".equals(name)) {
                            newEmployee.setName(value);
                            // 不允许姓名为空
                            if (value == null || value.isEmpty()) {
                                out.print(JsonUtil.commonResultCodeJson(1));// 返回插入失败json
                                return;
                            }
                        } else if ("departmentName".equals(name)) {
                            Department department = null;

                            if (value == null || value.isEmpty()) {
                                value = "公司员工";
                            }

                            department = DaoFactory.getDepartmentDao().queryByName(value);

                            if (department != null && department.getId() > 0) {
                                newEmployee.setDepartmentId(department.getId());
                            } else {
                                department = new Department();
                                department.setName(value);
                                DaoFactory.getDepartmentDao().insert(department);
                                department = DaoFactory.getDepartmentDao().queryByName(value);

                                newEmployee.setDepartmentId(department.getId());
                                newEmployee.setDepartmentName(department.getName());
                            }

                        } else if ("birthday".equals(name)) {
                            if (value != null && !value.isEmpty()) {
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                newEmployee.setBirthday(LocalDate.parse(value, dateTimeFormatter));
                            }
                        } else if ("phoneNum".equals(name)) {
                            newEmployee.setPhoneNum(value);
                        } else if ("qq".equals(name)) {
                            newEmployee.setQq(value);
                        } else if ("weChat".equals(name)) {
                            newEmployee.setWeChat(value);
                        }
                    } else {
                        // 文件上传存储
                        String fieldName = item.getFieldName(); // 表单提交过来的file input标签中name的属性值
                        
                        if(fieldName.equals("departmentName")) {
                            Department department=new Department();
                            department = DaoFactory.getDepartmentDao().queryByName("公司员工");

                            if (department != null && department.getId() > 0) {
                                newEmployee.setDepartmentId(department.getId());
                            } else {
                                department = new Department();
                                department.setName("公司员工");
                                DaoFactory.getDepartmentDao().insert(department);
                                department = DaoFactory.getDepartmentDao().queryByName("公司员工");

                                newEmployee.setDepartmentId(department.getId());
                                newEmployee.setDepartmentName(department.getName());
                            }
                        }else if(fieldName.equals("headPhoto")) {
                            String fileName = item.getName(); // file input上传的文件名
                            String contentType = item.getContentType(); // 获得上传文件的类型
                            if (!contentType.startsWith("image")) {
                                // 显示文件类型为图片
                                out.println(JsonUtil.commonResultCodeJson(1));
                                return;
                            }

                            newEmployee.setPhotoUrl(uuid + fileName.substring(fileName.lastIndexOf(".")));

                            filePath = realPath + uuid + fileName.substring(fileName.lastIndexOf("."));
                            File saveFile = new File(filePath);

                            item.write(saveFile); // 将文件写入磁盘中   
                        }
                    }
                }
            }
            DaoFactory.getEmployeeDao().insert(newEmployee);// 插入新员工
            Employee insertedEmployee = DaoFactory.getEmployeeDao().queryByNoId(newEmployee);
            
            out.print(JsonUtil.commonResultCodeJson(0));// 返回插入成功json
        } catch (Exception e) {
            e.printStackTrace();
            /* 报错时删除文件 */
            if (filePath != null) {
                File saveFile = new File(filePath);
                if (saveFile.exists()) {
                    saveFile.delete();
                }
            }
            out.print(JsonUtil.commonResultCodeJson(1));// 返回插入失败json
        }
    }

}
