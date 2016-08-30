package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zking.web.exception.DAOException;
import com.zking.web.util.JdbcUtil;

/**
 * 操作所有数据的通用方法
 * @author 胡博
 *
 * @param <T>
 */
public abstract class BaseDAO<T> {
	/**
	 * 查询一条记录
	 * @param sql
	 * @param params
	 * @return
	 */
	public T get(String sql, Object[] params) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		T t = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				t = getEntity(rs);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败");
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return t;
	}
	
	/**
	 * 查询数据集合
	 * @param sql
	 * @param params
	 * @return list集合
	 */
	public List<T> getAll(String sql, Object[] params) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				T t = getEntity(rs);
				list.add(t);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败");
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 不确定操作的数据属于哪个对象，交给子类完成
	 * @param rs
	 * @return 返回实体
	 */
	protected abstract T getEntity(ResultSet rs) throws DAOException;
	
	/**
	 * 添加记录
	 * @param sql
	 * @param params
	 * @return  添加成功返回true,否则返回false
	 */
	public boolean add(String sql, Object[] params) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				return true;
			}
 		} catch(Exception e) {
 			e.printStackTrace();
 			throw new RuntimeException("添加数据失败");
 		} finally {
 			JdbcUtil.close(conn, pstmt, rs);
 		}
 		
 		return false;
	}
	
	
	/**
	 * 修改记录
	 * @param sql
	 * @param params
	 * @return 若修改成功返回true,否则返回false
	 */
	public boolean update(String sql, Object[] params) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据更新失败");
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return false;
	}
	
	/**
	 * 删除记录
	 * @param sql
	 * @param params
	 * @return 若删除成功返回true,否则返回false
	 */
	public boolean delete(String sql, Object[] params) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除数据失败");
		}
		
		return false;
	}
}
