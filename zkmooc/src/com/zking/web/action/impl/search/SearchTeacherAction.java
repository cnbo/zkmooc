package com.zking.web.action.impl.search;

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
 * 对教师的模糊查询
 * @author 胡博
 *
 */
public class SearchTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			String searchType = request.getParameter("searchType");
			String searchKey = request.getParameter("searchKey");
			String pageStr = request.getParameter("page");
			int page = 1;
			if (pageStr != null && pageStr.length() > 0) {
				page = Integer.parseInt(pageStr);
			}
			int count = 9;
			int teacherCount = 0;
			List<Teacher> teachers = null;
			if ("searchName".equals(searchType)) {
				teachers =
					ServiceFactory.getTeacherService().selectTeacherByKeyPage(searchKey, page, count);
				teacherCount =
					ServiceFactory.getTeacherService().selectCountByNameKey(searchKey);
			} else {
				teachers =
					ServiceFactory.getTeacherService().selectTeacherByAcountKeyPage(searchKey, page, count);
				teacherCount =
					ServiceFactory.getTeacherService().selectCountByAcountKey(searchKey);
			}
			
			int pages = (int) Math.ceil((double)teacherCount/count);
			
			JSONObject obj = new JSONObject();
			obj.put("teachers", teachers);
			obj.put("pages", pages);
			obj.put("page", page);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		out.flush();
		out.close();
		return null;
	}

}
