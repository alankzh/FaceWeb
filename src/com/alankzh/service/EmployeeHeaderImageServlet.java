package com.alankzh.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.constant.Constant;

@WebServlet(name = "employeeHeaderImage", urlPatterns = "/employeeHeaderImage")
public class EmployeeHeaderImageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        
        String realPath = this.getServletContext().getRealPath(Constant.HEADER_IMAGE_SAVE_PATH);

        String photoName = request.getParameter("photoUrl");
        File file=new File(realPath,photoName);
        InputStream in = new FileInputStream(file);

        /*图片直接在浏览器中显示，而不是附件形式下载*/
        response.setContentType("image/"+photoName.substring(photoName.lastIndexOf(".")+1));
        response.addHeader("Content-Length", "" + file.length());
//        response.setHeader("content-disposition", "attachment;fileName=" + photoName);
//        response.setContentType("application/octet-stream");
        
        // 获取response字节流
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        // 关闭
        out.close();
        in.close();
    }
}
