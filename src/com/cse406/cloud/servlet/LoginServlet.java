package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.UserDao;
import com.cse406.cloud.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity user = new UserEntity();
        String name =request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println(name+" "+password);
        user.setName(name);
        user.setPassword(password);

        UserEntity userEntity = UserDao.login(user);
        if(userEntity!=null){
            request.setAttribute("info", "Login successfully");
//            request.setAttribute("user", userEntity);
            HttpSession session = request.getSession();
            session.setAttribute("user",userEntity);
            request.getRequestDispatcher("/loginsuccessfully.jsp").forward(request,response);
        }else{
            request.setAttribute("info", "Login failure");
            request.getRequestDispatcher("/loginfailure.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
