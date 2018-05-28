package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.UserDao;
import com.cse406.cloud.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = new UserEntity();

        String name =request.getParameter("name");
        String password = request.getParameter("password");
        String relpassword = request.getParameter("relpassword");
        String email = request.getParameter("email");

        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        if(password.equals(relpassword)){
            boolean flag = UserDao.register(user);
            if(flag){
                request.setAttribute("info", "Register successfully");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else{
                request.setAttribute("info", "Register failure");
                request.getRequestDispatcher("/info.jsp").forward(request,response);
            }
        }else
            request.setAttribute("info", "Register failure");
            request.getRequestDispatcher("/info.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
