package com.zking.web.sql;

/**
 * 操作usercourse的sql语句
 * @author 胡博
 *
 */
public interface UserCourseSql {
	//根据uuid查询用户关注的课程
	String SELECT_BY_UUID = 
		"select ucuid, uuid, cuid from usercourse where uuid=?";
	
	//插入一条usercourse记录
	String INSERT = 
		"insert into usercourse(cuid, ucuid, uuid) " +
		"values(?,?,?)";
	
	//根据cuid和uuid删除一条usercourse记录
	String DELETE_BY_CUID_UUID = 
		"delete from usercourse where cuid=? and uuid=?";
	
	//根据cuid查找用户与课程的关联
	String SELECT_BY_CUID_UUID =
		"select ucuid, uuid, cuid from usercourse where cuid=? and uuid=?";
}
