package org.hnylj.util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;  

public class PageDAO {
	
	private Connection conn ;  
    private PreparedStatement pstmt ;  
    private ResultSet rs ;  
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "TEST";
	String passwd = "TEST";
      
    private Student student ;  
      
  //数据库连接    
    public synchronized Connection getConnection () {  
        try {  
            Class.forName (driver) ;  
            conn = DriverManager.getConnection (url,userid,passwd) ;  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace () ;  
            return null ;  
        } catch (SQLException e) {  
            e.printStackTrace () ;  
            return null ;  
        }   
        return conn ;  
    }  
    
  //分页查询  
    public List<Student> queryByPage (int pageSize, int pageNow) {  
      List<Student> list = new ArrayList<Student> () ;  
      try {  
        if (this.getConnection()!=null && pageSize>0 && pageNow>0) {  
//        	String sql ="select * from student where rownum < "+pageSize;
        	String sql ="select * from(select a.*,rownum row_num from(select * from student order by stu_id) a where rownum <=" + (pageNow*pageSize)  +") where row_num>=" +  (pageNow*pageSize-pageSize+1);
        	System.out.println(sql);
            pstmt = this.getConnection().prepareStatement(sql);  
            rs = pstmt.executeQuery () ;  
              
            while (rs.next()) {  
                student = new Student () ;  
                student.setStu_id (rs.getInt(1)) ;  
                student.setStuName (rs.getString(2)) ;  
                student.setAddress (rs.getString(3)) ;  
                student.setStuPhone (rs.getString(4)) ;  
                list.add (student) ;  
            }  
        }  
      } catch(SQLException e) {  
          e.printStackTrace() ;  
      }  
      return list ;  
    }  

}
