package com.zking.web.sql;

/**
 * 操作user表的sql语句
 * @author 胡博
 *
 */
public interface UserSql {
	//添加一条user记录
	String INSERT = 
		"insert into user(uuid, uname, upass, usex, uage," +
		"unickname, uphone, umail, uimage, uprofession, address)" +
		"values(?,?,?,?,?,?,?,?,?,?,?)";
	
	//根据uid更新一条user记录
	String UPDATE_BY_UUID = 
		"update user set uname=?, upass=?, usex=?, uage=?," +
		"unickname=?, uphone=?, uimage=?, uprofession=?," +
		" address=? where uuid=?";
	
	//根据uuid删除一条user记录了
	String DELETE_BY_UUID = 
		"delete from user where uuid=?";
	
	//根据手机号查询user
	String SELECT_BY_UPHONE = 
		"select uuid, uname, upass, usex, uage," +
		"unickname, uphone, umail, uimage, uprofession, " +
		"address from user where uphone=?";
	
	//根据邮箱查询user
	String SELECT_BY_UMAIL = 
		"select uuid, uname, upass, usex, uage," +
		"unickname, uphone, umail, uimage, uprofession, " +
		"address from user where umail=?";
	
	//根据用户名查询user
	String SELECT_BY_UNICKNAME = 
		"select uuid, uname, upass, usex, uage," +
		"unickname, uphone, umail, uimage, uprofession, " +
		"address from user where unickname=?";
	
	//查询所有user
	String SELECT_USER_ALL = 
		"select uuid, uname, upass, usex, uage," +
		"unickname, uphone, umail, uimage, uprofession, " +
		"address from user";
}
