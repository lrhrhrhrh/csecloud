package com.cse406.cloud.dao;

import com.cse406.cloud.entity.FileEntity;
import com.cse406.cloud.entity.UserEntity;
import com.cse406.cloud.util.DBUtil;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDao {
    public static FileEntity query(int id){
        Connection conn = DBUtil.getConnection();
        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather, userid  from tbl_file where id=?";
        FileEntity entity = new FileEntity();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                entity.setId(rs.getInt("id"));
                entity.setFileName(rs.getString("file_name"));
                entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                entity.setFile_ext_name(rs.getString("file_ext_name"));
                entity.setFilePath(rs.getString("file_realpath"));
                entity.setFileUUID(rs.getString("file_uuid"));
                entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
                entity.setUserId(rs.getInt("userid"));
            }
            DBUtil.close(rs,ps,conn);



        }catch (SQLException e){
            e.printStackTrace();
        }
        return entity;
    }

    public static FileEntity query(String directoryName){
        Connection conn = DBUtil.getConnection();
        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather, userid  from tbl_file where file_name=?";
        FileEntity entity = new FileEntity();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,directoryName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                entity.setId(rs.getInt("id"));
                entity.setFileName(rs.getString("file_name"));
                entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                entity.setFile_ext_name(rs.getString("file_ext_name"));
                entity.setFilePath(rs.getString("file_realpath"));
                entity.setFileUUID(rs.getString("file_uuid"));
                entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
                entity.setUserId(rs.getInt("userid"));
            }
            DBUtil.close(rs,ps,conn);



        }catch (SQLException e){
            e.printStackTrace();
        }
        return entity;
    }

    public static void updateFatherDirectory(int id, String directory){
        Connection conn = DBUtil.getConnection();
        String sql = "update tbl_file set file_viewfather=? where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, directory);
            ps.setInt(2, id);
            ps.executeUpdate();
            DBUtil.close(null,ps,conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add(FileEntity entity, UserEntity user){
        Connection conn = DBUtil.getConnection();
        String sql = "insert into tbl_file(file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather, userid)value (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,entity.getFileName());
            ps.setDouble(2,entity.getFileSize());
            ps.setString(3,entity.getFile_ext_name());
            ps.setString(4, entity.getFilePath());
            ps.setString(5,entity.getFileUUID());
            ps.setString(6, entity.getFileFatherDirectoryForView());
            ps.setInt(7, user.getId());


            ps.execute();

            DBUtil.close(null, ps, conn);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<FileEntity> list(UserEntity user){
        Connection conn = DBUtil.getConnection();
        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather from tbl_file where userid=?";
        List<FileEntity> fileList = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                FileEntity entity = new FileEntity();
                entity.setId(rs.getInt("id"));
                entity.setFileName(rs.getString("file_name"));
                entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                entity.setFile_ext_name(rs.getString("file_ext_name"));
                entity.setFilePath(rs.getString("file_realpath"));
                entity.setFileUUID(rs.getString("file_uuid"));
                entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
//                entity.setUserId(rs.getInt("userid"));
                fileList.add(entity);
            }

            DBUtil.close(rs, ps, conn);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return fileList;
    }

    public static List<FileEntity> list(String directory, UserEntity user){
        Connection conn = DBUtil.getConnection();
        String sql = "select id, file_name, file_size, file_ext_name, file_realpath, file_uuid,file_viewfather from tbl_file where file_viewfather=? and userid=?";
        List<FileEntity> fileList = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,directory);
            ps.setInt(2,user.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                FileEntity entity = new FileEntity();
                entity.setId(rs.getInt("id"));
                entity.setFileName(rs.getString("file_name"));
                entity.setFileSize(new BigDecimal(rs.getDouble("file_size") / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                entity.setFile_ext_name(rs.getString("file_ext_name"));
                entity.setFilePath(rs.getString("file_realpath"));
                entity.setFileUUID(rs.getString("file_uuid"));
                entity.setFileFatherDirectoryForView(rs.getString("file_viewfather"));
//                entity.setUserId(rs.getInt("userid"));
                fileList.add(entity);
            }

            DBUtil.close(rs, ps, conn);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return fileList;
    }


    public static void remove(int id){
        Connection conn = DBUtil.getConnection();
        String sql = "delete from tbl_file where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            DBUtil.close(null, ps, conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
