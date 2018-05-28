package com.cse406.cloud.dao;

import com.cse406.cloud.entity.UserEntity;
import com.cse406.cloud.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static boolean addUpdateDelete(String sql, Object[] arr){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            if(arr!=null && arr.length!=0){
                for(int i=0; i<arr.length; i++){
                    ps.setObject(i+1, arr[i]);
                }
            }
            int count = ps.executeUpdate();
            if(count>0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

//    public static UserEntity query(UserEntity user){
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try{
//            con = DBUtil.getConnection();
//            String sql="select * from tbl_user where name=? and password=?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getPassword());
//
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public static UserEntity login(UserEntity user){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBUtil.getConnection();
            String sql = "select * from tbl_user where name=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            UserEntity userEntity = null;
            if(rs.next()){
                userEntity = new UserEntity();
                userEntity.setId(rs.getInt("id"));
                userEntity.setName(rs.getString("name"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setEmail(rs.getString("email"));

                return userEntity;
            }else{
                return null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(UserEntity user){
        String sql = "insert into tbl_user values (0,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add(user.getName());
        list.add(user.getPassword());
        list.add(user.getEmail());

        boolean flag = addUpdateDelete(sql, list.toArray());
        if(flag){
            return true;
        }else{
            return false;
        }
    }
}
