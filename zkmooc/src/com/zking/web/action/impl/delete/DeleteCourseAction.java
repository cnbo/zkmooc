package com.zking.web.action.impl.delete;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

/**
 * 删除课程
 * @author 胡博
 *
 */
public class DeleteCourseAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String cuid = request.getParameter("cuid");
		
		try {
			//删除课程及其相关视频
			ServiceFactory.getVideoService().deleteVideoByCuid(cuid);
			Course course =
					ServiceFactory.getCourseService()
					.selectByCuid(cuid);
			
			String cimage = course.getCimage();
			if (cimage != null) {
				cimage = request.getServletContext().getRealPath("/")+
						cimage.replaceFirst("/zkmooc/", "");
				File file = new File(cimage);
				file.delete();
			}
			ServiceFactory.getCourseService().deleteCourse(cuid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("manageCourseList.do", false);
	}

}
