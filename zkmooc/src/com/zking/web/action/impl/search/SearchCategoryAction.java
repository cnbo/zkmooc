package com.zking.web.action.impl.search;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.ServiceFactory;

/**
 * 根据关键字搜索课程类别
 * @author 胡博
 *
 */
public class SearchCategoryAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String ccname = request.getParameter("ccname");
			String pageStr = request.getParameter("page");
			int page = 1;
			if (pageStr != null && pageStr.length() > 0) {
				page = Integer.parseInt(pageStr);
			}
			
			int count = 9;
			List<CourseCategory> categories = 
				ServiceFactory.getCourseCategoryService().selectCourseCategoryByKeyPage(ccname, page, count);
			int categoryCount = 
				ServiceFactory.getCourseCategoryService().selectCountByKey(ccname);
			
			int pages = (int) Math.ceil((double)(categoryCount)/count);
			
			JSONObject obj = new JSONObject();
			obj.put("categories", categories);
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
