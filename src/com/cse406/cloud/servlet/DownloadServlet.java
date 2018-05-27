package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.AccessoryDao;
import com.cse406.cloud.entity.FileEntity;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        FileEntity entity = AccessoryDao.query(id);
        ServletContext context = this.getServletContext();

        String path = entity.getFilePath();
        File file = new File(path);
        String nameStr = entity.getFileName();
        String fileName = new String(nameStr.getBytes("utf-8"),"iso8859-1");
        FileInputStream in = new FileInputStream(file);
        response.addHeader("Content-Disposition", "attachment; filename="+fileName);

        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024*1024*500];
        int tmp=0;
        while ((tmp=in.read(b))!=-1){
            out.write(b, 0, tmp);
        }
        out.flush();
        out.close();
        in.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
