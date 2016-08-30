package com.zking.web.action.impl.other;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.ServiceFactory;

/**
 * 给前端发送要显示的课程
 * @author 胡博
 *
 */
public class CourseListAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		try {
			//获得课程类别名
			String ccname = request.getParameter("ccname");
			//获得要显示的页码
			String pageStr = request.getParameter("page");
			int page = 1;
			if (pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
			int count = 12;
			int courseCount = 0;
			List<Course> courses = new ArrayList<Course>();
			List<CourseCategory> categories = new ArrayList<CourseCategory>();
			if (ccname == null || "全部".equals(ccname)) {
				courseCount = 
					ServiceFactory.getCourseService().selectCourseCount();
				courses = 
					ServiceFactory.getCourseService().selectCoursesByPage(page, count);	
				ccname= "全部";
			} else {
				courseCount = 
					ServiceFactory.getCourseService().selectCourseCountByCcname(ccname);
				courses = 
					ServiceFactory.getCourseService().selectCoursesByCcnamePage(ccname, page, count);
			}
			categories = 
				ServiceFactory.getCourseCategoryService().selectCourseCategories();
			categories.add(0, new CourseCategory("-1", "全部"));
			int pages = (int) Math.ceil((double)(courseCount)/count);
			request.setAttribute("pages", pages);
			request.setAttribute("courses", courses);
			request.setAttribute("categories", categories);
			request.setAttribute("page", page);
			request.setAttribute("ccname", ccname);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ActionForward("WEB-INF/course/list.jsp", false);
	}

}
