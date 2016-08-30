package com.zking.web.exception;

/**
 * 自定义异常类
 * @author 胡博
 *
 */
public class DAOException extends Exception {
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String msg) {
		super(msg);
	}
}
