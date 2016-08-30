package com.zking.web.action.impl.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.course.Video;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 获得要播放的视频并跳转到播放界面
 * @author 胡博
 *
 */
public class CoursePlayAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String cname = request.getParameter("cname");
		
			String vname = request.getParameter("vname");
			
			if(cname == null){
				request.setAttribute("video",new Video("-1","课程视频未找到 >_<",null,"-1") );
				return new ActionForward("WEB-INF/course/play.jsp", false);
			}
			Course course = 
					ServiceFactory.getCourseService().selectCourseByCname(cname);
			Teacher teacher = 
					ServiceFactory.getTeacherService().selectByTuid(course.getTuid());
			List<String> vnames = 
						ServiceFactory.getVideoService().selectVnames(course.getCuid());
			if (vname == null && vnames.size() > 0) {
				vname = vnames.get(0);
			}
			Video video = 
					ServiceFactory.getVideoService().selectVideoByVname(vname);
			
			
			request.setAttribute("course", course);
			request.setAttribute("teacher", teacher);
			request.setAttribute("vnames", vnames);
			if (video != null) {
				request.setAttribute("video", video);
			} else {
				request.setAttribute("video",new Video("-1","课程视频未找到 >_<",null,"-1") );
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("WEB-INF/course/play.jsp", false);
	}

}
