package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.FileDao;
import com.cse406.cloud.entity.FileEntity;
import com.cse406.cloud.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createdirectory")
public class CreateDirectoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("idNo"));
        String directoryName = request.getParameter("directory");
        FileEntity entity = FileDao.query(id);
        String fatherPath = entity.getFileFatherDirectoryForView();

        FileEntity directoryEntity = new FileEntity();
        directoryEntity.setFileName(directoryName);
        directoryEntity.setFileFatherDirectoryForView(entity.getFileFatherDirectoryForView());
        directoryEntity.setFile_ext_name("directory");
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");
        FileDao.add(directoryEntity, user);
        FileDao.updateFatherDirectory(id,directoryName);

        PrintWriter out = response.getWriter();
        out.println(directoryName);
        out.println(id);
        response.sendRedirect("/list.jsp");
    }

}
