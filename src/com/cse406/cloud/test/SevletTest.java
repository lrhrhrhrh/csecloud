package com.cse406.cloud.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class SevletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        out.println("<h1>"+name+"</h1>");
//        out.println("xiaoxiannv");
//        response.sendRedirect("/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        System.out.println(name);
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(name+"<br>");
        out.println("ainio");

    }
}
