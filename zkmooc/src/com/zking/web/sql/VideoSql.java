package com.zking.web.sql;

/**
 * 操作video表的sql语句
 * @author 胡博
 *
 */
public interface VideoSql {
	//插入一条vieo记录
	String INSERT = 
		"insert into video(vuid, cuid, vname, vpath) " +
				"values(?,?,?,?)";
	
	//跟据vuid修改记录
	String UPDATE_BY_VUID = 
		"update video set vname=?,vpath=?,cuid=? where vuid=?";
	
	//根据cuid查找一条video记录
	String SELECT_ALL_BY_CUID = 
		"select vuid, cuid, vname, vpath from video where cuid=? " +
		"order by vname asc";
	
	//根据vuid查找一条video记录
	String SELECT_BY_VUID = 
		"select vuid, cuid, vname, vpath from video where vuid=?";
	
	//根据vuid查找一条video记录
	String SELECT_BY_VNAME = 
		"select vuid, cuid, vname, vpath from video where vname=?";
	
	//根据vuid删除一条video记录
	String DELETE_BY_VUID = 
		"delete from video where vuid=?";
	
	//根据指定课程下的相关视频
	String DELETE_BY_CUID =
		"delete from video where cuid=?";
	
	//根据cuid查找所有video的名称
	String SELECT_ALL_NAME_BY_CUID = 
		"select vname from video where cuid=? " +
		"order by vname asc";
	
	//查询有多少条video的记录
	String SELECT_COUNT_OF_VIDEO =  
		"select count(vuid) from video";
	
	//分页查询，指定查询的页码和每页最多显示的数量
	String SELECT_BY_PAGE = 
		"select vuid, cuid, vname, vpath from video order by vname asc" +
		" limit ?,? ";
	
	//查询指定教师旗下有多少视频
	String SELECT_COUNT_OF_VIDEO_BY_TUID =
		"select count(vuid) from video where video.cuid in (select course.cuid from course where tuid=?)";
	
	//对指定教师旗下的视频进行分页查询
	String SELECT_VIDEO_BY_TUID_PAGE =
		"select vuid, cuid, vname, vpath from video " +
		"where video.cuid in (select course.cuid from course where tuid=?) order by vname asc " +
		"limit ?, ? ";
	
	//根据视频名的关键字分页查询指定教师下的视频
	String SELECT_VIDEO_BY_TUID_KEY_PAGE =
		"select vuid, cuid, vname, vpath from video " +
		"where vname like ? and video.cuid in " +
		"(select course.cuid from course where tuid=?) " +
		"order by vname asc limit ?, ? ";
	
	//根据视频名的关键字分页查询指定教师下的视频数
	String SELECT_COUNT_BY_TUID_KEY =
		"select count(vuid) from video " +
		"where vname like ? and video.cuid in " +
		"(select course.cuid from course where tuid=?)";
}
