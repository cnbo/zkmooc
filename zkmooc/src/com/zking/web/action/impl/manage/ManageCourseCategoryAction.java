package com.zking.web.action.impl.manage;

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
 * 管理课程类别
 * @author 胡博
 *
 */
public class ManageCourseCategoryAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/htnl; charset=utf-8");*/
		PrintWriter out = null;
		String pageStr = request.getParameter("page");
		
		int page = Integer.parseInt(pageStr);
		int count = 9;			//每页最多能显示的记录数量
		
		try {
			out = response.getWriter();
			int categoryCount = 0;
			List<CourseCategory> categories = new ArrayList<CourseCategory>();
			categoryCount = 
				ServiceFactory.getCourseCategoryService().selectCategoryCount();
			
			//得到课程类别集合
			categories = 
				ServiceFactory.getCourseCategoryService().selectCourseCategoriesByPage(page, count);
			//总页数
			int pages = (int) Math.ceil((double)(categoryCount)/count);

			JSONObject obj = new JSONObject();
			obj.put("categories", categories);
			obj.put("pages", pages);
			obj.put("page", page);
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
