package com.zking.web.action.impl.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 管理员修改教师信息
 * @author 胡博
 *
 */
public class AdminEditTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			String tuid = request.getParameter("tuid");
			String tname = request.getParameter("tname");
			int age = Integer.parseInt(request.getParameter("tage"));
			String tmail = request.getParameter("tmail");
			String tphone = request.getParameter("tphone");
			String tacount = request.getParameter("tacount");
			String tsex = request.getParameter("tsex");
			String tinfo = request.getParameter("tinfo");
			
			Teacher teacher = ServiceFactory.getTeacherService().selectByTuid(tuid);
			teacher.setTname(tname);
			teacher.setTage(age);
			teacher.setTmail(tmail);
			teacher.setTphone(tphone);
			teacher.setTacount(tacount);
			teacher.setTsex(tsex);
			teacher.setTinfo(tinfo);
			
			ServiceFactory.getTeacherService().updateTeacher(teacher);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("adminTeacher.do");
	}

}
