package com.oracle.zibo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String jdbcName="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://127.0.0.1:3306/emp_dept";
	private static final String user="root";
	private static final String password="123456";
	public Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(jdbcName);
		Connection conn=DriverManager.getConnection(url, user, password);
		return conn;
	}
	public void closeConn(Connection conn) throws SQLException {
		if(conn!=null){
			conn.close();
		}
	}
	public static void main(String args[]){
		DbUtil dbUtil=new DbUtil();
		try {
			Connection conn=dbUtil.getConn();
			System.out.println(conn+"\n数据库连接成功");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
