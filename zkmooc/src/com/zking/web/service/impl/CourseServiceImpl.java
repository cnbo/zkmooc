package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zking.web.entity.course.Course;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.ICourseService;

/**
 * CourseService接口实现类
 * @author 胡博
 *
 */
public class CourseServiceImpl implements ICourseService {
	
	/**
	 * 添加一条course记录
	 * @param course所要添加的course
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveCourse(Course course) throws SQLException {
		return DAOFactory.getCourseDAO().saveCourse(course);
	}

	/**
	 * 更新一条course记录
	 * @param course所要更新的course记录
	 * @return 更新成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean updateCourse(Course course) throws SQLException {
		return DAOFactory.getCourseDAO().updateCourse(course);
	}

	/**
	 * 根据cuid删除一条course记录
	 * @param cuid所要删除course的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourse(String cuid) throws SQLException {
		return DAOFactory.getCourseDAO().deleteCourse(cuid);
	}

	/**
	 * 根据cuid查询一条course记录
	 * @param cuid所要查询course的id
	 * @return 对应的course
	 * @throws SQLException
	 */
	public Course selectByCuid(String cuid) throws SQLException {
		return DAOFactory.getCourseDAO().selectByCuid(cuid);
	}

	/**
	 * 查询共有多少条course记录
	 * @return course的记录总数
	 * @throws SQLException
	 */
	public int selectCourseCount() throws SQLException {
		return DAOFactory.getCourseDAO().selectCourseCount();
	}

	/**
	 * 查询名称为ccname的课程类别下有多少课程
	 * @param ccname课程类别名称
	 * @return 名称为ccname的课程类所包含的课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByCcname(String ccname) throws SQLException {
		return DAOFactory.getCourseDAO().selectCourseCountByCcname(ccname);
	}

	/**
	 * 查询出所有课程
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCourses() throws SQLException {
		return DAOFactory.getCourseDAO().selectCourses();
	}

	/**
	 * 查询指定页的课程，并指定所能查询的最大记录条数
	 * @param page指定查询的页码
	 * @param count所能查询的最大记录条数
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByPage(int page, int count) throws SQLException {
		return DAOFactory.getCourseDAO().selectCoursesByPage(page, count);
	}

	/**
	 * 对指定课程类别下的课程进行分页插叙
	 * @param ccname课程类别名
	 * @param page要查询页的页码
	 * @param count最多所能查询的记录数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcnamePage(String ccname, int page, int count) throws SQLException {
		return DAOFactory.getCourseDAO().selectCoursesByCcnamePage(ccname, page, count);
	}

	/**
	 * 根据指定的ccuid删除course
	 * @param ccuid课程类别的id
	 * @return 删除成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByCcuid(String ccuid) throws SQLException {
		return DAOFactory.getCourseDAO().deleteCourseByCcuid(ccuid);
	}

	/**
	 * 删除tuid标识的教师的所有课程
	 * @param tuid所要删除课程的教师的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByTuid(String tuid) throws SQLException {
		return DAOFactory.getCourseDAO().deleteCourseByTuid(tuid);
	}
	
	/**
	 * 得到以课程id为键，课程名为值得map集合
	 * @return 对应的map集合
	 * @throws SQLException
	 */
	public Map<String, String> selectCourseIdNameMap() throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		
		List<Course> courses = 
			DAOFactory.getCourseDAO().selectCourses();
		
		for (Course course : courses) {
			map.put(course.getCuid(), course.getCname());
		}
		
		return map;
	}

	/**
	 * 根据课程名查找课程
	 * @param cname课程名
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public Course selectCourseByCname(String cname) throws SQLException {
		return DAOFactory.getCourseDAO().selectCourseByCname(cname);
	}

	/**
	 * 查询指定教师指定页的课程，并指定每页最多包含多少条课程记录
	 * @param tuid教师的id
	 * @param page页码
	 * @param count数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCourseByTuidPage(String tuid, int page, int count)
			throws SQLException {
		return DAOFactory.getCourseDAO().selectCourseByTuidPage(tuid, page, count);
	}

	/**
	 * 查询某个教师的所有课程
	 * @param 教师的tuid
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectByTuid(String tuid) throws SQLException {
		return DAOFactory.getCourseDAO().selectByTuid(tuid);
	}
	
	/**
	 * 查询指定课程类别下的所有课程
	 * @param ccuid指定课程类别的id
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcuid(String ccuid) throws SQLException {
		return DAOFactory.getCourseDAO().selectCoursesByCcuid(ccuid);
	}
	
	/**
	 * 查询指定教师旗下的课程数
	 * @return 指定教师旗下的课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByTuid(String tuid) throws SQLException {
		return DAOFactory.getCourseDAO().selectCourseCountByTuid(tuid);
	}
	
	/**
	 * 根据用户输入的关键词查找相关课程
	 * @param keyword用户输入的关键词
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByKeyword(String keyword) throws SQLException {
		return DAOFactory.getCourseDAO().selectCoursesByKeyword(keyword);
	}
	
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
			throws SQLException {
		return DAOFactory.getCourseDAO()
				.selectCourseByKeyTuidPage(key, tuid, page, count);
	}
	
	/**
	 * 通过课程名的关键字查询在指定教师的课程中多少条相关记录
	 * @param key课程名的关键字
	 * @param tuid教师的id
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKeyTuid(String key, String tuid) throws SQLException {
		return DAOFactory.getCourseDAO()
				.selectCountByKeyTuid(key, tuid);
	}
}
