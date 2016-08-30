package com.zking.web.action.impl.search;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

public class UserSearchCourseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String keyword = request.getParameter("keyword");
			String isDirect = request.getParameter("isDirect");
			if ("false".equals(isDirect)) {
				keyword  = request.getParameter("keyword");
			}
			
			List<Course> courses =
					ServiceFactory.getCourseService().selectCoursesByKeyword(keyword);
			if ("true".equals(isDirect)) {
				out = response.getWriter();
				JSONObject obj = new JSONObject();
				obj.put("courses", courses);
				out.print(obj.toString());
				
				if (out != null) {
					out.flush();
					out.close();
				}
			} else {
				request.setAttribute("courses", courses);
				return new ActionForward("WEB-INF/course/search.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			if (out != null) {
				out.print("error");
			}
		}
		return null;
	}

}
