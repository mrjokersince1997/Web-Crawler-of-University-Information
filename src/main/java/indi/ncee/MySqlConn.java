package indi.ncee;

import indi.ncee.entity.University;
import java.sql.*;

public class MySqlConn {

    //配置数据库连接
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ncee?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "wdh19970506";

    //向数据库插入数据
    public static void insertData(University[] un){
        Connection conn = null;                //Connection对象：完成客户端与数据库的交互
        PreparedStatement stmt1 = null;         //Statement对象：向数据库发送sql语句(PreparedStatement防恶意注入）
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        try{
            Class.forName(JDBC_DRIVER);        //注册驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS);      //连接数据库

            //执行插入操作
            String sql1 = "INSERT INTO province(province_ID,province_name) VALUES (?,?) ON DUPLICATE KEY UPDATE province_ID = province_ID";
            stmt1 = conn.prepareStatement(sql1);
            String sql2 = "INSERT INTO city(city_ID,city_name,province_ID) VALUES (?,?,?) ON DUPLICATE KEY UPDATE city_ID = city_ID";
            stmt2 = conn.prepareStatement(sql2);
            String sql3 = "INSERT INTO university(ID,name,province_id,city_id,belong,type,level,is985,is211,description,num_master,num_doctor,num_academician,num_subject,num_library) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE ID = ID";
            stmt3 = conn.prepareStatement(sql3);

            for (int i = 0; i < 100; i++) {
                if(un[i] != null){
                    //导入数据
                    stmt1.setInt(1, un[i].getProvinceId());
                    stmt1.setString(2, un[i].getProvince());
                    stmt1.addBatch();
                    stmt2.setInt(1, un[i].getCityId());
                    stmt2.setString(2, un[i].getCity());
                    stmt2.setInt(3, un[i].getProvinceId());
                    stmt2.addBatch();
                    stmt3.setInt(1, un[i].getID());
                    stmt3.setString(2, un[i].getName());
                    stmt3.setInt(3, un[i].getProvinceId());
                    stmt3.setInt(4, un[i].getCityId());
                    stmt3.setString(5, un[i].getBelong());
                    stmt3.setString(6, un[i].getType());
                    stmt3.setInt(7, un[i].getLevel());
                    stmt3.setInt(8,un[i].getIs985());
                    stmt3.setInt(9,un[i].getIs211());
                    stmt3.setString(10, un[i].getDescription());
                    stmt3.setInt(11, un[i].getNumMaster());
                    stmt3.setInt(12, un[i].getNumDoctor());
                    stmt3.setInt(13, un[i].getNumAcademic());
                    stmt3.setInt(14, un[i].getNumSubject());
                    stmt3.setInt(15, un[i].getNumLibrary());
                    stmt3.addBatch();
                }
            }
            System.out.println("正在向数据库批量导入数据...");
            stmt1.executeBatch();               //sql语句发送给数据库批量处理
            stmt1.clearBatch();                 //清空批处理序列，以便下次使用
            stmt2.executeBatch();
            stmt2.clearBatch();
            stmt3.executeBatch();
            stmt3.clearBatch();
            System.out.println("数据导入执行完毕。");
            stmt1.close();stmt2.close();stmt3.close(); conn.close();        // 完成后关闭连接
        }catch(SQLException se){               // 处理JDBC错误
            se.printStackTrace();
        }catch(Exception e){                   // 处理Class.forName错误
            e.printStackTrace();
        }finally{                              //关闭资源
            try{
                if(stmt1!=null) stmt1.close();
                if(stmt2!=null) stmt2.close();
                if(stmt3!=null) stmt3.close();
            }catch(SQLException se2){ }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

}
