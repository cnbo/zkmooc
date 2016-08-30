package com.zking.web.action.impl.update;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.CourseCategory;
import com.zking.web.factory.ServiceFactory;

/**
 * 管理员修改课程类别信息
 * @author 胡博
 *
 */
public class EditCourseCategoryAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String ccuid = request.getParameter("ccuid");
		String ccname = request.getParameter("ccname");

		boolean isUpdate = false;		
		
		try {
			CourseCategory category = 
				ServiceFactory.getCourseCategoryService()
				.selectCourseCategoryByCcuid(ccuid);
			category.setCcname(ccname);
			
			ServiceFactory.getCourseCategoryService().updateCourseCategory(category);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("adminCC.do");
	}

}
