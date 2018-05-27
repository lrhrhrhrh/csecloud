package com.cse406.cloud.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Connection con=null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    private static String url="jdbc:mysql://localhost:3306/test";
    private static String username = "root";
    private static String driver="com.mysql.jdbc.Driver";
    private static String password="050330";

    private static Properties pp = null;
    private static InputStream fis = null;

//    static {
//        try{
//            pp = new Properties();
//            fis = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
//
//            pp.load(fis);
//            url = pp.getProperty("url");
//            username = pp.getProperty("username");
//            driver=pp.getProperty("driver");
//            password=pp.getProperty("password");
//
//            Class.forName(driver);
//
//        }catch (Exception e){
//            System.out.println("db driver load failure");
//            e.printStackTrace();
//        }finally {
//            try{
//                fis.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            fis = null;
//        }
//    }

    public static Connection getConnection(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            System.out.println("db link fail");
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs, Statement ps, Connection con){

        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
