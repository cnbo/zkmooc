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

import com.zking.web.dao.ICourseCategoryDAO;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.exception.DAOException;
import com.zking.web.sql.CourseCategorySql;
import com.zking.web.util.JdbcUtil;

/**
 * coursecategory表的dao操作
 * @author 胡博
 *
 */
public class CourseCategoryDAO extends BaseDAO<CourseCategory> implements ICourseCategoryDAO {
	/**
	 * 新增一条课程类别信息
	 * @param 所要新增的课程类别
	 * @return 添加成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean saveCourseCategory(CourseCategory ccategory)
			throws SQLException {
		return add(CourseCategorySql.INSERT, 
				new Object[]{ccategory.getCcuid(), ccategory.getCcname()});
	}

	/**
	 * 更新一条课程类别信息
	 * @param 需要新增的课程类别
	 * @return 修改成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean updateCourseCategory(CourseCategory ccategory)
			throws SQLException {
		String sql = "update coursecategory set ccname=? where ccuid=?";
		return update(CourseCategorySql.UPDATE_BY_CCUID, 
				new Object[]{ccategory.getCcname(), ccategory.getCcuid()});
	}

	/**
	 * 根据课程类别的ccuid删除一条记录
	 * @param 索要删除课程类别的ccuid
	 * @return 修改成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseCategory(String ccuid) throws SQLException {
		return delete(CourseCategorySql.DELETE_BY_CCUID, 
				new Object[]{ccuid});
	}

	/**
	 * 查询所有课程类别记录
	 * @return	返回课程类别集合
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategories() throws SQLException {
		return getAll(CourseCategorySql.SELECT_COURSE_CATEGORY_ALL, 
				new Object[]{});
	}

	/**
	 * 查询指定页的课程类别集合
	 * @param 指定查询页page，做多要查询记录了的数量count
	 * @return	返回指定页的课程类别集合
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategoriesByPage(int page, int count) throws SQLException {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		return getAll(CourseCategorySql.SELECT_BY_PAGE, 
				new Object[]{start, count});
	}
	
	/**
	 * 根据课程类别的ccuid查询一条课程记录
	 * @param 索要查询的课程类别ccuid
	 * @return	返回要查询的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByCcuid(String ccuid)
			throws SQLException {
		return get(CourseCategorySql.SELECT_BY_CCUID, 
				new Object[]{ccuid});
	}

	/**
	 * 查询有多少条课程类别记录
	 * @return	返回课程类别记录的总数
	 * @throws SQLException
	 */
	public int selectCategoryCount() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CourseCategorySql.SELECT_COUNT_OF_COURSECATEGORY);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count;
	}

	/**
	 * 根据课程类别名称插叙课程类别一条课程类别记录
	 * @param 所要查询课程类别的名称
	 * @return	所要查询的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByName(String ccname)
			throws SQLException {
		return get(CourseCategorySql.SELECT_BY_CCNAME, 
				new Object[]{ccname});
	}

	@Override
	protected CourseCategory getEntity(ResultSet rs) throws DAOException {
		CourseCategory category = new CourseCategory();
		
		try {
			category.setCcuid(rs.getString("ccuid"));
			category.setCcname(rs.getString("ccname"));
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("实体对象转换失败");
		}
		
		return category;
	}
	
	/**
	 * 根据课程类别名的关键字进行分页查询
	 * @param key要搜索课程类别的名称的关键字
	 * @param page要显示的页码
	 * @param count最多要查询的记录数量
	 * @return
	 */
	public List<CourseCategory> selectCourseCategoryByKeyPage(String key, int page, int count) {
		int start = 0;
		if (page != 1) {
			start = (page - 1) * count;
		}
		
		return getAll(CourseCategorySql.SELECT_BY_KEYWORD_PAGE, 
				new Object[]{"%"+key+"%", start, count});
	}
	
	/**
	 * 查询与关键字相匹配的记录的数量
	 * @param key课程类别名的关键字
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKey(String key) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				CourseCategorySql.SELECT_COUNT_BY_KEYWORD);
		pstmt.setString(1, "%"+key+"%");
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		JdbcUtil.close(conn, pstmt, rs);
		
		return count; 
	}
}
