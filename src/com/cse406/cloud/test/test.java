package com.cse406.cloud.test;

import com.cse406.cloud.dao.AccessoryDao;
import com.cse406.cloud.entity.FileEntity;
import com.cse406.cloud.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class test {
    public static void main(String[] args){
//        List<FileEntity> fileList = AccessoryDao.list();
//        for(FileEntity file : fileList) {
//            System.out.println(file);
//        }

//        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);

//        Connection conn = DBUtil.getConnection();
//        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather, userid  from tbl_file where id=?";
//        FileEntity entity = new FileEntity();
//        try{
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1,2);
//            ResultSet rs = ps.executeQuery();
//            entity.setId(rs.getInt("id"));
//            entity.setFileName(rs.getString("file_name"));
//            entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//            entity.setFile_ext_name(rs.getString("file_ext_name"));
//            entity.setFilePath(rs.getString("file_realpath"));
//            entity.setFileUUID(rs.getString("file_uuid"));
//            entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
//            entity.setUserId(rs.getInt("userid"));
//            System.out.println(rs.getInt("id"));
//            DBUtil.close(rs,ps,conn);
//
//
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

//        Connection conn = DBUtil.getConnection();
//        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather, userid from tbl_file where id=2";
//
//        try{
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                FileEntity entity = new FileEntity();
//                entity.setId(rs.getInt("id"));
//                entity.setFileName(rs.getString("file_name"));
//                entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//                entity.setFile_ext_name(rs.getString("file_ext_name"));
//                entity.setFilePath(rs.getString("file_realpath"));
//                entity.setFileUUID(rs.getString("file_uuid"));
//                entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
//                entity.setUserId(rs.getInt("userid"));
//                System.out.println(entity);
//            }
//
//            DBUtil.close(rs, ps, conn);
//        }catch (SQLException e){
//            e.printStackTrace();
//            System.out.println("Fuck");
//        }

//        FileEntity entity = AccessoryDao.query(2);
//        System.out.println(entity);
//        System.out.println(entity.getFilePath());

        String fileDirectory = "third";
        String tempDirectory = "third";

        while(!tempDirectory.equals("root")){
            FileEntity directoryEntity = AccessoryDao.query(tempDirectory);
            tempDirectory = directoryEntity.getFileFatherDirectoryForView();
            fileDirectory = tempDirectory+"/"+fileDirectory;
        }
        fileDirectory = "/"+fileDirectory;

        System.out.println(fileDirectory);


    }
}
