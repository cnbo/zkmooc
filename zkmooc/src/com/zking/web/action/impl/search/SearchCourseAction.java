package com.zking.web.action.impl.search;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.entity.show.ShowCourse;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

public class SearchCourseAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String cname = request.getParameter("searchCName");
			String pageStr = request.getParameter("page");
			int page = 1;
			if (pageStr != null && pageStr.length() > 0) {
				page = Integer.parseInt(pageStr);
			}
			
			Teacher teacher =
					(Teacher) request.getSession().getAttribute("teacher");
			
			int count = 9;
			
			List<Course> courses =
					ServiceFactory.getCourseService()
					.selectCourseByKeyTuidPage(cname, teacher.getTuid(), page, count);
			int courseCount = 
					ServiceFactory.getCourseService()
					.selectCountByKeyTuid(cname, teacher.getTuid());
			int pages = (int) Math.ceil((double)courseCount/count);
			
			List<ShowCourse> showCourses = new ArrayList<ShowCourse>();

			for (Course course : courses){
				CourseCategory category = 
					ServiceFactory.getCourseCategoryService().selectCourseCategoryByCcuid(course.getCcuid());
				ShowCourse showCourse = getShowCourse(course, category);
				showCourses.add(showCourse);
			}
			
			JSONObject obj = new JSONObject();
			obj.put("courses", showCourses);
			obj.put("page", page);
			obj.put("pages", pages);
			out = response.getWriter();
			out.print(obj.toString());
		} catch (Exception e) {
			out.print("error");
		}
		if (out != null) {
			out.flush();
			out.close();
		}
		return null;
	}
	
private ShowCourse getShowCourse(Course course, CourseCategory category) {
		
		ShowCourse showCourse = new ShowCourse();
		
		showCourse.setCuid(course.getCuid());
		showCourse.setCname(course.getCname());
		showCourse.setCinfo(course.getCinfo());
		showCourse.setCcname(category.getCcname());
		showCourse.setCcuid(course.getCcuid());

		return showCourse;
	}

}
