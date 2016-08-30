package com.zking.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zking.web.entity.course.CourseCategory;

/**
 * 操作coursecategory表规范
 * @author 胡博
 *
 */
public interface ICourseCategoryDAO {
	/**
	 * 新增一条课程类别信息
	 * @param 所要新增的课程类别
	 * @return 添加成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean saveCourseCategory(CourseCategory ccategory) throws SQLException;
	
	/**
	 * 更新一条课程类别信息
	 * @param 需要新增的课程类别
	 * @return 修改成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean updateCourseCategory(CourseCategory ccategory) throws SQLException;
	
	/**
	 * 根据课程类别的ccuid删除一条记录
	 * @param 索要删除课程类别的ccuid
	 * @return 修改成功则返回true,失败则返回false
	 * @throws SQLException
	 */
	public boolean deleteCourseCategory(String ccuid) throws SQLException;
	
	/**
	 * 查询所有课程类别记录
	 * @return	返回课程类别集合
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategories() throws SQLException;

	/**
	 * 查询指定页的课程类别集合
	 * @param 指定查询页page，做多要查询记录了的数量count
	 * @return	返回指定页的课程类别集合
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategoriesByPage(int page, int count) throws SQLException;
	
	/**
	 * 根据课程类别的ccuid查询一条课程记录
	 * @param 索要查询的课程类别ccuid
	 * @return	返回要查询的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByCcuid(String ccuid) throws SQLException;
	
	/**
	 * 查询有多少条课程类别记录
	 * @return	返回课程类别记录的总数
	 * @throws SQLException
	 */
	public int selectCategoryCount() throws SQLException;
	
	
	/**
	 * 根据课程类别名称插叙课程类别一条课程类别记录
	 * @param 所要查询课程类别的名称
	 * @return	所要查询的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByName(String ccname) throws SQLException;
	
	/**
	 * 根据课程类别名的关键字进行分页查询
	 * @param key要搜索课程类别的名称的关键字
	 * @param page要显示的页码
	 * @param count最多要查询的记录数量
	 * @return
	 */
	public List<CourseCategory> selectCourseCategoryByKeyPage(String key, int page, int count);
	
	/**
	 * 查询与关键字相匹配的记录的数量
	 * @param key课程类别名的关键字
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKey(String key) throws SQLException;
}
