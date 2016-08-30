package com.zking.web.action.impl.add;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.ServiceFactory;

/**
 * 添加课程类别
 * @author 胡博
 *
 */
public class AddCourseCategoryAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			String ccname = request.getParameter("ccname");
			
			
			
			CourseCategory category = new CourseCategory();
			//给课程类别一个全球唯一标识
			String ccuid = 
					UUID.randomUUID().toString().replace("-", "");
			category.setCcuid(ccuid);
			category.setCcname(ccname);
			ServiceFactory.getCourseCategoryService().saveCourseCategory(category);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("adminCC.do");
	}

}
