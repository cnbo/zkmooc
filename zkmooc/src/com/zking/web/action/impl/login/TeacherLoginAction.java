package com.zking.web.action.impl.login;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 处理教师登录
 * @author 胡博
 *
 */
public class TeacherLoginAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String tacount = request.getParameter("tacount");
		String tpass = request.getParameter("tpass");
		
		PrintWriter out = null;
		Boolean isLogin = true;
		try {
			Teacher teacher = ServiceFactory.getTeacherService().selectByTacount(tacount);
			
			if (teacher != null && teacher.getTpass().equals(tpass)) {
				request.getSession().setAttribute("teacher", teacher);
			} else {
				isLogin = false;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("isLogin", isLogin);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			out.print("error");
		}
		if (out != null) {
			out.flush();
			out.close();
		}
		return null;
	}

}
