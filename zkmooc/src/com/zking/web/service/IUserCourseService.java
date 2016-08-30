package com.zking.web.service;

import java.sql.SQLException;
import java.util.List;

import com.zking.web.entity.user.UserCourse;

/**
 * 
 * @author 胡博
 *
 */
public interface IUserCourseService {
	/**
	 * 根据uuid查询usercourse
	 * @param uuid
	 * @return 返回对应的usercourse
	 * @throws SQLException
	 */
	public List<UserCourse> selectUserCourses(String uuid) throws SQLException;
	
	/**
	 * 添加一条usercourse记录
	 * @param ucoure
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveUserCourse(UserCourse ucourse) throws SQLException;
	
	/**
	 * 根据课程id和用户Id查询usercourse
	 * @param cuid被关注课程的id
	 * @param uuid用户id
	 * @return UserCourse
	 * @throws SQLException
	 */
	public UserCourse selectByCuidUuid(String cuid, String uuid) throws SQLException;
	
	/**
	 * 通过课程的id和用户id删除用户与课程的关联
	 * @param cuid被关注课程的Id
	 * * @param uuid用户id
	 * @return 若删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteByCuidUuid(String cuid, String uuid) throws SQLException;
}
