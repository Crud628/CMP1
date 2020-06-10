package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;


public class DataUtil {
	//属性
	public static Connection con;
	public static PreparedStatement pst;
	/**
	 * 得到连接接口
	 * @return
	 */
	public static Connection getConnection(){
		//导入对应的jar包
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@Asuskk:1521:ocrl","lawyer2011","tiger");
			return con;
		} catch (ClassNotFoundException e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param sql  
	 * @param data 可选参数，当作数组使用
	 * @return
	 */
	public  static boolean update(String sql,Object ...data){
		//判断连接是否存在
		try {
			if (con!=null&&!con.isClosed()) {
				
			}
			else
				con=getConnection();
			//语句对象
			pst=con.prepareStatement(sql);
			//给占位符赋值:占位符从0开始还是1
			for(int i=0;i<data.length;i++)
				pst.setObject(i+1,data[i]);
			//执行sql语句
			if (pst.executeUpdate()>0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		
	}
	/**
	 * 
	 * @param sql  sql语句
	 * @param data  可选参数，可传递一个或者是多个参数，相当于一个变长的数组
	 * @return
	 */
	public static ResultSet select(String sql,Object ...data){
		try {
			//连接对象
			if (con!=null&&!con.isClosed()) {
				
			}
			else
				con=getConnection();
			//语句对象
			pst=con.prepareStatement(sql);
			//给点位符赋值
			for(int i=0;i<data.length;i++)
				pst.setObject(i+1, data[i]);
			//执行sql语句
			return pst.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}
