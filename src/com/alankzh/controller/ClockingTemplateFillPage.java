package com.alankzh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.annotation.Immutable;


@WebServlet(name="clockingTemplateFillPage",urlPatterns="/clockingTemplateFillPage")
@Immutable
public class ClockingTemplateFillPage extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/clockingTemplateFill.jsp");
//        dispatcher.forward(request, response);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().print("这个页面暂时废弃，目前不需要这个功能");
    }
}
