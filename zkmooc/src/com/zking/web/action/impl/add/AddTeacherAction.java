package com.zking.web.action.impl.add;

import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 添加教师
 * @author 胡博
 * 
 */
public class AddTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			String tacount = request.getParameter("tacount");
			String tname = request.getParameter("tname");
			String tsex = request.getParameter("tsex");
			int tage = Integer.parseInt(request.getParameter("tage"));
			String tmail = request.getParameter("tmail");
			String tphone = request.getParameter("tphone");
			String tpass = request.getParameter("tpass");
			String tinfo = request.getParameter("tinfo");
			Teacher teacher = new Teacher();
			//给教师生成一个全球唯一标识
			String tuid = 
					UUID.randomUUID().toString().replace("-", "");
			teacher.setTuid(tuid);
			teacher.setTacount(tacount);
			teacher.setTname(tname);
			teacher.setTsex(tsex);
			teacher.setTage(tage);
			teacher.setTmail(tmail);
			teacher.setTphone(tphone);
			teacher.setTpass(tpass);
			teacher.setTinfo(tinfo);
			
			ServiceFactory.getTeacherService().saveTeacher(teacher);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
				out.flush();
			}
		}
		
		return new ActionForward("adminTeacher.do");
	}

}
