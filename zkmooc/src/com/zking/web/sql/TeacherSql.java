package com.zking.web.sql;

/**
 * 操作teacher表的sql语句
 * @author 胡博
 *
 */
public interface TeacherSql {
	//插入一条teacher记录
	String INSERT_TEACHER = 
		"insert into teacher(tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass) values(?,?,?,?,?,?,?,?,?,?)";
	
	//根据tuid查找教师
	String UPDATE_BY_TUID = 
		"update teacher set tname=?, tage=?, tsex=?," +
		"tphone=?, timage=?, tinfo=?, tpass=? where tuid=?";
	
	//根据tuid删除teacher
	String DELETE_BY_TUID =
		"delete from teacher where tuid=?";
	
	//根据tuid查找teacher
	String SELECT_BY_TUID = 
		"select tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass from teacher where tuid=? ";
	
	//根据tacount查找teacher
	String SELECT_BY_TACOUNT = 
		"select tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass from teacher where tacount=? ";
	
	//查询出所有teacher
	String SELECT_TEACHER_ALL = 
		"select tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass from teacher " +
		"order by tacount asc ";
	
	//用户分页查询,第一个参数是查询的起始位置，第二个参数是最多要查询多少条记录
	String SELECT_BY_PAGE = 
		"select tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass from teacher order by tacount asc " +
		"limit ?,? ";
	
	//查询数据库中有多好条teacher的记录
	String SELECT_COUNT_OF_TEACHER = 
		"select count(tuid) from teacher";
	
	//根据tname查找teacher
	String SELECT_ALL_BY_TNAME = 
		"select tuid, tname, tage, tsex," +
		"tacount, tphone, tmail, timage, tinfo, tpass from teacher where tname=? " +
		"order by tname asc ";
	
	//根据教师姓名的关键字进行模糊分页查询
	String SELECT_TEACHER_BY_NAME_KEY_PAGE =
			"select tuid, tname, tage, tsex," +
			"tacount, tphone, tmail, timage, tinfo, tpass from teacher where tname like ? " +
			"order by tname asc " +
			"limit ?, ? ";
	
	//根据教师姓名的关键字查询有多少条相关记录
	String SELECT_COUNT_BY_NAME_KEY =
			"select count(tuid) from teacher where tname like ?";
	
	//根据教师编号的关键字进行模糊分页查询
	String SELECT_TEACHER_BY_ACOUNT_KEY_PAGE =
			"select tuid, tname, tage, tsex," +
			"tacount, tphone, tmail, timage, tinfo, tpass from teacher where tacount like ? " +
			"order by tname asc " +
			"limit ?, ?";
	
	//根据教师编号的关键字查询有多少条相关记录
		String SELECT_COUNT_BY_ACOUNT_KEY =
				"select count(tuid) from teacher where tacount like ?";
}
