package com.zking.web.factory;

import com.zking.web.dao.IAdminDAO;
import com.zking.web.dao.ICourseCategoryDAO;
import com.zking.web.dao.ICourseDAO;
import com.zking.web.dao.ITeacherDAO;
import com.zking.web.dao.IUserCourseDAO;
import com.zking.web.dao.IUserDAO;
import com.zking.web.dao.IVideoDAO;
import com.zking.web.dao.impl.AdminDAO;
import com.zking.web.dao.impl.CourseCategoryDAO;
import com.zking.web.dao.impl.CourseDAO;
import com.zking.web.dao.impl.TeacherDAO;
import com.zking.web.dao.impl.UserCourseDAO;
import com.zking.web.dao.impl.UserDAO;
import com.zking.web.dao.impl.VideoDAO;

/**
 * dao工厂类
 * @author 胡博
 *
 */
public class DAOFactory {
	private DAOFactory(){}
	
	/**
	 * 获得AdminDAO实例
	 * @return AdminDAO实例
	 */
	public static IAdminDAO getAdminDAO() {
		return new AdminDAO();
	}
	
	/**
	 * 获得TeacherDAO实例
	 * @return TeacherDAO实例
	 */
	public static ITeacherDAO getTeacherDAO() {
		return new TeacherDAO();
	}
	
	/**
	 * 获得UserDAO实例
	 * @return UserDAO实例
	 */
	public static IUserDAO getUserDAO() {
		return new UserDAO();
	}
	
	/**
	 * 获得CourseDAO实例
	 * @return CourseDAO实例
	 */
	public static ICourseDAO getCourseDAO() {
		return new CourseDAO();
	}
	
	/**
	 * 获得CourseCategoryDAO实例
	 * @return CourseCategoryDAO实例
	 */
	public static ICourseCategoryDAO getCourseCategoryDAO() {
		return new CourseCategoryDAO();
	}
	
	/**
	 * 获得VideoDAO实例
	 * @return VideoDAO实例
	 */
	public static IVideoDAO getVideoDAO() {
		return new VideoDAO();
	}
	
	/**
	 * 获得UserCourseDAO实例
	 * @return UserCourseDAO实例
	 */
	public static IUserCourseDAO getUserCourseDAO() {
		return new UserCourseDAO();
	}
}
