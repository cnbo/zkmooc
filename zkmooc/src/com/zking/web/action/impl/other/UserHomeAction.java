package com.zking.web.action.impl.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.user.User;
import com.zking.web.entity.user.UserCourse;
import com.zking.web.factory.ServiceFactory;

/**
 * 
 * @author 胡博
 *
 */
public class UserHomeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			User user =
					(User) request.getSession().getAttribute("user");
			List<UserCourse> ucourses =
					ServiceFactory.getUserCourseService()
					.selectUserCourses(user.getUuid());
			List<Course> courses = new ArrayList<Course>();
			
			for (UserCourse ucourse : ucourses) {
				courses.add(ServiceFactory.getCourseService()
						.selectByCuid(ucourse.getCuid()));
			}
			
			request.setAttribute("courses", courses);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ActionForward("WEB-INF/user/home.jsp");
	}

}
