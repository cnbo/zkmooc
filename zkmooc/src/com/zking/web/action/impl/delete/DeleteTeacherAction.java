package com.zking.web.action.impl.delete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

/**
 * 删除教师及其他名下的课程以及相关视频
 * @author 胡博
 *
 */
public class DeleteTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String tuid = request.getParameter("tuid");
		
		boolean isDelete = false;
		
		try {
			//删除教师需删除旗下所有的课程及相关视频
			//获得id号为tuid的教师的所有课程
			List<Course> courses =
				ServiceFactory.getCourseService().selectByTuid(tuid);
			for (Course course : courses) {
				String cuid = course.getCuid();
				//删除cuid标识的课程下的所有视频
				ServiceFactory.getVideoService().deleteVideoByCuid(cuid);
			}
			//删除tuid标识的课程类别下的所有课程
			ServiceFactory.getCourseService().deleteCourseByTuid(tuid);
			ServiceFactory.getTeacherService().deleteTeacher(tuid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("adminTeacher.do");
	}

}
