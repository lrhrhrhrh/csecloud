package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.AccessoryDao;
import com.cse406.cloud.entity.FileEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createdirectory")
public class CreateDirectoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("idNo"));
        String directoryName = request.getParameter("directory");
        FileEntity entity = AccessoryDao.query(id);
        String fatherPath = entity.getFileFatherDirectoryForView();

        FileEntity directoryEntity = new FileEntity();
        directoryEntity.setFileName(directoryName);
        directoryEntity.setFileFatherDirectoryForView(entity.getFileFatherDirectoryForView());
        directoryEntity.setFile_ext_name("directory");

        AccessoryDao.add(directoryEntity);
        AccessoryDao.updateFatherDirectory(id,directoryName);

        PrintWriter out = response.getWriter();
        out.println(directoryName);
        out.println(id);
        response.sendRedirect("/list.jsp");
    }

}
