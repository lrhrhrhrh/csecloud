package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.FileDao;
import com.cse406.cloud.entity.FileEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movehere")
public class MoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int originID = Integer.parseInt(request.getParameter("originid"));
        int id = Integer.parseInt(request.getParameter("id"));

        FileEntity entity =  FileDao.query(id);

        FileDao.updateFatherDirectory(originID,entity.getFileName());
        response.sendRedirect("/list.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
