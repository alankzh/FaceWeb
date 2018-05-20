package com.alankzh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alankzh.annotation.Immutable;


@WebServlet(name="employeeFillPage",urlPatterns="/employeeFillPage")
@Immutable
public class EmployeeFillPage extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/EmployeeFill.jsp");
        dispatcher.forward(request, response);
    }
}
