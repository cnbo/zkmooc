package com.zking.web.action.impl.manage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

/**
 * 教师管理他的课程
 * @author 胡博
 *
 */
public class ManageCourseListAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		PrintWriter out = null;
		int page = 1;
		String pageStr = request.getParameter("page");
		if(pageStr != null){
			page = Integer.parseInt(pageStr);
		}
		int count = 9;		//指定每页显示记录的数量
		
		try {
			out = response.getWriter();
			Teacher teacher = (Teacher)request.getSession().getAttribute("teacher");
			String tacount = teacher.getTacount();
			//要显示页的课程集合
			List<Course> courses =
				ServiceFactory.getCourseService().selectCourseByTuidPage(teacher.getTuid(), page, count);
			//课程的总数
			int courseCount = 
				ServiceFactory.getCourseService().selectCourseCountByTuid(teacher.getTuid());
			//总页数
			int pages = (int) Math.ceil((double)courseCount/count);
			
			//课程类别集合
			List<CourseCategory> categories = 
				ServiceFactory.getCourseCategoryService().selectCourseCategories();
			Map<String, String> categoryIdNameMap = 
				ServiceFactory.getCourseCategoryService().selectCategoryIdNameMap();
			List<ShowCourse> showCourses = getShowCourseList(courses, categoryIdNameMap);
			
			
			JSONObject obj = new JSONObject();
			obj.put("courses", showCourses);
			obj.put("pages", pages);
			obj.put("categories", categories);
			obj.put("page", page);
			out.print(obj.toString());
		} catch(Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		if (out != null) {
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	private List<ShowCourse> getShowCourseList(List<Course> courses, Map<String, String> categoryIdNameMap) {
		List<ShowCourse> showCourses = new ArrayList<ShowCourse>();
		
		for (Course course : courses) {
			ShowCourse showCourse = new ShowCourse();
			
			showCourse.setCuid(course.getCuid());
			showCourse.setCname(course.getCname());
			showCourse.setCinfo(course.getCinfo());
			showCourse.setCcname(categoryIdNameMap.get(course.getCcuid()));
			showCourse.setCcuid(course.getCcuid());
			
			showCourses.add(showCourse);
		}
		
		return showCourses;
	}


}
