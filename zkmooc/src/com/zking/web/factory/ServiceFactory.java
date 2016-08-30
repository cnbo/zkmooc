package com.zking.web.factory;

import com.zking.web.service.IAdminService;
import com.zking.web.service.ICourseCategoryService;
import com.zking.web.service.ICourseService;
import com.zking.web.service.ITeacherService;
import com.zking.web.service.IUserCourseService;
import com.zking.web.service.IUserService;
import com.zking.web.service.IVideoService;
import com.zking.web.service.impl.AdminServiceImpl;
import com.zking.web.service.impl.CourseCategoryServiceImpl;
import com.zking.web.service.impl.CourseServiceImpl;
import com.zking.web.service.impl.TeacherServiceImpl;
import com.zking.web.service.impl.UserCourseServiceImpl;
import com.zking.web.service.impl.UserServiceImpl;
import com.zking.web.service.impl.VideoServiceImpl;

/**
 * service静态工厂类
 * @author Administrator
 *
 */
public class ServiceFactory {
	private ServiceFactory(){}
	
	/**
	 * 获取AdminServiceImpl实例
	 * @return AdminServiceImpl实例
	 */
	public static IAdminService getAdminService() {
		return new AdminServiceImpl();
	}
	
	/**
	 * 获取TeacherServiceImpl实例
	 * @return TeacherServiceImpl实例
	 */
	public static ITeacherService getTeacherService() {
		return new TeacherServiceImpl();
	}
	
	/**
	 * 获取UserrServiceImpl实例
	 * @return UserServiceImpl实例
	 */
	public static IUserService getUserService() {
		return new UserServiceImpl();
	}
	
	/**
	 * 获取CourseCategoryServiceImpl实例
	 * @return CourseCategoryServiceImpl实例
	 */
	public static ICourseCategoryService getCourseCategoryService() {
		return new CourseCategoryServiceImpl();
	}
	
	/**
	 * 获取CourseServiceImpl实例
	 * @return CourseServiceImpl实例
	 */
	public static ICourseService getCourseService() {
		return new CourseServiceImpl();
	}
	
	/**
	 * 获取VideoServiceImpl实例
	 * @return VideoServiceImpl实例
	 */
	public static IVideoService getVideoService() {
		return new VideoServiceImpl();
	}
	
	/**
	 * 获取VideoServiceImpl实例
	 * @return VideoServiceImpl实例
	 */
	public static IUserCourseService getUserCourseService() {
		return new UserCourseServiceImpl();
	}
}
