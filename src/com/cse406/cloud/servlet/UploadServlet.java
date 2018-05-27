package com.cse406.cloud.servlet;

import com.cse406.cloud.dao.AccessoryDao;
import com.cse406.cloud.entity.FileEntity;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/UploadServlet")
/*

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //上传的文件存储目录
    private static final String UPLOAD_DIRECTORY = "Uploading_dir";

    //上传文件配置
    private static final int MEMORY_THRESHOLD = 1024*1024*3; //3MB
    private static final int MAX_FILE_SIZE = 1024*1024*40;  //40MB
    private static final int MAX_REQUEST_SIZE = 1024*1024*50; //50MB

    */
/*
     * 上传数据及保存文件
     *
     * *//*


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检测是否为多媒体上传
        if(!ServletFileUpload.isMultipartContent(request)){

            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/from-data");
            writer.flush();
            return;
        }

        //配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置内存临界值 - 超过后将产生临时文件并储存在临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        upload.setHeaderEncoding("UTF-8");

        //构造临时路径来存储上传的文件
        //这个路径相对当前应用的目录
//        String uploadPath = getServletContext().getRealPath("/Users/lrh/Desktop/")+File.separator+UPLOAD_DIRECTORY;
        String uploadPath = "/Users/lrh/Desktop"+File.separator+UPLOAD_DIRECTORY;

        //如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try{
            //解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if(formItems!=null && formItems.size()>0){
                for(FileItem item:formItems){
                    //处理不在表单中的字段
                    if(!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath+File.separator+fileName;
                        File storeFile = new File(filePath);

                        //控制台上输出文件的上传路径
                        System.out.println(filePath);
                        System.out.println(fileName);

                        //保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message", "Upload Successfully!");
                    }
                }
            }
        }catch (Exception e){
            request.setAttribute("message", "message error: "+e.getMessage());
        }
        //跳转到 message.jsp
        getServletContext().getRequestDispatcher("/uploadFeedback.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
*/
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置文件上传基本路径
        String savePath = this.getServletContext().getRealPath("/WEB-INF/uploadFiles");
        //设置临时文件路径
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/tempFiles");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }

        //定义异常消息
        String errorMessage = "";
        //创建file items工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置缓冲区大小
        factory.setSizeThreshold(1024 * 100);
        //设置临时文件路径
        factory.setRepository(tempFile);
        //创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        ProgressListener progressListener = new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("正在读取文件： " + pItems);
                if (pContentLength == -1) {
                    System.out.println("已读取： " + pBytesRead + " 剩余0");
                } else {
                    System.out.println("文件总大小：" + pContentLength + " 已读取：" + pBytesRead);
                }
            }
        };
        upload.setProgressListener(progressListener);

        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            //按照传统方式获取数据
            return;
        }

        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        //upload.setFileSizeMax(1024 * 1024);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(1024 * 1024 * 1000);

        try {
            //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = iterator.next();

                //判断jsp提交过来的是不是文件
                if (item.isFormField()) {
                    errorMessage = "请提交文件！";
                    break;
                } else {
                    //文件名
                    String fileName = item.getName();
                    if (fileName == null || fileName.trim() == "") {
                        System.out.println("文件名为空！");
                    }
                    //处理不同浏览器提交的文件名带路径问题
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    //文件大小
                    Long fileSize = item.getSize();
                    //文件扩展名
                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //判断扩展名是否合法
                    if (!validExtension(fileExtension)) {
                        errorMessage = "上传文件非法！";
                        item.delete();
                        break;
                    }
                    //获得文件输入流
                    InputStream in = item.getInputStream();
                    //得到保存文件的名称
                    String fileUUID = createFileUUID();
                    String saveFileName = fileUUID+"_"+fileName;
                    //得到文件保存路径
                    String realFilePath = createRealFilePath(savePath, saveFileName);
                    //创建文件输出流
                    FileOutputStream out = new FileOutputStream(realFilePath);
                    //创建缓冲区
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        //写文件
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除临时文件
                    item.delete();
                    FileEntity entity = new FileEntity();
                    entity.setFileName(fileName);
                    entity.setFileSize(fileSize);
                    entity.setFile_ext_name(fileExtension);
                    entity.setFilePath(realFilePath);
                    entity.setFileFatherDirectoryForView("root");
                    entity.setFileUUID(fileUUID);
//                    entity.setUserId();
                    AccessoryDao.add(entity);
                }

            }

        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            errorMessage = "单个文件超出最大值！！！";
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            errorMessage = "上传文件的总的大小超出限制的最大值！！！";
        } catch (FileUploadException e) {
            e.printStackTrace();
            errorMessage = "文件上传失败！！！";
        } finally {
            if (!"".equals(errorMessage)) {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("pages/upload/error.jsp").forward(request, response);
            } else {
                response.sendRedirect("/list.jsp");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean validExtension(String fileExtension) {
        String[] exts = {"jpg", "txt", "doc", "pdf", "png"};
        for (int i = 0; i < exts.length; i++) {
            if (fileExtension.equals(exts[i])) {
                return true;
            }

        }

        return false;
    }

    private String createFileUUID() {
        return UUID.randomUUID().toString() ;
    }

    /**
     * 根据基本路径和文件名称生成真实文件路径，基本路径\\年\\月\\fileName
     *
     * @param basePath
     * @param fileName
     * @return
     */
    private String createRealFilePath(String basePath, String fileName) {
        Calendar today = Calendar.getInstance();
        String year = String.valueOf(today.get(Calendar.YEAR));
        String month = String.valueOf(today.get(Calendar.MONTH) + 1);


        String upPath = basePath + File.separator + year + File.separator + month + File.separator;
        File uploadFolder = new File(upPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String realFilePath = upPath + fileName;

        return realFilePath;
    }
}