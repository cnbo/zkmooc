package com.zking.web.action.impl.manage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 管理教师
 * @author 胡博
 *
 */
public class ManageTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		String pageStr = request.getParameter("page");
		int page = 1;
		if (pageStr != null && pageStr.length() > 0) {
			page = Integer.parseInt(pageStr);
		}
		
		try {
			int teacherCount = 0;
			List<Teacher> teachers = new ArrayList<Teacher>();
			teacherCount = 
					ServiceFactory.getTeacherService().selectTeacherCount();
			
			teachers = 
					ServiceFactory.getTeacherService().selectTeachersByPage(page, 9);
			//若teacherCount=0,则pages=0
			int pages = (int) Math.ceil((double)teacherCount/9);
			JSONObject obj = new JSONObject();
			obj.put("teachers", teachers);
			obj.put("pages", pages);
			obj.put("page", page);
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

}
