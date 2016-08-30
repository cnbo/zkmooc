package com.zking.web.sql;

/**
 * 操作course表的sql语句
 * @author 胡博
 *
 */
public interface CourseSql {
	//添加一条course记录
	String INSERT = 
		"insert into course(cuid, ccuid, tuid, cname, cimage, cinfo) " +
		"values(?,?,?,?,?,?)";
	
	//根据cuid修改一条course记录
	String UPDATE_BY_CUID = 
		"update course set cimage=?, cinfo=?, cname=?, ccuid=? where cuid=?";
	
	//根据cuid删除一条course记录
	String DELETE_BY_CUID = 
		"delete from course where cuid=?";
	
	//删除ccuid标识的课程类别下的所有课程
	String DELETE_BY_CCUID =
		"delete from course where ccuid=?";
	
	//删除tuid标识的教师的所有课程
	String DELETE_BY_TUID =
		"delete from course where tuid=?";
	
	//根据cuid查找一条course记录
	String SELECT_BY_CUID = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course where cuid=? ";
	
	//根据tuid查找course记录
	String SELECT_COURSES_BY_TUID = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course where tuid=? " +
		"order by cname asc";
	
	//查询指定课程类别下的课程
	String SELECT_COURSES_BY_CCUID = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course where ccuid=? " +
		"order by cname asc";
	
	//查询总共有多少条course记录
	String SELECT_COUNT_OF_COURSE =  
		"select count(cuid) from course";
	
	//查询指定课程类别下有多少课程
	String SELECT_COUNT_OF_BY_CCNAME =  
		"select count(cuid) from course, coursecategory where" +
		" course.ccuid=coursecategory.ccuid and ccname=? ";
	
	//查询指定教师旗下的课程数
	String SELECT_COUNT_OF_COURSE_BY_TUID =
		"select count(cuid) from course where tuid=? ";
	
	//查询指定页的course
	String SELECT_BY_PAGE = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course order by cname asc " +
		"limit ?,? ";
	
	//查询指定课程类别下的指定页的course
	String SELECT_BY_CCNAME_PAGE = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course " +
		"where course.ccuid in (select coursecategory.ccuid " +
		"from coursecategory  where ccname=?) order by cname asc limit ?,? ";
	
	//对指定教师下的课程分页查询
	String SELECT_BY_TUID_PAGE = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course " +
		"where tuid=? order by cname asc limit ?,? ";
	
	//根据cname查询course
	String SELECT_BY_CNAME = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course where cname=? ";
	
	//查询所有课程
	String SELECT_COURSE_ALL = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course " +
		"order by cname asc";
	
	
	//查询某个教师的所有课程
	String SELECT_COURSE_ALL_BY_TUID = 
		"select cuid, ccuid, tuid, cname, cimage, cinfo from course where tuid=? " +
		"order by cname asc";
	
	//根据关键之进行模糊搜索
	String SELECT_COURSES_BY_KEYWORD =
			"select cuid, ccuid, tuid, cname, cimage, cinfo from course " +
			"where cname like ? " +
			"order by cname asc";
	
	//根据课程名的关键字和教师Id进行模糊分页查询
	String SELECT_COURSE_BY_KEY_TUID_PAGE =
			"select cuid, ccuid, tuid, cname, cimage, cinfo from course " +
					"where cname like ? and tuid=? order by cname asc limit ?, ? ";
	
	//根据课程名的关键字查询记录条数
	String SELECT_COUNT_BY_KEY_TUID =
			"select count(cuid) from course where cname like ? and tuid=? ";
}
