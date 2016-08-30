package com.zking.web.sql;

/**
 * 操作admin表的sql语句
 * @author 胡博
 *
 */
public interface AdminSql {
	String SELECT_ADMIN =
		"select aacount, apass from admin where aacount=?";
}
