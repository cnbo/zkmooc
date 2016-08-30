package com.zking.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.DAOFactory;
import com.zking.web.service.ICourseCategoryService;

/**
 * CourseCategoryServiceʵ����
 * @author ��
 *
 */
public class CourseCategoryServiceImpl implements ICourseCategoryService {
	/**
	 * ���һ��coursecategory��¼
	 * @param ��Ҫ��ӵ�ccategory
	 * @return ��ӳɹ�����true,���򷵻�false
	 * @throws SQLException
	 */
	public boolean saveCourseCategory(CourseCategory ccategory) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().saveCourseCategory(ccategory);
	}

	/**
	 * ���ccuidɾ��
	 * @param ccuid
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteCourseCategory(String ccuid) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().deleteCourseCategory(ccuid);
	}

	/**
	 * ����һ��coursecategory��¼
	 * @param ccategory��Ҫ���µ�һ��coursecategory��¼
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCourseCategory(CourseCategory ccategory) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().updateCourseCategory(ccategory);
	}

	/**
	 * ��ѯ�����еĿγ����
	 * @return �������еĿγ����
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategories() throws SQLException {
		return DAOFactory.getCourseCategoryDAO().selectCourseCategories();
	}

	/**
	 * ��ѯָ��ҳ�Ŀγ����
	 * @param page��Ҫ��ѯ��ҳ��
	 * @param count��Ҫ��ѯ������¼����
	 * @return ���ض�Ӧ�Ŀγ���𼯺�
	 * @throws SQLException
	 */
	public List<CourseCategory> selectCourseCategoriesByPage(int page, int count) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().selectCourseCategoriesByPage(page, count);
	}

	/**
	 * ���ccuid��ѯcoursecategory
	 * @param ��Ҫ��ѯ�γ�����ccuid
	 * @return ���ض�Ӧ�Ŀγ����
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByCcuid(String ccuid) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().selectCourseCategoryByCcuid(ccuid);
	}

	/**
	 * ��ѯ�ܹ��ж��ٿγ����
	 * @return ���ؿγ���������
	 * @throws SQLException
	 */
	public int selectCategoryCount() throws SQLException {
		return DAOFactory.getCourseCategoryDAO().selectCategoryCount();
	}

	/**
	 * ���������ѯ�γ����
	 * @param ccname��Ҫ��ѯ�γ��������
	 * @return ��Ӧ�Ŀγ����
	 * @throws SQLException
	 */
	public CourseCategory selectCourseCategoryByName(String ccname) throws SQLException {
		return DAOFactory.getCourseCategoryDAO().selectCourseCategoryByName(ccname);
	}

	/**
	 * ��ȡ��Ϊccuid��ֵΪccname��map����
	 * @return ��Ϊccuid��ֵΪccname��map����
	 * @throws SQLException
	 */
	public Map<String, String> selectCategoryIdNameMap() throws SQLException {
		Map<String, String> mapIdName = new HashMap<String, String>();
		List<CourseCategory> categories = 
			DAOFactory.getCourseCategoryDAO()
			.selectCourseCategories();
		
		for (CourseCategory category : categories) {
			mapIdName.put(category.getCcuid(), category.getCcname());
		}
		
		return mapIdName;
	}

	/**
	 * 根据课程类别名的关键字进行分页查询
	 * @param key要搜索课程类别的名称的关键字
	 * @param page要显示的页码
	 * @param count最多要查询的记录数量
	 * @return
	 */
	public List<CourseCategory> selectCourseCategoryByKeyPage(String key, int page, int count) {
		return DAOFactory.getCourseCategoryDAO()
				.selectCourseCategoryByKeyPage(key, page, count);
	}
	
	/**
	 * 查询与关键字相匹配的记录的数量
	 * @param key课程类别名的关键字
	 * @return 对应的记录数量
	 * @throws SQLException
	 */
	public int selectCountByKey(String key) throws SQLException {
		return DAOFactory.getCourseCategoryDAO()
				.selectCountByKey(key);
	}
}
