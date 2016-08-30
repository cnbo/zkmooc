package com.zking.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zking.web.entity.course.Course;

/**
 * CourseDAO的接口类
 * @author 胡博
 *
 */
public interface ICourseDAO {
	/**
	 * 添加一条course记录
	 * @param course
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveCourse(Course course) throws SQLException;
	
	/**
	 * 更新一条course记录
	 * @param course
	 * @return 更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateCourse(Course course) throws SQLException;
	
	/**
	 * 根据cuid删除一条course记录
	 * @param cuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourse(String cuid) throws SQLException;
	
	/**
	 * 删除ccuid标识的课程类别下的所有课程
	 * @param ccuid所要删除课程类别的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByCcuid(String ccuid) throws SQLException;
	
	/**
	 * 删除tuid标识的教师的所有课程
	 * @param tuid所要删除课程的教师的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByTuid(String tuid) throws SQLException;
	
	/**
	 * 根据cuid查询一条course记录
	 * @param cuid
	 * @return 对应的course
	 * @throws SQLException
	 */
	public Course selectByCuid(String cuid) throws SQLException;
	
	/**
	 * 查询某个教师的所有课程
	 * @param 教师的tuid
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectByTuid(String tuid) throws SQLException;
	
	/**
	 * 查询指定课程类别下的所有课程
	 * @param ccuid指定课程类别的id
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcuid(String ccuid) throws SQLException;
	
	/**
	 * 获得全部课程的数量
	 * @return 课程数
	 * @throws SQLException
	 */
	public int selectCourseCount() throws SQLException;
	
	/**
	 * 查询指定教师旗下的课程数
	 * @return 指定教师旗下的课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByTuid(String tuid) throws SQLException;
	
	/**
	 * 查询某类课程的课程数
	 * @param ccname
	 * @return 课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByCcname(String ccname) throws SQLException;
	
	/**
	 * 查询指定页的课程，并指定每页最多包含多少条course记录
	 * @param page
	 * @param count
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByPage(int page, int count) throws SQLException;
	
	/**
	 * 查询所有的课程
	 * @return 返回对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectCourses() throws SQLException;
	
	/**
	 * 查询指定课程类别下指定页的课程，并指定每页最多包含多少条课程记录
	 * @param ccname 课程类别
	 * @param page	页码
	 * @param count	数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcnamePage(String ccname, int page, int count) throws SQLException;
	
	/**
	 * 查询指定教师指定页的课程，并指定每页最多包含多少条课程记录
	 * @param tuid教师的id
	 * @param page页码
	 * @param count数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCourseByTuidPage(String tuid, int page, int count) throws SQLException;
	
	/**
	 * 根据cname查询课程
	 * @param cname
	 * @return 对应的course
	 * @throws SQLException
	 */
	public Course selectCourseByCname(String cname) throws SQLException;
	
	//public Map<String, String> selectCourseIdNameMap() throws SQLException;
	
	/**
	 * 根据用户输入的关键词查找相关课程
	 * @param keyword用户输入的关键词
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByKeyword(String keyword) throws SQLException;
	
	/**
	 * 在指定教师的课程中模糊分页查询课程
	 * @param key课程名的关键字
	 * @param tuid要查询课程的教师的Id
	 * @param page指定的显示页
	 * @param count最多要查询的记录数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCourseByKeyTuidPage(String key, String tuid, int page, int count)
			throws SQLException;
	
	/**
	 * 通过课程名的关键字查询在指定教师的课程中多少条相关记录
	 * @param key课程名的关键字
	 * @param tuid教师的id
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKeyTuid(String key, String tuid) throws SQLException;
}
