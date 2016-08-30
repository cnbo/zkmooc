package com.zking.web.sql;

/**
 * 操作coursecategory表的sql语句
 * @author 胡博
 *
 */
public interface CourseCategorySql {
	//添加一条coursecategory记录
	String INSERT = 
		"insert into coursecategory(ccuid, ccname) values(?,?)";
	
	//更新一条coursecategory记录
	String UPDATE_BY_CCUID = 
		"update coursecategory set ccname=? where ccuid=?";
	
	//删除一条coursecatetoy记录
	String DELETE_BY_CCUID = 
		"delete from coursecategory where ccuid=?";
	
	//查询所有coursecategory
	String SELECT_COURSE_CATEGORY_ALL = 
		"select ccuid, ccname from coursecategory " +
		"order by ccname asc";
	
	//查询指定页的coursecategory，第一个参数为查询起始位置，第二个参数是所要查询的最大记录数量
	String SELECT_BY_PAGE = 
		"select ccuid, ccname from coursecategory order by ccname asc limit ?,? ";
	
	//根据ccuid查找coursecategory
	String SELECT_BY_CCUID = 
		"select ccuid, ccname from coursecategory where ccuid=? ";
	
	//查询共有多少条记录
	String SELECT_COUNT_OF_COURSECATEGORY = 
		"select count(ccuid) from coursecategory ";
	
	//根据cccname查找coursecategory
	String SELECT_BY_CCNAME = 
		"select ccuid, ccname from coursecategory where ccname=? ";
	
	//根据关键字进行模糊分页查询
	String SELECT_BY_KEYWORD_PAGE =
		"select ccuid, ccname from coursecategory where " +
		"ccname like ? order by ccname asc limit ?, ? ";
	
	//根据关键字查询有多少条相关记录
	String SELECT_COUNT_BY_KEYWORD =
		"select count(ccuid) from coursecategory where ccname like ? ";
}
