package com.zking.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zking.web.dao.ICourseDAO;
import com.zking.web.entity.course.Course;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.CourseSql;
import com.zking.web.util.JdbcUtil;

/**
 * 
 * @author 胡博
 *
 */
public class CourseDAO extends BaseDAO<Course> implements ICourseDAO {
	/**
	 * 添加一条course记录
	 * @param course
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveCourse(Course course) throws SQLException {
		return add(CourseSql.INSERT, 
				new Object[]{course.getCuid(), course.getCcuid(), course.getTuid(), 
							course.getCname(), course.getCimage(), course.getCinfo()});
	}

	/**
	 * 更新一条course记录
	 * @param course
	 * @return 更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean updateCourse(Course course) throws SQLException {
		return update(CourseSql.UPDATE_BY_CUID, 
				new Object[]{course.getCimage(), course.getCinfo(), course.getCname(),
							course.getCcuid(), course.getCuid()});
	}

	/**
	 * 根据cuid删除一条course记录
	 * @param cuid
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourse(String cuid) throws SQLException {
		return delete(CourseSql.DELETE_BY_CUID, 
				new Object[]{cuid});
	}

	/**
	 * 根据cuid查询一条course记录
	 * @param cuid
	 * @return 对应的course
	 * @throws SQLException
	 */
	public Course selectByCuid(String cuid) throws SQLException {
		return get(CourseSql.SELECT_BY_CUID, 
				new Object[]{cuid});
	}

	/**
	 * 查询某个教师的所有课程
	 * @param 教师的tuid
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public int selectCourseCount() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CourseSql.SELECT_COUNT_OF_COURSE);
		
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 查询某类课程的课程数
	 * @param ccname
	 * @return 课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByCcname(String ccname) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CourseSql.SELECT_COUNT_OF_BY_CCNAME);
		pstmt.setString(1, ccname);
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 查询指定页的课程，并指定每页最多包含多少条course记录
	 * @param page
	 * @param count
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByPage(int page, int count) throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		
		return getAll(CourseSql.SELECT_BY_PAGE, 
				new Object[]{start, count});
	}

	/**
	 * 查询指定课程类别下指定页的课程，并指定每页最多包含多少条课程记录
	 * @param ccname 课程类别
	 * @param page	页码
	 * @param count	数量
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcnamePage(String ccname, int page, int count)
			throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		return getAll(CourseSql.SELECT_BY_CCNAME_PAGE, 
				new Object[]{ccname, start, count});
	}

	/**
	 * 根据cname查询课程
	 * @param cname
	 * @return 对应的course
	 * @throws SQLException
	 */
	public Course selectCourseByCname(String cname) throws SQLException {
		return get(CourseSql.SELECT_BY_CNAME, 
				new Object[]{cname});
	}

	/**
	 * 查询所有的课程
	 * @return 返回对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectCourses() throws SQLException {
		return getAll(CourseSql.SELECT_COURSE_ALL, 
				new Object[]{});
	}

	/**
	 * 查询某个教师的所有课程
	 * @param 教师的tuid
	 * @return 对应的课程
	 * @throws SQLException
	 */
	public List<Course> selectByTuid(String tuid) throws SQLException {
		return getAll(CourseSql.SELECT_COURSE_ALL_BY_TUID, 
				new Object[]{tuid});
	}


	@Override
	protected Course getEntity(ResultSet rs) throws DAOException {
		Course course = new Course();
		
		try {
			course.setCuid(rs.getString("cuid"));
			course.setCname(rs.getString("cname"));
			course.setCcuid(rs.getString("ccuid"));
			course.setTuid(rs.getString("tuid"));
			course.setCimage(rs.getString("cimage"));
			course.setCinfo(rs.getString("cinfo"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return course;
	}

	public List<Course> selectCourseByTuidPage(String tuid, int page, int count)
			throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		return getAll(CourseSql.SELECT_BY_TUID_PAGE, 
				new Object[]{tuid, start, count});
	}

	/**
	 * 查询指定教师旗下的课程数
	 * @return 指定教师旗下的课程数
	 * @throws SQLException
	 */
	public int selectCourseCountByTuid(String tuid) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CourseSql.SELECT_COUNT_OF_COURSE_BY_TUID);
		pstmt.setString(1, tuid);
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 查询指定课程类别下的所有课程
	 * @param ccuid指定课程类别的id
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByCcuid(String ccuid) throws SQLException {
		return getAll(CourseSql.SELECT_COURSES_BY_CCUID,
				new Object[]{ccuid});
	}

	/**
	 * 删除ccuid标识的课程类别下的所有课程
	 * @param ccuid所要删除课程类别的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByCcuid(String ccuid) throws SQLException {
		return delete(CourseSql.DELETE_BY_CCUID, 
				new Object[]{ccuid});
	}

	/**
	 * 删除tuid标识的教师的所有课程
	 * @param tuid所要删除课程的教师的id
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseByTuid(String tuid) throws SQLException {
		return delete(CourseSql.DELETE_BY_TUID,
				new Object[]{tuid});
	}

	/**
	 * 根据用户输入的关键词查找相关课程
	 * @param keyword用户输入的关键词
	 * @return 对应的课程集合
	 * @throws SQLException
	 */
	public List<Course> selectCoursesByKeyword(String keyword) throws SQLException {
		return getAll(CourseSql.SELECT_COURSES_BY_KEYWORD, 
				new Object[]{"%"+keyword+"%"});
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
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		
		return getAll(CourseSql.SELECT_COURSE_BY_KEY_TUID_PAGE, 
				new Object[]{"%"+key+"%", tuid, start, count});
		
	}
	
	/**
	 * 通过课程名的关键字查询在指定教师的课程中多少条相关记录
	 * @param key课程名的关键字
	 * @param tuid教师的id
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKeyTuid(String key, String tuid) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				CourseSql.SELECT_COUNT_BY_KEY_TUID);
		pstmt.setString(1, "%"+key+"%");
		pstmt.setString(2, tuid);
		ResultSet rs =  pstmt.executeQuery();
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}
	

}
