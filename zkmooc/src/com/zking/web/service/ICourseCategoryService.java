package com.zking.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zking.web.entity.course.CourseCategory;

/**
 * CourseCategoryService的接口类
 * @author 胡博
 *
 */
public interface ICourseCategoryService {
	/**
	 * 添加一条coursecategory记录
	 * @param 所要添加的ccategory
	 * @return 添加成功返回true,否则返回false
	 * @throws SQLException
	 */
	public boolean saveCourseCategory(CourseCategory ccategory) throws SQLException;
	
	/**
	 * 根据ccuid删除
	 * @param ccuid
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteCourseCategory(String ccuid) throws SQLException;
	
	/**
	 * 更新一条coursecategory记录
	 * @param ccategory所要更新的一条coursecategory记录
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCourseCategory(CourseCategory ccategory) throws SQLException;
	
	/**
	 * 查询出所有的课程类别
	 * @return 返回所有的课程类别
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategories() throws SQLException;
	
	/**
	 * 查询指定页的课程类别
	 * @param page所要查询的页码
	 * @param count所要查询的最大记录条数
	 * @return 返回对应的课程类别集合
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategoriesByPage(int page, int count) throws SQLException;
	
	/**
	 * 根据ccuid查询coursecategory
	 * @param 所要查询课程类别的ccuid
	 * @return 返回对应的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByCcuid(String ccuid) throws SQLException;
	
	/**
	 * 查询总共有多少课程类别
	 * @return 返回课程类别的总数
	 * @throws SQLException
	 */
	public int selectCategoryCount() throws SQLException;
	
	/**
	 * 根据类别名查询课程类别
	 * @param ccname所要查询课程类别的名称
	 * @return 对应的课程类别
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByName(String ccname) throws SQLException;
	
	/**
	 * 获取键为ccuid，值为ccname的map集合
	 * @return 键为ccuid，值为ccname的map集合
	 * @throws SQLException
	 */
	public Map<String, String> selectCategoryIdNameMap() throws SQLException;
	
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
