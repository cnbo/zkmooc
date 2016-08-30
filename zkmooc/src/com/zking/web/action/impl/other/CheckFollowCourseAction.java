package com.zking.web.action.impl.other;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.user.UserCourse;
import com.zking.web.factory.ServiceFactory;

public class CheckFollowCourseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = null;
		try {
			String cuid = request.getParameter("cuid");
			String uuid = request.getParameter("uuid");
			UserCourse ucourse = 
					ServiceFactory.getUserCourseService()
					.selectByCuidUuid(cuid, uuid);
			
			Boolean isExist = true;
			if (ucourse == null) {
				isExist = false;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("isExist", isExist);
			out = response.getWriter();
			out.print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

}
