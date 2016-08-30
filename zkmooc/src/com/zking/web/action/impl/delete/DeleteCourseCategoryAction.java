package com.zking.web.action.impl.delete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

/**
 * 处理课程类别的删除
 * @author 胡博
 *
 */
public class DeleteCourseCategoryAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String ccuid = request.getParameter("ccuid");
				
		try {
			//删除课程类别需将该课程类别下的课程及视频全删
			
			//获得id号为ccuid课程类别下的所有课程
			List<Course> courses = 
				ServiceFactory.getCourseService().selectCoursesByCcuid(ccuid);
			for (Course course : courses) {
				String cuid = course.getCuid();
				//删除id号为cuid的课程下的所有视频
				ServiceFactory.getVideoService().deleteVideoByCuid(cuid);
			}
			//删除id号为ccuid课程类别下的所有课程
			ServiceFactory.getCourseService().deleteCourseByCcuid(ccuid);
			//删除id号为ccuid课程类别
			ServiceFactory.getCourseCategoryService().deleteCourseCategory(ccuid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("adminCC.do");
	}

}
