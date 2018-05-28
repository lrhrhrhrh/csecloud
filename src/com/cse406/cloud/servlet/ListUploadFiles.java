package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.FileDao;
import com.cse406.cloud.entity.FileEntity;
import com.cse406.cloud.entity.UserEntity;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListUploadFiles")
public class ListUploadFiles extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");
        List<FileEntity> fileList = FileDao.list(user);
        request.setAttribute("fileList", fileList);

        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static void main(String[] args){

//        List<FileEntity> fileList = FileDao.list();
//        System.out.println(fileList);
    }
}
