package com.zking.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



/**
 * 数据库工具类
 * @author 胡博
 *
 */
public class JdbcUtil {
	//数据库驱动
	private static String driverName;
	//数据库连接url
	private static String url;
	//访问数据库的用户名
	private static String username;
	//访问数据库的密码
	private static String password;
	
	static {
		Properties prop = new Properties();
		//通过输入流获取数据库的连接信息
		InputStream in = 
			JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		//将信息加载到prop中
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载属性文件失败");
		}
		
		//获取信息
		driverName = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("user");
		password = prop.getProperty("password");
		//加载驱动
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库驱动加载失败");
		}
	}
	
	//工具类不需要提供具体事例，需将构造方法私有化
	private JdbcUtil() {}
	
	/**
	 * 获取数据库连接
	 * @throws SQLException 
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败");
		}
		
		return conn;
	}
	
	/**
	 * 释放资源
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
